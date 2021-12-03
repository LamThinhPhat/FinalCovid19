package getDB.PaymentUser;

import jdbc.connect.jdbc_connector;
import table.payment_user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FunctionPaymentUser {
    static public payment_user GetPaymentAccount(String username)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM payment_user WHERE username=? ";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1,username);
            ResultSet rs = PrSt.executeQuery();

            while(rs.next())
            {
                payment_user login = new payment_user();
                login.setUsername(rs.getString(1));
                login.setBalance(rs.getInt(2));
                login.setDebt(rs.getInt(3));
                return login;
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return null;
    }

    static public ArrayList<payment_user> GetAllPaymentAccount()
    {
        ArrayList<payment_user> list = new ArrayList<payment_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM payment_user ";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();

            while(rs.next())
            {
                payment_user user = new payment_user();
                user.setUsername(rs.getString(1));
                user.setBalance(rs.getInt(2));
                user.setDebt(rs.getInt(3));
                list.add(user);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return list;
    }
}
