package UserFrame;

import ColorFont.Constant;
import table.payment_user;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CheckOut extends JPanel{
    CheckOut(boolean connected, String username) {
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
        JTextField DebtField = new JTextField();
        DebtField.setFont(Constant.LABEL_FONT);

        DebtField.setColumns(15);
        DebtField.setEditable(false);
        DebtField.setText(Integer.toString(user.getDebt()));

        JLabel BalanceLabel = new JLabel("Balance:");
        BalanceLabel.setFont(Constant.LABEL_FONT);
        JTextField BalanceField = new JTextField();
        BalanceField.setFont(Constant.LABEL_FONT);
        BalanceField.setColumns(15);
        BalanceField.setEditable(false);
        BalanceField.setText(Integer.toString(user.getBalance()));

        JLabel CheckoutLabel = new JLabel("Checkout:");
        CheckoutLabel.setFont(Constant.LABEL_FONT);
        JTextField CheckoutField = new JTextField();
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

        JLabel check_connection=new JLabel();
        if(connected) check_connection.setText("Connected");
        else check_connection.setText("Disconnected");

        ButtonPanel.add(check_connection);
        JButton CheckoutButton = new JButton("Checkout");
        CheckoutButton.setForeground(Constant.my_white);
        CheckoutButton.setBackground(new Color(77,82,77));
        CheckoutButton.setFont(Constant.LABEL_FONT);
        ButtonPanel.add(CheckoutButton);
        if(connected)CheckoutButton.setEnabled(true);
        else CheckoutButton.setEnabled(false);

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
                            getDB.PaymentUser.FunctionPaymentUser.UpdateDebtBalance(user);
                            getDB.PaymentHistory.FunctionPaymentHistory.UpdatePaymentHistory(user.getUsername(), balancepay, user.getBalance());
                            DebtField.setText(String.valueOf(user.getDebt()));
                            BalanceField.setText(String.valueOf(user.getBalance()));
                            JOptionPane.showMessageDialog(CheckOut.this, "Success","success", JOptionPane.ERROR_MESSAGE);
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