package ManagerFrame;

import ColorFont.Constant;
import Login.LogInFrame;
import table.covid_user;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.concurrent.CompletionService;

import static ManagerFrame.ManagerFrame.ShowPanel;

public class ListUserPanel extends JPanel {
    public ListUserPanel()
    {
        setBackground(Constant.my_gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Constant.my_gray);

        JButton RefreshButton = new JButton("Refresh");
        RefreshButton.setForeground(Constant.my_white);
        RefreshButton.setBackground(new Color(77,82,77));
        RefreshButton.setFont(Constant.INFO_FONT);

        JTextField SearchUserNameField = new JTextField();
        SearchUserNameField.setFont(Constant.INFO_FONT);
        SearchUserNameField.setColumns(20);
        SearchUserNameField.setForeground(Color.GRAY);
        SearchUserNameField.setText("Username");

        SearchUserNameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (SearchUserNameField.getText().equals("Username")) {
                    SearchUserNameField.setText("");
                    SearchUserNameField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (SearchUserNameField.getText().isEmpty()) {
                    SearchUserNameField.setForeground(Color.GRAY);
                    SearchUserNameField.setText("Username");
                }
            }
        });

        JButton SearchUserNameButton = new JButton("Search");
        SearchUserNameButton.setForeground(Constant.my_white);
        SearchUserNameButton.setBackground(new Color(77,82,77));
        SearchUserNameButton.setFont(Constant.INFO_FONT);

        topPanel.add(SearchUserNameField);
        topPanel.add(SearchUserNameButton);
        topPanel.add(RefreshButton);
        topPanel.add(Box.createRigidArea(new Dimension(450,0)));

        //sort
        JLabel sortLabel = new JLabel("Sort by:");
        sortLabel.setFont(Constant.INFO_FONT);
        sortLabel.setForeground(Constant.my_white);

        String[] sort = {"Username","Name", "ID", "Date of birth", "City", "Status", "Facility"};
        JComboBox comboBox = new JComboBox(sort);
        comboBox.setFont(Constant.INFO_FONT);
        comboBox.setBackground(Constant.my_gray);
        comboBox.setForeground(Constant.my_white);

        topPanel.add(sortLabel);
        topPanel.add(comboBox);

        //table panel
        JPanel tablePanel = new JPanel();
        JTable CovidUserTable = new JTable();
        CovidUserTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        CovidUserTable.setFont(Constant.TABLE_FONT);
        CovidUserTable.getTableHeader().setFont(Constant.TABLE_HEADER);

        DefaultTableModel def = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        CovidUserTable.setModel(def);
        def.addColumn("Username");
        def.addColumn("Full name");
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

        JScrollPane ListCovidUserCenter = new JScrollPane(CovidUserTable);
        ListCovidUserCenter.setPreferredSize(new Dimension(1230,460));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        CovidUserTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        CovidUserTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        CovidUserTable.getColumnModel().getColumn(1).setPreferredWidth(160);
        CovidUserTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        CovidUserTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        CovidUserTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        CovidUserTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        CovidUserTable.getColumnModel().getColumn(6).setPreferredWidth(120);
        CovidUserTable.getColumnModel().getColumn(7).setPreferredWidth(150);
        CovidUserTable.getColumnModel().getColumn(8).setPreferredWidth(120);
        CovidUserTable.getColumnModel().getColumn(9).setPreferredWidth(150);

        CovidUserTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        CovidUserTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

        tablePanel.add(ListCovidUserCenter);

        //Option panel
        JPanel OptionPanel = new JPanel();
        OptionPanel.setBackground(Constant.my_gray);
        OptionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton ShowListRelated = new JButton("List related");
        ShowListRelated.setForeground(Constant.my_white);
        ShowListRelated.setBackground(new Color(77,82,77));
        ShowListRelated.setFont(Constant.INFO_FONT);

        JButton ShowHistoryUpdate = new JButton("Update History");
        ShowHistoryUpdate.setForeground(Constant.my_white);
        ShowHistoryUpdate.setBackground(new Color(77,82,77));
        ShowHistoryUpdate.setFont(Constant.INFO_FONT);

        JButton EditUser = new JButton("Change status");
        EditUser.setForeground(Constant.my_white);
        EditUser.setBackground(new Color(77,82,77));
        EditUser.setFont(Constant.INFO_FONT);

        OptionPanel.add(ShowListRelated);
        OptionPanel.add(ShowHistoryUpdate);
        OptionPanel.add(EditUser);


        add(topPanel);
        add(tablePanel);
        add(OptionPanel);

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
                comboBox.setSelectedItem("Username");
                SearchUserNameField.setForeground(Color.GRAY);
                SearchUserNameField.setText("Username");
            }
        });

        SearchUserNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!SearchUserNameField.getText().equals("Username"))
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

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //comboBox action
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sortChoose = (String) comboBox.getSelectedItem();
                if(sortChoose.equals("Facility")) {
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
                } else if(sortChoose.equals("Status")) {
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
                } else if(sortChoose.equals("City")) {
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
                } else if(sortChoose.equals("Date of birth")) {
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
                } else if(sortChoose.equals("ID")) {
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
                } else if(sortChoose.equals("Name")) {
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
                } else if(sortChoose.equals("Username")) {
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
                    ShowPanel.add(new ChangeStatusPatient(username, patient_status, LogInFrame.ManagerUsername));
                    ShowPanel.revalidate();
                    ShowPanel.repaint();
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
                    ShowPanel.repaint();
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
                    ShowPanel.repaint();
                    ShowPanel.setVisible(true);
                }
            }
        });
    }
}
