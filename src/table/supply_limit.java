package table;

import java.sql.Date;

public class supply_limit {
    String supply_id, username;
    Date start_date,update_date;
    int use_day;
    int use_week;
    int use_month;

    public static supply_limit create_new(String supply_id, String username){
        supply_limit new_sl=new supply_limit();
        long millis=System.currentTimeMillis();

        new_sl.setSupply_id(supply_id);
        new_sl.setUsername(username);
        new_sl.setUse_month(0);
        new_sl.setUse_week(0);
        new_sl.setUse_day(0);
        new_sl.setStart_date(new Date(millis));
        new_sl.setUpdate_date(new Date(millis));

        return new_sl;
    };

    public String getSupply_id() {
        return supply_id;
    }

    public void setSupply_id(String supply_id) {
        this.supply_id = supply_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUse_day() {
        return use_day;
    }

    public void setUse_day(int use_day) {
        this.use_day = use_day;
    }

    public int getUse_week() {
        return use_week;
    }

    public void setUse_week(int use_week) {
        this.use_week = use_week;
    }

    public int getUse_month() {
        return use_month;
    }

    public void setUse_month(int use_month) {
        this.use_month = use_month;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }


}
