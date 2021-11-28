package ManagerFrame;

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
        setBackground(color.my_gray);
        JPanel ListUsercontentPane = new JPanel(new BorderLayout());
        add(ListUsercontentPane);

        JPanel ListUserbuttonPaneSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ListUsercontentPane.add(ListUserbuttonPaneSouth, BorderLayout.SOUTH);
        ListUserbuttonPaneSouth.setBackground(color.my_gray);

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

        JPanel WestPanelListUser = new JPanel();
        WestPanelListUser.setBackground(color.my_gray);
        WestPanelListUser.setLayout(new BoxLayout(WestPanelListUser, BoxLayout.Y_AXIS));
        ListUsercontentPane.add(WestPanelListUser,BorderLayout.EAST);

        JButton ShowListRelated = new JButton("List related");
        JButton EditUser = new JButton("Edit");

        WestPanelListUser.add(ShowListRelated);
        WestPanelListUser.add(EditUser);

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
                    ShowPanel.setVisible(false);
                    ShowPanel.removeAll();
                    ShowPanel.add(new ChangeStatusPatient(username));
                    ShowPanel.revalidate();
                    ShowPanel.setVisible(true);
                }
            }
        });

    }
}
