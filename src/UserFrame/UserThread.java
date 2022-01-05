package UserFrame;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class UserThread implements Runnable{
    private static boolean exit;
    Thread network;
    PrintWriter printWriter = null;
    InputStream inputStream = null;
    BufferedReader bufferedReader = null;
    OutputStream outputStream = null;
    CheckOut Checkout;
    String username;

    public Thread getThread(){
        return network;
    }
    public UserThread(String username,CheckOut Checkout) {
        this.username=username;
        this.Checkout=Checkout;
        network= new Thread(this);
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

    public static UserThread start_socket(String username,CheckOut checkOut) {
        UserThread myThrd = new UserThread(username,checkOut);
        myThrd.network.start(); // start the thread
        exit = false;
        return myThrd;
    }
    @Override
    public void run() {
        Socket client=null;
        final int portNumber = 33000;

        try {
            client = new Socket(InetAddress.getLocalHost(), portNumber);
            System.out.println("Client socket is created" + client);
            inputStream = client.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            outputStream = client.getOutputStream();
            printWriter = new PrintWriter(outputStream, true);
            printWriter.println(username);

            JOptionPane.showMessageDialog(this.Checkout, "Connected to Server");

            this.Checkout.getServerButton().setEnabled(true);
            this.Checkout.getCheckoutButton().setEnabled(true);
            this.Checkout.getCheck_connection().setText("Connected");

            while(true){
                String messageFromServer = bufferedReader.readLine();
                if(messageFromServer.equalsIgnoreCase("{Success}")){
                    JOptionPane.showMessageDialog(this.Checkout, "Checkout successfully");
                }
            }
        } catch (IOException e) {
            this.Checkout.getServerButton().setEnabled(true);
            this.Checkout.getCheckoutButton().setEnabled(false);
            this.Checkout.getCheck_connection().setText("Disconnected");
            exit = true;
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
    }
}

