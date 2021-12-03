package Login;

import AdminFrame.AdminFrame;
import ColorFont.Constant;
import ManagerFrame.ManagerFrame;
import UserFrame.UserFrame;
import getDB.Account.FunctionAccount;
import table.account;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class LogInFrame extends JFrame {
    static public String ManagerUsername;
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
        contentPane.setBackground(Constant.my_gray);
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
        Header.setBackground(Constant.my_gray);
        contentPane.add(Header);

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

        JButton Login = new JButton("Login");
        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Login.setForeground(Constant.my_white);
        Login.setBackground(new Color(77,82,77));
        Login.setFont(Constant.LABEL_FONT);
        ButtonPanel.add(Login);
        ButtonPanel.setBackground(Constant.my_gray);
        contentPane.add(ButtonPanel);


        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = UserNameInput.getText();
                String password = String.valueOf(String.valueOf(PassInput.getPassword()).hashCode());
                account login = FunctionAccount.GetAccountToLogin(username,password);
                if (login==null)
                {
                    JOptionPane.showMessageDialog(LogInFrame.this, "Wrong id or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if (login.getBan_unban() == 1)
                    {
                        JOptionPane.showMessageDialog(LogInFrame.this, "This account is being banned", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        if (login.getUser_role() == 1)
                        {
                            ManagerUsername = username;
                            new ManagerFrame(ManagerUsername).setVisible(true);
                            LogInFrame.this.dispose();
                        }
                        else if (login.getUser_role() == 2)
                        {
                            new AdminFrame(username).setVisible(true);
                            LogInFrame.this.dispose();
                        }
                        else
                        {
                            new UserFrame(username).setVisible(true);
                            LogInFrame.this.dispose();
                        }
                    }
                }

            }
        });


    }
}