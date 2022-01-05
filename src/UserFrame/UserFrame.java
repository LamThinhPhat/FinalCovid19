package UserFrame;

import Login.LogInFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class UserFrame extends JFrame {
    private JMenuBar mainMenu;
    private JMenu mUser, mSupply, mPayment;
    private JMenuItem iInformation, iManagedHistory, iChangePass, iLogout, iSupplyHistory, iBuySupply ,iCheckOut, iPaymentHistory;

    UserThread socket;
    MutableBoolean check_connected;

    public UserFrame(String username)
    {
        setTitle("Covid Management System");
        ImageIcon covid_icon=null;
        try {
            covid_icon=new ImageIcon(ImageIO.read(new File("rsc/covid_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(covid_icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        JPanel contentPane = (JPanel) getContentPane();
        setContentPane(contentPane);

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
        iChangePass = new JMenuItem("Change Password");


        check_connected=new MutableBoolean();
        check_connected.setValue(false);
        Socket client=null;
        socket= new UserThread(username,check_connected,client);
        Thread t = new Thread(socket);
        t.start();

        mUser.add(iInformation);
        mUser.add(iManagedHistory);
        mUser.add(iChangePass);
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
        contentPane.add(informationPanel);

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

        iChangePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel contentPane = (JPanel) getContentPane();
                JPanel changePassword = new ChangePassword(username);
                changePassword.setVisible(true);
                contentPane.removeAll();
                contentPane.add(changePassword);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        iLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LogInFrame().setVisible(true);
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

        iCheckOut.addActionListener(e -> {
            JPanel contentPane1 = (JPanel) getContentPane();
            CheckOut checkOut=null;
            CheckOutController controller=null;
            checkOut = new CheckOut(username,socket,controller,check_connected,client,t);

            contentPane1.removeAll();
            contentPane1.add(checkOut);
            contentPane1.revalidate();
            contentPane1.repaint();
        });

    }
}
