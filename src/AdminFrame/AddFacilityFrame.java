package AdminFrame;

import ColorFont.Constant;
import table.facility;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AddFacilityFrame extends JFrame {
    public AddFacilityFrame(DefaultTableModel def) {
        setTitle("Covid Management System");
        ImageIcon covid_icon=null;
        try {
            covid_icon=new ImageIcon(ImageIO.read(new File("rsc/covid_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(covid_icon.getImage());
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Constant.my_gray);
        setContentPane(contentPane);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Constant.my_gray);
        JLabel SignUpLabel = new JLabel("Add new facility");
        SignUpLabel.setFont(Constant.HEADER_FONT);
        SignUpLabel.setForeground(Constant.my_white);
        headerPanel.add(SignUpLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.setBackground(Constant.my_gray);
        mainPanel.setForeground(Constant.my_white);

        JLabel InputFacilityLabel = new JLabel("Facility ID:");
        InputFacilityLabel.setFont(Constant.LABEL_FONT);
        InputFacilityLabel.setForeground(Constant.my_white);
        JTextField NameField = new JTextField();
        NameField.setFont(Constant.INFO_FONT);
        NameField.setColumns(20);

        JLabel InputIDLabel = new JLabel("Facility Name:");
        InputIDLabel.setFont(Constant.LABEL_FONT);
        InputIDLabel.setForeground(Constant.my_white);
        JTextField IDField = new JTextField();
        IDField.setFont(Constant.INFO_FONT);
        IDField.setColumns(20);

        JLabel InputQuantityLabel = new JLabel("Facility Quantity:");
        InputQuantityLabel.setFont(Constant.LABEL_FONT);
        InputQuantityLabel.setForeground(Constant.my_white);
        JTextField QuantityField = new JTextField();
        QuantityField.setFont(Constant.INFO_FONT);
        QuantityField.setColumns(20);

        JLabel InputCapacityLabel = new JLabel("Facility Capacity:");
        InputCapacityLabel.setFont(Constant.LABEL_FONT);
        InputCapacityLabel.setForeground(Constant.my_white);
        JTextField CapacityField = new JTextField();
        CapacityField.setFont(Constant.INFO_FONT);
        CapacityField.setColumns(20);

        JPanel ButtonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ButtonPane.setBackground(Constant.my_gray);

        JButton CancelButton = new JButton("Cancel");
        CancelButton.setBackground(new Color(77,82,77));
        CancelButton.setForeground(Constant.my_white);
        CancelButton.setFont(Constant.INFO_FONT);

        JButton ConfirmButton = new JButton("Confirm");
        ConfirmButton.setBackground(new Color(77,82,77));
        ConfirmButton.setForeground(Constant.my_white);
        ConfirmButton.setFont(Constant.INFO_FONT);

        ButtonPane.add(CancelButton);
        ButtonPane.add(ConfirmButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(InputFacilityLabel, gbc);

        gbc.gridy++;
        mainPanel.add(InputIDLabel, gbc);
        gbc.gridy++;
        mainPanel.add(InputQuantityLabel, gbc);
        gbc.gridy++;
        mainPanel.add(InputCapacityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(NameField, gbc);

        gbc.gridy++;
        mainPanel.add(IDField, gbc);
        gbc.gridy++;
        mainPanel.add(QuantityField, gbc);
        gbc.gridy++;
        mainPanel.add(CapacityField, gbc);


        contentPane.add(headerPanel);
        contentPane.add(mainPanel);
        contentPane.add(ButtonPane);
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddFacilityFrame.this.dispose();
            }
        });

        ConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CapacityField.getText().isEmpty() ||IDField.getText().isEmpty()||QuantityField.getText().isEmpty() ||IDField.getText().isEmpty() )
                {
                    JOptionPane.showMessageDialog(AddFacilityFrame.this, "Please fill correcly information","error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    facility fac= new facility();
                    fac.setFacility_name(IDField.getText());
                    fac.setCapacity(Integer.parseInt(CapacityField.getText()));
                    fac.setCurrent_quantity(Integer.parseInt(QuantityField.getText()));
                    fac.setFacility_id(IDField.getText());
                    getDB.Facility.FunctionFacility.AddFacility(fac);
                    JOptionPane.showMessageDialog(AddFacilityFrame.this, "Add successfully","success",JOptionPane.INFORMATION_MESSAGE);
                    ArrayList<facility> ListFacility = null;
                    def.setRowCount(0);
                    ListFacility = getDB.Facility.FunctionFacility.GetAllFacilityInfo();
                    for (facility i : ListFacility) {
                        def.addRow(new Object[]{
                                i.getFacility_id(), i.getFacility_name(), i.getCurrent_quantity(), i.getCapacity(),
                        });
                    }
                    AddFacilityFrame.this.dispose();
                }
            }
        });
    }
}
