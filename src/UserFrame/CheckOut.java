package UserFrame;

import ColorFont.Constant;

import javax.swing.*;
import java.awt.*;

public class CheckOut extends JPanel{
    private JLabel lbHeader;
    private JPanel headerPanel;

    CheckOut() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
        DebtPanel.add(DebtLabel);
        DebtPanel.add(DebtField);

        JPanel BalancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ContentPanel.add(BalancePanel);
        JLabel BalanceLabel = new JLabel("Balance:      ");
        BalanceLabel.setFont(Constant.LABEL_FONT);
        JTextField BalanceField = new JTextField();
        BalanceField.setColumns(30);
        BalanceField.setEditable(false);
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

        JButton CheckoutButton = new JButton("Checkout");
        CheckoutButton.setForeground(Constant.my_white);
        CheckoutButton.setBackground(new Color(77,82,77));
        ButtonPanel.add(CheckoutButton);

    }
}