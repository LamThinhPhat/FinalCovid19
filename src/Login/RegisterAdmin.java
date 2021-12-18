package Login;

import ColorFont.Constant;
import UserFrame.UserFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class RegisterAdmin extends JFrame {
    public RegisterAdmin() {
        setTitle("Covid Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon covid_icon=null;
        try {
            covid_icon=new ImageIcon(ImageIO.read(new File("rsc/covid_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(covid_icon.getImage());

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Constant.my_gray);
        setContentPane(contentPane);

        JPanel AdminNotiFy = new JPanel(new FlowLayout(FlowLayout.CENTER));
        AdminNotiFy.setBackground(Constant.my_gray);
        JLabel AdminNotice = new JLabel("Register As Admin");
        AdminNotiFy.add(AdminNotice);
        AdminNotice.setFont(Constant.LABEL_FONT);
        AdminNotice.setForeground(Constant.my_white);
        contentPane.add(AdminNotiFy);

        JPanel UserNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel UserNameLabel = new JLabel("Username: ");
        UserNameLabel.setFont(Constant.LABEL_FONT);
        JTextField UserNameInput = new JTextField();
        UserNameInput.setFont(Constant.INFO_FONT);
        UserNameLabel.setForeground(Constant.my_white);
        UserNamePanel.setBackground(Constant.my_gray);

        UserNameInput.setColumns(15);
        UserNamePanel.add(UserNameLabel);
        UserNamePanel.add(UserNameInput);
        contentPane.add(UserNamePanel);

        JPanel PassPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel PassLabel = new JLabel("Password: ");
        PassLabel.setFont(Constant.LABEL_FONT);
        JPasswordField PassInput = new JPasswordField();
        PassLabel.setForeground(Constant.my_white);
        PassPannel.setBackground(Constant.my_gray);
        PassInput.setColumns(15);
        PassInput.setFont(Constant.INFO_FONT);
        PassPannel.add(PassLabel);
        PassPannel.add(PassInput);
        contentPane.add(PassPannel);

        JButton Signup = new JButton("Sign up");
        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Signup.setForeground(Constant.my_white);
        Signup.setBackground(new Color(77,82,77));
        Signup.setFont(Constant.LABEL_FONT);
        ButtonPanel.add(Signup);
        ButtonPanel.setBackground(Constant.my_gray);
        contentPane.add(ButtonPanel);

        Signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = UserNameInput.getText();
                String password = String.valueOf(String.valueOf(PassInput.getPassword()).hashCode());
                getDB.Account.FunctionAccount.registerAdmin(username, password);
                new LogInFrame().setVisible(true);
                RegisterAdmin.this.dispose();
            }
        });
    }
}
