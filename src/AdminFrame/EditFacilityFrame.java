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
        setSize(600, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Constant.my_gray);
        setContentPane(contentPane);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Constant.my_gray);
        JLabel SignUpLabel = new JLabel("Edit facility");
        SignUpLabel.setFont(Constant.HEADER_FONT);
        SignUpLabel.setForeground(Constant.my_white);
        headerPanel.add(SignUpLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Constant.my_gray);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel InputFacilityLabel = new JLabel("Facility Name:");
        InputFacilityLabel.setFont(Constant.LABEL_FONT);
        InputFacilityLabel.setForeground(Constant.my_white);
        JTextField NameField = new JTextField();
        NameField.setFont(Constant.INFO_FONT);
        NameField.setColumns(20);

        JLabel InputCapacityLabel = new JLabel("Facility Capacity:");
        InputCapacityLabel.setFont(Constant.LABEL_FONT);
        InputCapacityLabel.setForeground(Constant.my_white);
        JTextField CapacityField = new JTextField();
        CapacityField.setFont(Constant.INFO_FONT);
        CapacityField.setColumns(20);

        JPanel ButtonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ButtonPane.setBackground(Constant.my_gray);
        contentPane.add(ButtonPane);

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
        mainPanel.add(InputCapacityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(NameField, gbc);

        gbc.gridy++;
        mainPanel.add(CapacityField, gbc);

        contentPane.add(headerPanel);
        contentPane.add(mainPanel);
        contentPane.add(ButtonPane);

        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditFacilityFrame.this.dispose();
            }
        });
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
    }
}
