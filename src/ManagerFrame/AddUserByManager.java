package ManagerFrame;

import ColorFont.Constant;
import Login.LogInFrame;
import getDB.Account.FunctionAccount;
import getDB.Address.FunctionAddress;
import getDB.Facility.FunctionFacility;
import table.account;
import table.covid_user;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import table.payment_user;
import javax.swing.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;


public class AddUserByManager extends JFrame {
    public AddUserByManager() {
        setTitle("Covid Management System");
        ImageIcon covid_icon=null;
        try {
            covid_icon=new ImageIcon(ImageIO.read(new File("rsc/covid_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(covid_icon.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Constant.my_gray);
        setContentPane(contentPane);

        JPanel headerLPanel = new JPanel();
        JLabel SignUpLabel = new JLabel("Add new user to the system");
        SignUpLabel.setFont(Constant.HEADER_FONT);
        SignUpLabel.setForeground(Constant.my_white);
        headerLPanel.setBackground(Constant.my_gray);
        headerLPanel.add(SignUpLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Constant.my_gray);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel InputUserLabel = new JLabel("Username:");
        InputUserLabel.setFont(Constant.LABEL_FONT);
        InputUserLabel.setForeground(Constant.my_white);
        JTextField UserField = new JTextField();
        UserField.setFont(Constant.INFO_FONT);
        UserField.setColumns(25);

        JLabel InputPassLabel = new JLabel("Password:");
        InputPassLabel.setFont(Constant.LABEL_FONT);
        InputPassLabel.setForeground(Constant.my_white);
        JPasswordField PassField = new JPasswordField();
        PassField.setFont(Constant.INFO_FONT);
        PassField.setColumns(25);


        JLabel InputNameLabel = new JLabel("FullName:");
        InputNameLabel.setFont(Constant.LABEL_FONT);
        InputNameLabel.setForeground(Constant.my_white);
        JTextField NameField = new JTextField();
        NameField.setFont(Constant.INFO_FONT);
        NameField.setColumns(25);


        JLabel InputDobLabel = new JLabel("Date of birth (yyyy-mm-dd):");
        InputDobLabel.setFont(Constant.LABEL_FONT);
        InputDobLabel.setForeground(Constant.my_white);
        JTextField DobField = new JTextField();
        DobField.setFont(Constant.INFO_FONT);
        DobField.setColumns(25);


        JLabel InputAddressLabel = new JLabel("House Number:");
        InputAddressLabel.setFont(Constant.LABEL_FONT);
        InputAddressLabel.setForeground(Constant.my_white);
        JTextField AddressField = new JTextField();
        AddressField.setFont(Constant.INFO_FONT);
        AddressField.setColumns(25);

        //not add yet
        JLabel ProvinceLabel = new JLabel("Province:");
        ProvinceLabel.setFont(Constant.LABEL_FONT);
        ProvinceLabel.setForeground(Constant.my_white);
        JComboBox Province = new JComboBox();
        Province.setFont(Constant.INFO_FONT);

        JLabel WardLabel = new JLabel("Ward:");
        WardLabel.setFont(Constant.LABEL_FONT);
        WardLabel.setForeground(Constant.my_white);
        JComboBox Ward = new JComboBox();
        Ward.setFont(Constant.INFO_FONT);

        JLabel DistrictLabel = new JLabel("District:");
        DistrictLabel.setFont(Constant.LABEL_FONT);
        DistrictLabel.setForeground(Constant.my_white);
        JComboBox District = new JComboBox();
        District.setFont(Constant.INFO_FONT);


        Set<String> provinces = FunctionAddress.GetAllProvince();
        for (String s : provinces)
        {
            Province.addItem(s);
        }

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

        JLabel InputIdLabel = new JLabel("Input ID:");
        InputIdLabel.setFont(Constant.LABEL_FONT);
        InputIdLabel.setForeground(Constant.my_white);
        JTextField IdField = new JTextField();
        IdField.setFont(Constant.INFO_FONT);
        IdField.setColumns(25);

        JLabel FacilityLabel = new JLabel("Facility:");
        FacilityLabel.setFont(Constant.LABEL_FONT);
        FacilityLabel.setForeground(Constant.my_white);
        JComboBox Facility = new JComboBox();
        Facility.setFont(Constant.INFO_FONT);

        JLabel StatusLabel = new JLabel("Status:");
        StatusLabel.setFont(Constant.LABEL_FONT);
        StatusLabel.setForeground(Constant.my_white);
        JComboBox Status_patient = new JComboBox();
        Status_patient.setPrototypeDisplayValue("Status");
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


        JPanel ButtonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ButtonPane.setBackground(Constant.my_gray);

        JButton CancelButton = new JButton("Cancel");
        CancelButton.setForeground(Constant.my_white);
        CancelButton.setBackground(new Color(77,82,77));

        CancelButton.setBackground(new Color(77,82,77));
        CancelButton.setForeground(Constant.my_white);
        CancelButton.setFont(Constant.INFO_FONT);

        JButton ConfirmButton = new JButton("Confirm");
        ConfirmButton.setBackground(new Color(77,82,77));
        ConfirmButton.setForeground(Constant.my_white);
        ConfirmButton.setFont(Constant.INFO_FONT);

        ButtonPane.add(CancelButton);
        ButtonPane.add(ConfirmButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(InputUserLabel, gbc);

        gbc.gridy++;
        mainPanel.add(InputPassLabel, gbc);

        gbc.gridy++;
        mainPanel.add(InputNameLabel, gbc);

        gbc.gridy++;
        mainPanel.add(InputDobLabel, gbc);

        gbc.gridy++;
        mainPanel.add(InputAddressLabel, gbc);

        gbc.gridy++;
        mainPanel.add(ProvinceLabel, gbc);

        gbc.gridy++;
        mainPanel.add(DistrictLabel, gbc);

        gbc.gridy++;
        mainPanel.add(WardLabel, gbc);

        gbc.gridy++;
        mainPanel.add(InputIdLabel, gbc);

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
        mainPanel.add(NameField, gbc);

        gbc.gridy++;
        mainPanel.add(DobField, gbc);

        gbc.gridy++;
        mainPanel.add(AddressField, gbc);

        gbc.gridy++;
        mainPanel.add(Province, gbc);

        gbc.gridy++;
        mainPanel.add(District, gbc);

        gbc.gridy++;
        mainPanel.add(Ward, gbc);

        gbc.gridy++;
        mainPanel.add(IdField, gbc);

        gbc.gridy++;
        mainPanel.add(Facility, gbc);

        gbc.gridy++;
        mainPanel.add(Status_patient, gbc);

        //add panel
        contentPane.add(headerLPanel);
        contentPane.add(mainPanel);
        contentPane.add(ButtonPane);

        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserByManager.this.dispose();
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
                        || NameField.getText().isEmpty() || DobField.getText().isEmpty()|| AddressField.getText().isEmpty()
                        || IdField.getText().isEmpty() || province.equals("")|| district.equals("")|| ward.equals(""))
                {
                    JOptionPane.showMessageDialog(AddUserByManager.this, "Please fill correcly information","error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    account acc = new account();
                    acc.setUsername(UserField.getText());
                    acc.setPass(String.valueOf(String.valueOf(PassField.getPassword()).hashCode()));
                    acc.setUser_role(3);
                    acc.setBan_unban(0);
                    if (FunctionAccount.CheckExisted(acc.getUsername()))
                    {
                        JOptionPane.showMessageDialog(AddUserByManager.this, "Username's already existed","error",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {

                        covid_user coviduser = new covid_user();
                        coviduser.setUsername(acc.getUsername());
                        coviduser.setFull_name(NameField.getText());
                        coviduser.setId(IdField.getText());

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            java.util.Date str = format.parse(DobField.getText());
                            java.sql.Date temp = new java.sql.Date(str.getTime());
                            coviduser.setDob(temp);
                            coviduser.setHouse_number(AddressField.getText());

                            coviduser.setAddress_id(FunctionAddress.GetAddressId(province,ward,district));
                            coviduser.setPatient_status(status_patient);
                            coviduser.setFacility_id(FunctionFacility.getIdFacilityByName(facility_name));

                            int CQuantity = getDB.Facility.FunctionFacility.GetCurrentQuantity(coviduser.getFacility_id());
                            int Capacity = getDB.Facility.FunctionFacility.GetCapacity(coviduser.getFacility_id());
                            if ( CQuantity >= Capacity && Capacity != -1)
                            {
                                JOptionPane.showMessageDialog(AddUserByManager.this,"Facility is full", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                getDB.Facility.FunctionFacility.SetCurrentQuantity(coviduser.getFacility_id(), CQuantity + 1);
                                FunctionAccount.AddAccount(acc);
                                FunctionAccount.AddInfoAccount(coviduser);
                                payment_user paymentUser = new payment_user();
                                paymentUser.setUsername(acc.getUsername());
                                paymentUser.setBalance(50000);
                                paymentUser.setDebt(0);
                                getDB.PaymentUser.FunctionPaymentUser.AddPaymentAccount(paymentUser);
                                JOptionPane.showMessageDialog(AddUserByManager.this, "Create successfully", "success", JOptionPane.ERROR_MESSAGE);
                                new ManagerFrame(LogInFrame.ManagerUsername).setVisible(true);
                                AddUserByManager.this.dispose();
                            }
                        } catch (ParseException err) {
                            JOptionPane.showMessageDialog(AddUserByManager.this, "Please fill correct format date (yyyy-mm-dd)","error",JOptionPane.ERROR_MESSAGE);
                        }


                    }
                }
            }
        });
    }
}