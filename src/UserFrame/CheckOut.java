package UserFrame;

import ColorFont.Constant;
import table.payment_user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CheckOut extends JPanel{
    boolean connected=false;
    UserThread socket;

    JButton ServerButton;
    JButton CheckoutButton;
    JLabel check_connection;
    JTextField CheckoutField;
    JTextField DebtField;
    JTextField BalanceField;

    public JTextField getDebtField(){
        return DebtField;
    }
    public JTextField getBalanceField(){
        return BalanceField;
    }
    public JButton getServerButton(){
        return ServerButton;
    }

    public JButton getCheckoutButton(){
        return CheckoutButton;
    }

    public JLabel getCheck_connection(){
        return check_connection;
    }

    CheckOut( String username) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        payment_user user=getDB.PaymentUser.FunctionPaymentUser.GetPaymentAccount(username);

        JPanel headerPane=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbHeader = new JLabel("CHECKOUT");
        lbHeader.setFont(Constant.HEADER_FONT);
        lbHeader.setForeground(Constant.my_white);
        headerPane.add(lbHeader);
        headerPane.setBackground(Constant.my_gray);
        add(headerPane);

        JPanel ContentPanel = new JPanel();
        ContentPanel.setLayout(new BoxLayout(ContentPanel,BoxLayout.Y_AXIS));
        ContentPanel.setBackground(Constant.my_gray);
        add(ContentPanel);

        JPanel midPanel = new JPanel();
        midPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10,10,10);


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

        check_connection=new JLabel();


        ServerButton = new JButton("Connect to server");
        ServerButton.setForeground(Constant.my_white);
        ServerButton.setBackground(new Color(77,82,77));
        ServerButton.setFont(Constant.LABEL_FONT);
        ButtonPanel.add(ServerButton);
        ServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                socket=UserThread.start_socket(username,CheckOut.this);
            }
        });


        ButtonPanel.add(check_connection);
        CheckoutButton = new JButton("Checkout");
        CheckoutButton.setForeground(Constant.my_white);
        CheckoutButton.setBackground(new Color(77,82,77));
        CheckoutButton.setFont(Constant.LABEL_FONT);
        ButtonPanel.add(CheckoutButton);


        CheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CheckoutField.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(CheckOut.this, "Please input balance to pay", "error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    try
                    {
                        int balancepay = Integer.parseInt(CheckoutField.getText());
                        if (balancepay <= 0)
                        {
                            JOptionPane.showMessageDialog(CheckOut.this, "Please input valid balance", "error",JOptionPane.ERROR_MESSAGE);
                        }
                        else if (balancepay > user.getBalance())
                        {
                            JOptionPane.showMessageDialog(CheckOut.this, "Balance's not enough", "error",JOptionPane.ERROR_MESSAGE);
                        }
                        else if (balancepay > user.getDebt())
                        {
                            JOptionPane.showMessageDialog(CheckOut.this, "Out of debt", "error",JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            user.setDebt(user.getDebt() - balancepay);
                            user.setBalance(user.getBalance() - balancepay);
                            socket.getPrintWriter().println(balancepay);

                        }

                    }
                    catch (NumberFormatException  err)
                    {
                        JOptionPane.showMessageDialog(CheckOut.this, "Please input valid balance", "error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

}