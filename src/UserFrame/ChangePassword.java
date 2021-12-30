package UserFrame;

import ColorFont.Constant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import getDB.Account.FunctionAccount;

public class ChangePassword extends JPanel {
    private JLabel lbHeader, lbOldPass, lbNewPass, lbRepass;
    private JPasswordField oldPassInput, newPassInput, rePassInput;
    private JButton btnChange;


    ChangePassword(String username) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        lbHeader = new JLabel("CHANGE PASSWORD");
        lbHeader.setFont(Constant.HEADER_FONT);
        lbHeader.setForeground(Constant.my_white);
        headerPanel.add(lbHeader);
        headerPanel.setBackground(Constant.my_gray);
        add(headerPanel, BorderLayout.NORTH);

        JPanel passPanel = new JPanel();
        passPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0,20,20,0);
        gbc.anchor = GridBagConstraints.WEST;

        lbOldPass = new JLabel("Old password:");
        lbNewPass = new JLabel("New password:");
        lbRepass = new JLabel("Retype new password:");

        lbOldPass.setFont(Constant.LABEL_FONT);
        lbNewPass.setFont(Constant.LABEL_FONT);
        lbRepass.setFont(Constant.LABEL_FONT);

        oldPassInput = new JPasswordField();
        newPassInput = new JPasswordField();
        rePassInput = new JPasswordField();

        oldPassInput.setFont(Constant.INFO_FONT);
        newPassInput.setFont(Constant.INFO_FONT);
        rePassInput.setFont(Constant.INFO_FONT);

        oldPassInput.setColumns(15);
        newPassInput.setColumns(15);
        rePassInput.setColumns(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        passPanel.add(lbOldPass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        passPanel.add(lbNewPass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        passPanel.add(lbRepass, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        passPanel.add(oldPassInput, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        passPanel.add(newPassInput, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        passPanel.add(rePassInput, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        btnChange = new JButton("Change");
        btnChange.setFont(Constant.LABEL_FONT);
        btnChange.setForeground(Constant.my_white);
        btnChange.setBackground(new Color(77,82,77));
        gbc.gridx = 1;
        gbc.gridy = 3;
        passPanel.add(btnChange, gbc);

        add(passPanel);

        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPass = String.valueOf(oldPassInput.getPassword());
                String newPass = String.valueOf(newPassInput.getPassword());
                String rePass = String.valueOf(rePassInput.getPassword());

                if(FunctionAccount.validateChangePass(username, oldPass, newPass, rePass) == 1) {
                    JOptionPane.showMessageDialog(ChangePassword.this, "Missing field", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(FunctionAccount.validateChangePass(username, oldPass, newPass, rePass) == 2) {
                    JOptionPane.showMessageDialog(ChangePassword.this, "Wrong current password", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(FunctionAccount.validateChangePass(username, oldPass, newPass, rePass) == 3) {
                    JOptionPane.showMessageDialog(ChangePassword.this, "Cannot change the same password", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(FunctionAccount.validateChangePass(username, oldPass, newPass, rePass) == 4) {
                    JOptionPane.showMessageDialog(ChangePassword.this, "Retype password does not match", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(FunctionAccount.validateChangePass(username, oldPass, newPass, rePass) == 0) {
                    FunctionAccount.UpdatePassword(username, newPass);
                    JOptionPane.showMessageDialog(ChangePassword.this, "Update success", "Notice", JOptionPane.INFORMATION_MESSAGE);
                    oldPassInput.setText("");
                    newPassInput.setText("");
                    rePassInput.setText("");
                }
            }
        });
    }
}
