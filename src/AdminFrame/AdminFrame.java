package AdminFrame;

import ColorFont.Constant;
import Login.LogInFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AdminFrame extends JFrame {
    public AdminFrame(String AdminUsername)
    {
        setTitle("Covid Management System");

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
        JPanel ShowPanel = new JPanel();
        ShowPanel.setLayout(new BoxLayout(ShowPanel,BoxLayout.Y_AXIS));
        JPanel ManagerPanel = new ListManagerPanel();
        ManagerPanel.setVisible(true);
        JPanel FacilityPanel = new ListFacilitiesPanel();
        FacilityPanel.setVisible(false);


        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPane.setBackground(Constant.my_gray);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel,BoxLayout.Y_AXIS));
        controlPanel.setBackground(Constant.my_gray);

        Icon managers_icon = new ImageIcon("rsc/person-icon-1682.png");
        JButton manage_manager=new JButton("Manage managers",managers_icon);
        manage_manager.setForeground(Constant.my_gray);
        manage_manager.setVerticalTextPosition(SwingConstants.BOTTOM);
        manage_manager.setHorizontalTextPosition(SwingConstants.CENTER);
        manage_manager.setFocusPainted(false);
        manage_manager.setBackground(Constant.my_white);
        manage_manager.setBackground(Constant.my_white);
        manage_manager.addActionListener(e ->{
            ManagerPanel.setVisible(true);
            FacilityPanel.setVisible(false);
        });

        Icon facility_icon = new ImageIcon("rsc/facility.png");
        JButton manage_facility=new JButton("Manage facilites",facility_icon);
        manage_facility.setForeground(Constant.my_gray);
        manage_facility.setVerticalTextPosition(SwingConstants.BOTTOM);
        manage_facility.setHorizontalTextPosition(SwingConstants.CENTER);
        manage_facility.setFocusPainted(false);
        manage_facility.setBackground(Constant.my_white);
        manage_facility.addActionListener(e ->{
            ManagerPanel.setVisible(false);
            FacilityPanel.setVisible(true);
        });

        JLabel UserName=new JLabel();
        UserName.setText("Login as: "+AdminUsername);
        UserName.setForeground(Constant.my_white);
        JButton Logout= new JButton("Logout");
        Logout.setForeground(Constant.my_white);
        Logout.setBackground(new Color(77,82,77));
        Logout.addActionListener(e->{
            new LogInFrame().setVisible(true);
            this.dispose();

        });
        controlPanel.add(UserName);
        controlPanel.add(Logout);
        controlPanel.add(manage_manager);
        controlPanel.add(Box.createRigidArea(new Dimension(20,10)));
        controlPanel.add(manage_facility);

        JLabel AdminLabel=new JLabel("Adminstrator");
        AdminLabel.setFont(new Font("Serif", Font.BOLD, 45));
        AdminLabel.setForeground(Constant.my_white);
        JPanel HeaderPane=new JPanel(new FlowLayout(FlowLayout.LEFT));
        HeaderPane.setBackground(Constant.my_gray);
        HeaderPane.add(AdminLabel);
        ShowPanel.add(HeaderPane);
        ShowPanel.add(ManagerPanel);
        ShowPanel.add(FacilityPanel);
        ShowPanel.setBackground(Constant.my_gray);

        contentPane.add(controlPanel);
        contentPane.add(ShowPanel);
        setContentPane(contentPane);
    }
}
