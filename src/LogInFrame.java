import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class LogInFrame extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogInFrame frame = new LogInFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LogInFrame() {
        setTitle("Covid Management System");
        ImageIcon covid_icon=null;
        try {
            covid_icon=new ImageIcon(ImageIO.read(new File("rsc/covid_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(covid_icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);
        contentPane.setBackground(color.my_gray);
        JPanel Header = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel Icon_label = new JLabel(covid_icon);
        ImageIcon text_icon=null;
        try {
            text_icon=new ImageIcon(ImageIO.read(new File("rsc/covid_text.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel Text_Icon_label = new JLabel(text_icon);

        Header.add(Icon_label);
        Header.add(Text_Icon_label);
        Header.setBackground(color.my_gray);
        contentPane.add(Header);



        JPanel UserNamePannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel UserNameLabel = new JLabel("Username");
        JTextField UserNameInput = new JTextField();
        UserNameLabel.setForeground(color.my_white);
        UserNamePannel.setBackground(color.my_gray);
        UserNameInput.setColumns(15);
        UserNamePannel.add(UserNameLabel);
        UserNamePannel.add(UserNameInput);
        contentPane.add(UserNamePannel);

        JPanel PassPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel PassLabel = new JLabel("Password");
        JPasswordField PassInput = new JPasswordField();
        PassLabel.setForeground(color.my_white);
        PassPannel.setBackground(color.my_gray);
        PassInput.setColumns(15);
        PassPannel.add(PassLabel);
        PassPannel.add(PassInput);
        contentPane.add(PassPannel);

        JButton Login = new JButton("Login");
        JButton Signup = new JButton("Signup");
        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Login.setForeground(color.my_white);
        Login.setBackground(new Color(77,82,77));

        Signup.setForeground(color.my_white);
        Signup.setBackground(new Color(77,82,77));
        ButtonPanel.add(Signup);
        ButtonPanel.add(Login);
        ButtonPanel.setBackground(color.my_gray);
        contentPane.add(ButtonPanel);

        Signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUpFrame().setVisible(true);
                LogInFrame.this.dispose();
            }
        });


    }
}