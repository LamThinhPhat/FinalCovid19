package table;

public class supply {
    String supply_id, supply_name;
    int limit_period, limit_per_person, price;

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

    public int getLimit_period() {
        return limit_period;
    }

    public void setLimit_period(int limit_period) {
        this.limit_period = limit_period;
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
