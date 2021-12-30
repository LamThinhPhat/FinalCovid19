package UserFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import ColorFont.Constant;
import getDB.Account.FunctionAccount;
import table.covid_user;

public class Information  extends JPanel{
    private JLabel lbHeader;
    private JLabel lbName, lbId, lbDoB, lbAddress, lbStatus, lbFacility;
    private JLabel name, id, doB, address, province,status, facility;

    Information(String username) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lbHeader = new JLabel("USER INFORMATION");
        lbHeader.setFont(Constant.HEADER_FONT);
        lbHeader.setForeground(Constant.my_white);
        headerPanel.add(lbHeader);
        headerPanel.setBackground(Constant.my_gray);

        lbName = new JLabel("Name:");
        lbId = new JLabel("ID:");
        lbDoB = new JLabel("Date of birth:");
        lbAddress = new JLabel("Address:");
        lbStatus = new JLabel("Status:");
        lbFacility = new JLabel("Current facility:");

        name = new JLabel("null");
        id = new JLabel("null");
        doB = new JLabel("null");
        address = new JLabel("null");
        status = new JLabel("null");
        facility = new JLabel("null");
        province = new JLabel();

        covid_user user = FunctionAccount.GetCovidUserInfoByUserName(username);
        String fullAddress = FunctionAccount.getUserAddress(user.getHouse_number(), user.getAddress_id());
        String firstAddress = fullAddress.substring(0, fullAddress.lastIndexOf(","));
        String lastAddress = fullAddress.substring(fullAddress.lastIndexOf(",") + 1, fullAddress.length());
        String facilityName = FunctionAccount.getFacilityName(user.getFacility_id());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        name.setText(user.getFull_name());
        id.setText(user.getId());
        doB.setText(format.format(user.getDob()));
        address.setText(firstAddress);
        province.setText(lastAddress);
        status.setText(user.getPatient_status());
        facility.setText(facilityName);


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

        gbc.gridx = 1;
        gbc.gridy = 4;
        province.setFont(Constant.INFO_FONT);
        infoPanel.add(province, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        lbStatus.setFont(Constant.LABEL_FONT);
        infoPanel.add(lbStatus, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        status.setFont(Constant.INFO_FONT);
        infoPanel.add(status, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        lbFacility.setFont(Constant.LABEL_FONT);
        infoPanel.add(lbFacility, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        facility.setFont(Constant.INFO_FONT);
        infoPanel.add(facility, gbc);

        add(headerPanel);
        add(infoPanel);
    }
}
