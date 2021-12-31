package ManagerFrame;

import ColorFont.Constant;
import table.supply;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditSupplyPanel extends JPanel {
    public EditSupplyPanel(String supplyID) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        supply EditInfoSupply = getDB.Supply.FunctionSupply.GetInfoSupply(supplyID);
        setBackground(Constant.my_gray);
        JPanel EditSupplyPanel = new JPanel();
        EditSupplyPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor =GridBagConstraints.WEST;

        EditSupplyPanel.setBackground(Constant.my_gray);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Constant.my_gray);
        JLabel AddNewSupplyLabel = new JLabel("Edit supply");
        AddNewSupplyLabel.setFont(Constant.HEADER_FONT);
        AddNewSupplyLabel.setForeground(Constant.my_white);
        headerPanel.add(AddNewSupplyLabel);

        JLabel SupplyIdLabel = new JLabel("Supply ID:");
        SupplyIdLabel.setFont(Constant.LABEL_FONT);
        SupplyIdLabel.setForeground(Constant.my_white);
        JTextField SupplyIdField = new JTextField();
        SupplyIdField.setFont(Constant.INFO_FONT);
        SupplyIdField.setColumns(30);
        SupplyIdField.setText(EditInfoSupply.getSupply_id());
        SupplyIdField.setEditable(false);


        JLabel SupplyNameLabel = new JLabel("Supply name:");
        SupplyNameLabel.setFont(Constant.LABEL_FONT);
        SupplyNameLabel.setForeground(Constant.my_white);
        JTextField SupplyNameField = new JTextField();
        SupplyNameField.setFont(Constant.INFO_FONT);
        SupplyNameField.setText(EditInfoSupply.getSupply_name());
        SupplyNameField.setColumns(30);


        JLabel LimitDayLabel = new JLabel("Limit day:");
        LimitDayLabel.setFont(Constant.LABEL_FONT);
        LimitDayLabel.setForeground(Constant.my_white);
        JTextField LimitDayField = new JTextField();
        LimitDayField.setFont(Constant.INFO_FONT);
        LimitDayField.setText(String.valueOf(EditInfoSupply.getLimit_day()));
        LimitDayField.setColumns(30);


        JLabel LimitWeekLabel = new JLabel("Limit week:");
        LimitWeekLabel.setFont(Constant.LABEL_FONT);
        LimitWeekLabel.setForeground(Constant.my_white);
        JTextField LimitWeekField = new JTextField();
        LimitWeekField.setFont(Constant.INFO_FONT);
        LimitWeekField.setText(String.valueOf(EditInfoSupply.getLimit_week()));
        LimitWeekField.setColumns(30);


        JLabel LimitMonthLabel = new JLabel("Limit month:");
        LimitMonthLabel.setFont(Constant.LABEL_FONT);
        LimitMonthLabel.setForeground(Constant.my_white);
        JTextField LimitMonthField = new JTextField();
        LimitMonthField.setFont(Constant.INFO_FONT);
        LimitMonthField.setText(String.valueOf(EditInfoSupply.getLimit_month()));
        LimitMonthField.setColumns(30);

        JLabel PriceLabel = new JLabel("Price:");
        PriceLabel.setFont(Constant.LABEL_FONT);
        PriceLabel.setForeground(Constant.my_white);
        JTextField PriceField = new JTextField();
        PriceField.setFont(Constant.INFO_FONT);
        PriceField.setText(String.valueOf(EditInfoSupply.getPrice()));
        PriceField.setColumns(30);

        JPanel EditSupplyButtonSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        EditSupplyButtonSouth.setBackground(Constant.my_gray);

        JButton EditSupplyCancelButton = new JButton("Cancel");
        EditSupplyCancelButton.setForeground(Constant.my_white);
        EditSupplyCancelButton.setBackground(new Color(77,82,77));
        EditSupplyCancelButton.setFont(Constant.INFO_FONT);

        JButton EditSupplyConfirmButton = new JButton("Confirm");
        EditSupplyConfirmButton.setForeground(Constant.my_white);
        EditSupplyConfirmButton.setBackground(new Color(77,82,77));
        EditSupplyConfirmButton.setFont(Constant.INFO_FONT);

        EditSupplyButtonSouth.add(EditSupplyCancelButton);
        EditSupplyButtonSouth.add(EditSupplyConfirmButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        EditSupplyPanel.add(SupplyIdLabel, gbc);

        gbc.gridy++;EditSupplyPanel.add(SupplyNameLabel, gbc);
        gbc.gridy++;EditSupplyPanel.add(LimitDayLabel, gbc);
        gbc.gridy++;EditSupplyPanel.add(LimitWeekLabel, gbc);
        gbc.gridy++;EditSupplyPanel.add(LimitMonthLabel, gbc);
        gbc.gridy++;EditSupplyPanel.add(PriceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        EditSupplyPanel.add(SupplyIdField, gbc);

        gbc.gridy++;EditSupplyPanel.add(SupplyNameField, gbc);
        gbc.gridy++;EditSupplyPanel.add(LimitDayField, gbc);
        gbc.gridy++;EditSupplyPanel.add(LimitWeekField, gbc);
        gbc.gridy++;EditSupplyPanel.add(LimitMonthField, gbc);
        gbc.gridy++;EditSupplyPanel.add(PriceField, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy++;EditSupplyPanel.add(EditSupplyButtonSouth, gbc);

        add(headerPanel);
        add(EditSupplyPanel);

        EditSupplyCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                removeAll();
                add(new ListSupplyPanel());
                revalidate();
                repaint();
                setVisible(true);
            }
        });

        EditSupplyConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SupplyIdField.getText().isEmpty() || SupplyNameField.getText().isEmpty() || LimitDayField.getText().isEmpty()
                        || LimitWeekField.getText().isEmpty() || LimitMonthField.getText().isEmpty() || PriceField.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(EditSupplyPanel.this, "Please fill correcly information","error",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    supply newinfosupply = new supply();

                    newinfosupply.setSupply_id(SupplyIdField.getText());
                    newinfosupply.setSupply_name(SupplyNameField.getText());
                    try{
                        newinfosupply.setLimit_day(Integer.parseInt(LimitDayField.getText()));
                        newinfosupply.setLimit_week(Integer.parseInt(LimitWeekField.getText()));
                        newinfosupply.setLimit_month(Integer.parseInt(LimitMonthField.getText()));
                        newinfosupply.setPrice(Integer.parseInt(PriceField.getText()));

                        getDB.Supply.FunctionSupply.UpdateSupply(newinfosupply);

                        JOptionPane.showMessageDialog(EditSupplyPanel.this, "Edit successfully", "success", JOptionPane.ERROR_MESSAGE);
                        setVisible(false);
                        removeAll();
                        add(new ListSupplyPanel());
                        revalidate();
                        repaint();
                        setVisible(true);
                    }
                    catch (NumberFormatException err)
                    {
                        JOptionPane.showMessageDialog(EditSupplyPanel.this, "Please fill correcly information","error",JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }
}