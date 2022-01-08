package ManagerFrame;

import ColorFont.Constant;
import getDB.Supply.FunctionSupply;
import table.supply;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import static ManagerFrame.ManagerFrame.ShowPanel;

public class ListSupplyPanel extends JPanel {
    public ListSupplyPanel()
    {
        setBackground(Constant.my_gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel tablePanel = new JPanel();
        JTable SupplyTable = new JTable();
        SupplyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel Productdef = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane ListSupplyCenter = new JScrollPane(SupplyTable);
        ListSupplyCenter.setPreferredSize(new Dimension(1230, 460));

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

        SupplyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        SupplyTable.getTableHeader().setFont(Constant.TABLE_HEADER);
        SupplyTable.setFont(Constant.TABLE_FONT);

        SupplyTable.getColumnModel().getColumn(0).setPreferredWidth(205);
        SupplyTable.getColumnModel().getColumn(1).setPreferredWidth(205);
        SupplyTable.getColumnModel().getColumn(2).setPreferredWidth(205);
        SupplyTable.getColumnModel().getColumn(3).setPreferredWidth(205);
        SupplyTable.getColumnModel().getColumn(4).setPreferredWidth(205);
        SupplyTable.getColumnModel().getColumn(5).setPreferredWidth(205);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        SupplyTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        SupplyTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        SupplyTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        tablePanel.add(ListSupplyCenter);


        JPanel topPanel = new JPanel();
        topPanel.setBackground(Constant.my_gray);
        topPanel.setForeground(Constant.my_white);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton RefreshSupplyButton = new JButton("Refresh");
        RefreshSupplyButton.setForeground(Constant.my_white);
        RefreshSupplyButton.setBackground(new Color(77,82,77));
        RefreshSupplyButton.setFont(Constant.INFO_FONT);

        JTextField SearchSupplyField = new JTextField();
        SearchSupplyField.setFont(Constant.INFO_FONT);
        SearchSupplyField.setColumns(20);
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

        topPanel.add(SearchSupplyField);
        topPanel.add(SearchSupplyNameButton);
        topPanel.add(RefreshSupplyButton);
        topPanel.add(Box.createRigidArea(new Dimension(450,0)));

        JLabel sortLabel = new JLabel("Sort by:");
        sortLabel.setFont(Constant.INFO_FONT);
        sortLabel.setForeground(Constant.my_white);

        String[] sort = {"ID", "Name", "Limit day", "Limit week", "Limit month", "Price"};
        JComboBox comboBox = new JComboBox(sort);
        comboBox.setFont(Constant.INFO_FONT);
        comboBox.setBackground(Constant.my_gray);
        comboBox.setForeground(Constant.my_white);

        topPanel.add(sortLabel);
        topPanel.add(comboBox);

        //Option panel
        JPanel OptionPanel = new JPanel();
        OptionPanel.setBackground(Constant.my_gray);
        OptionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton AddSupply = new JButton("Add new supply");
        AddSupply.setForeground(Constant.my_white);
        AddSupply.setBackground(new Color(77,82,77));
        AddSupply.setFont(Constant.INFO_FONT);

        JButton EditSupply = new JButton("Edit supply");
        EditSupply.setForeground(Constant.my_white);
        EditSupply.setBackground(new Color(77,82,77));
        EditSupply.setFont(Constant.INFO_FONT);

        JButton DeleteSupply = new JButton("Delete supply");
        DeleteSupply.setForeground(Constant.my_white);
        DeleteSupply.setBackground(new Color(77,82,77));
        DeleteSupply.setFont(Constant.INFO_FONT);

        OptionPanel.add(AddSupply);
        OptionPanel.add(EditSupply);
        OptionPanel.add(DeleteSupply);

        add(topPanel);
        add(tablePanel);
        add(OptionPanel);

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
                comboBox.setSelectedItem("ID");
                SearchSupplyField.setForeground(Color.GRAY);
                SearchSupplyField.setText("Supply name");
            }
        });

        SearchSupplyNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!SearchSupplyField.getText().equals("Supply name"))
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
                ShowPanel.repaint();
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
                    ShowPanel.repaint();
                    ShowPanel.setVisible(true);
                }
            }
        });

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sortChoose = (String) comboBox.getSelectedItem();
                if(sortChoose.equals("ID")) {
                    Productdef.setRowCount(0);
                    ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyIDSort();
                    for (supply i: SupplyList)
                    {
                        Productdef.addRow(new Object[]{
                                i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                                i.getLimit_month(),i.getPrice()
                        });
                    }
                } else if(sortChoose.equals("Name")) {
                    Productdef.setRowCount(0);
                    ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplySupplyNameSort();
                    for (supply i: SupplyList)
                    {
                        Productdef.addRow(new Object[]{
                                i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                                i.getLimit_month(), i.getPrice()
                        });
                    }
                } else if(sortChoose.equals("Limit day")) {
                    Productdef.setRowCount(0);
                    ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyLimitDaySort();
                    for (supply i: SupplyList)
                    {
                        Productdef.addRow(new Object[]{
                                i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                                i.getLimit_month(), i.getPrice()
                        });
                    }
                } else if(sortChoose.equals("Limit month")) {
                    Productdef.setRowCount(0);
                    ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyLimitMonthSort();
                    for (supply i: SupplyList)
                    {
                        Productdef.addRow(new Object[]{
                                i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                                i.getLimit_month(), i.getPrice()
                        });
                    }
                } else if(sortChoose.equals("Limit week")) {
                    Productdef.setRowCount(0);
                    ArrayList<supply> SupplyList = getDB.Supply.FunctionSupply.GetAllSupplyLimitWeekSort();
                    for (supply i: SupplyList)
                    {
                        Productdef.addRow(new Object[]{
                                i.getSupply_id(), i.getSupply_name(), i.getLimit_day(), i.getLimit_week(),
                                i.getLimit_month(), i.getPrice()
                        });
                    }
                } else if(sortChoose.equals("Price")) {
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
            }
        });
    }
}