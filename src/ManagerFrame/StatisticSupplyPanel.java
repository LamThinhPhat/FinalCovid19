package ManagerFrame;

import ColorFont.Constant;
import table.supply;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Map;
import java.util.Set;

import static javax.swing.BoxLayout.Y_AXIS;

public class StatisticSupplyPanel extends JPanel{
    public StatisticSupplyPanel(){
            setBackground(Constant.my_gray);
            setLayout(new BoxLayout(this, Y_AXIS));

            JPanel TitlePanel = new JPanel();
            TitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            TitlePanel.setBackground(Constant.my_gray);
            JLabel Title = new JLabel("Total Supply");
            Title.setForeground(Constant.my_white);
            Title.setFont(Constant.LABEL_FONT);
            TitlePanel.add(Title);
            add(TitlePanel);

            JComboBox DateChoose = new JComboBox();
            add(DateChoose);
            Set<Date> GetDateChooser = getDB.Statistic.FunctionStatistic.GetAllDateSupply();
            for (Date i : GetDateChooser) {
                DateChoose.addItem(i);
            }

            Map<String, Integer> Transfer = getDB.Statistic.FunctionStatistic.GetTotalSupply((Date) DateChoose.getSelectedItem());
            Component[] AllLabel;
            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, Y_AXIS));
            row.setBackground(Constant.my_gray);
            for (Map.Entry<String, Integer> i : Transfer.entrySet()) {
                JPanel lines = new JPanel(new FlowLayout(FlowLayout.LEFT));
                lines.setBackground(Constant.my_gray);
                supply newsupply = getDB.Supply.FunctionSupply.GetInfoSupply(i.getKey());
                JLabel status = new JLabel(newsupply.getSupply_name() + ": ");
                JLabel statuscount = new JLabel(String.valueOf(i.getValue()));
                status.setForeground(Constant.my_white);
                status.setFont(Constant.LABEL_FONT);
                statuscount.setForeground(Constant.my_white);
                statuscount.setFont(Constant.LABEL_FONT);
                lines.add(status);
                lines.add(statuscount);
                row.add(lines);
            }
            DateChoose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    row.removeAll();
                    row.revalidate();
                    row.repaint();
                    Map<String,Integer> Total = getDB.Statistic.FunctionStatistic.GetTotalSupply((Date) DateChoose.getSelectedItem());

                    for (Map.Entry<String, Integer> i : Total.entrySet()) {
                        JPanel lines = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        lines.setBackground(Constant.my_gray);
                        supply newsupply = getDB.Supply.FunctionSupply.GetInfoSupply(i.getKey());
                        JLabel status = new JLabel(newsupply.getSupply_name() + ": ");
                        JLabel statuscount = new JLabel(String.valueOf(i.getValue()));
                        status.setForeground(Constant.my_white);
                        status.setFont(Constant.LABEL_FONT);
                        statuscount.setForeground(Constant.my_white);
                        statuscount.setFont(Constant.LABEL_FONT);
                        lines.add(status);
                        lines.add(statuscount);
                        row.add(lines);
                    }

                }
            });

            add(row);
    }
}
