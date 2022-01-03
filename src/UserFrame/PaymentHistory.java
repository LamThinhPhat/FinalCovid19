package UserFrame;

import ColorFont.Constant;
import table.payment_history;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PaymentHistory extends JPanel{
    PaymentHistory(String username) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel lbHeader = new JLabel("PAYMENT HISTORY");
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

        JTable PaymentHistoryTable = new JTable();
        PaymentHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel HistoryDef = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        PaymentHistoryTable.getTableHeader().setFont(Constant.TABLE_HEADER);
        PaymentHistoryTable.setFont(Constant.TABLE_FONT);
        PaymentHistoryTable.setModel(HistoryDef);
        HistoryDef.addColumn("Date created  ");
        HistoryDef.addColumn("Debt pay");
        HistoryDef.addColumn("New balance");

        ArrayList<payment_history> UDList = getDB.PaymentHistory.FunctionPaymentHistory.GetUpdateHistory(username);

        for(payment_history i : UDList)
        {
            HistoryDef.addRow(new Object[] {
                    i.getDate_create(),i.getDebt_pay(),i.getNew_balance()
            });
        }

        ShowListCenter.setViewportView(PaymentHistoryTable);
    }
}
