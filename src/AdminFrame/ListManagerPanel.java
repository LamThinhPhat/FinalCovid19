package AdminFrame;

import ColorFont.Constant;
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

public class ListManagerPanel extends JPanel {
    public ListManagerPanel()
    {
        setBackground(Constant.my_gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel ListUsercontentPane = new JPanel(new BorderLayout());
        add(ListUsercontentPane);

        JPanel ListUserbuttonPaneNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ListUsercontentPane.add(ListUserbuttonPaneNorth, BorderLayout.NORTH);
        ListUserbuttonPaneNorth.setBackground(Constant.my_gray);

        JScrollPane ListCovidUserCenter = new JScrollPane();
        ListCovidUserCenter.setPreferredSize(new Dimension(1150,380));
        ListUsercontentPane.add(ListCovidUserCenter,BorderLayout.CENTER);

        JTable CovidUserTable = new JTable();
        CovidUserTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        CovidUserTable.getTableHeader().setFont(Constant.TABLE_HEADER);
        CovidUserTable.setFont(Constant.TABLE_FONT);
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
        CovidUserTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        CovidUserTable.getColumnModel().getColumn(0).setPreferredWidth(130);
        CovidUserTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        CovidUserTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        CovidUserTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        CovidUserTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        CovidUserTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        CovidUserTable.getColumnModel().getColumn(6).setPreferredWidth(150);
        CovidUserTable.getColumnModel().getColumn(7).setPreferredWidth(150);
        CovidUserTable.getColumnModel().getColumn(8).setPreferredWidth(150);
        CovidUserTable.getColumnModel().getColumn(9).setPreferredWidth(150);
        CovidUserTable.getColumnModel().getColumn(10).setPreferredWidth(120);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        CovidUserTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        CovidUserTable.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);


        JTextField SearchUserNameField = new JTextField();
        SearchUserNameField.setColumns(15);
        SearchUserNameField.setFont(Constant.INFO_FONT);
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

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setForeground(Constant.my_white);
        refreshBtn.setBackground(new Color(77,82,77));
        refreshBtn.setFont(Constant.INFO_FONT);

        String sort_list[]={"Username","Full name","ID","DoB","City","Status","Facility"};
        JComboBox Sort = new JComboBox(sort_list);
        Sort.setForeground(Constant.my_white);
        Sort.setBackground(new Color(77,82,77));
        Sort.setFont(Constant.INFO_FONT);

        JLabel sortLabel = new JLabel("Sort by:");
        sortLabel.setFont(Constant.LABEL_FONT);
        sortLabel.setForeground(Constant.my_white);

        ListUserbuttonPaneNorth.add(SearchUserNameField);
        ListUserbuttonPaneNorth.add(SearchUserNameButton);
        ListUserbuttonPaneNorth.add(refreshBtn);

        ListUserbuttonPaneNorth.add(Box.createRigidArea(new Dimension(520,0)));
        ListUserbuttonPaneNorth.add(sortLabel);
        ListUserbuttonPaneNorth.add(Sort);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Constant.my_gray);
        JButton CreateManager   = new JButton("Create Manager");
        CreateManager.setForeground(Constant.my_white);
        CreateManager.setBackground(new Color(77,82,77));
        CreateManager.setFont(Constant.INFO_FONT);

        JButton BanUser = new JButton("Ban User");
        BanUser.setForeground(Constant.my_white);
        BanUser.setBackground(new Color(77,82,77));
        BanUser.setFont(Constant.INFO_FONT);

        JButton ManagerHistory = new JButton("Manager History");
        ManagerHistory.setForeground(Constant.my_white);
        ManagerHistory.setBackground(new Color(77,82,77));
        ManagerHistory.setFont(Constant.INFO_FONT);

        bottomPanel.add(CreateManager);
        bottomPanel.add(BanUser);
        bottomPanel.add(ManagerHistory);
        add(bottomPanel);

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

        ManagerHistory.addActionListener(e->{
            int row = CovidUserTable.getSelectedRow();
            if(row == -1)
            {
                JOptionPane.showMessageDialog(ListManagerPanel.this, "Please pick a manager to ban", "Error",JOptionPane.ERROR_MESSAGE);
            }else {
                new ManagerHistoryFrame((String) CovidUserTable.getValueAt(row, 0)).setVisible(true);
            }
        });

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

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def.setRowCount(0);
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
                Sort.setSelectedItem("Username");
            }
        });

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
    }
}
