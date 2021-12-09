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
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Constant.my_gray);
        setContentPane(contentPane);

        JLabel SignUpLabel = new JLabel("Add new facility");
        SignUpLabel.setFont(Constant.LABEL_FONT);
        SignUpLabel.setForeground(Constant.my_white);
        contentPane.add(SignUpLabel);

        JPanel InputFacilityPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(InputFacilityPane);
        JLabel InputFacilityLabel = new JLabel("Input Facility ID:        ");
        InputFacilityLabel.setFont(Constant.LABEL_FONT);
        InputFacilityLabel.setForeground(Constant.my_white);
        JTextField NameField = new JTextField();
        NameField.setColumns(20);
        InputFacilityPane.setBackground(Constant.my_gray);
        InputFacilityPane.add(InputFacilityLabel);
        InputFacilityPane.add(NameField);

        JPanel InputIDPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(InputIDPane);
        JLabel InputIDLabel = new JLabel("Input Facility Name:        ");
        InputIDLabel.setFont(Constant.LABEL_FONT);
        InputIDLabel.setForeground(Constant.my_white);
        JTextField IDField = new JTextField();
        IDField.setColumns(20);
        InputIDPane.setBackground(Constant.my_gray);
        InputIDPane.add(InputIDLabel);
        InputIDPane.add(IDField);

        JPanel Inputquantity = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(Inputquantity);
        JLabel InputQuantityLabel = new JLabel("Input Facility Quantity:        ");
        InputQuantityLabel.setFont(Constant.LABEL_FONT);
        InputQuantityLabel.setForeground(Constant.my_white);
        JTextField QuantityField = new JTextField();
        QuantityField.setColumns(20);
        Inputquantity.setBackground(Constant.my_gray);
        Inputquantity.add(InputQuantityLabel);
        Inputquantity.add(QuantityField);

        JPanel InputCapacity = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(InputCapacity);
        JLabel InputCapacityLabel = new JLabel("Input Facility Capacity:        ");
        InputCapacityLabel.setFont(Constant.LABEL_FONT);
        InputCapacityLabel.setForeground(Constant.my_white);
        JTextField CapacityField = new JTextField();
        CapacityField.setColumns(20);
        InputCapacity.setBackground(Constant.my_gray);
        InputCapacity.add(InputCapacityLabel);
        InputCapacity.add(CapacityField);

        JPanel ButtonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ButtonPane.setBackground(Constant.my_gray);
        contentPane.add(ButtonPane);

        JButton CancelButton = new JButton("Cancel");
        CancelButton.setBackground(new Color(77,82,77));
        CancelButton.setForeground(Constant.my_white);
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddFacilityFrame.this.dispose();
            }
        });
        ButtonPane.add(CancelButton);

        JButton ConfirmButton = new JButton("Confirm");
        ConfirmButton.setBackground(new Color(77,82,77));
        ConfirmButton.setForeground(Constant.my_white);

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

        ButtonPane.add(ConfirmButton);

    }
}
