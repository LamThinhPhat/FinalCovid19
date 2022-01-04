package AdminFrame;

import ColorFont.Constant;
import getDB.Account.FunctionAccount;
import getDB.Address.FunctionAddress;
import getDB.Facility.FunctionFacility;
import table.account;
import table.covid_user;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class AddManager extends JFrame {
    public AddManager(JComboBox sort) {
        setTitle("Covid Management System");
        ImageIcon covid_icon=null;
        try {
            covid_icon=new ImageIcon(ImageIO.read(new File("rsc/covid_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(covid_icon.getImage());
        setSize(700, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Constant.my_gray);
        setContentPane(contentPane);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Constant.my_gray);
        JLabel SignUpLabel = new JLabel("Add new manager");
        SignUpLabel.setFont(Constant.HEADER_FONT);
        SignUpLabel.setForeground(Constant.my_white);
        headerPanel.add(SignUpLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Constant.my_gray);
        mainPanel.setForeground(Constant.my_white);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel InputUserLabel = new JLabel("Username:");
        InputUserLabel.setFont(Constant.LABEL_FONT);
        InputUserLabel.setForeground(Constant.my_white);
        JTextField UserField = new JTextField();
        UserField.setFont(Constant.INFO_FONT);
        UserField.setColumns(20);

        JLabel InputPassLabel = new JLabel("Password:");
        InputPassLabel.setFont(Constant.LABEL_FONT);
        InputPassLabel.setForeground(Constant.my_white);
        JPasswordField PassField = new JPasswordField();
        PassField.setFont(Constant.INFO_FONT);
        PassField.setColumns(20);

        JLabel ProvinceLable = new JLabel("Province:");
        ProvinceLable.setFont(Constant.LABEL_FONT);
        ProvinceLable.setForeground(Constant.my_white);
        JComboBox Province = new JComboBox();
        Province.setFont(Constant.INFO_FONT);
        JLabel WardLable = new JLabel("Ward:");
        WardLable.setFont(Constant.LABEL_FONT);
        WardLable.setForeground(Constant.my_white);
        JComboBox Ward = new JComboBox();
        Ward.setFont(Constant.INFO_FONT);
        JLabel DistrictLable = new JLabel("District:");
        DistrictLable.setFont(Constant.LABEL_FONT);
        DistrictLable.setForeground(Constant.my_white);
        JComboBox District = new JComboBox();
        District.setFont(Constant.INFO_FONT);

        Set<String> provinces = FunctionAddress.GetAllProvince();
        for (String s : provinces)
        {
            Province.addItem(s);
        }

        JLabel FacilityLabel = new JLabel("Facility:");
        FacilityLabel.setFont(Constant.LABEL_FONT);
        FacilityLabel.setForeground(Constant.my_white);
        JComboBox Facility = new JComboBox();
        Facility.setFont(Constant.INFO_FONT);

        JLabel StatusLabel = new JLabel("Status:");
        StatusLabel.setFont(Constant.LABEL_FONT);
        StatusLabel.setForeground(Constant.my_white);
        JComboBox Status_patient = new JComboBox();
        Status_patient.setFont(Constant.INFO_FONT);

        Set<String> facilities = FunctionFacility.getNameFacility();
        for (String s : facilities)
        {
            Facility.addItem(s);
        }

        Status_patient.addItem("F0");
        Status_patient.addItem("F1");
        Status_patient.addItem("F2");
        Status_patient.addItem("F3");
        Status_patient.addItem("RV");
        Status_patient.addItem("NI");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Constant.my_gray);
        JButton CancelButton = new JButton("Cancel");
        CancelButton.setBackground(new Color(77,82,77));
        CancelButton.setForeground(Constant.my_white);
        CancelButton.setFont(Constant.INFO_FONT);

        JButton ConfirmButton = new JButton("Confirm");
        ConfirmButton.setBackground(new Color(77,82,77));
        ConfirmButton.setForeground(Constant.my_white);
        ConfirmButton.setFont(Constant.INFO_FONT);

        buttonPanel.add(CancelButton);
        buttonPanel.add(ConfirmButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(InputUserLabel, gbc);

        gbc.gridy++;
        mainPanel.add(InputPassLabel, gbc);
        gbc.gridy++;
        mainPanel.add(ProvinceLable, gbc);
        gbc.gridy++;
        mainPanel.add(DistrictLable, gbc);
        gbc.gridy++;
        mainPanel.add(WardLable, gbc);
        gbc.gridy++;
        mainPanel.add(FacilityLabel, gbc);
        gbc.gridy++;
        mainPanel.add(StatusLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(UserField, gbc);

        gbc.gridy++;
        mainPanel.add(PassField, gbc);
        gbc.gridy++;
        mainPanel.add(Province, gbc);
        gbc.gridy++;
        mainPanel.add(District, gbc);
        gbc.gridy++;
        mainPanel.add(Ward, gbc);
        gbc.gridy++;
        mainPanel.add(Facility, gbc);
        gbc.gridy++;
        mainPanel.add(Status_patient, gbc);

//        gbc.anchor = GridBagConstraints.EAST;
//        gbc.gridy++;
//        mainPanel.add(buttonPanel, gbc);

        contentPane.add(headerPanel);
        contentPane.add(mainPanel);
        contentPane.add(buttonPanel);

        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddManager.this.dispose();
                sort.setSelectedIndex(0);
            }
        });

        Province.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SelectedProvince = (String) Province.getSelectedItem();
                Ward.removeAllItems();
                District.removeAllItems();
                Set<String> districts = FunctionAddress.GetAllDistrict(SelectedProvince);
                for (String s : districts)
                {
                    District.addItem(s);
                }

            }
        });

        District.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SelectedDistrict = (String) District.getSelectedItem();
                String SelectedProvince = (String) Province.getSelectedItem();
                Ward.removeAllItems();
                Set<String> wards = FunctionAddress.GetAllWard(SelectedDistrict,SelectedProvince);
                for (String s : wards)
                {
                    Ward.addItem(s);
                }
            }
        });

            ConfirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String province = (String) Province.getSelectedItem();
                    String district = (String) District.getSelectedItem();
                    String ward = (String) Ward.getSelectedItem();
                    String facility_name = (String) Facility.getSelectedItem();
                    String status_patient = (String) Status_patient.getSelectedItem();
                    if (UserField.getText().isEmpty() || String.valueOf(PassField.getPassword()) ==""
                            || province.equals("")|| district.equals("")|| ward.equals("")
                            || facility_name.equals(""))
                    {
                        JOptionPane.showMessageDialog(AddManager.this, "Please fill correcly information","error",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        account acc = new account();
                        acc.setUsername(UserField.getText());
                        acc.setPass(String.valueOf(String.valueOf(PassField.getPassword()).hashCode()));
                        acc.setUser_role(1);
                        acc.setBan_unban(0);
                        if (FunctionAccount.CheckExisted(acc.getUsername()))
                        {
                            JOptionPane.showMessageDialog(AddManager.this, "Username's already existed","error",JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            covid_user coviduser = new covid_user();
                            coviduser.setUsername(acc.getUsername());

                            coviduser.setAddress_id(FunctionAddress.GetAddressId(province,ward,district));
                            coviduser.setPatient_status(status_patient);
                            coviduser.setFacility_id(FunctionFacility.getIdFacilityByName(facility_name));

                            FunctionAccount.AddAccount(acc);
                            FunctionAccount.AddInfoAccount(coviduser);
                            JOptionPane.showMessageDialog(AddManager.this, "Add successfully","success",JOptionPane.ERROR_MESSAGE);
                            AddManager.this.dispose();
                            sort.setSelectedIndex(0);
                        }
                    }
                }
            });
        }
}
