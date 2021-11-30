package ManagerFrame;

import table.covid_user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

public class ChangeStatusPatient extends JPanel {
    public ChangeStatusPatient(String username, String patient_status)
    {
        setBackground(color.my_gray);

        covid_user user = getDB.Account.FunctionAccount.GetCovidUserInfoByUserName(username);
        ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(user.getAddress_id());

        JPanel EditUserPane = new JPanel(new BorderLayout());
        add(EditUserPane);

        JPanel ShowInfoCenter = new JPanel();
        ShowInfoCenter.setBackground(color.my_gray);
        ShowInfoCenter.setLayout(new BoxLayout(ShowInfoCenter,BoxLayout.Y_AXIS));
        EditUserPane.add(ShowInfoCenter, BorderLayout.CENTER);

        JPanel UsernamePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        UsernamePane.setBackground(color.my_gray);
        ShowInfoCenter.add(UsernamePane);
        JLabel UserNameLabel = new JLabel("Username:          ");
        UserNameLabel.setForeground(color.my_white);
        JTextField UserNameField = new JTextField();
        UserNameField.setColumns(30);
        UserNameField.setText(String.valueOf(user.getUsername()));
        UserNameField.setEditable(false);
        UsernamePane.add(UserNameLabel);
        UsernamePane.add(UserNameField);

        JPanel NamePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        NamePane.setBackground(color.my_gray);
        ShowInfoCenter.add(NamePane);
        JLabel NameLabel = new JLabel("Fullname:             ");
        NameLabel.setForeground(color.my_white);
        JTextField NameField = new JTextField();
        NameField.setColumns(30);
        NameField.setText(String.valueOf(user.getFull_name()));
        NameField.setEditable(false);
        NamePane.add(NameLabel);
        NamePane.add(NameField);

        JPanel IdNumPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        IdNumPane.setBackground(color.my_gray);
        ShowInfoCenter.add(IdNumPane);
        JLabel IdNumLabel = new JLabel("ID number           ");
        IdNumLabel.setForeground(color.my_white);
        JTextField IdNumField = new JTextField();
        IdNumField.setColumns(30);
        IdNumField.setText(String.valueOf(user.getId()));
        IdNumField.setEditable(false);
        IdNumPane.add(IdNumLabel);
        IdNumPane.add(IdNumField);

        JPanel DoBPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        DoBPane.setBackground(color.my_gray);
        ShowInfoCenter.add(DoBPane);
        JLabel DobLabel = new JLabel("Date of birth:       ");
        DobLabel.setForeground(color.my_white);
        JTextField YobField = new JTextField();
        YobField.setColumns(30);
        YobField.setText(String.valueOf(user.getDob()));
        YobField.setEditable(false);
        DoBPane.add(DobLabel);
        DoBPane.add(YobField);

        JPanel AddressPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        AddressPane.setBackground(color.my_gray);
        ShowInfoCenter.add(AddressPane);
        JLabel AddressLabel = new JLabel("House number:  ");
        AddressLabel.setForeground(color.my_white);
        JTextField AddressField = new JTextField();
        AddressField.setColumns(30);
        AddressField.setText(String.valueOf(user.getHouse_number()));
        AddressField.setEditable(false);
        AddressPane.add(AddressLabel);
        AddressPane.add(AddressField);

        JPanel ProvincePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ProvincePane.setBackground(color.my_gray);
        ShowInfoCenter.add(ProvincePane);
        JLabel ProvinceLable = new JLabel("Province:            ");
        ProvinceLable.setForeground(color.my_white);
        JTextField ProviceField = new JTextField();
        ProviceField.setColumns(30);
        ProviceField.setText(String.valueOf(PDW.get(0)));
        ProviceField.setEditable(false);
        ProvincePane.add(ProvinceLable);
        ProvincePane.add(ProviceField);

        JPanel DistrictPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        DistrictPane.setBackground(color.my_gray);
        ShowInfoCenter.add(DistrictPane);
        JLabel DistrictLabel = new JLabel("District:               ");
        DistrictLabel.setForeground(color.my_white);
        JTextField DistrictField = new JTextField();
        DistrictField.setColumns(30);
        DistrictField.setText(String.valueOf(PDW.get(1)));
        DistrictField.setEditable(false);
        DistrictPane.add(DistrictLabel);
        DistrictPane.add(DistrictField);


        JPanel WardPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        WardPane.setBackground(color.my_gray);
        ShowInfoCenter.add(WardPane);
        JLabel WardLabel = new JLabel("Ward:                  ");
        WardLabel.setForeground(color.my_white);
        JTextField WardField = new JTextField();
        WardField.setColumns(30);
        WardField.setText(String.valueOf(PDW.get(2)));
        WardField.setEditable(false);
        WardPane.add(WardLabel);
        WardPane.add(WardField);

        JPanel ChangeStatusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ChangeStatusPanel.setBackground(color.my_gray);
        JLabel ChangeStatusLabel = new JLabel("Change status: ");
        ChangeStatusLabel.setForeground(color.my_white);
        JComboBox Change_status_patient = new JComboBox();
        if (patient_status.equals("RV") || patient_status.equals("NI"))
        {
            Change_status_patient.addItem("F0");
            Change_status_patient.addItem("F1");
            Change_status_patient.addItem("F2");
            Change_status_patient.addItem("F3");
            Change_status_patient.addItem("RV");
            Change_status_patient.addItem("NI");
        }
        else {
            int patient_status_max = Integer.parseInt(patient_status.substring(patient_status.indexOf("F") + 1, patient_status.length()));
            for (int i = 0; i <= patient_status_max; ++i)
            {
                Change_status_patient.addItem("F" + i);
            }
            Change_status_patient.addItem("RV");
            Change_status_patient.addItem("NI");

        }
        ChangeStatusPanel.add(ChangeStatusLabel);
        ChangeStatusPanel.add(Change_status_patient);

        ShowInfoCenter.add(ChangeStatusPanel);

        JPanel ChangeFacilityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ChangeFacilityPanel.setBackground(color.my_gray);
        JLabel ChangeFacilityLabel = new JLabel("Change facility: ");
        ChangeFacilityLabel.setForeground(color.my_white);

        JComboBox Change_Facility = new JComboBox();
        Set<String> facilites = getDB.Facility.FunctionFacility.getNameFacility();
        for(String i : facilites)
        {
            Change_Facility.addItem(i);
        }

        ChangeFacilityPanel.add(ChangeFacilityLabel);
        ChangeFacilityPanel.add(Change_Facility);

        ShowInfoCenter.add(ChangeFacilityPanel);

        JPanel ChangeStatusButtonPaneSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ChangeStatusButtonPaneSouth.setBackground(color.my_gray);
        EditUserPane.add(ChangeStatusButtonPaneSouth,BorderLayout.SOUTH);

        JButton ChangeStatusCancelButton = new JButton("Cancel");
        ChangeStatusCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                removeAll();
                add(new ListUserPanel());
                revalidate();
                repaint();
                setVisible(true);
            }
        });
        ChangeStatusButtonPaneSouth.add(ChangeStatusCancelButton);


        JButton ChangeStatusConfirmUserButton = new JButton("Confirm");
        ChangeStatusConfirmUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setPatient_status((String) Change_status_patient.getSelectedItem());
                String SelectedFacilityName = (String) Change_Facility.getSelectedItem();
                String IdFacility = getDB.Facility.FunctionFacility.getIdFacilityByName(SelectedFacilityName);
                int CQuantity = getDB.Facility.FunctionFacility.GetCurrentQuantity(IdFacility);
                int Capacity = getDB.Facility.FunctionFacility.GetCapacity(IdFacility);
                if ( CQuantity >= Capacity)
                {
                    JOptionPane.showMessageDialog(ChangeStatusPatient.this,"Facility is full", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    getDB.Facility.FunctionFacility.SetCurrentQuantity(IdFacility, CQuantity + 1);
                    covid_user user = getDB.Account.FunctionAccount.GetCovidUserInfoByUserName(username);
                    int OldCQuantity = getDB.Facility.FunctionFacility.GetCurrentQuantity(user.getFacility_id());
                    getDB.Facility.FunctionFacility.SetCurrentQuantity(user.getFacility_id(), OldCQuantity - 1);

                    user.setFacility_id(getDB.Facility.FunctionFacility.getIdFacilityByName(SelectedFacilityName));
                    getDB.Account.FunctionAccount.UpdateCovidUser(user);
                    setVisible(false);
                    removeAll();
                    add(new ListUserPanel());
                    revalidate();
                    setVisible(true);
                }
            }
        });

        ChangeStatusButtonPaneSouth.add(ChangeStatusConfirmUserButton);












    }
}
