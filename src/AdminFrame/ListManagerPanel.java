package AdminFrame;

import ColorFont.Constant;
import table.covid_user;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListManagerPanel extends JPanel {
    public ListManagerPanel()
    {
        setBackground(Constant.my_gray);

        JPanel ListUsercontentPane = new JPanel(new BorderLayout());
        add(ListUsercontentPane);

        JPanel ListUserbuttonPaneNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ListUsercontentPane.add(ListUserbuttonPaneNorth, BorderLayout.NORTH);
        ListUserbuttonPaneNorth.setBackground(Constant.my_gray);

        JScrollPane ListCovidUserCenter = new JScrollPane();
        ListCovidUserCenter.setPreferredSize(new Dimension(1100,400));
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
        def.addColumn("Ban status");


        ArrayList<covid_user> ListCovidUserTable = getDB.Account.FunctionManagerAccount.GetAllManagerInfo();
        for(covid_user i : ListCovidUserTable)
        {
            ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(i.getAddress_id());
            String FacilityName = getDB.Facility.FunctionFacility.GetNameFacilityById(i.getFacility_id());
            def.addRow(new Object[] {
                    i.getUsername(), i.getFull_name(), i.getId(), i.getDob(), i.getHouse_number(),
                    PDW.get(0), PDW.get(1),PDW.get(2), i.getPatient_status(), FacilityName,i.getBan_unban()
            });
        }

        ListCovidUserCenter.setViewportView(CovidUserTable);


        JTextField SearchUserNameField = new JTextField();
        SearchUserNameField.setColumns(15);

        JButton SearchUserNameButton = new JButton("Search by UserName");
        SearchUserNameButton.setForeground(Constant.my_white);
        SearchUserNameButton.setBackground(new Color(77,82,77));
        SearchUserNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!SearchUserNameField.getText().equals(""))
                {
                    String usernamesearch = SearchUserNameField.getText();

                    covid_user username = getDB.Account.FunctionManagerAccount.GetManagerInfoByUserName(usernamesearch);

                    if (username == null)
                    {
                        JOptionPane.showMessageDialog(ListManagerPanel.this, "There is no user",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(username.getAddress_id());
                        String FacilityName = getDB.Facility.FunctionFacility.GetNameFacilityById(username.getFacility_id());
                        def.setRowCount(0);
                        def.addRow(new Object[] {
                                username.getUsername(), username.getFull_name(), username.getId(), username.getDob(), username.getHouse_number(),
                                PDW.get(0), PDW.get(1),PDW.get(2), username.getPatient_status(), FacilityName,username.getBan_unban()
                        });
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(ListManagerPanel.this, "Please input username",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        String sort_list[]={"Sort username","Sort fullname","Sort ID","Sort DoB","Sort City","Sort Status","Sort Facility"};
        JComboBox Sort = new JComboBox(sort_list);
        Sort.setForeground(Constant.my_white);
        Sort.setBackground(new Color(77,82,77));

        Sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Sort.getSelectedIndex() != -1) {
                    ArrayList<covid_user> ListCovidUserTable = null;
                    def.setRowCount(0);
                    ListCovidUserTable = getDB.Account.FunctionManagerAccount.GetAllManagerSort(String.valueOf(Sort.getSelectedItem()));
                    for (covid_user i : ListCovidUserTable) {
                        ArrayList<String> PDW = getDB.Address.FunctionAddress.GetPDW(i.getAddress_id());
                        String FacilityName = getDB.Facility.FunctionFacility.GetNameFacilityById(i.getFacility_id());
                        def.addRow(new Object[]{
                                i.getUsername(), i.getFull_name(), i.getId(), i.getDob(), i.getHouse_number(),
                                PDW.get(0), PDW.get(1), PDW.get(2), i.getPatient_status(), FacilityName, i.getBan_unban()
                        });
                    }
                }
            }
        });

        ListUserbuttonPaneNorth.add(SearchUserNameButton);
        ListUserbuttonPaneNorth.add(SearchUserNameField);
        ListUserbuttonPaneNorth.add(Sort);

        JButton CreateManager   = new JButton("Create Manager");
        CreateManager.setForeground(Constant.my_white);
        CreateManager.setBackground(new Color(77,82,77));

        JButton BanUser = new JButton("Ban User");
        BanUser.setForeground(Constant.my_white);
        BanUser.setBackground(new Color(77,82,77));

        ListUserbuttonPaneNorth.add(CreateManager);
        ListUserbuttonPaneNorth.add(BanUser);

        BanUser.addActionListener(e -> {
            int row = CovidUserTable.getSelectedRow();
            covid_user user=null;
            if(row == -1)
            {
                JOptionPane.showMessageDialog(ListManagerPanel.this, "Please pick a manager to ban", "Error",JOptionPane.ERROR_MESSAGE);
            }else {
                String username = (String) CovidUserTable.getValueAt(row, 0);
                int ban_status = (int) CovidUserTable.getValueAt(row, 10);
                user= getDB.Account.FunctionManagerAccount.GetManagerInfoByUserName(username);
                if(user.getBan_unban()==0){
                    user.setBan_unban(1);
                    String mess= username+" has been banned";
                    JOptionPane.showMessageDialog(ListUserbuttonPaneNorth,mess);
                }
                else if (user.getBan_unban()==1)
                {
                    user.setBan_unban(0);
                    String mess= username+" has been unbanned";
                    JOptionPane.showMessageDialog(ListCovidUserCenter,mess);
                }
                else {
                    user.setBan_unban(1);
                    String mess= username+" has been banned";
                    JOptionPane.showMessageDialog(ListCovidUserCenter,mess);
                }
                getDB.Account.FunctionManagerAccount.Ban_Manager_DB(user);
                Sort.setSelectedIndex(0);
            }
            });
        CreateManager.addActionListener(e->{
            new AddManager(Sort).setVisible(true);
        });
    }


}
