package UserFrame;

import table.payment_user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CheckOutController implements ActionListener {

    CheckOut checkOut;

    InputStream inputStream;
    BufferedReader bufferedReader;

    OutputStream outputStream;
    PrintWriter printWriter;

    String username;
    CheckOutController(
            CheckOut checkOut,
            InputStream inputStream,
            BufferedReader bufferedReader,
            OutputStream outputStream,
            PrintWriter printWriter,
            String username
    )
    {
        this.checkOut = checkOut;
        this.checkOut.getCheck_connection();
        this.checkOut.getDebtField();
        this.checkOut.getBalanceField();
        this.checkOut.getCheckoutButton().addActionListener(this);

        this.inputStream = inputStream;
        this.bufferedReader = bufferedReader;

        this.outputStream = outputStream;
        this.printWriter = printWriter;

        this.username=username;

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == checkOut.getCheckoutButton()) {
            if (checkOut.getCheckoutField().getText().isEmpty())
            {
                JOptionPane.showMessageDialog(checkOut, "Please input balance to pay", "error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                try
                {
                    payment_user user=getDB.PaymentUser.FunctionPaymentUser.GetPaymentAccount(username);
                    int balancepay = Integer.parseInt(checkOut.getCheckoutField().getText());
                    if (balancepay <= 0)
                    {
                        JOptionPane.showMessageDialog(checkOut, "Please input valid balance", "error",JOptionPane.ERROR_MESSAGE);
                    }
                    else if (balancepay > user.getBalance())
                    {
                        JOptionPane.showMessageDialog(checkOut, "Balance's not enough", "error",JOptionPane.ERROR_MESSAGE);
                    }
                    else if (balancepay > user.getDebt())
                    {
                        JOptionPane.showMessageDialog(checkOut, "Out of debt", "error",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        user.setDebt(user.getDebt() - balancepay);
                        user.setBalance(user.getBalance() - balancepay);
                        printWriter.println(balancepay);
                        JOptionPane.showMessageDialog(this.checkOut, "Checkout successfully");
                        this.checkOut.getDebtField().setText(String.valueOf(user.getDebt()));
                        this.checkOut.getBalanceField().setText(String.valueOf(user.getBalance()));
                    }

                }
                catch (NumberFormatException  err)
                {
                    JOptionPane.showMessageDialog(checkOut, "Please input valid balance", "error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
