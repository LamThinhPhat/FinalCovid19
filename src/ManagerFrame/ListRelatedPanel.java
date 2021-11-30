package ManagerFrame;

import table.related_user;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ListRelatedPanel extends JPanel{
    public ListRelatedPanel(String username)
    {
        setBackground(color.my_gray);
        JPanel ListRelatedPanel = new JPanel(new BorderLayout());
        add(ListRelatedPanel);

        JScrollPane ShowListCenter = new JScrollPane();
        ListRelatedPanel.add(ShowListCenter, BorderLayout.CENTER);
        ListRelatedPanel.setBackground(color.my_gray);

        JTable RelatedTable = new JTable();
        RelatedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel RelatedDef = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        RelatedTable.setModel(RelatedDef);
        RelatedDef.addColumn("Username");
        RelatedDef.addColumn("Related username");

        ArrayList<related_user> relateduserlist = getDB.Account.FunctionAccount.GetRealatedUser(username);

        for(related_user i : relateduserlist)
        {
            RelatedDef.addRow(new Object[] {
                    i.getUsername(), i.getRelated_username()
            });
        }

        ShowListCenter.setViewportView(RelatedTable);

        JButton RefreshRelatedListButton = new JButton("Refresh list");
        RefreshRelatedListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RelatedDef.setRowCount(0);
                ArrayList<related_user> userlist = getDB.Account.FunctionAccount.GetRealatedUser(username);
                for(related_user i : userlist)
                {
                    RelatedDef.addRow(new Object[] {
                            i.getUsername(), i.getRelated_username()
                    });
                }
            }
        });

        JButton CancelRelatedListButton = new JButton("Cancel");
        CancelRelatedListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                removeAll();
                add(new ListUserPanel());
                revalidate();
                setVisible(true);
            }
        });

        JPanel ListRelatedPaneSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ListRelatedPanel.add(ListRelatedPaneSouth, BorderLayout.SOUTH);
        ListRelatedPaneSouth.add(CancelRelatedListButton);
        ListRelatedPaneSouth.add(RefreshRelatedListButton);
        ListRelatedPaneSouth.setBackground(color.my_gray);
    }
}