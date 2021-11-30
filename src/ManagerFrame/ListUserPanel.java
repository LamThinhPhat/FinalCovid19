package ManagerFrame;

import ColorFont.Constant;
import table.covid_user;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static ManagerFrame.ManagerFrame.ShowPanel;

public class ListUserPanel extends JPanel {
    public ListUserPanel()
    {
        setBackground(Constant.my_gray);
        JPanel ListUsercontentPane = new JPanel(new BorderLayout());
        add(ListUsercontentPane);

        JPanel ListUserbuttonPaneSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ListUsercontentPane.add(ListUserbuttonPaneSouth, BorderLayout.SOUTH);
        ListUserbuttonPaneSouth.setBackground(Constant.my_gray);

        JScrollPane ListCovidUserCenter = new JScrollPane();
        ListCovidUserCenter.setPreferredSize(new Dimension(800,600));
        ListUsercontentPane.add(ListCovidUserCenter,BorderLayout.CENTER);

        JTable CovidUserTable = new JTable();
        CovidUserTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel def = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        CovidUserTable.setModel(def);
        def.addColumn("Username");
        def.addColumn("Fullname");
        def.addColumn("ID");
        def.addColumn("Date of birth");
        def.addColumn("House number");
        def.addColumn("Province");
        def.addColumn("District");
        def.addColumn("Ward");
        def.addColumn("Patient status");
        def.addColumn("Facility's name");


        ArrayList<covid_user> ListCovidUserTable = getDB.Account.FunctionAccount.GetAllCovidUserInfo();
        for(covid_user i : ListCovidUserTable)
        {
            ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(i.getAddress_id());
            String FacilityName = getDB.Facility.FunctionFacility.GetNameFacilityById(i.getFacility_id());
            def.addRow(new Object[] {
                    i.getUsername(), i.getFull_name(), i.getId(), i.getDob(), i.getHouse_number(),
                    PDW.get(0), PDW.get(1),PDW.get(2), i.getPatient_status(), FacilityName
            });
        }

        ListCovidUserCenter.setViewportView(CovidUserTable);

        JButton RefreshButton = new JButton("Refresh");
        RefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def.setRowCount(0);
                ArrayList<covid_user> ListCovidUserTable = getDB.Account.FunctionAccount.GetAllCovidUserInfo();
                for(covid_user i : ListCovidUserTable)
                {
                    ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(i.getAddress_id());
                    String FacilityName = getDB.Facility.FunctionFacility.GetNameFacilityById(i.getFacility_id());
                    def.addRow(new Object[] {
                            i.getUsername(), i.getFull_name(), i.getId(), i.getDob(), i.getHouse_number(),
                            PDW.get(0), PDW.get(1),PDW.get(2), i.getPatient_status(), FacilityName
                    });
                }
            }
        });

        JTextField SearchUserNameField = new JTextField();
        SearchUserNameField.setColumns(15);

        JButton SearchUserNameButton = new JButton("Search by UserName");

        SearchUserNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!SearchUserNameField.getText().equals(""))
                {
                    String usernamesearch = SearchUserNameField.getText();

                    covid_user username = getDB.Account.FunctionAccount.GetCovidUserInfoByUserName(usernamesearch);

                    if (username == null)
                    {
                        JOptionPane.showMessageDialog(ListUserPanel.this, "There is no user",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(username.getAddress_id());
                        String FacilityName = getDB.Facility.FunctionFacility.GetNameFacilityById(username.getFacility_id());
                        def.setRowCount(0);
                        def.addRow(new Object[] {
                                username.getUsername(), username.getFull_name(), username.getId(), username.getDob(), username.getHouse_number(),
                                PDW.get(0), PDW.get(1),PDW.get(2), username.getPatient_status(), FacilityName
                        });
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(ListUserPanel.this, "Please input username",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ListUserbuttonPaneSouth.add(RefreshButton);
        ListUserbuttonPaneSouth.add(SearchUserNameField);
        ListUserbuttonPaneSouth.add(SearchUserNameButton);

        JPanel EastPanelListUser = new JPanel();
        EastPanelListUser.setBackground(Constant.my_gray);
        EastPanelListUser.setLayout(new BoxLayout(EastPanelListUser, BoxLayout.Y_AXIS));
        ListUsercontentPane.add(EastPanelListUser,BorderLayout.EAST);

        JButton ShowListRelated = new JButton("List related");
        JButton ShowHistoryUpdate = new JButton("Update History");
        JButton EditUser = new JButton("Edit");

        EastPanelListUser.add(ShowListRelated);
        EastPanelListUser.add(ShowHistoryUpdate);
        EastPanelListUser.add(EditUser);

        JPanel WestPanelSortUser = new JPanel();
        WestPanelSortUser.setBackground(Constant.my_gray);
        WestPanelSortUser.setLayout(new BoxLayout(WestPanelSortUser, BoxLayout.Y_AXIS));
        ListUsercontentPane.add(WestPanelSortUser,BorderLayout.WEST);

        JButton SortByUserName = new JButton("Sort username");
        JButton SortByFullName = new JButton("Sort fullname");
        JButton SortByID = new JButton("Sort ID");
        JButton SortByDoB = new JButton("Sort DoB");
        JButton SortByCity = new JButton("Sort City");
        JButton SortByStatus = new JButton("Sort Status");
        JButton SortByFacility = new JButton("Sort Facility");

        WestPanelSortUser.add(SortByUserName);
        WestPanelSortUser.add(SortByFullName);
        WestPanelSortUser.add(SortByID);
        WestPanelSortUser.add(SortByDoB);
        WestPanelSortUser.add(SortByCity);
        WestPanelSortUser.add(SortByStatus);
        WestPanelSortUser.add(SortByFacility);

        SortByFacility.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def.setRowCount(0);
                ArrayList<covid_user> ListCovidUserTable = getDB.Account.FunctionAccount.GetAllCovidUserInfoFacilitySort();
                for(covid_user i : ListCovidUserTable)
                {
                    ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(i.getAddress_id());
                    String FacilityName = getDB.Facility.FunctionFacility.GetNameFacilityById(i.getFacility_id());
                    def.addRow(new Object[] {
                            i.getUsername(), i.getFull_name(), i.getId(), i.getDob(), i.getHouse_number(),
                            PDW.get(0), PDW.get(1),PDW.get(2), i.getPatient_status(), FacilityName
                    });
                }
            }
        });

        SortByStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def.setRowCount(0);
                ArrayList<covid_user> ListCovidUserTable = getDB.Account.FunctionAccount.GetAllCovidUserInfoStatusSort();
                for(covid_user i : ListCovidUserTable)
                {
                    ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(i.getAddress_id());
                    String FacilityName = getDB.Facility.FunctionFacility.GetNameFacilityById(i.getFacility_id());
                    def.addRow(new Object[] {
                            i.getUsername(), i.getFull_name(), i.getId(), i.getDob(), i.getHouse_number(),
                            PDW.get(0), PDW.get(1),PDW.get(2), i.getPatient_status(), FacilityName
                    });
                }
            }
        });

        SortByCity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def.setRowCount(0);
                ArrayList<covid_user> ListCovidUserTable = getDB.Account.FunctionAccount.GetAllCovidUserCitySort();
                for(covid_user i : ListCovidUserTable)
                {
                    ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(i.getAddress_id());
                    String FacilityName = getDB.Facility.FunctionFacility.GetNameFacilityById(i.getFacility_id());
                    def.addRow(new Object[] {
                            i.getUsername(), i.getFull_name(), i.getId(), i.getDob(), i.getHouse_number(),
                            PDW.get(0), PDW.get(1),PDW.get(2), i.getPatient_status(), FacilityName
                    });
                }
            }
        });


        SortByDoB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def.setRowCount(0);
                ArrayList<covid_user> ListCovidUserTable = getDB.Account.FunctionAccount.GetAllCovidUserDoBSort();
                for(covid_user i : ListCovidUserTable)
                {
                    ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(i.getAddress_id());
                    String FacilityName = getDB.Facility.FunctionFacility.GetNameFacilityById(i.getFacility_id());
                    def.addRow(new Object[] {
                            i.getUsername(), i.getFull_name(), i.getId(), i.getDob(), i.getHouse_number(),
                            PDW.get(0), PDW.get(1),PDW.get(2), i.getPatient_status(), FacilityName
                    });
                }
            }
        });

        SortByID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def.setRowCount(0);
                ArrayList<covid_user> ListCovidUserTable = getDB.Account.FunctionAccount.GetAllCovidUserIDSort();
                for(covid_user i : ListCovidUserTable)
                {
                    ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(i.getAddress_id());
                    String FacilityName = getDB.Facility.FunctionFacility.GetNameFacilityById(i.getFacility_id());
                    def.addRow(new Object[] {
                            i.getUsername(), i.getFull_name(), i.getId(), i.getDob(), i.getHouse_number(),
                            PDW.get(0), PDW.get(1),PDW.get(2), i.getPatient_status(), FacilityName
                    });
                }
            }
        });

        SortByFullName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def.setRowCount(0);
                ArrayList<covid_user> ListCovidUserTable = getDB.Account.FunctionAccount.GetAllCovidUserInfoFullNameSort();
                for(covid_user i : ListCovidUserTable)
                {
                    ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(i.getAddress_id());
                    String FacilityName = getDB.Facility.FunctionFacility.GetNameFacilityById(i.getFacility_id());
                    def.addRow(new Object[] {
                            i.getUsername(), i.getFull_name(), i.getId(), i.getDob(), i.getHouse_number(),
                            PDW.get(0), PDW.get(1),PDW.get(2), i.getPatient_status(), FacilityName
                    });
                }
            }
        });


        SortByUserName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def.setRowCount(0);
                ArrayList<covid_user> ListCovidUserTable = getDB.Account.FunctionAccount.GetAllCovidUserInfoUserNameSort();
                for(covid_user i : ListCovidUserTable)
                {
                    ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(i.getAddress_id());
                    String FacilityName = getDB.Facility.FunctionFacility.GetNameFacilityById(i.getFacility_id());
                    def.addRow(new Object[] {
                            i.getUsername(), i.getFull_name(), i.getId(), i.getDob(), i.getHouse_number(),
                            PDW.get(0), PDW.get(1),PDW.get(2), i.getPatient_status(), FacilityName
                    });
                }
            }
        });


        EditUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int row = CovidUserTable.getSelectedRow();
                if(row == -1)
                {
                    JOptionPane.showMessageDialog(ListUserPanel.this, "Please pick a user to edit", "Error",JOptionPane.ERROR_MESSAGE);
                }else {
                    String  username = (String) CovidUserTable.getValueAt(row, 0);
                    String  patient_status = (String) CovidUserTable.getValueAt(row, 8);
                    ShowPanel.setVisible(false);
                    ShowPanel.removeAll();
                    ShowPanel.add(new ChangeStatusPatient(username, patient_status));
                    ShowPanel.revalidate();
                    ShowPanel.setVisible(true);
                }
            }
        });

        ShowListRelated.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = CovidUserTable.getSelectedRow();
                if(row == -1)
                {
                    JOptionPane.showMessageDialog(ListUserPanel.this, "Please pick a user to show", "Error",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    String  username = (String) CovidUserTable.getValueAt(row, 0);

                    ShowPanel.setVisible(false);
                    ShowPanel.removeAll();
                    ShowPanel.add(new ListRelatedPanel(username));
                    ShowPanel.revalidate();
                    ShowPanel.setVisible(true);
                }
            }
        });

        ShowHistoryUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = CovidUserTable.getSelectedRow();
                if(row == -1)
                {
                    JOptionPane.showMessageDialog(ListUserPanel.this, "Please pick a user to show", "Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    String  username = (String) CovidUserTable.getValueAt(row, 0);

                    ShowPanel.setVisible(false);
                    ShowPanel.removeAll();
                    ShowPanel.add(new UpdateHistoryPanel(username));
                    ShowPanel.revalidate();
                    ShowPanel.setVisible(true);
                }
            }
        });
    }
}
