package UserFrame;

import javax.swing.*;

public class UserFrame extends JFrame {
    public UserFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(475, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        setContentPane(contentPane);
        JLabel DAYLAUSER = new JLabel("DAY LA USER FRAME");
        contentPane.add(DAYLAUSER);
    }
}
