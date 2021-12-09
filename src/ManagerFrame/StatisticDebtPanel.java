package ManagerFrame;

import ColorFont.Constant;
import table.covid_user;
import table.payment_user;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.BoxLayout.Y_AXIS;

public class StatisticDebtPanel extends JPanel {
    public StatisticDebtPanel()
    {
        setBackground(Constant.my_gray);
        setLayout(new BoxLayout(this, Y_AXIS));

        JPanel TitlePanel = new JPanel();
        TitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        TitlePanel.setBackground(Constant.my_gray);
        JLabel Title = new JLabel("Statistic debt");
        Title.setForeground(Constant.my_white);
        Title.setFont(Constant.LABEL_FONT);
        TitlePanel.add(Title);
        add(TitlePanel);

        JScrollPane ListUserDebt = new JScrollPane();
        ListUserDebt.setPreferredSize(new Dimension(300,300));
        add(ListUserDebt,BorderLayout.CENTER);

        JTable DebtTable = new JTable();
        DebtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel def = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DebtTable.setModel(def);
        def.addColumn("Username");
        def.addColumn("Balance");
        def.addColumn("debt");

        ArrayList<payment_user> list = getDB.PaymentUser.FunctionPaymentUser.GetAllPaymentAccount();

        for(payment_user i : list)
        {
            def.addRow(new Object[] {
                    i.getUsername(), i.getBalance(), i.getDebt()
            });
        }

        ListUserDebt.setViewportView(DebtTable);

        JButton RefreshButton = new JButton("Refresh");
        RefreshButton.setForeground(Constant.my_white);
        RefreshButton.setBackground(new Color(77,82,77));
        RefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def.setRowCount(0);
                ArrayList<payment_user> list = getDB.PaymentUser.FunctionPaymentUser.GetAllPaymentAccount();
                for(payment_user i : list)
                {
                    def.addRow(new Object[] {
                            i.getUsername(), i.getBalance(), i.getDebt()
                    });
                }
            }
        });
        JPanel ButtonRefreshPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ButtonRefreshPanel.add(RefreshButton);
        ButtonRefreshPanel.setBackground(Constant.my_gray);
        add(ButtonRefreshPanel);


    }
}
