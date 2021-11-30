package UserFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.connect.*;

public class Information  extends JPanel{
    private JLabel lbHeader;
    private JLabel lbName, lbId, lbDoB, lbAddress, lbStatus, lbFacility;
    private JLabel name, id, doB, address, province,status, facility;

    Information(String username) {
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
        lbFacility = new JLabel("Current facility:");

        name = new JLabel("null");
        id = new JLabel("null");
        doB = new JLabel("null");
        address = new JLabel("null");
        status = new JLabel("null");
        facility = new JLabel("null");
        province = new JLabel();


        Connection conn = jdbc_connector.getConnection();
        try {
            Statement sta = conn.createStatement();
            String sql = "select * from covid_user left join facility on covid_user.facility_id = facility.facility_id"
                    + " left join address on covid_user.address_id = address.address_id"
                    + " where username = '" + username + "';";
            System.out.println(sql);
            ResultSet re = sta.executeQuery(sql);

            while(re.next()) {
                name.setText(re.getString("full_name"));
                id.setText(re.getString("id"));
                doB.setText(re.getString("dob"));
                address.setText(re.getString("house_number") +", " + re.getString("ward")
                        + ", " + re.getString("district") + ',');
                province.setText(re.getString("province"));
                status.setText(re.getString("patient_status"));
                facility.setText(re.getString("facility_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


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

        add(headerPanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.WEST);
    }
}
