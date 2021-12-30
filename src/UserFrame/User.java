package UserFrame;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class User {
    String username;
    Socket client = null;

    public User(String username, boolean connected){
        this.username=username;
        connected=true;
        final int portNumber = 33000;
        this.client=client;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        try {
            client = new Socket(InetAddress.getLocalHost(), portNumber);
            System.out.println("Client socket is created" + client);

            inputStream = client.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            outputStream = client.getOutputStream();
            printWriter = new PrintWriter(outputStream, true);


        } catch (IOException e) {
            connected=false;
            e.printStackTrace();
        }
        finally {
            try {
                if (client != null) {
                    client.close();
                }
                if (inputStream != null)
                    inputStream.close();
                if (bufferedReader != null)
                    bufferedReader.close();
                if (outputStream != null)
                    outputStream.close();
                if (printWriter != null)
                    printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        UserFrame view=new UserFrame(username,connected);
        displayUI(view);
    }
    synchronized private static void displayUI(UserFrame view) {
        SwingUtilities.invokeLater(() -> {
                view.display();

        });
    }
}

