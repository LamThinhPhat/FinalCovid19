package ManagerFrame;

import ColorFont.Constant;
import table.covid_user;
import table.supply;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        ListSupplyCenter.setPreferredSize(new Dimension(800, 600));
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
        Productdef.addColumn("Limit Period");
        Productdef.addColumn("Limit per person");
        Productdef.addColumn("Price");

        ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyInfo();
        for (supply i: SupplyList)
        {
            Productdef.addRow(new Object[]{
                    i.getSupply_id(), i.getSupply_name(), i.getLimit_period(), i.getLimit_per_person(),i.getPrice()
            });
        }

        ListSupplyCenter.setViewportView(SupplyTable);

        JButton RefreshSupplyButton = new JButton("Refresh");
        RefreshSupplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Productdef.setRowCount(0);
                ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyInfo();
                for (supply i: SupplyList)
                {
                    Productdef.addRow(new Object[]{
                            i.getSupply_id(), i.getSupply_name(), i.getLimit_period(), i.getLimit_per_person(),i.getPrice()
                    });
                }
            }
        });

        JTextField SearchSupplyField = new JTextField();
        SearchSupplyField.setColumns(15);

        JButton SearchSupplyNameButton = new JButton("Search by supply");

        SearchSupplyNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!SearchSupplyField.getText().equals(""))
                {
                    String supplynamesearch = SearchSupplyField.getText();

                    ArrayList<supply> SupplyListName = getDB.Supply.FunctionSupply.GetSupplyInfoByName(supplynamesearch);

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
                                    i.getSupply_id(), i.getSupply_name(), i.getLimit_period(), i.getLimit_per_person(),i.getPrice()
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
        JButton EditSupply = new JButton("Edit supply");
        JButton DeleteSupply = new JButton("Delete supply");

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
                                i.getSupply_id(), i.getSupply_name(), i.getLimit_period(), i.getLimit_per_person(),i.getPrice()
                        });
                    }
                }
            }
        });


    }
}