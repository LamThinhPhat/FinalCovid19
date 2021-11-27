package AdminFrame;

import javax.swing.*;

public class AdminFrame extends JFrame {
    public AdminFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(475, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        setContentPane(contentPane);
        JLabel DAYLAADMIN = new JLabel("DAY LA ADMIN FRAME");
        contentPane.add(DAYLAADMIN);
    }
}
