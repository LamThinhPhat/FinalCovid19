package ManagerFrame;


import ColorFont.Constant;

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
                    ManagerFrame frame = new ManagerFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public ManagerFrame()
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
        setSize(1024, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel ButtonChangePane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton AddNewUserButton = new JButton("Add new user");
        JButton ChangeListUserButton = new JButton("List User");
        JButton ChangeListProductButton = new JButton("List Supply");
        ButtonChangePane.add(AddNewUserButton);
        ButtonChangePane.add(ChangeListUserButton);
        ButtonChangePane.add(ChangeListProductButton);
        ButtonChangePane.setBackground(Constant.my_gray);


        setContentPane(contentPane);
        contentPane.add(ButtonChangePane);
        ShowPanel = new ListSupplyPanel();
        contentPane.add(ShowPanel);

        AddNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUserByManager().setVisible(true);
                ManagerFrame.this.dispose();
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

        ChangeListProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowPanel.setVisible(false);
                ShowPanel.removeAll();
                ShowPanel.add(new ListSupplyPanel());
                ShowPanel.revalidate();
                ShowPanel.setVisible(true);
            }
        });

    }
}

