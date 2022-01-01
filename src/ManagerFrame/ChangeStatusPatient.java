package ManagerFrame;

import ColorFont.Constant;
import Login.LogInFrame;
import table.covid_user;
import table.related_user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import static ManagerFrame.ManagerFrame.ShowPanel;
import static ManagerFrame.ManagerFrame.main;


public class ChangeStatusPatient extends JPanel {
    public ChangeStatusPatient(String username, String patient_status, String ManagerUsername)
    {
        setBackground(Constant.my_gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        covid_user user = getDB.Account.FunctionAccount.GetCovidUserInfoByUserName(username);
        ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(user.getAddress_id());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Constant.my_gray);
        JLabel headerLabel = new JLabel("Change user status");
        headerLabel.setFont(Constant.HEADER_FONT);
        headerLabel.setForeground(Constant.my_white);
        headerPanel.add(headerLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setForeground(Constant.my_white);
        mainPanel.setBackground(Constant.my_gray);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel UserNameLabel = new JLabel("Username:");
        UserNameLabel.setFont(Constant.LABEL_FONT);
        UserNameLabel.setForeground(Constant.my_white);
        UserNameLabel.setFont(Constant.LABEL_FONT);
        JTextField UserNameField = new JTextField();
        UserNameField.setColumns(20);
        UserNameField.setText(String.valueOf(user.getUsername()));
        UserNameField.setEditable(false);
        UserNameField.setFont(Constant.INFO_FONT);

        JLabel NameLabel = new JLabel("Full name:");
        NameLabel.setFont(Constant.LABEL_FONT);
        NameLabel.setForeground(Constant.my_white);
        NameLabel.setFont(Constant.LABEL_FONT);
        JTextField NameField = new JTextField();
        NameField.setColumns(20);
        NameField.setText(String.valueOf(user.getFull_name()));
        NameField.setEditable(false);
        NameField.setFont(Constant.INFO_FONT);

        JLabel IdNumLabel = new JLabel("ID:");
        IdNumLabel.setFont(Constant.LABEL_FONT);
        IdNumLabel.setForeground(Constant.my_white);
        IdNumLabel.setFont(Constant.LABEL_FONT);
        JTextField IdNumField = new JTextField();
        IdNumField.setColumns(20);
        IdNumField.setText(String.valueOf(user.getId()));
        IdNumField.setEditable(false);
        IdNumField.setFont(Constant.INFO_FONT);

        JLabel DobLabel = new JLabel("Date of birth:");
        DobLabel.setFont(Constant.LABEL_FONT);
        DobLabel.setForeground(Constant.my_white);
        DobLabel.setFont(Constant.LABEL_FONT);
        JTextField YobField = new JTextField();
        YobField.setColumns(20);
        YobField.setText(String.valueOf(user.getDob()));
        YobField.setEditable(false);
        YobField.setFont(Constant.INFO_FONT);

        JLabel AddressLabel = new JLabel("House number:");
        AddressLabel.setFont(Constant.LABEL_FONT);
        AddressLabel.setForeground(Constant.my_white);
        AddressLabel.setFont(Constant.LABEL_FONT);
        JTextField AddressField = new JTextField();
        AddressField.setColumns(20);
        AddressField.setText(String.valueOf(user.getHouse_number()));
        AddressField.setEditable(false);
        AddressField.setFont(Constant.INFO_FONT);

        JLabel ProvinceLable = new JLabel("Province:");
        ProvinceLable.setFont(Constant.LABEL_FONT);
        ProvinceLable.setForeground(Constant.my_white);
        ProvinceLable.setFont(Constant.LABEL_FONT);
        JTextField ProviceField = new JTextField();
        ProviceField.setColumns(20);
        ProviceField.setText(String.valueOf(PDW.get(0)));
        ProviceField.setEditable(false);
        ProviceField.setFont(Constant.INFO_FONT);

        JLabel DistrictLabel = new JLabel("District:");
        DistrictLabel.setFont(Constant.LABEL_FONT);
        DistrictLabel.setForeground(Constant.my_white);
        DistrictLabel.setFont(Constant.LABEL_FONT);
        JTextField DistrictField = new JTextField();
        DistrictField.setColumns(20);
        DistrictField.setText(String.valueOf(PDW.get(1)));
        DistrictField.setEditable(false);
        DistrictField.setFont(Constant.INFO_FONT);

        JLabel WardLabel = new JLabel("Ward:");
        WardLabel.setFont(Constant.LABEL_FONT);
        WardLabel.setForeground(Constant.my_white);
        WardLabel.setFont(Constant.LABEL_FONT);
        JTextField WardField = new JTextField();
        WardField.setColumns(20);
        WardField.setText(String.valueOf(PDW.get(2)));
        WardField.setEditable(false);
        WardField.setFont(Constant.INFO_FONT);

        JLabel ChangeStatusLabel = new JLabel("Change status:");
        ChangeStatusLabel.setFont(Constant.LABEL_FONT);
        ChangeStatusLabel.setForeground(Constant.my_white);
        ChangeStatusLabel.setFont(Constant.LABEL_FONT);
        JComboBox Change_status_patient = new JComboBox();
        Change_status_patient.setFont(Constant.INFO_FONT);
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

        JLabel ChangeFacilityLabel = new JLabel("Change facility:");
        ChangeFacilityLabel.setFont(Constant.LABEL_FONT);
        ChangeFacilityLabel.setForeground(Constant.my_white);
        Change_status_patient.setFont(Constant.LABEL_FONT);
        JComboBox Change_Facility = new JComboBox();
        Change_Facility.setFont(Constant.INFO_FONT);
        Set<String> facilites = getDB.Facility.FunctionFacility.getNameFacility();
        for(String i : facilites)
        {
            Change_Facility.addItem(i);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Constant.my_gray);

        JButton ChangeStatusCancelButton = new JButton("Cancel");
        ChangeStatusCancelButton.setForeground(Constant.my_white);
        ChangeStatusCancelButton.setBackground(new Color(77,82,77));
        ChangeStatusCancelButton.setFont(Constant.INFO_FONT);

        JButton ChangeStatusConfirmUserButton = new JButton("Confirm");
        ChangeStatusConfirmUserButton.setForeground(Constant.my_white);
        ChangeStatusConfirmUserButton.setBackground(new Color(77,82,77));
        ChangeStatusConfirmUserButton.setFont(Constant.INFO_FONT);

        buttonPanel.add(ChangeStatusCancelButton);
        buttonPanel.add(ChangeStatusConfirmUserButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(UserNameLabel, gbc);
        gbc.gridy++;
        mainPanel.add(NameLabel, gbc);
        gbc.gridy++;
        mainPanel.add(AddressLabel, gbc);
        gbc.gridy++;
        mainPanel.add(DistrictLabel, gbc);
        gbc.gridy++;
        mainPanel.add(ChangeStatusLabel, gbc);


        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(UserNameField, gbc);
        gbc.gridy++;
        mainPanel.add(NameField, gbc);
        gbc.gridy++;
        mainPanel.add(AddressField, gbc);
        gbc.gridy++;
        mainPanel.add(DistrictField, gbc);
        gbc.gridy++;
        mainPanel.add(Change_status_patient, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        mainPanel.add(IdNumLabel, gbc);
        gbc.gridy++;
        mainPanel.add(DobLabel, gbc);
        gbc.gridy++;
        mainPanel.add(WardLabel, gbc);
        gbc.gridy++;
        mainPanel.add(ProvinceLable, gbc);
        gbc.gridy++;
        mainPanel.add(ChangeFacilityLabel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        mainPanel.add(IdNumField, gbc);
        gbc.gridy++;
        mainPanel.add(YobField, gbc);
        gbc.gridy++;
        mainPanel.add(WardField, gbc);
        gbc.gridy++;
        mainPanel.add(ProviceField, gbc);
        gbc.gridy++;
        mainPanel.add(Change_Facility, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy++;
        mainPanel.add(buttonPanel, gbc);

        add(headerPanel);
        add(mainPanel);

        ChangeStatusCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowPanel.setVisible(false);
                ShowPanel.removeAll();
                ShowPanel.add(new ListUserPanel());
                ShowPanel.revalidate();
                ShowPanel.repaint();
                ShowPanel.setVisible(true);
            }
        });

        ChangeStatusConfirmUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String old_status = user.getPatient_status();
                String old_facility = user.getFacility_id();
                String SelectedFacilityName = (String) Change_Facility.getSelectedItem();
                String IdFacility = getDB.Facility.FunctionFacility.getIdFacilityByName(SelectedFacilityName);
                int CQuantity = getDB.Facility.FunctionFacility.GetCurrentQuantity(IdFacility);
                int Capacity = getDB.Facility.FunctionFacility.GetCapacity(IdFacility);
                if ( CQuantity >= Capacity && Capacity != -1)
                {
                    JOptionPane.showMessageDialog(ChangeStatusPatient.this,"Facility is full", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    user.setPatient_status((String) Change_status_patient.getSelectedItem());
                    getDB.Facility.FunctionFacility.SetCurrentQuantity(IdFacility, CQuantity + 1);

                    int OldCQuantity = getDB.Facility.FunctionFacility.GetCurrentQuantity(user.getFacility_id());
                    getDB.Facility.FunctionFacility.SetCurrentQuantity(user.getFacility_id(), OldCQuantity - 1);

                    user.setFacility_id(getDB.Facility.FunctionFacility.getIdFacilityByName(SelectedFacilityName));
                    getDB.Account.FunctionAccount.UpdateCovidUser(user);

                    getDB.UpdateHistory.FunctionUpdateHistory.UpdateHistoryList(user,old_status, old_facility, LogInFrame.ManagerUsername);
                    if (old_status.equals("NI") && old_status.equals("RV"))
                    {
                        int step = Integer.parseInt(old_status.substring(old_status.indexOf("F") + 1, old_status.length()))
                                - Integer.parseInt(user.getPatient_status().substring(user.getPatient_status().indexOf("F") + 1, user.getPatient_status().length()));
                        if (step != 0)
                        {

                            ArrayList<related_user> related = getDB.Account.FunctionAccount.GetRealatedUser(user.getUsername());

                            for (related_user i : related) {
                                covid_user related_patient = getDB.Account.FunctionAccount.GetCovidUserInfoByUserName(i.getRelated_username());
                                String old_related_status = related_patient.getPatient_status();
                                if (related_patient.getPatient_status().equals("NI") || related_patient.getPatient_status().equals("RV"))
                                {
                                    int related_step = step + 1;
                                    related_patient.setPatient_status("F" + related_step);
                                    getDB.Account.FunctionAccount.UpdateCovidUser(related_patient);
                                    getDB.UpdateHistory.FunctionUpdateHistory.UpdateHistoryList(related_patient,old_related_status, related_patient.getFacility_id(), LogInFrame.ManagerUsername);
                                }
                                else
                                {
                                    int related_step = Integer.parseInt(old_related_status.substring(old_related_status.indexOf("F") + 1, old_related_status.length()))
                                            - step;
                                    if (related_step > 0)
                                    {
                                        related_patient.setPatient_status("F" + related_step);
                                        getDB.Account.FunctionAccount.UpdateCovidUser(related_patient);
                                        getDB.UpdateHistory.FunctionUpdateHistory.UpdateHistoryList(related_patient,old_related_status, related_patient.getFacility_id(), LogInFrame.ManagerUsername);
                                    }
                                }
                            }
                        }

                    }
                    ShowPanel.setVisible(false);
                    ShowPanel.removeAll();
                    ShowPanel.add(new ListUserPanel());
                    ShowPanel.revalidate();
                    ShowPanel.repaint();
                    ShowPanel.setVisible(true);
                }
            }
        });
    }
}
