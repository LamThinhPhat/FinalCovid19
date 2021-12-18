package ManagerFrame;

import ColorFont.Constant;
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
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Constant.my_gray);
        JPanel ListRelatedPanelLeft = new JPanel(new BorderLayout());
        JPanel ListRelatedPanelRight = new JPanel(new BorderLayout());
        add(ListRelatedPanelLeft);
        add(ListRelatedPanelRight);

        JScrollPane ShowListLeft = new JScrollPane();
        ListRelatedPanelLeft.add(ShowListLeft, BorderLayout.CENTER);
        ListRelatedPanelLeft.setBackground(Constant.my_gray);

        JScrollPane ShowListRight = new JScrollPane();
        ListRelatedPanelRight.add(ShowListRight, BorderLayout.CENTER);
        ListRelatedPanelRight.setBackground(Constant.my_gray);

        JTable RelatedTableLeft = new JTable();
        RelatedTableLeft.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel RelatedDefLeft = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        RelatedTableLeft.setModel(RelatedDefLeft);
        RelatedDefLeft.addColumn("Username");



        JTable RelatedTableRight = new JTable();
        RelatedTableRight.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel RelatedDefRight = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        RelatedTableRight.setModel(RelatedDefRight);
        RelatedDefRight.addColumn("Username");
        RelatedDefRight.addColumn("Related username");

        ArrayList<related_user> relateduserlist = getDB.Account.FunctionAccount.GetRealatedUser(username);

        ArrayList<String> userlisttoadd = getDB.Account.FunctionAccount.GetUserNameNotInRelated();

        for(String i : userlisttoadd)
        {
            if (!i.equals(username))
            {
                RelatedDefLeft.addRow(new Object[] {
                        i
                });
            }
        }

        for(related_user i : relateduserlist)
        {
            RelatedDefRight.addRow(new Object[] {
                    i.getUsername(), i.getRelated_username()
            });
        }

        ShowListRight.setViewportView(RelatedTableRight);
        ShowListLeft.setViewportView(RelatedTableLeft);

        JButton DeleteRelatedListButton = new JButton("Delete");
        DeleteRelatedListButton.setForeground(Constant.my_white);
        DeleteRelatedListButton.setBackground(new Color(77,82,77));

        DeleteRelatedListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = RelatedTableRight.getSelectedRow();
                if (row == -1)
                {
                    JOptionPane.showMessageDialog(ListRelatedPanel.this, "Please pick a row to delete", "Error",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    String related_username = String.valueOf(RelatedDefRight.getValueAt(row,1));
                    getDB.Account.FunctionAccount.DeleteRelated(username, related_username);

                    RelatedDefLeft.setRowCount(0);
                    ArrayList<String> userlisttoadd = getDB.Account.FunctionAccount.GetUserNameNotInRelated();
                    for(String i : userlisttoadd)
                    {
                        if (!i.equals(username))
                        {
                            RelatedDefLeft.addRow(new Object[] {
                                    i
                            });
                        }
                    }

                    RelatedDefRight.setRowCount(0);
                    ArrayList<related_user> userlist = getDB.Account.FunctionAccount.GetRealatedUser(username);
                    for(related_user i : userlist)
                    {
                        RelatedDefRight.addRow(new Object[] {
                                i.getUsername(), i.getRelated_username()
                        });
                    }
                }
            }
        });

        JButton CancelRelatedListButton = new JButton("Cancel");
        CancelRelatedListButton.setForeground(Constant.my_white);
        CancelRelatedListButton.setBackground(new Color(77,82,77));

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
        ListRelatedPanelRight.add(ListRelatedPaneSouth, BorderLayout.SOUTH);
        ListRelatedPaneSouth.add(CancelRelatedListButton);
        ListRelatedPaneSouth.add(DeleteRelatedListButton);
        ListRelatedPaneSouth.setBackground(Constant.my_gray);

        JButton AddRelatedButton = new JButton("Add related");
        AddRelatedButton.setForeground(Constant.my_white);
        AddRelatedButton.setBackground(new Color(77,82,77));

        AddRelatedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = RelatedTableLeft.getSelectedRow();
                if (row == -1)
                {
                    JOptionPane.showMessageDialog(ListRelatedPanel.this, "Please pick a user to add", "Error",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    String related_username = String.valueOf(RelatedTableLeft.getValueAt(row,0));
                    getDB.Account.FunctionAccount.AddRelatedAccount(username, related_username);

                    RelatedDefLeft.setRowCount(0);
                    ArrayList<String> userlisttoadd = getDB.Account.FunctionAccount.GetUserNameNotInRelated();
                    for(String i : userlisttoadd)
                    {
                        if (!i.equals(username))
                        {
                            RelatedDefLeft.addRow(new Object[] {
                                    i
                            });
                        }
                    }

                    RelatedDefRight.setRowCount(0);
                    ArrayList<related_user> userlist = getDB.Account.FunctionAccount.GetRealatedUser(username);
                    for(related_user i : userlist)
                    {
                        RelatedDefRight.addRow(new Object[] {
                                i.getUsername(), i.getRelated_username()
                        });
                    }


                }
            }
        });
        JPanel ListRelatedPaneLeftSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ListRelatedPaneLeftSouth.setBackground(Constant.my_gray);
        ListRelatedPanelLeft.add(ListRelatedPaneLeftSouth,BorderLayout.SOUTH);
        ListRelatedPaneLeftSouth.add(AddRelatedButton);
    }
}