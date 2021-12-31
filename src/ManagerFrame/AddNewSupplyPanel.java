package ManagerFrame;

import ColorFont.Constant;
import table.supply;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewSupplyPanel extends JPanel {
    public AddNewSupplyPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Constant.my_gray);
        JPanel AddSupplyPanel = new JPanel();
        AddSupplyPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.WEST;

        AddSupplyPanel.setBackground(Constant.my_gray);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Constant.my_gray);
        JLabel AddNewSupplyLabel = new JLabel("Add new supply");
        AddNewSupplyLabel.setFont(Constant.HEADER_FONT);
        AddNewSupplyLabel.setForeground(Constant.my_white);
        headerPanel.add(AddNewSupplyLabel);

        JLabel SupplyIdLabel = new JLabel("Supply ID:");
        SupplyIdLabel.setFont(Constant.LABEL_FONT);
        SupplyIdLabel.setForeground(Constant.my_white);
        JTextField SupplyIdField = new JTextField();
        SupplyIdField.setFont(Constant.INFO_FONT);
        SupplyIdField.setColumns(30);

        JLabel SupplyNameLabel = new JLabel("Supply name:");
        SupplyNameLabel.setFont(Constant.LABEL_FONT);
        SupplyNameLabel.setForeground(Constant.my_white);
        JTextField SupplyNameField = new JTextField();
        SupplyNameField.setFont(Constant.INFO_FONT);
        SupplyNameField.setColumns(30);

        JLabel LimitDayLabel = new JLabel("Limit day:");
        LimitDayLabel.setFont(Constant.LABEL_FONT);
        LimitDayLabel.setForeground(Constant.my_white);
        JTextField LimitDayField = new JTextField();
        LimitDayField.setFont(Constant.INFO_FONT);
        LimitDayField.setColumns(30);

        JLabel LimitWeekLabel = new JLabel("Limit week:");
        LimitWeekLabel.setFont(Constant.LABEL_FONT);
        LimitWeekLabel.setForeground(Constant.my_white);
        JTextField LimitWeekField = new JTextField();
        LimitWeekField.setFont(Constant.INFO_FONT);
        LimitWeekField.setColumns(30);

        JLabel LimitMonthLabel = new JLabel("Limit month:");
        LimitMonthLabel.setFont(Constant.LABEL_FONT);
        LimitMonthLabel.setForeground(Constant.my_white);
        JTextField LimitMonthField = new JTextField();
        LimitMonthField.setFont(Constant.INFO_FONT);
        LimitMonthField.setColumns(30);

        JLabel PriceLabel = new JLabel("Price:");
        PriceLabel.setFont(Constant.LABEL_FONT);
        PriceLabel.setForeground(Constant.my_white);
        JTextField PriceField = new JTextField();
        PriceField.setFont(Constant.INFO_FONT);
        PriceField.setColumns(30);

        JPanel AddSupplyButtonSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        AddSupplyButtonSouth.setBackground(Constant.my_gray);

        JButton AddSupplyCancelButton = new JButton("Cancel");
        AddSupplyCancelButton.setForeground(Constant.my_white);
        AddSupplyCancelButton.setBackground(new Color(77,82,77));
        AddSupplyCancelButton.setFont(Constant.INFO_FONT);

        JButton AddSupplyConfirmButton = new JButton("Confirm");
        AddSupplyConfirmButton.setForeground(Constant.my_white);
        AddSupplyConfirmButton.setBackground(new Color(77,82,77));
        AddSupplyConfirmButton.setFont(Constant.INFO_FONT);

        AddSupplyButtonSouth.add(AddSupplyCancelButton);
        AddSupplyButtonSouth.add(AddSupplyConfirmButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        AddSupplyPanel.add(SupplyIdLabel, gbc);

        gbc.gridy++;AddSupplyPanel.add(SupplyNameLabel, gbc);
        gbc.gridy++;AddSupplyPanel.add(LimitDayLabel, gbc);
        gbc.gridy++;AddSupplyPanel.add(LimitWeekLabel, gbc);
        gbc.gridy++;AddSupplyPanel.add(LimitMonthLabel, gbc);
        gbc.gridy++;AddSupplyPanel.add(PriceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        AddSupplyPanel.add(SupplyIdField, gbc);

        gbc.gridy++;AddSupplyPanel.add(SupplyNameField, gbc);
        gbc.gridy++;AddSupplyPanel.add(LimitDayField, gbc);
        gbc.gridy++;AddSupplyPanel.add(LimitWeekField, gbc);
        gbc.gridy++;AddSupplyPanel.add(LimitMonthField, gbc);
        gbc.gridy++;AddSupplyPanel.add(PriceField, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy++;AddSupplyPanel.add(AddSupplyButtonSouth, gbc);

        add(headerPanel);
        add(AddSupplyPanel);

        AddSupplyCancelButton.addActionListener(new ActionListener() {
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

        AddSupplyConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SupplyIdField.getText().isEmpty() || SupplyNameField.getText().isEmpty() || LimitDayField.getText().isEmpty()
                    || LimitWeekField.getText().isEmpty() || LimitMonthField.getText().isEmpty() || PriceField.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(AddNewSupplyPanel.this, "Please fill correcly information","error",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    supply newsupply = new supply();

                    newsupply.setSupply_id(SupplyIdField.getText());
                    newsupply.setSupply_name(SupplyNameField.getText());
                    try{
                        newsupply.setLimit_day(Integer.parseInt(LimitDayField.getText()));
                        newsupply.setLimit_week(Integer.parseInt(LimitWeekField.getText()));
                        newsupply.setLimit_month(Integer.parseInt(LimitMonthField.getText()));
                        newsupply.setPrice(Integer.parseInt(PriceField.getText()));
                        if (getDB.Supply.FunctionSupply.CheckExisted(newsupply.getSupply_id()))
                        {
                            JOptionPane.showMessageDialog(AddNewSupplyPanel.this, "Supply Id existed","error",JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            getDB.Supply.FunctionSupply.AddNewSupply(newsupply);
                            JOptionPane.showMessageDialog(AddNewSupplyPanel.this, "Add successfully", "success", JOptionPane.ERROR_MESSAGE);

                            setVisible(false);
                            removeAll();
                            add(new ListSupplyPanel());
                            revalidate();
                            repaint();
                            setVisible(true);
                        }
                    }
                    catch (NumberFormatException err)
                    {
                        JOptionPane.showMessageDialog(AddNewSupplyPanel.this, "Please fill correcly information","error",JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }
}