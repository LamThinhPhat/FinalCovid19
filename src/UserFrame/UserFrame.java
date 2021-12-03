package UserFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends JFrame {
    private JMenuBar mainMenu;
    private JMenu mUser, mSupply, mPayment;
    private JMenuItem iInformation, iManagedHistory, iLogout, iSupplyHistory, iBuySupply ,iCheckOut, iPaymentHistory;

    public UserFrame(String username)
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        mainMenu = new JMenuBar();
        mUser = new JMenu("User");
        mSupply = new JMenu("Supply");
        mPayment = new JMenu("Payment");
        iInformation = new JMenuItem("Information");
        iManagedHistory = new JMenuItem("Managed history");
        iSupplyHistory = new JMenuItem("Supply history");
        iBuySupply = new JMenuItem("Buy Supply");
        iPaymentHistory = new JMenuItem("Payment history");
        iCheckOut = new JMenuItem("Checkout");
        iLogout = new JMenuItem("Log out");

        mUser.add(iInformation);
        mUser.add(iManagedHistory);
        mUser.add(iLogout);

        mSupply.add(iSupplyHistory);
        mSupply.add(iBuySupply);

        mPayment.add(iPaymentHistory);
        mPayment.add(iCheckOut);

        mainMenu.add(mUser);
        mainMenu.add(mSupply);
        mainMenu.add(mPayment);
        setJMenuBar(mainMenu);

        JPanel informationPanel = new Information(username);
        setContentPane(informationPanel);

        iInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel contentPane = (JPanel) getContentPane();
                JPanel informationPanel = new Information(username);

                contentPane.removeAll();
                contentPane.add(informationPanel);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        iManagedHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel contentPane = (JPanel) getContentPane();
                JPanel managedHistoryPanel = new ManagedHistory(username);
                managedHistoryPanel.setVisible(true);
                contentPane.removeAll();
                contentPane.add(managedHistoryPanel);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        iLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //open login frame
            }
        });

        iSupplyHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel contentPane = (JPanel) getContentPane();
                JPanel supplyHistory = new SupplyHistory(username);

                contentPane.removeAll();
                contentPane.add(supplyHistory);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        iBuySupply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel contentPane = (JPanel) getContentPane();
                JPanel buySupply = new BuySupply(username);

                contentPane.removeAll();
                contentPane.add(buySupply);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        iPaymentHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel contentPane = (JPanel) getContentPane();
                JPanel paymentHistory = new PaymentHistory(username);

                contentPane.removeAll();
                contentPane.add(paymentHistory);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        iCheckOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel contentPane = (JPanel) getContentPane();
                JPanel checkOut = new CheckOut();

                contentPane.removeAll();
                contentPane.add(checkOut);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });


    }
}
