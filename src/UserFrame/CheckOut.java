package UserFrame;

import ColorFont.Constant;
import table.payment_user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;


public class CheckOut extends JPanel{
    boolean connected=false;
    UserThread socket;
    MutableBoolean check_connected;
    Thread t;
    JButton ServerButton;
    JButton CheckoutButton;
    JLabel check_connection;
    JTextField CheckoutField;
    JTextField DebtField;
    JTextField BalanceField;
    String username;
    Socket client;

    public JTextField getDebtField(){
        return DebtField;
    }
    public JTextField getBalanceField(){
        return BalanceField;
    }
    public JTextField getCheckoutField(){
        return CheckoutField;
    }

    public JButton getCheckoutButton(){
        return CheckoutButton;
    }
    public JButton getServerButton(){
        return ServerButton;
    }

    public JLabel getCheck_connection(){
        return check_connection;
    }

    CheckOut(String username, UserThread socket, CheckOutController Controller, MutableBoolean check_connected, Socket client,Thread t) {
        this.check_connected=check_connected;
        this.username=username;
        this.socket = socket;
        this.client=client;
        this.t=t;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        payment_user user = getDB.PaymentUser.FunctionPaymentUser.GetPaymentAccount(username);

        JPanel headerPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbHeader = new JLabel("CHECKOUT");
        lbHeader.setFont(Constant.HEADER_FONT);
        lbHeader.setForeground(Constant.my_white);
        headerPane.add(lbHeader);
        headerPane.setBackground(Constant.my_gray);
        add(headerPane);

        JPanel ContentPanel = new JPanel();
        ContentPanel.setLayout(new BoxLayout(ContentPanel, BoxLayout.Y_AXIS));
        ContentPanel.setBackground(Constant.my_gray);
        add(ContentPanel);

        JPanel midPanel = new JPanel();
        midPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);


        JLabel DebtLabel = new JLabel("Debt:");
        DebtLabel.setFont(Constant.LABEL_FONT);
        DebtField = new JTextField();
        DebtField.setFont(Constant.LABEL_FONT);

        DebtField.setColumns(15);
        DebtField.setEditable(false);
        DebtField.setText(Integer.toString(user.getDebt()));

        JLabel BalanceLabel = new JLabel("Balance:");
        BalanceLabel.setFont(Constant.LABEL_FONT);
        BalanceField = new JTextField();
        BalanceField.setFont(Constant.LABEL_FONT);
        BalanceField.setColumns(15);
        BalanceField.setEditable(false);
        BalanceField.setText(Integer.toString(user.getBalance()));

        JLabel CheckoutLabel = new JLabel("Checkout:");
        CheckoutLabel.setFont(Constant.LABEL_FONT);
        CheckoutField = new JTextField();
        CheckoutField.setFont(Constant.LABEL_FONT);
        CheckoutField.setColumns(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        midPanel.add(DebtLabel, gbc);

        gbc.gridy++;
        midPanel.add(BalanceLabel, gbc);

        gbc.gridy++;
        midPanel.add(CheckoutLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        midPanel.add(DebtField, gbc);

        gbc.gridy++;
        midPanel.add(BalanceField, gbc);

        gbc.gridy++;
        midPanel.add(CheckoutField, gbc);

        ContentPanel.add(midPanel);
        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ContentPanel.add(ButtonPanel);

        check_connection = new JLabel();

        ServerButton = new JButton("Connect to server");
        ServerButton.setForeground(Constant.my_white);
        ServerButton.setBackground(new Color(77, 82, 77));
        ServerButton.setFont(Constant.LABEL_FONT);
        ButtonPanel.add(ServerButton);



        ButtonPanel.add(check_connection);
        CheckoutButton = new JButton("Checkout");
        CheckoutButton.setForeground(Constant.my_white);
        CheckoutButton.setBackground(new Color(77, 82, 77));
        CheckoutButton.setFont(Constant.LABEL_FONT);
        ButtonPanel.add(CheckoutButton);

        ServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(!t.isAlive())
                    {
                        Thread new_thread= new Thread(socket);
                        new_thread.start();
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        if(new_thread.isAlive()){
                            ServerButton.setEnabled(false);
                            CheckoutButton.setEnabled(true);
                            check_connection.setText("Connected");
                            new CheckOutController(CheckOut.this, socket.getInputStream(), socket.getBufferedReader(), socket.getOutputStream(),
                                    socket.getPrintWriter(), username,new_thread);
                        }
                        else{
                            JOptionPane.showMessageDialog(CheckOut.this, "Can't connect to server", "error",JOptionPane.ERROR_MESSAGE);

                        }
                    }
            }
        });


        if(check_connected.getValue())
        {
            ServerButton.setEnabled(false);
            CheckoutButton.setEnabled(true);
            check_connection.setText("Connected");
        }
        else{
            ServerButton.setEnabled(true);
            CheckoutButton.setEnabled(false);
            check_connection.setText("Disconnected");
        }
        if(socket!=null&&socket.getBufferedReader()!=null) new CheckOutController(this, socket.getInputStream(), socket.getBufferedReader(), socket.getOutputStream(),
                socket.getPrintWriter(), username,t);
    }


}