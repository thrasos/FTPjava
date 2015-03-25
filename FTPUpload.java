package getlinks;

/**
 *
 * @author thrasos
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

public class FTPUpload {

    public static void main(String args[]) throws IOException {

        uploadFile();
    }

    public static boolean uploadFile() throws FileNotFoundException, IOException {
/* the name of the file you want to upload from the working direcory*/
        String filename = "index.html";
        System.out.println("FTP start for "+filename);
        FTPClient client = new FTPClient();

        /* enter your FTP credentials[host, username,password]
           obviously its not ftp.example.com etc */
        client.connect("FTP.EXAMPLE.COM");
        client.login("USERNAME", "PASSWORD");
        /*The Directory you want the file to be uploaded in my case it's the top folder.*/
        client.changeWorkingDirectory("/");
        /*Prints the current DIrectory you are in.*/
        System.out.println("Current directory is " + client.printWorkingDirectory());
        /*yes you need this*/
        client.enterLocalPassiveMode();
        /*get input stream*/
        InputStream input;
        input = new FileInputStream(filename);
        /*store the file in the remote server*/
        client.storeFile(filename, input);
        /*close the stream*/
        input.close();
        /*logout*/
        client.logout();
        /*disconect from the host*/
        client.disconnect();

        System.out.println("Category Index Uploaded");
        return true;
    }
}
