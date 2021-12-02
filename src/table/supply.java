package table;

public class supply {
    String supply_id, supply_name;
    int limit_day;
    int limit_month;

    public int getLimit_month() {
        return limit_month;
    }

    public void setLimit_month(int limit_month) {
        this.limit_month = limit_month;
    }

    public int getLimit_week() {
        return limit_week;
    }

    public void setLimit_week(int limit_week) {
        this.limit_week = limit_week;
    }

    int limit_week;
    int limit_per_person;
    int price;

    public String getSupply_id() {
        return supply_id;
    }

    public void setSupply_id(String supply_id) {
        this.supply_id = supply_id;
    }

    public String getSupply_name() {
        return supply_name;
    }

    public void setSupply_name(String supply_name) {
        this.supply_name = supply_name;
    }

    public int getLimit_day() {
        return limit_day;
    }

    public void setLimit_day(int limit_day) {
        this.limit_day = limit_day;
    }

    public int getLimit_per_person() {
        return limit_per_person;
    }

    public void setLimit_per_person(int limit_per_person) {
        this.limit_per_person = limit_per_person;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
