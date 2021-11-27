import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.security.MessageDigest;
import java.util.Set;


public class SignUpFrame extends JFrame {
    Color my_gray=new Color(61,64,62);
    Color my_white=new Color(235,235,235);
    public SignUpFrame() {
        setTitle("Covid Management System");
        ImageIcon covid_icon=null;
        try {
            covid_icon=new ImageIcon(ImageIO.read(new File("rsc/covid_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(covid_icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(460, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(my_gray);
        setContentPane(contentPane);


        JLabel AddStudentLabel = new JLabel("Sign up");
        AddStudentLabel.setForeground(my_white);
        contentPane.add(AddStudentLabel);

        JPanel InputUserPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(InputUserPane);
        JLabel InputUserLabel = new JLabel("Input Username:      ");
        InputUserLabel.setForeground(my_white);
        JTextField UserField = new JTextField();
        UserField.setColumns(30);
        InputUserPane.setBackground(my_gray);
        InputUserPane.add(InputUserLabel);
        InputUserPane.add(UserField);

        JPanel InputPasstPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(InputPasstPane);
        JLabel InputPassLabel = new JLabel("Input Password:      ");
        InputPassLabel.setForeground(my_white);
        JPasswordField PassField = new JPasswordField();
        PassField.setColumns(30);
        InputPasstPane.setBackground(my_gray);
        InputPasstPane.add(InputPassLabel);
        InputPasstPane.add(PassField);

        JPanel InputNamePane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(InputNamePane);
        JLabel InputNameLabel = new JLabel("Input FullName:        ");
        InputNameLabel.setForeground(my_white);
        JTextField NameField = new JTextField();
        NameField.setColumns(30);
        InputNamePane.setBackground(my_gray);
        InputNamePane.add(InputNameLabel);
        InputNamePane.add(NameField);

        JPanel InputYoBPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(InputYoBPane);
        JLabel InputYobLabel = new JLabel("Input Date of birth:  ");
        InputYobLabel.setForeground(my_white);
        JTextField YobField = new JTextField();
        YobField.setColumns(30);
        InputYoBPane.setBackground(my_gray);
        InputYoBPane.add(InputYobLabel);
        InputYoBPane.add(YobField);

        JPanel InputAddressPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(InputAddressPane);
        JLabel InputAddressLabel = new JLabel("Input House Number:");
        InputAddressLabel.setForeground(my_white);
        JTextField AddressField = new JTextField();
        AddressField.setColumns(30);
        InputAddressPane.setBackground(my_gray);
        InputAddressPane.add(InputAddressLabel);
        InputAddressPane.add(AddressField);

        JPanel PPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        PPane.setBackground(color.my_gray);
        contentPane.add(PPane);

        JPanel DPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        DPane.setBackground(color.my_gray);
        contentPane.add(DPane);

        JPanel WPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        WPane.setBackground(color.my_gray);
        contentPane.add(WPane);

        JLabel ProvinceLable = new JLabel("Province");
        ProvinceLable.setForeground(color.my_white);
        JComboBox Province = new JComboBox();
        JLabel WardLable = new JLabel("Ward      ");
        WardLable.setForeground(color.my_white);
        JComboBox Ward = new JComboBox();
        JLabel DistrictLable = new JLabel("District   ");
        DistrictLable.setForeground(color.my_white);
        JComboBox District = new JComboBox();

        PPane.add(ProvinceLable);
        PPane.add(Province);
        DPane.add(DistrictLable);
        DPane.add(District);
        WPane.add(WardLable);
        WPane.add(Ward);


        Set<String> provinces = getDB.Address.Function.GetAllProvince();
        for (String s : provinces)
        {
            Province.addItem(s);
        }

        Province.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SelectedProvince = (String) Province.getSelectedItem();
                Ward.removeAllItems();
                District.removeAllItems();
                Set<String> districts = getDB.Address.Function.GetAllDistrict(SelectedProvince);
                for (String s : districts)
                    {
                        District.addItem(s);
                    }

            }
        });

        District.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SelectedDistrict = (String) District.getSelectedItem();
                String SelectedProvince = (String) Province.getSelectedItem();
                Ward.removeAllItems();
                Set<String> wards = getDB.Address.Function.GetAllWard(SelectedDistrict,SelectedProvince);
                for (String s : wards)
                {
                    Ward.addItem(s);
                }
            }
        });

        JPanel InputIdPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(InputIdPane);
        JLabel InputIdLabel = new JLabel("Input ID:                     ");
        InputIdLabel.setForeground(my_white);
        JTextField IdField = new JTextField();
        IdField.setColumns(30);
        InputIdPane.setBackground(my_gray);
        InputIdPane.add(InputIdLabel);
        InputIdPane.add(IdField);








        JPanel ButtonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ButtonPane.setBackground(my_gray);
        contentPane.add(ButtonPane);

        JButton CancelButton = new JButton("Cancel");
        CancelButton.setBackground(new Color(77,82,77));
        CancelButton.setForeground(my_white);
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogInFrame().setVisible(true);
                SignUpFrame.this.dispose();
            }
        });
        ButtonPane.add(CancelButton);

        JButton ConfirmButton = new JButton("Confirm");
        ConfirmButton.setBackground(new Color(77,82,77));
        ConfirmButton.setForeground(my_white);

        ConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        ButtonPane.add(ConfirmButton);



    }
}