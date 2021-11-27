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


public class AddUserByAdmin extends JFrame {
    Color my_gray=new Color(61,64,62);
    Color my_white=new Color(235,235,235);
    public AddUserByAdmin() {
        setTitle("Covid Management System");
        ImageIcon covid_icon=null;
        try {
            covid_icon=new ImageIcon(ImageIO.read(new File("rsc/covid_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(covid_icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(460, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(my_gray);
        setContentPane(contentPane);


        JLabel SignUpLabel = new JLabel("Sign up");
        SignUpLabel.setForeground(my_white);
        contentPane.add(SignUpLabel);

        JPanel InputUserPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(InputUserPane);
        JLabel InputUserLabel = new JLabel("Input Username:      ");
        InputUserLabel.setForeground(my_white);
        JTextField UserField = new JTextField();
        UserField.setColumns(30);
        InputUserPane.setBackground(my_gray);
        InputUserPane.add(InputUserLabel);
        InputUserPane.add(UserField);

        JPanel InputPasstPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(InputPasstPane);
        JLabel InputPassLabel = new JLabel("Input Password:      ");
        InputPassLabel.setForeground(my_white);
        JPasswordField PassField = new JPasswordField();
        PassField.setColumns(30);
        InputPasstPane.setBackground(my_gray);
        InputPasstPane.add(InputPassLabel);
        InputPasstPane.add(PassField);

        JPanel InputNamePane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(InputNamePane);
        JLabel InputNameLabel = new JLabel("Input FullName:        ");
        InputNameLabel.setForeground(my_white);
        JTextField NameField = new JTextField();
        NameField.setColumns(30);
        InputNamePane.setBackground(my_gray);
        InputNamePane.add(InputNameLabel);
        InputNamePane.add(NameField);

        JPanel InputDoBPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(InputDoBPane);
        JLabel InputDobLabel = new JLabel("Input Date of birth(yyyy-mm-dd):");
        InputDobLabel.setForeground(my_white);
        JTextField DobField = new JTextField();
        DobField.setColumns(25);
        InputDoBPane.setBackground(my_gray);
        InputDoBPane.add(InputDobLabel);
        InputDoBPane.add(DobField);

        JPanel InputAddressPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(InputAddressPane);
        JLabel InputAddressLabel = new JLabel("Input House Number:");
        InputAddressLabel.setForeground(my_white);
        JTextField AddressField = new JTextField();
        AddressField.setColumns(30);
        InputAddressPane.setBackground(my_gray);
        InputAddressPane.add(InputAddressLabel);
        InputAddressPane.add(AddressField);

        JPanel PPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        PPane.setBackground(color.my_gray);
        contentPane.add(PPane);

        JPanel DPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        DPane.setBackground(color.my_gray);
        contentPane.add(DPane);

        JPanel WPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        WPane.setBackground(color.my_gray);
        contentPane.add(WPane);

        JLabel ProvinceLable = new JLabel("Province");
        ProvinceLable.setForeground(color.my_white);
        JComboBox Province = new JComboBox();
        JLabel WardLable = new JLabel("Ward      ");
        WardLable.setForeground(color.my_white);
        JComboBox Ward = new JComboBox();
        JLabel DistrictLable = new JLabel("District   ");
        DistrictLable.setForeground(color.my_white);
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

        JPanel InputIdPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(InputIdPane);
        JLabel InputIdLabel = new JLabel("Input ID:                     ");
        InputIdLabel.setForeground(my_white);
        JTextField IdField = new JTextField();
        IdField.setColumns(30);
        InputIdPane.setBackground(my_gray);
        InputIdPane.add(InputIdLabel);
        InputIdPane.add(IdField);

        JPanel StatusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,1));
        JLabel FacilityLabel = new JLabel("Facility: ");
        FacilityLabel.setForeground(color.my_white);
        JComboBox Facility = new JComboBox();

        JLabel StatusLabel = new JLabel("Status: ");
        StatusLabel.setForeground(color.my_white);
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
        StatusPanel.setBackground(color.my_gray);
        StatusPanel.add(FacilityLabel);
        StatusPanel.add(Facility);
        StatusPanel.add(StatusLabel);
        StatusPanel.add(Status_patient);


        JPanel ButtonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ButtonPane.setBackground(my_gray);
        contentPane.add(ButtonPane);

        JButton CancelButton = new JButton("Cancel");
        CancelButton.setBackground(new Color(77,82,77));
        CancelButton.setForeground(my_white);
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogInFrame().setVisible(true);
                AddUserByAdmin.this.dispose();
            }
        });
        ButtonPane.add(CancelButton);

        JButton ConfirmButton = new JButton("Confirm");
        ConfirmButton.setBackground(new Color(77,82,77));
        ConfirmButton.setForeground(my_white);

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
                        || IdField.getText().isEmpty() || province.equals("")|| district.equals("")|| ward.equals("")
                        || facility_name.equals(""))
                {
                    JOptionPane.showMessageDialog(AddUserByAdmin.this, "Please fill correcly information","error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    account acc = new account();
                    acc.setUsername(UserField.getText());
                    acc.setPass(String.valueOf(String.valueOf(PassField.getPassword()).hashCode()));
                    acc.setUser_role(3);
                    acc.setBan_unban(0);
                    if (FunctionAccount.CheckExisted(acc.getUsername()))
                    {
                        JOptionPane.showMessageDialog(AddUserByAdmin.this, "Username's already existed","error",JOptionPane.ERROR_MESSAGE);
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
                        coviduser.setFacility_id(FunctionFacility.getIdFacility(facility_name));


                        FunctionAccount.AddAccount(acc);
                        FunctionAccount.AddInfoAccount(coviduser);
                        JOptionPane.showMessageDialog(AddUserByAdmin.this, "Create successfully","success",JOptionPane.ERROR_MESSAGE);
                        new LogInFrame().setVisible(true);
                        AddUserByAdmin.this.dispose();

                    }
                }
            }
        });

        ButtonPane.add(ConfirmButton);



    }
}