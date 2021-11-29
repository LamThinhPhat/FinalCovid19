package UserFrame;

import javax.swing.*;
import java.awt.*;

public class UserFrame extends JFrame {
    private JLabel lbHeader;
    private JLabel lbName, lbId, lbDoB, lbAddress, lbStatus, lbRelated;
    private JLabel name, id, doB, address, status, related;

    private JMenuBar mainMenu;
    private JMenu mUser, mSupply, mPayment;
    private JMenuItem iInformation, iManagedHistory, iLogout, iSupplyHistory, iBuySupply ,iCheckOut, iPaymentHistory;

    public UserFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        mainMenu = new JMenuBar();
        mUser = new JMenu("User");
        mSupply = new JMenu("Suppy");
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

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        lbHeader = new JLabel("USER INFORMATION");
        lbHeader.setFont(new Font("Serif", Font.PLAIN, 35));
        headerPanel.add(lbHeader);

        lbName = new JLabel("Name:");
        lbId = new JLabel("ID:");
        lbDoB = new JLabel("Date of birth:");
        lbAddress = new JLabel("Address:");
        lbStatus = new JLabel("Status:");
        lbRelated = new JLabel("Related people:");

        name = new JLabel("Hoang Thien Nhan");
        id = new JLabel("123456789");
        doB = new JLabel("10/10/2021");
        address = new JLabel("12 CMT8, Ninh Binh, Can tho");
        status = new JLabel("F4");
        related = new JLabel("hoang, thien, nhan");

        gbc.insets = new Insets(0, 10,10,0);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        lbName.setFont(new Font("Serif", Font.PLAIN, 25 ));
        infoPanel.add(lbName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        name.setFont(new Font("Serif", Font.PLAIN, 25 ));
        infoPanel.add(name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        lbId.setFont(new Font("Serif", Font.PLAIN, 25 ));
        infoPanel.add(lbId, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        id.setFont(new Font("Serif", Font.PLAIN, 25 ));
        infoPanel.add(id, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        lbDoB.setFont(new Font("Serif", Font.PLAIN, 25 ));
        infoPanel.add(lbDoB, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        doB.setFont(new Font("Serif", Font.PLAIN, 25 ));
        infoPanel.add(doB, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        lbAddress.setFont(new Font("Serif", Font.PLAIN, 25 ));
        infoPanel.add(lbAddress, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        address.setFont(new Font("Serif", Font.PLAIN, 25 ));
        infoPanel.add(address, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        lbStatus.setFont(new Font("Serif", Font.PLAIN, 25 ));
        infoPanel.add(lbStatus, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        status.setFont(new Font("Serif", Font.PLAIN, 25 ));
        infoPanel.add(status, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        lbRelated.setFont(new Font("Serif", Font.PLAIN, 25 ));
        infoPanel.add(lbRelated, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        related.setFont(new Font("Serif", Font.PLAIN, 25 ));
        infoPanel.add(related, gbc);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.WEST);
        setContentPane(mainPanel);
    }
}
