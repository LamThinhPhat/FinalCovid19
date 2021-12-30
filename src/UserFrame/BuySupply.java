package UserFrame;

import ColorFont.Constant;
import getDB.Supply.FunctionSupply;
import table.supply;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        SearchSupplyField.setColumns(15);

        JButton SearchSupplyNameButton = new JButton("Search by supply");
        SearchSupplyNameButton.setForeground(Constant.my_white);
        SearchSupplyNameButton.setBackground(new Color(77,82,77));
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

        String[] sort_list ={"Sort id","Sort name","Sort price"};
        JComboBox<String> Sort = new JComboBox<>(sort_list);
        Sort.setForeground(Constant.my_white);
        Sort.setBackground(new Color(77,82,77));

        Sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Sort.getSelectedIndex() != -1) {
                    ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyInfoSort(String.valueOf(Sort.getSelectedItem()));
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


        JButton Filter_all = new JButton("Filter all");
        Filter_all.setForeground(Constant.my_white);
        Filter_all.setBackground(new Color(77,82,77));
        Filter_all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Sort.setSelectedIndex(1);
            }});
        JButton Filter_50k = new JButton("<50000đ");
        Filter_50k.setForeground(Constant.my_white);
        Filter_50k.setBackground(new Color(77,82,77));
        Filter_50k.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyInfoFilter50k();
                Productdef.setRowCount(0);
                for (supply i: SupplyList)
                {
                    Productdef.addRow(new Object[]{
                            i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                            i.getLimit_month(),i.getPrice()
                    });
                }
            }});

        JButton Filter_100k = new JButton("<100000đ");
        Filter_100k.setForeground(Constant.my_white);
        Filter_100k.setBackground(new Color(77,82,77));
        Filter_100k.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyInfoFilter100k();
                Productdef.setRowCount(0);
                for (supply i: SupplyList)
                {
                    Productdef.addRow(new Object[]{
                            i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                            i.getLimit_month(),i.getPrice()
                    });
                }
            }});


        nav.add(Sort);
        nav.add(SearchSupplyField);
        nav.add(SearchSupplyNameButton);
        nav.add(Filter_all);
        nav.add(Filter_50k);
        nav.add(Filter_100k);

        JPanel BuyPanel= new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton Buy = new JButton("Buy");
        Buy.setForeground(Constant.my_white);
        Buy.setBackground(new Color(77,82,77));
        BuyPanel.add(Buy);

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

        add(nav);
        add(ListSupply);
        add(BuyPanel);

    }
}
