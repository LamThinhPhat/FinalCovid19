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


public class ChangeStatusPatient extends JPanel {
    public ChangeStatusPatient(String username, String patient_status, String ManagerUsername)
    {
        setBackground(Constant.my_gray);
        covid_user user = getDB.Account.FunctionAccount.GetCovidUserInfoByUserName(username);
        ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(user.getAddress_id());

        JPanel EditUserPane = new JPanel(new BorderLayout());
        add(EditUserPane);

        JPanel ShowInfoCenter = new JPanel();
        ShowInfoCenter.setBackground(Constant.my_gray);
        ShowInfoCenter.setLayout(new BoxLayout(ShowInfoCenter,BoxLayout.Y_AXIS));
        EditUserPane.add(ShowInfoCenter, BorderLayout.CENTER);

        JPanel UsernamePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        UsernamePane.setBackground(Constant.my_gray);
        ShowInfoCenter.add(UsernamePane);
        JLabel UserNameLabel = new JLabel("Username:          ");
        UserNameLabel.setFont(Constant.LABEL_FONT);
        UserNameLabel.setForeground(Constant.my_white);
        JTextField UserNameField = new JTextField();
        UserNameField.setColumns(30);
        UserNameField.setText(String.valueOf(user.getUsername()));
        UserNameField.setEditable(false);
        UsernamePane.add(UserNameLabel);
        UsernamePane.add(UserNameField);

        JPanel NamePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        NamePane.setBackground(Constant.my_gray);
        ShowInfoCenter.add(NamePane);
        JLabel NameLabel = new JLabel("Fullname:           ");
        NameLabel.setFont(Constant.LABEL_FONT);
        NameLabel.setForeground(Constant.my_white);
        JTextField NameField = new JTextField();
        NameField.setColumns(30);
        NameField.setText(String.valueOf(user.getFull_name()));
        NameField.setEditable(false);
        NamePane.add(NameLabel);
        NamePane.add(NameField);

        JPanel IdNumPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        IdNumPane.setBackground(Constant.my_gray);
        ShowInfoCenter.add(IdNumPane);
        JLabel IdNumLabel = new JLabel("ID number:        ");
        IdNumLabel.setFont(Constant.LABEL_FONT);
        IdNumLabel.setForeground(Constant.my_white);
        JTextField IdNumField = new JTextField();
        IdNumField.setColumns(30);
        IdNumField.setText(String.valueOf(user.getId()));
        IdNumField.setEditable(false);
        IdNumPane.add(IdNumLabel);
        IdNumPane.add(IdNumField);

        JPanel DoBPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        DoBPane.setBackground(Constant.my_gray);
        ShowInfoCenter.add(DoBPane);
        JLabel DobLabel = new JLabel("Date of birth:     ");
        DobLabel.setFont(Constant.LABEL_FONT);
        DobLabel.setForeground(Constant.my_white);
        JTextField YobField = new JTextField();
        YobField.setColumns(30);
        YobField.setText(String.valueOf(user.getDob()));
        YobField.setEditable(false);
        DoBPane.add(DobLabel);
        DoBPane.add(YobField);

        JPanel AddressPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        AddressPane.setBackground(Constant.my_gray);
        ShowInfoCenter.add(AddressPane);
        JLabel AddressLabel = new JLabel("House number:  ");
        AddressLabel.setFont(Constant.LABEL_FONT);
        AddressLabel.setForeground(Constant.my_white);
        JTextField AddressField = new JTextField();
        AddressField.setColumns(30);
        AddressField.setText(String.valueOf(user.getHouse_number()));
        AddressField.setEditable(false);
        AddressPane.add(AddressLabel);
        AddressPane.add(AddressField);

        JPanel ProvincePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ProvincePane.setBackground(Constant.my_gray);
        ShowInfoCenter.add(ProvincePane);
        JLabel ProvinceLable = new JLabel("Province:            ");
        ProvinceLable.setFont(Constant.LABEL_FONT);
        ProvinceLable.setForeground(Constant.my_white);
        JTextField ProviceField = new JTextField();
        ProviceField.setColumns(30);
        ProviceField.setText(String.valueOf(PDW.get(0)));
        ProviceField.setEditable(false);
        ProvincePane.add(ProvinceLable);
        ProvincePane.add(ProviceField);

        JPanel DistrictPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        DistrictPane.setBackground(Constant.my_gray);
        ShowInfoCenter.add(DistrictPane);
        JLabel DistrictLabel = new JLabel("District:              ");
        DistrictLabel.setFont(Constant.LABEL_FONT);
        DistrictLabel.setForeground(Constant.my_white);
        JTextField DistrictField = new JTextField();
        DistrictField.setColumns(30);
        DistrictField.setText(String.valueOf(PDW.get(1)));
        DistrictField.setEditable(false);
        DistrictPane.add(DistrictLabel);
        DistrictPane.add(DistrictField);


        JPanel WardPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        WardPane.setBackground(Constant.my_gray);
        ShowInfoCenter.add(WardPane);
        JLabel WardLabel = new JLabel("Ward:                 ");
        WardLabel.setFont(Constant.LABEL_FONT);
        WardLabel.setForeground(Constant.my_white);
        JTextField WardField = new JTextField();
        WardField.setColumns(30);
        WardField.setText(String.valueOf(PDW.get(2)));
        WardField.setEditable(false);
        WardPane.add(WardLabel);
        WardPane.add(WardField);

        JPanel ChangeStatusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ChangeStatusPanel.setBackground(Constant.my_gray);
        JLabel ChangeStatusLabel = new JLabel("Change status:   ");
        ChangeStatusLabel.setFont(Constant.LABEL_FONT);
        ChangeStatusLabel.setForeground(Constant.my_white);
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
        ChangeFacilityPanel.setBackground(Constant.my_gray);
        JLabel ChangeFacilityLabel = new JLabel("Change facility: ");
        ChangeFacilityLabel.setFont(Constant.LABEL_FONT);
        ChangeFacilityLabel.setForeground(Constant.my_white);

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
        ChangeStatusButtonPaneSouth.setBackground(Constant.my_gray);
        EditUserPane.add(ChangeStatusButtonPaneSouth,BorderLayout.SOUTH);

        JButton ChangeStatusCancelButton = new JButton("Cancel");
        ChangeStatusCancelButton.setForeground(Constant.my_white);
        ChangeStatusCancelButton.setBackground(new Color(77,82,77));
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
        ChangeStatusButtonPaneSouth.add(ChangeStatusCancelButton);


        JButton ChangeStatusConfirmUserButton = new JButton("Confirm");
        ChangeStatusConfirmUserButton.setForeground(Constant.my_white);
        ChangeStatusConfirmUserButton.setBackground(new Color(77,82,77));

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

        ChangeStatusButtonPaneSouth.add(ChangeStatusConfirmUserButton);


    }
}
