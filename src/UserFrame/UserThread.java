package UserFrame;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class UserThread implements Runnable{
    private static boolean exit;
    PrintWriter printWriter ;
    InputStream inputStream ;
    BufferedReader bufferedReader ;
    OutputStream outputStream ;
    String username;
    MutableBoolean check_connected;
    Socket client;
    public Socket socket(){
        return client;
    }
    public UserThread(String username,MutableBoolean check_connected,Socket client) {
        this.client=client;
        this.username=username;
        this.check_connected=check_connected;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public PrintWriter getPrintWriter(){
        return printWriter;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }


    @Override
    public void run() {
        client=null;
        final int portNumber = 33000;

        try {
            client = new Socket(InetAddress.getLocalHost(), portNumber);
            System.out.println(client);
            System.out.println("Client socket is created" + client);
            inputStream = client.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            outputStream = client.getOutputStream();
            printWriter = new PrintWriter(outputStream, true);
            printWriter.println(username);
            check_connected.setValue(true);

            while(true){
                String messageFromServer = bufferedReader.readLine();
            }
        } catch (IOException e) {
            check_connected.setValue(false);
            exit = true;
            e.printStackTrace();
        }
        finally {
            check_connected.setValue(false);
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
    }
}

