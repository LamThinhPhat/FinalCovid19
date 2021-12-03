package ManagerFrame;

import ColorFont.Constant;
import table.supply;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditSupplyPanel extends JPanel {
    public EditSupplyPanel(String supplyID) {

        supply EditInfoSupply = getDB.Supply.FunctionSupply.GetInfoSupply(supplyID);

        setBackground(Constant.my_gray);
        JPanel EditSupplyPanel = new JPanel();
        EditSupplyPanel.setLayout(new BoxLayout(EditSupplyPanel,BoxLayout.Y_AXIS));
        EditSupplyPanel.setBackground(Constant.my_gray);
        add(EditSupplyPanel);

        JLabel AddNewSupplyLabel = new JLabel("Edit supply");
        AddNewSupplyLabel.setFont(Constant.LABEL_FONT);
        AddNewSupplyLabel.setForeground(Constant.my_white);
        EditSupplyPanel.add(AddNewSupplyLabel);

        JPanel SupplyIdPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        SupplyIdPane.setBackground(Constant.my_gray);
        EditSupplyPanel.add(SupplyIdPane);
        JLabel SupplyIdLabel = new JLabel("Supply ID:      ");
        SupplyIdLabel.setFont(Constant.LABEL_FONT);
        SupplyIdLabel.setForeground(Constant.my_white);
        JTextField SupplyIdField = new JTextField();
        SupplyIdField.setColumns(30);
        SupplyIdField.setText(EditInfoSupply.getSupply_id());
        SupplyIdField.setEditable(false);
        SupplyIdPane.add(SupplyIdLabel);
        SupplyIdPane.add(SupplyIdField);

        JPanel SupplyNamePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        SupplyNamePane.setBackground(Constant.my_gray);
        EditSupplyPanel.add(SupplyNamePane);
        JLabel SupplyNameLabel = new JLabel("Supply name: ");
        SupplyNameLabel.setFont(Constant.LABEL_FONT);
        SupplyNameLabel.setForeground(Constant.my_white);
        JTextField SupplyNameField = new JTextField();
        SupplyNameField.setText(EditInfoSupply.getSupply_name());
        SupplyNameField.setColumns(30);
        SupplyNamePane.add(SupplyNameLabel);
        SupplyNamePane.add(SupplyNameField);

        JPanel LimitDayPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LimitDayPane.setBackground(Constant.my_gray);
        EditSupplyPanel.add(LimitDayPane);
        JLabel LimitDayLabel = new JLabel("Limit day:       ");
        LimitDayLabel.setFont(Constant.LABEL_FONT);
        LimitDayLabel.setForeground(Constant.my_white);
        JTextField LimitDayField = new JTextField();
        LimitDayField.setText(String.valueOf(EditInfoSupply.getLimit_day()));
        LimitDayField.setColumns(30);
        LimitDayPane.add(LimitDayLabel);
        LimitDayPane.add(LimitDayField);

        JPanel LimitWeekPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LimitWeekPane.setBackground(Constant.my_gray);
        EditSupplyPanel.add(LimitWeekPane);
        JLabel LimitWeekLabel = new JLabel("Limit week:     ");
        LimitWeekLabel.setFont(Constant.LABEL_FONT);
        LimitWeekLabel.setForeground(Constant.my_white);
        JTextField LimitWeekField = new JTextField();
        LimitWeekField.setText(String.valueOf(EditInfoSupply.getLimit_week()));
        LimitWeekField.setColumns(30);
        LimitWeekPane.add(LimitWeekLabel);
        LimitWeekPane.add(LimitWeekField);

        JPanel LimitMonthPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LimitMonthPane.setBackground(Constant.my_gray);
        EditSupplyPanel.add(LimitMonthPane);
        JLabel LimitMonthLabel = new JLabel("Limit month:  ");
        LimitMonthLabel.setFont(Constant.LABEL_FONT);
        LimitMonthLabel.setForeground(Constant.my_white);
        JTextField LimitMonthField = new JTextField();
        LimitMonthField.setText(String.valueOf(EditInfoSupply.getLimit_month()));
        LimitMonthField.setColumns(30);
        LimitMonthPane.add(LimitMonthLabel);
        LimitMonthPane.add(LimitMonthField);

        JPanel PricePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        PricePane.setBackground(Constant.my_gray);
        EditSupplyPanel.add(PricePane);
        JLabel PriceLabel = new JLabel("Price:               ");
        PriceLabel.setFont(Constant.LABEL_FONT);
        PriceLabel.setForeground(Constant.my_white);
        JTextField PriceField = new JTextField();
        PriceField.setText(String.valueOf(EditInfoSupply.getPrice()));
        PriceField.setColumns(30);
        PricePane.add(PriceLabel);
        PricePane.add(PriceField);

        JPanel EditSupplyButtonSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        EditSupplyButtonSouth.setBackground(Constant.my_gray);
        EditSupplyPanel.add(EditSupplyButtonSouth);

        JButton EditSupplyCancelButton = new JButton("Cancel");
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

        EditSupplyButtonSouth.add(EditSupplyCancelButton);

        JButton EditSupplyConfirmButton = new JButton("Confirm");
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

        EditSupplyButtonSouth.add(EditSupplyConfirmButton);


    }
}