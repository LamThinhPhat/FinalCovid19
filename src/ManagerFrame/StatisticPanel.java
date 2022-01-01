package ManagerFrame;

import ColorFont.Constant;

import javax.swing.*;
import java.awt.*;

public class StatisticPanel extends JPanel {
    public StatisticPanel() {
        setBackground(Constant.my_gray);
        JPanel ListStatisticContentPane = new JPanel(new FlowLayout(FlowLayout.CENTER,50,20));
        ListStatisticContentPane.setBackground(Constant.my_gray);
        add(ListStatisticContentPane);


        ListStatisticContentPane.add(new StatisticPerPersonPanel());
        ListStatisticContentPane.add(new TransferSymptomPanel());
        ListStatisticContentPane.add(new StatisticSupplyPanel());
        ListStatisticContentPane.add(new StatisticDebtPanel());

    }
}