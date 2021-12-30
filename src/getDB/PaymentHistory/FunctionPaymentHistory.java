package getDB.PaymentHistory;

import jdbc.connect.jdbc_connector;
import table.payment_history;
import table.payment_user;

import java.sql.*;
import java.util.ArrayList;

public class FunctionPaymentHistory {
    static public ArrayList<payment_history> GetUpdateHistory(String username)
    {
        ArrayList<payment_history> PaymentHistoryList = new ArrayList<payment_history>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM payment_history WHERE username = ? ORDER BY date_create DESC";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1,username);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                payment_history history = new payment_history();

                history.setUsername(rs.getString(1));
                history.setDate_create(rs.getDate(2));
                history.setDebt_pay(rs.getInt(3));
                history.setNew_balance(rs.getInt(4));
                PaymentHistoryList.add(history);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return PaymentHistoryList;
    }

    static public void UpdatePaymentHistory(String username, int debt_pay, int new_balance)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "INSERT INTO payment_history (username, date_create, debt_pay, new_balance) "
                + "VALUE(?, ?, ?, ?)";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);

            PrSt.setString(1, username);
            PrSt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            PrSt.setInt(3, debt_pay);
            PrSt.setInt(4, new_balance);
            PrSt.executeUpdate();
        }catch(SQLException err)
        {
            err.printStackTrace();
        }
    }
}
