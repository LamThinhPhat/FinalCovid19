package UserFrame;

import ColorFont.Constant;
import getDB.Supply.FunctionSupply;
import table.supply;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class BuySupply extends JPanel {

    BuySupply(String username) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel HeaderPane= new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbHeader = new JLabel("SUPPLY");
        lbHeader.setFont(Constant.HEADER_FONT);
        lbHeader.setForeground(Constant.my_white);
        HeaderPane.add(lbHeader);
        HeaderPane.setBackground(Constant.my_gray);

        add(HeaderPane);

        setBackground(Constant.my_gray);

        JPanel ListSupply = new JPanel(new BorderLayout());
        setSize(1000, 500);

        JScrollPane ShowListCenter = new JScrollPane();
        ShowListCenter.setSize(800, 500);
        ListSupply.add(ShowListCenter, BorderLayout.CENTER);
        ListSupply.setBackground(Constant.my_gray);

        JTable SupplyTable = new JTable();
        SupplyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel Productdef = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        SupplyTable.getTableHeader().setFont(Constant.TABLE_HEADER);
        SupplyTable.setFont(Constant.TABLE_FONT);

        SupplyTable.setModel(Productdef);
        Productdef.addColumn("Supply ID");
        Productdef.addColumn("Supply Name");
        Productdef.addColumn("Limit day");
        Productdef.addColumn("Limit week");
        Productdef.addColumn("Limit month");
        Productdef.addColumn("Price");

        ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyInfo();
        for (supply i: SupplyList)
        {
            Productdef.addRow(new Object[]{
                    i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                    i.getLimit_month(),i.getPrice()
            });
        }

        ShowListCenter.setViewportView(SupplyTable);

        JPanel nav=new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField SearchSupplyField = new JTextField();
        SearchSupplyField.setColumns(10);
        SearchSupplyField.setFont(Constant.INFO_FONT);
        SearchSupplyField.setForeground(Color.GRAY);
        SearchSupplyField.setText("Supply name");

        SearchSupplyField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (SearchSupplyField.getText().equals("Supply name")) {
                    SearchSupplyField.setText("");
                    SearchSupplyField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (SearchSupplyField.getText().isEmpty()) {
                    SearchSupplyField.setForeground(Color.GRAY);
                    SearchSupplyField.setText("Supply name");
                }
            }
        });

        JButton SearchSupplyNameButton = new JButton("Search");
        SearchSupplyNameButton.setForeground(Constant.my_white);
        SearchSupplyNameButton.setBackground(new Color(77,82,77));
        SearchSupplyNameButton.setFont(Constant.INFO_FONT);

        String[] sort_list ={"ID","Name","Price"};
        JComboBox Sort = new JComboBox(sort_list);
        Sort.setForeground(Constant.my_white);
        Sort.setBackground(new Color(77,82,77));
        Sort.setFont(Constant.INFO_FONT);

        String[] filterValue = {"", "<50.000đ", "<100.000đ"};
        JComboBox filter = new JComboBox(filterValue);
        filter.setFont(Constant.INFO_FONT);
        filter.setForeground(Constant.my_white);
        filter.setBackground(new Color(77,82,77));

        JButton Filter_all = new JButton("Refresh");
        Filter_all.setForeground(Constant.my_white);
        Filter_all.setBackground(new Color(77,82,77));
        Filter_all.setFont(Constant.INFO_FONT);


        JLabel filterLabel = new JLabel("Sort: ");
        filterLabel.setFont(Constant.INFO_FONT);

        nav.add(SearchSupplyField);
        nav.add(SearchSupplyNameButton);
        nav.add(Filter_all);
        nav.add(Box.createRigidArea(new Dimension(50,0)));
        nav.add(filterLabel);
        nav.add(Sort);
        nav.add(filter);

        JPanel BuyPanel= new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton Buy = new JButton("Buy");
        Buy.setForeground(Constant.my_white);
        Buy.setBackground(new Color(77,82,77));
        Buy.setFont(Constant.INFO_FONT);
        BuyPanel.add(Buy);

        add(nav);
        add(ListSupply);
        add(BuyPanel);

        Buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int row = SupplyTable.getSelectedRow();
                if(row == -1)
                {
                    JOptionPane.showMessageDialog(BuySupply.this, "Please pick a supply to buy", "Error",JOptionPane.ERROR_MESSAGE);
                }else {
                    supply chosen=getDB.Supply.FunctionSupply.GetInfoSupply((String) SupplyTable.getValueAt(row, 0));
                    String supply_id=chosen.getSupply_id();
                    new BuyFrame(chosen,username ).setVisible(true);
                }
            }});


        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valueChoose = (String) filter.getSelectedItem();
                Sort.setSelectedIndex(0);
                filter.setSelectedItem(valueChoose);

                if(valueChoose.equals("<50.000đ")) {
                    ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyInfoFilter50k();
                    Productdef.setRowCount(0);
                    for (supply i: SupplyList)
                    {
                        Productdef.addRow(new Object[]{
                                i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                                i.getLimit_month(),i.getPrice()
                        });
                    }
                } else if(valueChoose.equals("<100.000đ")) {
                    ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyInfoFilter100k();
                    Productdef.setRowCount(0);
                    for (supply i: SupplyList)
                    {
                        Productdef.addRow(new Object[]{
                                i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                                i.getLimit_month(),i.getPrice()
                        });
                    }
                }
            }
        });

        Sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sortChoose = (String) Sort.getSelectedItem();
                filter.setSelectedIndex(0);
                Sort.setSelectedItem(sortChoose);

                ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyInfoSort(sortChoose);
                Productdef.setRowCount(0);
                for (supply i: SupplyList)
                {
                    Productdef.addRow(new Object[]{
                            i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                            i.getLimit_month(),i.getPrice()
                    });
                }
            }
        });

        SearchSupplyNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!SearchSupplyField.getText().equals(""))
                {
                    String supplynamesearch = SearchSupplyField.getText();

                    ArrayList<supply> SupplyListName = FunctionSupply.GetSupplyInfoByName(supplynamesearch);

                    if (SupplyListName.isEmpty())
                    {
                        JOptionPane.showMessageDialog(nav, "There is no supply",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        Productdef.setRowCount(0);
                        for (supply i: SupplyListName)
                        {
                            Productdef.addRow(new Object[]{
                                    i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                                    i.getLimit_month(), i.getPrice()
                            });
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(nav, "Please input supply name",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        Filter_all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Sort.setSelectedIndex(0);
                filter.setSelectedIndex(0);
            }});


    }
}
