package table;

public class facility {
    String facility_id, facility_name;

    public String getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(String facility_id) {
        this.facility_id = facility_id;
    }

    public String getFacility_name() {
        return facility_name;
    }

    public void setFacility_name(String facility_name) {
        this.facility_name = facility_name;
    }

    public int getCurrent_quantity() {
        return current_quantity;
    }

    public void setCurrent_quantity(int current_quantity) {
        this.current_quantity = current_quantity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    int current_quantity, capacity;

}
