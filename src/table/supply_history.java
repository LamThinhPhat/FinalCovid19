package table;

import java.sql.Date;

public class supply_history {
    String supply_id, username, supply_name;
    Date create_date;
    int sh_id;
    int quantity;

    public String getSupply_name() {
        return supply_name;
    }

    public void setSupply_name(String supply_name) {
        this.supply_name = supply_name;
    }

    public int getSHId() {
        return sh_id;
    }

    public void setSHId(int sh_id) {
        this.sh_id = sh_id;
    }

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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
