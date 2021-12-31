package ManagerFrame;


import ColorFont.Constant;
import Login.LogInFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ManagerFrame extends JFrame {
    static public JPanel ShowPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManagerFrame frame = new ManagerFrame(LogInFrame.ManagerUsername);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public ManagerFrame(String ManagerUsername)
    {
        setTitle("Manager - Covid Management System");
        ImageIcon covid_icon=null;
        try {
            covid_icon=new ImageIcon(ImageIO.read(new File("rsc/covid_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(covid_icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        contentPane.setBackground(Constant.my_gray);

        JPanel ButtonChangePane = new JPanel();

        ButtonChangePane.setLayout(new GridLayout(10,1));


        JButton AddNewUserButton = new JButton("Add new user");
        AddNewUserButton.setForeground(Constant.my_white);
        AddNewUserButton.setBackground(new Color(77,82,77));
        AddNewUserButton.setFont(Constant.INFO_FONT);

        JButton ChangeListUserButton = new JButton("List User");
        ChangeListUserButton.setForeground(Constant.my_white);
        ChangeListUserButton.setBackground(new Color(77,82,77));
        ChangeListUserButton.setFont(Constant.INFO_FONT);

        JButton ChangeListSupplyButton = new JButton("List Supply");
        ChangeListSupplyButton.setForeground(Constant.my_white);
        ChangeListSupplyButton.setBackground(new Color(77,82,77));
        ChangeListSupplyButton.setFont(Constant.INFO_FONT);

        JButton ChangeStatisticButton = new JButton("Statistic");
        ChangeStatisticButton.setForeground(Constant.my_white);
        ChangeStatisticButton.setBackground(new Color(77,82,77));
        ChangeStatisticButton.setFont(Constant.INFO_FONT);

        JButton LogOutButton = new JButton("Log Out");
        LogOutButton.setForeground(Constant.my_white);
        LogOutButton.setBackground(new Color(77,82,77));
        LogOutButton.setFont(Constant.INFO_FONT);

        ButtonChangePane.add(AddNewUserButton);
        ButtonChangePane.add(ChangeListUserButton);
        ButtonChangePane.add(ChangeListSupplyButton);
        ButtonChangePane.add(ChangeStatisticButton);
        ButtonChangePane.add(LogOutButton);
        ButtonChangePane.add(Box.createRigidArea(new Dimension(0,0)));
        ButtonChangePane.add(Box.createRigidArea(new Dimension(0,0)));
        ButtonChangePane.add(Box.createRigidArea(new Dimension(0,0)));
        ButtonChangePane.add(Box.createRigidArea(new Dimension(0,0)));
        ButtonChangePane.add(Box.createRigidArea(new Dimension(0,0)));
        ButtonChangePane.setBackground(Constant.my_gray);

        ShowPanel = new ListUserPanel();
        ShowPanel.setPreferredSize(new Dimension(1250,600));


        setContentPane(contentPane);
        contentPane.add(ButtonChangePane);
        contentPane.add(ShowPanel);

        AddNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUserByManager().setVisible(true);
            }
        });

        ChangeListUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowPanel.setVisible(false);
                ShowPanel.removeAll();
                ShowPanel.add(new ListUserPanel());
                ShowPanel.revalidate();
                ShowPanel.setVisible(true);
            }
        });

        ChangeListSupplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowPanel.setVisible(false);
                ShowPanel.removeAll();
                ShowPanel.add(new ListSupplyPanel());
                ShowPanel.revalidate();
                ShowPanel.setVisible(true);
            }
        });

        ChangeStatisticButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowPanel.setVisible(false);
                ShowPanel.removeAll();
                ShowPanel.add(new StatisticPanel());
                ShowPanel.revalidate();
                ShowPanel.setVisible(true);
            }
        });

        LogOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogInFrame().setVisible(true);
                ManagerFrame.this.dispose();
            }
        });

    }
}

