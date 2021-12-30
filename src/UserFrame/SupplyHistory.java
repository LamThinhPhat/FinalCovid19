package UserFrame;

import ColorFont.Constant;
import table.supply_history;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class SupplyHistory extends JPanel {
    SupplyHistory(String username) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel lbHeader = new JLabel("SUPPLY HISTORY");
        lbHeader.setFont(Constant.HEADER_FONT);
        lbHeader.setForeground(Constant.my_white);
        add(lbHeader);

        JLabel lDebt = new JLabel("Current debt:"+getDB.PaymentUser.FunctionPaymentUser.GetPaymentAccount(username).getDebt());
        lDebt.setForeground(Constant.my_white);
        add(lDebt);

        setBackground(Constant.my_gray);

        JPanel ManageHistoryPanel = new JPanel(new BorderLayout());
        setSize(1000, 500);
        add(ManageHistoryPanel);

        JScrollPane ShowListCenter = new JScrollPane();
        ShowListCenter.setSize(1000, 500);
        ManageHistoryPanel.add(ShowListCenter, BorderLayout.CENTER);
        ManageHistoryPanel.setBackground(Constant.my_gray);

        JTable SupplyHistoryTable = new JTable();
        SupplyHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel SupplyDef = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        SupplyHistoryTable.setModel(SupplyDef);
        SupplyDef.addColumn("Supply ID");
        SupplyDef.addColumn("Create Date");
        SupplyDef.addColumn("Quantity");
        SupplyDef.addColumn("Price");


        ArrayList<supply_history> UDList = getDB.SupplyHistory.FunctionSupplyHistory.GetSupplyHistory(username);

        for(supply_history i : UDList)
        {
            SupplyDef.addRow(new Object[] {
                    i.getSupply_id(),i.getCreate_date(),i.getQuantity(),getDB.SupplyHistory.FunctionSupplyHistory.GetPrice(i.getSupply_id(),username,i.getSHId())
            });
        }

        ShowListCenter.setViewportView(SupplyHistoryTable);
    }
}
