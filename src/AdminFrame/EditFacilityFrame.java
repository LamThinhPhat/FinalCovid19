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

public class EditFacilityFrame extends JFrame {
    public EditFacilityFrame(DefaultTableModel def,facility update) {
        setTitle("Covid Management System");
        ImageIcon covid_icon=null;
        try {
            covid_icon=new ImageIcon(ImageIO.read(new File("rsc/covid_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(covid_icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Constant.my_gray);
        setContentPane(contentPane);

        JLabel SignUpLabel = new JLabel("Edit facility");
        SignUpLabel.setFont(Constant.LABEL_FONT);
        SignUpLabel.setForeground(Constant.my_white);
        contentPane.add(SignUpLabel);

        JPanel InputFacilityPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(InputFacilityPane);
        JLabel InputFacilityLabel = new JLabel("Input Facility Name:        ");
        InputFacilityLabel.setFont(Constant.LABEL_FONT);
        InputFacilityLabel.setForeground(Constant.my_white);
        JTextField NameField = new JTextField();
        NameField.setColumns(20);
        InputFacilityPane.setBackground(Constant.my_gray);
        InputFacilityPane.add(InputFacilityLabel);
        InputFacilityPane.add(NameField);

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
                EditFacilityFrame.this.dispose();
            }
        });
        ButtonPane.add(CancelButton);

        JButton ConfirmButton = new JButton("Confirm");
        ConfirmButton.setBackground(new Color(77,82,77));
        ConfirmButton.setForeground(Constant.my_white);

        ConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CapacityField.getText().isEmpty() ||NameField.getText().isEmpty() )
                {
                    JOptionPane.showMessageDialog(EditFacilityFrame.this, "Please fill correcly information","error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if(Integer.parseInt(CapacityField.getText())<update.getCurrent_quantity())
                    {JOptionPane.showMessageDialog(EditFacilityFrame.this, "Capacity must not be lower than current quantity","error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                    facility fac= new facility();
                    fac.setFacility_name(NameField.getText());
                    fac.setCapacity(Integer.parseInt(CapacityField.getText()));
                    fac.setCurrent_quantity(update.getCurrent_quantity());
                    fac.setFacility_id(update.getFacility_id());
                    getDB.Facility.FunctionFacility.UpdateFacility(fac);
                    JOptionPane.showMessageDialog(EditFacilityFrame.this, "Edit successfully","success",JOptionPane.INFORMATION_MESSAGE);
                    ArrayList<facility> ListFacility = null;
                    def.setRowCount(0);
                    ListFacility = getDB.Facility.FunctionFacility.GetAllFacilityInfo();
                    for (facility i : ListFacility) {
                        def.addRow(new Object[]{
                                i.getFacility_id(), i.getFacility_name(), i.getCurrent_quantity(), i.getCapacity(),
                        });
                    }
                    EditFacilityFrame.this.dispose();
                }
                }
            }
        });

        ButtonPane.add(ConfirmButton);

    }
}
