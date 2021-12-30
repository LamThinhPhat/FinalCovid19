package UserFrame;

import ColorFont.Constant;
import table.update_history;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ManagedHistory extends JPanel {
    ManagedHistory(String username) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel lbHeader = new JLabel("MANAGE HISTORY");
        lbHeader.setFont(Constant.HEADER_FONT);
        lbHeader.setForeground(Constant.my_white);
        add(lbHeader);

        setBackground(Constant.my_gray);

        JPanel ManageHistoryPanel = new JPanel(new BorderLayout());
        setSize(1000, 500);
        add(ManageHistoryPanel);

        JScrollPane ShowListCenter = new JScrollPane();
        ShowListCenter.setSize(800, 500);
        ManageHistoryPanel.add(ShowListCenter, BorderLayout.CENTER);
        ManageHistoryPanel.setBackground(Constant.my_gray);

        JTable UpdateHistoryTable = new JTable();
        UpdateHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel HistoryDef = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        UpdateHistoryTable.setModel(HistoryDef);
        HistoryDef.addColumn("Update date");
        HistoryDef.addColumn("Old status");
        HistoryDef.addColumn("Current status");
        HistoryDef.addColumn("Old facility");
        HistoryDef.addColumn("Current facility");
        HistoryDef.addColumn("Manager");


        ArrayList<update_history> UDList = getDB.UpdateHistory.FunctionUpdateHistory.GetUpdateHistory(username);

        for(update_history i : UDList)
        {
            HistoryDef.addRow(new Object[] {
                    i.getUpdate_date(),i.getOld_status(),i.getCurrent_status(),i.getOld_facility(),i.getCurrent_facility(),i.getManager_username()
            });
        }

        ShowListCenter.setViewportView(UpdateHistoryTable);
    }
}
