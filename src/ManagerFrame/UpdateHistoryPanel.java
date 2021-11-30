package ManagerFrame;

import ColorFont.Constant;
import table.update_history;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateHistoryPanel extends JPanel {
    public UpdateHistoryPanel(String username) {
        setBackground(Constant.my_gray);
        JPanel UpdateHistoryPanel = new JPanel(new BorderLayout());
        add(UpdateHistoryPanel);

        JScrollPane ShowHistoryCenter = new JScrollPane();
        UpdateHistoryPanel.add(ShowHistoryCenter, BorderLayout.CENTER);
        UpdateHistoryPanel.setBackground(Constant.my_gray);

        JTable HistoryTable = new JTable();
        HistoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel HistoryDef = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        HistoryTable.setModel(HistoryDef);
        HistoryDef.addColumn("Username");
        HistoryDef.addColumn("Update date");
        HistoryDef.addColumn("Old status");
        HistoryDef.addColumn("Current status");
        HistoryDef.addColumn("Old facility");
        HistoryDef.addColumn("New facility");

        ArrayList<update_history> HistoryList = getDB.UpdateHistory.FunctionUpdateHistory.GetUpdateHistory(username);

        for(update_history i : HistoryList)
        {

            HistoryDef.addRow(new Object[] {
                    i.getUsername(), i.getUpdate_date(), i.getOld_status(), i.getCurrent_status(),
                    getDB.Facility.FunctionFacility.GetNameFacilityById(i.getOld_facility()),
                    getDB.Facility.FunctionFacility.GetNameFacilityById(i.getCurrent_facility())
            });
        }

        ShowHistoryCenter.setViewportView(HistoryTable);

        JButton RefreshHistoryListButton = new JButton("Refresh list");
        RefreshHistoryListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoryDef.setRowCount(0);
                ArrayList<update_history> HistoryList = getDB.UpdateHistory.FunctionUpdateHistory.GetUpdateHistory(username);

                for(update_history i : HistoryList)
                {
                    HistoryDef.addRow(new Object[] {
                            i.getUsername(), i.getUpdate_date(), i.getOld_status(),
                            i.getCurrent_status(), i.getOld_facility(), i.getCurrent_facility()
                    });
                }
            }
        });

        JButton CancelHistoryListButton = new JButton("Cancel");
        CancelHistoryListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                removeAll();
                add(new ListUserPanel());
                revalidate();
                setVisible(true);
            }
        });

        JPanel HistoryListPaneSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        UpdateHistoryPanel.add(HistoryListPaneSouth, BorderLayout.SOUTH);
        HistoryListPaneSouth.add(CancelHistoryListButton);
        HistoryListPaneSouth.add(RefreshHistoryListButton);
        HistoryListPaneSouth.setBackground(Constant.my_gray);
    }
}