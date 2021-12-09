package ManagerFrame;

import ColorFont.Constant;
import getDB.Supply.FunctionSupply;
import table.supply;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static ManagerFrame.ManagerFrame.ShowPanel;

public class ListSupplyPanel extends JPanel {
    public ListSupplyPanel()
    {
        setBackground(Constant.my_gray);
        JPanel ListSupplycontentPane = new JPanel(new BorderLayout());
        add(ListSupplycontentPane);


        JPanel ListSupplybuttonPaneSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ListSupplycontentPane.add(ListSupplybuttonPaneSouth, BorderLayout.SOUTH);
        ListSupplybuttonPaneSouth.setBackground(Constant.my_gray);

        JScrollPane ListSupplyCenter = new JScrollPane();
        ListSupplyCenter.setPreferredSize(new Dimension(750, 600));
        ListSupplycontentPane.add(ListSupplyCenter, BorderLayout.CENTER);

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

        ListSupplyCenter.setViewportView(SupplyTable);

        JButton RefreshSupplyButton = new JButton("Refresh");
        RefreshSupplyButton.setForeground(Constant.my_white);
        RefreshSupplyButton.setBackground(new Color(77,82,77));

        RefreshSupplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Productdef.setRowCount(0);
                ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyInfo();
                for (supply i: SupplyList)
                {
                    Productdef.addRow(new Object[]{
                            i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                            i.getLimit_month(), i.getPrice()
                    });
                }
            }
        });

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
                        JOptionPane.showMessageDialog(ListSupplyPanel.this, "There is no supply",
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
                    JOptionPane.showMessageDialog(ListSupplyPanel.this, "Please input supply name",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ListSupplybuttonPaneSouth.add(RefreshSupplyButton);
        ListSupplybuttonPaneSouth.add(SearchSupplyField);
        ListSupplybuttonPaneSouth.add(SearchSupplyNameButton);

        JPanel EastPanelListSupply = new JPanel();
        EastPanelListSupply.setBackground(Constant.my_gray);
        EastPanelListSupply.setLayout(new BoxLayout(EastPanelListSupply, BoxLayout.Y_AXIS));
        ListSupplycontentPane.add(EastPanelListSupply,BorderLayout.EAST);

        JButton AddSupply = new JButton("Add new supply");
        AddSupply.setForeground(Constant.my_white);
        AddSupply.setBackground(new Color(77,82,77));

        JButton EditSupply = new JButton("Edit supply");
        EditSupply.setForeground(Constant.my_white);
        EditSupply.setBackground(new Color(77,82,77));

        JButton DeleteSupply = new JButton("Delete supply");
        DeleteSupply.setForeground(Constant.my_white);
        DeleteSupply.setBackground(new Color(77,82,77));


        EastPanelListSupply.add(AddSupply);
        EastPanelListSupply.add(EditSupply);
        EastPanelListSupply.add(DeleteSupply);

        DeleteSupply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = SupplyTable.getSelectedRow();
                if (row == -1)
                {
                    JOptionPane.showMessageDialog(ListSupplyPanel.this, "Please pick a supply to delete", "Error",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    String supplyid = String.valueOf(SupplyTable.getValueAt(row,0));
                    getDB.Supply.FunctionSupply.DeleteSupply(supplyid);

                    Productdef.setRowCount(0);
                    ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyInfo();
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


        AddSupply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowPanel.setVisible(false);
                ShowPanel.removeAll();
                ShowPanel.add(new AddNewSupplyPanel());
                ShowPanel.revalidate();
                ShowPanel.setVisible(true);
            }
        });

        EditSupply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = SupplyTable.getSelectedRow();
                if(row == -1)
                {
                    JOptionPane.showMessageDialog(ListSupplyPanel.this, "Please pick a supply to edit", "Error",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    String supplyId = (String) SupplyTable.getValueAt(row, 0);
                    ShowPanel.setVisible(false);
                    ShowPanel.removeAll();
                    ShowPanel.add(new EditSupplyPanel(supplyId));
                    ShowPanel.revalidate();
                    ShowPanel.setVisible(true);
                }
            }
        });

        JPanel WestPanelSortSupply = new JPanel();
        WestPanelSortSupply.setBackground(Constant.my_gray);
        WestPanelSortSupply.setLayout(new BoxLayout(WestPanelSortSupply, BoxLayout.Y_AXIS));
        ListSupplycontentPane.add(WestPanelSortSupply,BorderLayout.WEST);

        JButton SortById = new JButton("Sort ID");
        SortById.setForeground(Constant.my_white);
        SortById.setBackground(new Color(77,82,77));

        JButton SortBySupplyName = new JButton("Sort Name");
        SortBySupplyName.setForeground(Constant.my_white);
        SortBySupplyName.setBackground(new Color(77,82,77));

        JButton SortByLimitDay = new JButton("Sort Limit Day");
        SortByLimitDay.setForeground(Constant.my_white);
        SortByLimitDay.setBackground(new Color(77,82,77));

        JButton SortByLimitWeek = new JButton("Sort Limit Week");
        SortByLimitWeek.setForeground(Constant.my_white);
        SortByLimitWeek.setBackground(new Color(77,82,77));

        JButton SortByLimitMonth = new JButton("Sort Limit Month");
        SortByLimitMonth.setForeground(Constant.my_white);
        SortByLimitMonth.setBackground(new Color(77,82,77));

        JButton SortByPrice = new JButton("Sort Price");
        SortByPrice.setForeground(Constant.my_white);
        SortByPrice.setBackground(new Color(77,82,77));


        SortById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Productdef.setRowCount(0);
                ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyIDSort();
                for (supply i: SupplyList)
                {
                    Productdef.addRow(new Object[]{
                            i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                            i.getLimit_month(),i.getPrice()
                    });
                }
            }
        });

        SortBySupplyName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Productdef.setRowCount(0);
                ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplySupplyNameSort();
                for (supply i: SupplyList)
                {
                    Productdef.addRow(new Object[]{
                            i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                            i.getLimit_month(), i.getPrice()
                    });
                }
            }
        });

        SortByLimitDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Productdef.setRowCount(0);
                ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyLimitDaySort();
                for (supply i: SupplyList)
                {
                    Productdef.addRow(new Object[]{
                            i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                            i.getLimit_month(), i.getPrice()
                    });
                }
            }
        });

        SortByLimitMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Productdef.setRowCount(0);
                ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyLimitMonthSort();
                for (supply i: SupplyList)
                {
                    Productdef.addRow(new Object[]{
                            i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                            i.getLimit_month(), i.getPrice()
                    });
                }
            }
        });

        SortByLimitWeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Productdef.setRowCount(0);
                ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyLimitWeekSort();
                for (supply i: SupplyList)
                {
                    Productdef.addRow(new Object[]{
                            i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                            i.getLimit_month(), i.getPrice()
                    });
                }
            }
        });


        SortByPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Productdef.setRowCount(0);
                ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyPriceSort();
                for (supply i: SupplyList)
                {
                    Productdef.addRow(new Object[]{
                            i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                            i.getLimit_month(), i.getPrice()
                    });
                }
            }
        });




        WestPanelSortSupply.add(SortById);
        WestPanelSortSupply.add(SortBySupplyName);
        WestPanelSortSupply.add(SortByLimitDay);
        WestPanelSortSupply.add(SortByLimitWeek);
        WestPanelSortSupply.add(SortByLimitMonth);
        WestPanelSortSupply.add(SortByPrice);


    }
}