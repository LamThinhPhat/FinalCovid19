package UserFrame;

import ColorFont.Constant;
import table.payment_user;
import table.supply;
import table.supply_history;
import table.supply_limit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class BuyFrame extends JFrame {
    public BuyFrame(supply chosen, String user) {
        setSize(720, 800);
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
        BuySupplyPanel.setLayout(new BoxLayout(BuySupplyPanel,BoxLayout.Y_AXIS));
        BuySupplyPanel.setBackground(Constant.my_gray);
        add(BuySupplyPanel);

        JPanel SupplyIdPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        SupplyIdPane.setBackground(Constant.my_gray);
        BuySupplyPanel.add(SupplyIdPane);
        JLabel SupplyIdLabel = new JLabel("Supply ID:      ");
        SupplyIdLabel.setFont(Constant.LABEL_FONT);
        SupplyIdLabel.setForeground(Constant.my_white);
        JTextField SupplyIdField = new JTextField();
        SupplyIdField.setColumns(30);
        SupplyIdField.setText(new_limit.getSupply_id());
        SupplyIdField.setEditable(false);
        SupplyIdPane.add(SupplyIdLabel);
        SupplyIdPane.add(SupplyIdField);

        JPanel SupplyNamePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        SupplyNamePane.setBackground(Constant.my_gray);
        BuySupplyPanel.add(SupplyNamePane);
        JLabel SupplyNameLabel = new JLabel("Supply name: ");
        SupplyNameLabel.setFont(Constant.LABEL_FONT);
        SupplyNameLabel.setForeground(Constant.my_white);
        JTextField SupplyNameField = new JTextField();
        SupplyNameField.setText(chosen.getSupply_name());
        SupplyNameField.setColumns(30);
        SupplyNameField.setEditable(false);
        SupplyNamePane.add(SupplyNameLabel);
        SupplyNamePane.add(SupplyNameField);

        JPanel LimitDayPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LimitDayPane.setBackground(Constant.my_gray);
        BuySupplyPanel.add(LimitDayPane);
        JLabel LimitDayLabel = new JLabel("Limit day:       ");
        LimitDayLabel.setFont(Constant.LABEL_FONT);
        LimitDayLabel.setForeground(Constant.my_white);
        JTextField LimitDayField = new JTextField();
        LimitDayField.setText(String.valueOf(chosen.getLimit_day()));
        LimitDayField.setColumns(30);
        LimitDayField.setEditable(false);
        LimitDayPane.add(LimitDayLabel);
        LimitDayPane.add(LimitDayField);

        JPanel CurrentDay = new JPanel(new FlowLayout(FlowLayout.LEFT));
        CurrentDay.setBackground(Constant.my_gray);
        BuySupplyPanel.add(CurrentDay);
        JLabel CurrentDayLabel = new JLabel("Bought today:       ");
        CurrentDayLabel.setFont(Constant.LABEL_FONT);
        CurrentDayLabel.setForeground(Constant.my_white);
        JTextField CurrentDayField = new JTextField();
        CurrentDayField.setText(String.valueOf(new_limit.getUse_day()));
        CurrentDayField.setColumns(30);
        CurrentDayField.setEditable(false);
        CurrentDay.add(CurrentDayLabel);
        CurrentDay.add(CurrentDayField);

        JPanel LimitWeekPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LimitWeekPane.setBackground(Constant.my_gray);
        BuySupplyPanel.add(LimitWeekPane);
        JLabel LimitWeekLabel = new JLabel("Limit week:     ");
        LimitWeekLabel.setFont(Constant.LABEL_FONT);
        LimitWeekLabel.setForeground(Constant.my_white);
        JTextField LimitWeekField = new JTextField();
        LimitWeekField.setText(String.valueOf(chosen.getLimit_week()));
        LimitWeekField.setColumns(30);
        LimitWeekField.setEditable(false);
        LimitWeekPane.add(LimitWeekLabel);
        LimitWeekPane.add(LimitWeekField);

        JPanel CurrentWeek = new JPanel(new FlowLayout(FlowLayout.LEFT));
        CurrentWeek.setBackground(Constant.my_gray);
        BuySupplyPanel.add(CurrentWeek);
        JLabel CurrentWeekLabel = new JLabel("Bought this week:       ");
        CurrentWeekLabel.setFont(Constant.LABEL_FONT);
        CurrentWeekLabel.setForeground(Constant.my_white);
        JTextField CurrentWeekField = new JTextField();
        CurrentWeekField.setText(String.valueOf(new_limit.getUse_week()));
        CurrentWeekField.setColumns(30);
        CurrentWeekField.setEditable(false);
        CurrentWeek.add(CurrentWeekLabel);
        CurrentWeek.add(CurrentWeekField);

        JPanel LimitMonthPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        LimitMonthPane.setBackground(Constant.my_gray);
        BuySupplyPanel.add(LimitMonthPane);
        JLabel LimitMonthLabel = new JLabel("Limit month:  ");
        LimitMonthLabel.setFont(Constant.LABEL_FONT);
        LimitMonthLabel.setForeground(Constant.my_white);
        JTextField LimitMonthField = new JTextField();
        LimitMonthField.setText(String.valueOf(chosen.getLimit_month()));
        LimitMonthField.setColumns(30);
        LimitMonthField.setEditable(false);
        LimitMonthPane.add(LimitMonthLabel);
        LimitMonthPane.add(LimitMonthField);

        JPanel CurrentMonth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        CurrentMonth.setBackground(Constant.my_gray);
        BuySupplyPanel.add(CurrentMonth);
        JLabel CurrentMonthLabel = new JLabel("Bought this month:       ");
        CurrentMonthLabel.setFont(Constant.LABEL_FONT);
        CurrentMonthLabel.setForeground(Constant.my_white);
        JTextField CurrentMonthField = new JTextField();
        CurrentMonthField.setText(String.valueOf(new_limit.getUse_week()));
        CurrentMonthField.setColumns(30);
        CurrentMonthField.setEditable(false);
        CurrentMonth.add(CurrentMonthLabel);
        CurrentMonth.add(CurrentMonthField);

        JPanel Quantity = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Quantity.setBackground(Constant.my_gray);
        BuySupplyPanel.add(Quantity);
        JLabel QuantityLabel = new JLabel("Quantity:       ");
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
        Quantity.add(QuantityLabel);
        Quantity.add(QuantityBox);
        QuantityBox.addActionListener( new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if (QuantityBox.getSelectedIndex() != -1) {
                        Integer temp=Integer.parseInt(QuantityBox.getSelectedItem().toString())*chosen.getPrice();
                        PriceField.setText(String.valueOf(temp));
                    }
                }});

        JPanel PricePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        PricePane.setBackground(Constant.my_gray);
        BuySupplyPanel.add(PricePane);
        JLabel PriceLabel = new JLabel("Price:               ");
        PriceLabel.setFont(Constant.LABEL_FONT);
        PriceLabel.setForeground(Constant.my_white);
        PriceField.setText(String.valueOf(chosen.getPrice()));
        PriceField.setEditable(false);
        PriceField.setColumns(30);
        PricePane.add(PriceLabel);
        PricePane.add(PriceField);

        JPanel EditSupplyButtonSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        EditSupplyButtonSouth.setBackground(Constant.my_gray);
        BuySupplyPanel.add(EditSupplyButtonSouth);

        JButton EditSupplyCancelButton = new JButton("Cancel");
        EditSupplyCancelButton.setForeground(Constant.my_white);
        EditSupplyCancelButton.setBackground(new Color(77,82,77));
        EditSupplyCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyFrame.this.dispose();
            }
        });


        JButton EditSupplyConfirmButton = new JButton("Confirm");
        EditSupplyConfirmButton.setForeground(Constant.my_white);
        EditSupplyConfirmButton.setBackground(new Color(77,82,77));
        boolean finalIsNew = isNew;
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
                int shid= getDB.SupplyHistory.FunctionSupplyHistory.GetNewID(newSH);
                if(shid==-1) shid=1;
                newSH.setSHId(shid);
                getDB.SupplyHistory.FunctionSupplyHistory.AddNewSupplyHistory(newSH);
                payment_user update_user = getDB.PaymentUser.FunctionPaymentUser.GetPaymentAccount(user);
                update_user.setDebt(update_user.getDebt()+Integer.parseInt(QuantityBox.getSelectedItem().toString())*chosen.getPrice());
                getDB.PaymentUser.FunctionPaymentUser.UpdateDebt(update_user);
                BuyFrame.this.dispose();

                }
                else JOptionPane.showMessageDialog(BuyFrame.this, "Please select quantity", "Error",JOptionPane.ERROR_MESSAGE);
            }
        });

        if(reachLimit) {
            JLabel ReachLimit=new JLabel("Reach purchase limit");
            ReachLimit.setFont(Constant.LABEL_FONT);
            ReachLimit.setForeground(Constant.my_white);
            EditSupplyButtonSouth.add(ReachLimit);
            EditSupplyConfirmButton.setEnabled(false);
        }
        EditSupplyButtonSouth.add(EditSupplyCancelButton);
        EditSupplyButtonSouth.add(EditSupplyConfirmButton);



    }
}
