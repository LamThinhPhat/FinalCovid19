package UserFrame;

import ColorFont.Constant;
import table.payment_user;
import table.supply;
import table.supply_history;
import table.supply_limit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class BuyFrame extends JFrame {
    public BuyFrame(supply chosen, String user) {
        setTitle("Covid Management System");
        ImageIcon covid_icon=null;
        try {
            covid_icon=new ImageIcon(ImageIO.read(new File("rsc/covid_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(covid_icon.getImage());
        setSize(600, 700);
        setResizable(true);
        setLocationRelativeTo(null);

        supply_limit old_limit;
        old_limit = getDB.SupplyLimit.FunctionSupplyLimit.GetInfoSupplyLimit(chosen.getSupply_id(),user);
        supply_limit new_limit=table.supply_limit.create_new(chosen.getSupply_id(),user);
        boolean isNew=true;
        boolean reachLimit=false;
        boolean new_day=false,new_week = false,new_month=false;
        ArrayList<String> quantity = new ArrayList<String>();

        if(old_limit!=null){
            isNew=false;
            LocalDate newcv_date= new_limit.getUpdate_date().toLocalDate();
            LocalDate oldcv_update_date =old_limit.getUpdate_date().toLocalDate();
            LocalDate oldcv_create_date =old_limit.getUpdate_date().toLocalDate();
            if(newcv_date.until(oldcv_update_date, ChronoUnit.DAYS)<0) new_day = true;
            if(newcv_date.until(oldcv_update_date, ChronoUnit.WEEKS)<0) new_week = true;
            if(newcv_date.until(oldcv_create_date, ChronoUnit.MONTHS)<0) new_month = true;

            if(!new_month){
                new_limit.setStart_date(old_limit.getStart_date());
                new_limit.setUse_month(old_limit.getUse_month());
                if(!new_week) new_limit.setUse_week(old_limit.getUse_week());
                if(!new_day) new_limit.setUse_day(old_limit.getUse_day());
            }
        }

        if(chosen.getLimit_month()-new_limit.getUse_month()<chosen.getLimit_day())
            for(Integer i=1;i<=chosen.getLimit_month()-new_limit.getUse_month();i++) quantity.add(i.toString());

        else if(chosen.getLimit_week()-new_limit.getUse_week()<chosen.getLimit_day())
            for(Integer i=1;i<=chosen.getLimit_week()-new_limit.getUse_month();i++) quantity.add(i.toString());

        else for(Integer i=1;i<=chosen.getLimit_day()-new_limit.getUse_day();i++)quantity.add(i.toString());

        if(new_limit.getUse_day()==chosen.getLimit_day()||new_limit.getUse_week()==chosen.getLimit_week()||
                new_limit.getUse_month()==chosen.getLimit_month()) {
            reachLimit=true;}

        if(reachLimit){
            quantity.clear();
            quantity.add("0");
        }
        setBackground(Constant.my_gray);

        JPanel BuySupplyPanel = new JPanel();
        BuySupplyPanel.setLayout(new GridBagLayout());
        BuySupplyPanel.setBackground(Constant.my_gray);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.anchor = GridBagConstraints.WEST;


        JLabel SupplyIdLabel = new JLabel("Supply ID:");
        SupplyIdLabel.setFont(Constant.LABEL_FONT);
        SupplyIdLabel.setForeground(Constant.my_white);
        JTextField SupplyIdField = new JTextField();
        SupplyIdField.setFont(Constant.INFO_FONT);
        SupplyIdField.setColumns(15);
        SupplyIdField.setText(new_limit.getSupply_id());
        SupplyIdField.setEditable(false);



        JLabel SupplyNameLabel = new JLabel("Supply name:");
        SupplyNameLabel.setFont(Constant.LABEL_FONT);
        SupplyNameLabel.setForeground(Constant.my_white);
        JTextField SupplyNameField = new JTextField();
        SupplyNameField.setFont(Constant.INFO_FONT);
        SupplyNameField.setText(chosen.getSupply_name());
        SupplyNameField.setColumns(15);
        SupplyNameField.setEditable(false);

        JLabel LimitDayLabel = new JLabel("Limit day:");
        LimitDayLabel.setFont(Constant.LABEL_FONT);
        LimitDayLabel.setForeground(Constant.my_white);
        JTextField LimitDayField = new JTextField();
        LimitDayField.setFont(Constant.INFO_FONT);
        LimitDayField.setText(String.valueOf(chosen.getLimit_day()));
        LimitDayField.setColumns(15);
        LimitDayField.setEditable(false);

        JLabel CurrentDayLabel = new JLabel("Bought today:");
        CurrentDayLabel.setFont(Constant.LABEL_FONT);
        CurrentDayLabel.setForeground(Constant.my_white);
        JTextField CurrentDayField = new JTextField();
        CurrentDayField.setFont(Constant.INFO_FONT);
        CurrentDayField.setText(String.valueOf(new_limit.getUse_day()));
        CurrentDayField.setColumns(15);
        CurrentDayField.setEditable(false);

        JLabel LimitWeekLabel = new JLabel("Limit week:");
        LimitWeekLabel.setFont(Constant.LABEL_FONT);
        LimitWeekLabel.setForeground(Constant.my_white);
        JTextField LimitWeekField = new JTextField();
        LimitWeekField.setFont(Constant.INFO_FONT);
        LimitWeekField.setText(String.valueOf(chosen.getLimit_week()));
        LimitWeekField.setColumns(15);
        LimitWeekField.setEditable(false);

        JLabel CurrentWeekLabel = new JLabel("Bought this week:");
        CurrentWeekLabel.setFont(Constant.LABEL_FONT);
        CurrentWeekLabel.setForeground(Constant.my_white);
        JTextField CurrentWeekField = new JTextField();
        CurrentWeekField.setFont(Constant.INFO_FONT);
        CurrentWeekField.setText(String.valueOf(new_limit.getUse_week()));
        CurrentWeekField.setColumns(15);
        CurrentWeekField.setEditable(false);

        JLabel LimitMonthLabel = new JLabel("Limit month:");
        LimitMonthLabel.setFont(Constant.LABEL_FONT);
        LimitMonthLabel.setForeground(Constant.my_white);
        JTextField LimitMonthField = new JTextField();
        LimitMonthField.setFont(Constant.INFO_FONT);
        LimitMonthField.setText(String.valueOf(chosen.getLimit_month()));
        LimitMonthField.setColumns(15);
        LimitMonthField.setEditable(false);

        JLabel CurrentMonthLabel = new JLabel("Bought this month:");
        CurrentMonthLabel.setFont(Constant.LABEL_FONT);
        CurrentMonthLabel.setForeground(Constant.my_white);
        JTextField CurrentMonthField = new JTextField();
        CurrentMonthField.setFont(Constant.INFO_FONT);
        CurrentMonthField.setText(String.valueOf(new_limit.getUse_week()));
        CurrentMonthField.setColumns(15);
        CurrentMonthField.setEditable(false);


        JLabel QuantityLabel = new JLabel("Quantity:");
        QuantityLabel.setFont(Constant.LABEL_FONT);
        QuantityLabel.setForeground(Constant.my_white);
        String[] array = new String[quantity.size()];
        for(int i = 0; i < array.length; i++) {
            array[i] = quantity.get(i);
        }
        JTextField PriceField = new JTextField();
        JComboBox<String> QuantityBox = new JComboBox<>(array);
        QuantityBox.setForeground(Constant.my_white);
        QuantityBox.setBackground(new Color(77,82,77));
        QuantityBox.setFont(Constant.INFO_FONT);
        QuantityBox.setPrototypeDisplayValue("Quantity");

        JLabel PriceLabel = new JLabel("Price:");
        PriceLabel.setFont(Constant.LABEL_FONT);
        PriceLabel.setForeground(Constant.my_white);
        PriceField.setText(String.valueOf(chosen.getPrice()));
        PriceField.setEditable(false);
        PriceField.setColumns(15);
        PriceField.setFont(Constant.INFO_FONT);

        JPanel EditSupplyButtonSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        EditSupplyButtonSouth.setBackground(Constant.my_gray);

        JButton EditSupplyCancelButton = new JButton("Cancel");
        EditSupplyCancelButton.setFont(Constant.INFO_FONT);
        EditSupplyCancelButton.setForeground(Constant.my_white);
        EditSupplyCancelButton.setBackground(new Color(77,82,77));

        JButton EditSupplyConfirmButton = new JButton("Confirm");
        EditSupplyConfirmButton.setFont(Constant.INFO_FONT);
        EditSupplyConfirmButton.setForeground(Constant.my_white);
        EditSupplyConfirmButton.setBackground(new Color(77,82,77));
        boolean finalIsNew = isNew;
        EditSupplyButtonSouth.add(EditSupplyCancelButton);
        EditSupplyButtonSouth.add(EditSupplyConfirmButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        BuySupplyPanel.add(SupplyIdLabel, gbc);

        gbc.gridy++;
        BuySupplyPanel.add(SupplyNameLabel, gbc);gbc.gridy++;
        BuySupplyPanel.add(LimitDayLabel, gbc);gbc.gridy++;
        BuySupplyPanel.add(CurrentDayLabel, gbc);gbc.gridy++;
        BuySupplyPanel.add(LimitWeekLabel, gbc);gbc.gridy++;
        BuySupplyPanel.add(CurrentWeekLabel, gbc);gbc.gridy++;
        BuySupplyPanel.add(LimitMonthLabel, gbc);gbc.gridy++;
        BuySupplyPanel.add(CurrentMonthLabel, gbc);gbc.gridy++;
        BuySupplyPanel.add(QuantityLabel, gbc);gbc.gridy++;
        BuySupplyPanel.add(PriceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        BuySupplyPanel.add(SupplyIdField, gbc);

        gbc.gridy++;
        BuySupplyPanel.add(SupplyNameField, gbc);gbc.gridy++;
        BuySupplyPanel.add(LimitDayField, gbc);gbc.gridy++;
        BuySupplyPanel.add(CurrentDayField, gbc);gbc.gridy++;
        BuySupplyPanel.add(LimitWeekField, gbc);gbc.gridy++;
        BuySupplyPanel.add(CurrentWeekField, gbc);gbc.gridy++;
        BuySupplyPanel.add(LimitMonthField, gbc);gbc.gridy++;
        BuySupplyPanel.add(CurrentMonthField, gbc);gbc.gridy++;
        BuySupplyPanel.add(QuantityBox, gbc);gbc.gridy++;
        BuySupplyPanel.add(PriceField, gbc);

        if(reachLimit) {
            JLabel ReachLimit = new JLabel("Reach purchase limit");
            ReachLimit.setFont(Constant.LABEL_FONT);
            ReachLimit.setForeground(Constant.my_white);
            gbc.anchor = GridBagConstraints.EAST;
            gbc.gridx = 1;
            gbc.gridy++;
            gbc.insets = new Insets(10,0,0,0);
            BuySupplyPanel.add(ReachLimit, gbc);
            EditSupplyConfirmButton.setEnabled(false);
        }

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy++;
        BuySupplyPanel.add(EditSupplyButtonSouth, gbc);

        add(BuySupplyPanel);
        QuantityBox.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (QuantityBox.getSelectedIndex() != -1) {
                    Integer temp=Integer.parseInt(QuantityBox.getSelectedItem().toString())*chosen.getPrice();
                    PriceField.setText(String.valueOf(temp));
                }
            }});

        EditSupplyCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyFrame.this.dispose();
            }
        });

        EditSupplyConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(QuantityBox.getSelectedIndex() != -1){
                    new_limit.setUse_day(new_limit.getUse_day()+Integer.parseInt(QuantityBox.getSelectedItem().toString()));
                    new_limit.setUse_week(new_limit.getUse_week()+Integer.parseInt(QuantityBox.getSelectedItem().toString()));
                    new_limit.setUse_month(new_limit.getUse_month()+Integer.parseInt(QuantityBox.getSelectedItem().toString()));
                    if(finalIsNew){
                        getDB.SupplyLimit.FunctionSupplyLimit.AddNewSupplyLimit(new_limit);
                    }
                    else getDB.SupplyLimit.FunctionSupplyLimit.UpdateSupplyLimit(new_limit);
                    supply_history newSH=new supply_history();
                    newSH.setUsername(new_limit.getUsername());
                    newSH.setSupply_id(new_limit.getSupply_id());
                    newSH.setCreate_date(new_limit.getUpdate_date());
                    newSH.setQuantity(Integer.parseInt(QuantityBox.getSelectedItem().toString()));
                    newSH.setSHId(getDB.SupplyHistory.FunctionSupplyHistory.GetNewID(newSH));
                    getDB.SupplyHistory.FunctionSupplyHistory.AddNewSupplyHistory(newSH);
                    payment_user update_user = getDB.PaymentUser.FunctionPaymentUser.GetPaymentAccount(user);
                    update_user.setDebt(update_user.getDebt()+Integer.parseInt(QuantityBox.getSelectedItem().toString())*chosen.getPrice());
                    getDB.PaymentUser.FunctionPaymentUser.UpdateDebt(update_user);
                    BuyFrame.this.dispose();

                }
                else JOptionPane.showMessageDialog(BuyFrame.this, "Please select quantity", "Error",JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
