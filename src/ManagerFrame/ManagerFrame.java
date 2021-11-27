package ManagerFrame;


import javax.swing.*;

public class ManagerFrame extends JFrame {
    public ManagerFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(475, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        setContentPane(contentPane);
        JLabel DAYLAMANAGER = new JLabel("DAY LA MANAGER FRAME");
        contentPane.add(DAYLAMANAGER);
    }
}

