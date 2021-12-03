package ManagerFrame;

import ColorFont.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static javax.swing.BoxLayout.Y_AXIS;


public class StatisticPerPersonPanel extends JPanel {
    public StatisticPerPersonPanel()
    {
        setBackground(Constant.my_gray);
        setLayout(new BoxLayout(this,Y_AXIS));

        JPanel TitlePanel = new JPanel();
        TitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        TitlePanel.setBackground(Constant.my_gray);
        JLabel Title = new JLabel("Total Status");
        Title.setForeground(Constant.my_white);
        Title.setFont(Constant.LABEL_FONT);
        TitlePanel.add(Title);
        add(TitlePanel);

        JComboBox DateChoosePerPerson = new JComboBox();
        add(DateChoosePerPerson);
        Set<Date> GetDateChooser = getDB.Statistic.FunctionStatistic.GetAllDate("update_history");
        DateChoosePerPerson.addItem("Now");
        for (Date i : GetDateChooser)
        {
            DateChoosePerPerson.addItem(i);
        }

        JPanel ShowF0Panel = new JPanel();
        ShowF0Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        ShowF0Panel.setBackground(Constant.my_gray);
        JLabel F0 = new JLabel("F0: ");
        JLabel F0Count = new JLabel();
        F0.setForeground(Constant.my_white);
        F0.setFont(Constant.LABEL_FONT);
        F0Count.setForeground(Constant.my_white);
        F0Count.setFont(Constant.LABEL_FONT);
        ShowF0Panel.add(F0);
        ShowF0Panel.add(F0Count);

        JPanel ShowF1Panel = new JPanel();
        ShowF1Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        ShowF1Panel.setBackground(Constant.my_gray);
        JLabel F1 = new JLabel("F1: ");
        JLabel F1Count = new JLabel();
        F1.setForeground(Constant.my_white);
        F1.setFont(Constant.LABEL_FONT);
        F1Count.setForeground(Constant.my_white);
        F1Count.setFont(Constant.LABEL_FONT);
        ShowF1Panel.add(F1);
        ShowF1Panel.add(F1Count);


        JPanel ShowF2Panel = new JPanel();
        ShowF2Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        ShowF2Panel.setBackground(Constant.my_gray);
        JLabel F2 = new JLabel("F2: ");
        JLabel F2Count = new JLabel();
        F2.setForeground(Constant.my_white);
        F2.setFont(Constant.LABEL_FONT);
        F2Count.setForeground(Constant.my_white);
        F2Count.setFont(Constant.LABEL_FONT);
        ShowF2Panel.add(F2);
        ShowF2Panel.add(F2Count);

        JPanel ShowF3Panel = new JPanel();
        ShowF3Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        ShowF3Panel.setBackground(Constant.my_gray);
        JLabel F3 = new JLabel("F3: ");
        JLabel F3Count = new JLabel();
        F3.setForeground(Constant.my_white);
        F3.setFont(Constant.LABEL_FONT);
        F3Count.setForeground(Constant.my_white);
        F3Count.setFont(Constant.LABEL_FONT);
        ShowF3Panel.add(F3);
        ShowF3Panel.add(F3Count);

        JPanel ShowNIPanel = new JPanel();
        ShowNIPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        ShowNIPanel.setBackground(Constant.my_gray);
        JLabel NI = new JLabel("NI: ");
        JLabel NICount = new JLabel();
        NI.setForeground(Constant.my_white);
        NI.setFont(Constant.LABEL_FONT);
        NICount.setForeground(Constant.my_white);
        NICount.setFont(Constant.LABEL_FONT);
        ShowNIPanel.add(NI);
        ShowNIPanel.add(NICount);

        JPanel ShowRVPanel = new JPanel();
        ShowRVPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        ShowRVPanel.setBackground(Constant.my_gray);
        JLabel RV = new JLabel("RV: ");
        JLabel RVCount = new JLabel();
        RV.setForeground(Constant.my_white);
        RV.setFont(Constant.LABEL_FONT);
        RVCount.setForeground(Constant.my_white);
        RVCount.setFont(Constant.LABEL_FONT);
        ShowRVPanel.add(RV);
        ShowRVPanel.add(RVCount);

        add(ShowF0Panel);
        add(ShowF1Panel);
        add(ShowF2Panel);
        add(ShowF3Panel);
        add(ShowRVPanel);
        add(ShowNIPanel);

        Map<String,Integer> TotalFirst = getDB.Statistic.FunctionStatistic.GetPerPersonNow();
        F0Count.setText(String.valueOf(TotalFirst.get("F0")));
        F1Count.setText(String.valueOf(TotalFirst.get("F1")));
        F2Count.setText(String.valueOf(TotalFirst.get("F2")));
        F3Count.setText(String.valueOf(TotalFirst.get("F3")));
        RVCount.setText(String.valueOf(TotalFirst.get("RV")));
        NICount.setText(String.valueOf(TotalFirst.get("NI")));

        DateChoosePerPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!DateChoosePerPerson.getSelectedItem().equals("Now"))
                {
                    Map<String,Integer> Total = getDB.Statistic.FunctionStatistic.GetPerPerson((Date) DateChoosePerPerson.getSelectedItem());
                    F0Count.setText(String.valueOf(Total.get("F0")));
                    F1Count.setText(String.valueOf(Total.get("F1")));
                    F2Count.setText(String.valueOf(Total.get("F2")));
                    F3Count.setText(String.valueOf(Total.get("F3")));
                    RVCount.setText(String.valueOf(Total.get("RV")));
                    NICount.setText(String.valueOf(Total.get("NI")));
                }
                else
                {
                    Map<String,Integer> Total = getDB.Statistic.FunctionStatistic.GetPerPersonNow();
                    F0Count.setText(String.valueOf(Total.get("F0")));
                    F1Count.setText(String.valueOf(Total.get("F1")));
                    F2Count.setText(String.valueOf(Total.get("F2")));
                    F3Count.setText(String.valueOf(Total.get("F3")));
                    RVCount.setText(String.valueOf(Total.get("RV")));
                    NICount.setText(String.valueOf(Total.get("NI")));
                }

            }
        });

    }
}
