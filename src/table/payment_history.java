package table;


import java.sql.Date;

public class payment_history {
    String username;
    Date date_create;
    int debt_pay, new_balance;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate_create() {
        return date_create;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    public int getDebt_pay() {
        return debt_pay;
    }

    public void setDebt_pay(int debt_pay) {
        this.debt_pay = debt_pay;
    }

    public int getNew_balance() {
        return new_balance;
    }

    public void setNew_balance(int new_balance) {
        this.new_balance = new_balance;
    }
}
