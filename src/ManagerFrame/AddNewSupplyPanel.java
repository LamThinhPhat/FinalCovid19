package ManagerFrame;

import ColorFont.Constant;
import table.supply;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewSupplyPanel extends JPanel {
    public AddNewSupplyPanel() {
        setBackground(Constant.my_gray);
        JPanel AddSupplyPanel = new JPanel();
        AddSupplyPanel.setLayout(new BoxLayout(AddSupplyPanel,BoxLayout.Y_AXIS));
        AddSupplyPanel.setBackground(Constant.my_gray);
        add(AddSupplyPanel);

        JLabel AddNewSupplyLabel = new JLabel("Add new supply");
        AddNewSupplyLabel.setFont(Constant.LABEL_FONT);
        AddNewSupplyLabel.setForeground(Constant.my_white);
        AddSupplyPanel.add(AddNewSupplyLabel);

        JPanel SupplyIdPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        SupplyIdPane.setBackground(Constant.my_gray);
        AddSupplyPanel.add(SupplyIdPane);
        JLabel SupplyIdLabel = new JLabel("Supply ID:      ");
        SupplyIdLabel.setFont(Constant.LABEL_FONT);
        SupplyIdLabel.setForeground(Constant.my_white);
        JTextField SupplyIdField = new JTextField();
        SupplyIdField.setColumns(30);
        SupplyIdPane.add(SupplyIdLabel);
        SupplyIdPane.add(SupplyIdField);

        JPanel SupplyNamePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        SupplyNamePane.setBackground(Constant.my_gray);
        AddSupplyPanel.add(SupplyNamePane);
        JLabel SupplyNameLabel = new JLabel("Supply name: ");
        SupplyNameLabel.setFont(Constant.LABEL_FONT);
        SupplyNameLabel.setForeground(Constant.my_white);
        JTextField SupplyNameField = new JTextField();
        SupplyNameField.setColumns(30);
        SupplyNamePane.add(SupplyNameLabel);
        SupplyNamePane.add(SupplyNameField);

        JPanel LimitDayPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LimitDayPane.setBackground(Constant.my_gray);
        AddSupplyPanel.add(LimitDayPane);
        JLabel LimitDayLabel = new JLabel("Limit day:       ");
        LimitDayLabel.setFont(Constant.LABEL_FONT);
        LimitDayLabel.setForeground(Constant.my_white);
        JTextField LimitDayField = new JTextField();
        LimitDayField.setColumns(30);
        LimitDayPane.add(LimitDayLabel);
        LimitDayPane.add(LimitDayField);

        JPanel LimitWeekPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LimitWeekPane.setBackground(Constant.my_gray);
        AddSupplyPanel.add(LimitWeekPane);
        JLabel LimitWeekLabel = new JLabel("Limit week:     ");
        LimitWeekLabel.setFont(Constant.LABEL_FONT);
        LimitWeekLabel.setForeground(Constant.my_white);
        JTextField LimitWeekField = new JTextField();
        LimitWeekField.setColumns(30);
        LimitWeekPane.add(LimitWeekLabel);
        LimitWeekPane.add(LimitWeekField);

        JPanel LimitMonthPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LimitMonthPane.setBackground(Constant.my_gray);
        AddSupplyPanel.add(LimitMonthPane);
        JLabel LimitMonthLabel = new JLabel("Limit month:  ");
        LimitMonthLabel.setFont(Constant.LABEL_FONT);
        LimitMonthLabel.setForeground(Constant.my_white);
        JTextField LimitMonthField = new JTextField();
        LimitMonthField.setColumns(30);
        LimitMonthPane.add(LimitMonthLabel);
        LimitMonthPane.add(LimitMonthField);


        JPanel LimitPersonPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LimitPersonPane.setBackground(Constant.my_gray);
        AddSupplyPanel.add(LimitPersonPane);
        JLabel LitmitPersonLabel = new JLabel("Limit person:  ");
        LitmitPersonLabel.setFont(Constant.LABEL_FONT);
        LitmitPersonLabel.setForeground(Constant.my_white);
        JTextField LimitPersonField = new JTextField();
        LimitPersonField.setColumns(30);
        LimitPersonPane.add(LitmitPersonLabel);
        LimitPersonPane.add(LimitPersonField);

        JPanel PricePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        PricePane.setBackground(Constant.my_gray);
        AddSupplyPanel.add(PricePane);
        JLabel PriceLabel = new JLabel("Price:               ");
        PriceLabel.setFont(Constant.LABEL_FONT);
        PriceLabel.setForeground(Constant.my_white);
        JTextField PriceField = new JTextField();
        PriceField.setColumns(30);
        PricePane.add(PriceLabel);
        PricePane.add(PriceField);

        JPanel AddSupplyButtonSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        AddSupplyButtonSouth.setBackground(Constant.my_gray);
        AddSupplyPanel.add(AddSupplyButtonSouth);

        JButton AddSupplyCancelButton = new JButton("Cancel");
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

        AddSupplyButtonSouth.add(AddSupplyCancelButton);

        JButton AddSupplyConfirmButton = new JButton("Confirm");
        AddSupplyConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SupplyIdField.getText().isEmpty() || SupplyNameField.getText().isEmpty() || LimitDayField.getText().isEmpty() || LimitPersonField.getText().isEmpty()
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
                        newsupply.setLimit_per_person(Integer.parseInt(LimitPersonField.getText()));
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

        AddSupplyButtonSouth.add(AddSupplyConfirmButton);

    }
}