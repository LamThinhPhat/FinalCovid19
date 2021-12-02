package AdminFrame;

import ColorFont.Constant;
import table.update_history;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ManagerHistoryFrame extends JFrame {
    public ManagerHistoryFrame(String manager_username)
    {
        setTitle(manager_username+" update history");
        ImageIcon covid_icon=null;
        try {
            covid_icon=new ImageIcon(ImageIO.read(new File("rsc/covid_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(covid_icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setBackground(Constant.my_gray);

        JPanel ListRelatedPanel = new JPanel(new BorderLayout());
        add(ListRelatedPanel);

        JScrollPane ShowListCenter = new JScrollPane();
        ListRelatedPanel.add(ShowListCenter, BorderLayout.CENTER);
        ListRelatedPanel.setBackground(Constant.my_gray);

        JTable UpdateHistoryTable = new JTable();
        UpdateHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel HistoryDef = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        UpdateHistoryTable.setModel(HistoryDef);
        HistoryDef.addColumn("Username");
        HistoryDef.addColumn("Update date");
        HistoryDef.addColumn("Old status");
        HistoryDef.addColumn("Current status");
        HistoryDef.addColumn("Old facility");
        HistoryDef.addColumn("Current facility");

        ArrayList<update_history> UDList = getDB.UpdateHistory.FunctionUpdateHistory.GetUpdateHistoryByManager(manager_username);

        for(update_history i : UDList)
        {
            HistoryDef.addRow(new Object[] {
                    i.getUsername(), i.getUpdate_date(),i.getOld_status(),i.getCurrent_status(),i.getOld_facility(),i.getCurrent_facility(),
            });
        }

        ShowListCenter.setViewportView(UpdateHistoryTable);
        this.dispose();
    }
}
