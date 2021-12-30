package UserFrame;

import ColorFont.Constant;
import table.payment_history;
import table.payment_user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;

public class CheckOut extends JPanel{
    private JLabel lbHeader;
    private JPanel headerPanel;
    CheckOut(boolean connected, String username) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        payment_user user=getDB.PaymentUser.FunctionPaymentUser.GetPaymentAccount(username);
        JPanel headerPane=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbHeader = new JLabel("Checkout");
        lbHeader.setFont(Constant.HEADER_FONT);
        lbHeader.setForeground(Constant.my_white);
        headerPane.add(lbHeader);
        headerPane.setBackground(Constant.my_gray);
        add(headerPane);


        JPanel ContentPanel = new JPanel();
        ContentPanel.setLayout(new BoxLayout(ContentPanel,BoxLayout.Y_AXIS));
        ContentPanel.setBackground(Constant.my_gray);
        add(ContentPanel);

        JPanel DebtPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ContentPanel.add(DebtPanel);
        JLabel DebtLabel = new JLabel("Debt:      ");
        DebtLabel.setFont(Constant.LABEL_FONT);
        JTextField DebtField = new JTextField();
        DebtField.setColumns(30);
        DebtField.setEditable(false);
        DebtField.setText(Integer.toString(user.getDebt()));
        DebtPanel.add(DebtLabel);
        DebtPanel.add(DebtField);

        JPanel BalancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ContentPanel.add(BalancePanel);
        JLabel BalanceLabel = new JLabel("Balance:      ");
        BalanceLabel.setFont(Constant.LABEL_FONT);
        JTextField BalanceField = new JTextField();
        BalanceField.setColumns(30);
        BalanceField.setEditable(false);
        BalanceField.setText(Integer.toString(user.getBalance()));
        BalancePanel.add(BalanceLabel);
        BalancePanel.add(BalanceField);

        JPanel CheckoutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ContentPanel.add(CheckoutPanel);
        JLabel CheckoutLabel = new JLabel("Checkout:      ");
        CheckoutLabel.setFont(Constant.LABEL_FONT);
        JTextField CheckoutField = new JTextField();
        CheckoutField.setColumns(30);
        CheckoutPanel.add(CheckoutLabel);
        CheckoutPanel.add(CheckoutField);

        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ContentPanel.add(ButtonPanel);

        JLabel check_connection=new JLabel();
        if(connected) check_connection.setText("Connected");
        else check_connection.setText("Disconnected");

        ButtonPanel.add(check_connection);
        JButton CheckoutButton = new JButton("Checkout");
        CheckoutButton.setForeground(Constant.my_white);
        CheckoutButton.setBackground(new Color(77,82,77));
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