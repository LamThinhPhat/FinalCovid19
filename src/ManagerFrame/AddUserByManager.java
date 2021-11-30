package ManagerFrame;

import ColorFont.Constant;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Constant.my_gray);
        setContentPane(contentPane);


        JLabel SignUpLabel = new JLabel("Add new user to the system");
        SignUpLabel.setFont(Constant.LABEL_FONT);
        SignUpLabel.setForeground(Constant.my_white);
        contentPane.add(SignUpLabel);

        JPanel InputUserPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(InputUserPane);
        JLabel InputUserLabel = new JLabel("Input Username:        ");
        InputUserLabel.setFont(Constant.LABEL_FONT);
        InputUserLabel.setForeground(Constant.my_white);
        JTextField UserField = new JTextField();
        UserField.setColumns(40);
        InputUserPane.setBackground(Constant.my_gray);
        InputUserPane.add(InputUserLabel);
        InputUserPane.add(UserField);

        JPanel InputPasstPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(InputPasstPane);
        JLabel InputPassLabel = new JLabel("Input Password:         ");
        InputPassLabel.setFont(Constant.LABEL_FONT);
        InputPassLabel.setForeground(Constant.my_white);
        JPasswordField PassField = new JPasswordField();
        PassField.setColumns(40);
        InputPasstPane.setBackground(Constant.my_gray);
        InputPasstPane.add(InputPassLabel);
        InputPasstPane.add(PassField);

        JPanel InputNamePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(InputNamePane);
        JLabel InputNameLabel = new JLabel("Input FullName:         ");
        InputNameLabel.setFont(Constant.LABEL_FONT);
        InputNameLabel.setForeground(Constant.my_white);
        JTextField NameField = new JTextField();
        NameField.setColumns(40);
        InputNamePane.setBackground(Constant.my_gray);
        InputNamePane.add(InputNameLabel);
        InputNamePane.add(NameField);

        JPanel InputDoBPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(InputDoBPane);
        JLabel InputDobLabel = new JLabel("Input Date of birth:    ");
        InputDobLabel.setFont(Constant.LABEL_FONT);
        InputDobLabel.setForeground(Constant.my_white);
        JTextField DobField = new JTextField();
        DobField.setColumns(32);
        JLabel FormatDob = new JLabel("(yyyy-mm-dd)");
        FormatDob.setForeground(Constant.my_white);
        InputDoBPane.setBackground(Constant.my_gray);
        InputDoBPane.add(InputDobLabel);
        InputDoBPane.add(DobField);
        InputDoBPane.add(FormatDob);

        JPanel InputAddressPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(InputAddressPane);
        JLabel InputAddressLabel = new JLabel("Input House Number:");
        InputAddressLabel.setFont(Constant.LABEL_FONT);
        InputAddressLabel.setForeground(Constant.my_white);
        JTextField AddressField = new JTextField();
        AddressField.setColumns(40);
        InputAddressPane.setBackground(Constant.my_gray);
        InputAddressPane.add(InputAddressLabel);
        InputAddressPane.add(AddressField);

        JPanel PPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        PPane.setBackground(Constant.my_gray);
        contentPane.add(PPane);

        JPanel DPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        DPane.setBackground(Constant.my_gray);
        contentPane.add(DPane);

        JPanel WPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        WPane.setBackground(Constant.my_gray);
        contentPane.add(WPane);

        JLabel ProvinceLable = new JLabel("Province:                     ");
        ProvinceLable.setFont(Constant.LABEL_FONT);
        ProvinceLable.setForeground(Constant.my_white);
        JComboBox Province = new JComboBox();
        JLabel WardLable = new JLabel("Ward:                          ");
        WardLable.setFont(Constant.LABEL_FONT);
        WardLable.setForeground(Constant.my_white);
        JComboBox Ward = new JComboBox();
        JLabel DistrictLable = new JLabel("District:                       ");
        DistrictLable.setFont(Constant.LABEL_FONT);
        DistrictLable.setForeground(Constant.my_white);
        JComboBox District = new JComboBox();

        PPane.add(ProvinceLable);
        PPane.add(Province);
        DPane.add(DistrictLable);
        DPane.add(District);
        WPane.add(WardLable);
        WPane.add(Ward);


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

        JPanel InputIdPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(InputIdPane);
        JLabel InputIdLabel = new JLabel("Input ID:                     ");
        InputIdLabel.setFont(Constant.LABEL_FONT);
        InputIdLabel.setForeground(Constant.my_white);
        JTextField IdField = new JTextField();
        IdField.setColumns(40);
        InputIdPane.setBackground(Constant.my_gray);
        InputIdPane.add(InputIdLabel);
        InputIdPane.add(IdField);

        JPanel StatusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,1));
        JLabel FacilityLabel = new JLabel("Facility: ");
        FacilityLabel.setFont(Constant.LABEL_FONT);
        FacilityLabel.setForeground(Constant.my_white);
        JComboBox Facility = new JComboBox();

        JLabel StatusLabel = new JLabel("Status: ");
        StatusLabel.setFont(Constant.LABEL_FONT);
        StatusLabel.setForeground(Constant.my_white);
        JComboBox Status_patient = new JComboBox();

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



        contentPane.add(StatusPanel);
        StatusPanel.setBackground(Constant.my_gray);
        StatusPanel.add(FacilityLabel);
        StatusPanel.add(Facility);
        StatusPanel.add(StatusLabel);
        StatusPanel.add(Status_patient);


        JPanel ButtonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ButtonPane.setBackground(Constant.my_gray);
        contentPane.add(ButtonPane);

        JButton CancelButton = new JButton("Cancel");
        CancelButton.setBackground(new Color(77,82,77));
        CancelButton.setForeground(Constant.my_white);
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagerFrame().setVisible(true);
                AddUserByManager.this.dispose();
            }
        });
        ButtonPane.add(CancelButton);

        JButton ConfirmButton = new JButton("Confirm");
        ConfirmButton.setBackground(new Color(77,82,77));
        ConfirmButton.setForeground(Constant.my_white);

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
                        } catch (ParseException err) {
                            err.printStackTrace();
                        }

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
                            JOptionPane.showMessageDialog(AddUserByManager.this, "Create successfully", "success", JOptionPane.ERROR_MESSAGE);
                            new ManagerFrame().setVisible(true);
                            AddUserByManager.this.dispose();
                        }

                    }
                }
            }
        });

        ButtonPane.add(ConfirmButton);



    }
}