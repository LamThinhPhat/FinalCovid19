package table;

import java.sql.Date;

public class update_history {
    String username, old_status, current_status, old_facility, current_facility;
    Date update_date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOld_status() {
        return old_status;
    }

    public void setOld_status(String old_status) {
        this.old_status = old_status;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }

    public String getOld_facility() {
        return old_facility;
    }

    public void setOld_facility(String old_facility) {
        this.old_facility = old_facility;
    }

    public String getCurrent_facility() {
        return current_facility;
    }

    public void setCurrent_facility(String current_facility) {
        this.current_facility = current_facility;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}
