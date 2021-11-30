package UserFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Information  extends JPanel{
    private JLabel lbHeader;
    private JLabel lbName, lbId, lbDoB, lbAddress, lbStatus, lbRelated;
    private JLabel name, id, doB, address, status, related;

    Information() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10,10,10,10));
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        lbHeader = new JLabel("USER INFORMATION");
        lbHeader.setFont(Constant.HEADER_FONT);
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
        lbName.setFont(Constant.LABEL_FONT);
        infoPanel.add(lbName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        name.setFont(Constant.INFO_FONT);
        infoPanel.add(name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        lbId.setFont(Constant.LABEL_FONT);
        infoPanel.add(lbId, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        id.setFont(Constant.INFO_FONT);
        infoPanel.add(id, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        lbDoB.setFont(Constant.LABEL_FONT);
        infoPanel.add(lbDoB, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        doB.setFont(Constant.INFO_FONT);
        infoPanel.add(doB, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        lbAddress.setFont(Constant.LABEL_FONT);
        infoPanel.add(lbAddress, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        address.setFont(Constant.INFO_FONT);
        infoPanel.add(address, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        lbStatus.setFont(Constant.LABEL_FONT);
        infoPanel.add(lbStatus, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        status.setFont(Constant.INFO_FONT);
        infoPanel.add(status, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        lbRelated.setFont(Constant.LABEL_FONT);
        infoPanel.add(lbRelated, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        related.setFont(Constant.INFO_FONT);
        infoPanel.add(related, gbc);

        add(headerPanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.WEST);
    }
}
