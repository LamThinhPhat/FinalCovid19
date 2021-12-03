package getDB.Statistic;

import jdbc.connect.jdbc_connector;
import table.update_history;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class FunctionStatistic {
    static public Set<Date> GetAllDate(String db_url)
    {
        Set<Date> GetAllDate = new HashSet<Date>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "select date(update_date) " +
                "from " + db_url;
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next())
            {
                GetAllDate.add(rs.getDate(1));
            }
        }
        catch (SQLException err)
        {
            err.printStackTrace();
        }
        return GetAllDate;
    }
    static public Map<String,Integer> GetPerPerson(Date dateChoose)
    {
        Map<String,Integer> Total = new HashMap<String,Integer>();
        Total.put("F0",0);
        Total.put("F1",0);
        Total.put("F2",0);
        Total.put("F3",0);
        Total.put("NI",0);
        Total.put("RV",0);
        Connection conn = jdbc_connector.getConnection();
        String sql = "select date(update_date), current_status, count(*) as TotalPerson " +
                "from covid19_management.update_history where date(update_date) = ?" +
                "group by date(update_date), current_status";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setDate(1, dateChoose);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next())
            {
                Total.put(rs.getString(2),Total.get(rs.getString(2)) + rs.getInt(3));
            }
        }
        catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Total;
    }

    static public Map<String,Integer> GetPerPersonNow()
    {
        Map<String,Integer> Total = new HashMap<String,Integer>();
        Total.put("F0",0);
        Total.put("F1",0);
        Total.put("F2",0);
        Total.put("F3",0);
        Total.put("NI",0);
        Total.put("RV",0);
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT patient_status, count(*) as TotalPerson  FROM covid_user cu join account_table ac on (cu.username=ac.username)" +
                " where ac.user_role = 3 group by patient_status";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next())
            {
                Total.put(rs.getString(1),Total.get(rs.getString(1)) + rs.getInt(2));
            }
        }
        catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Total;
    }

    static public Map<String,Integer> GetTransfer(Date dateChoose)
    {
        Map<String,Integer> Total = new HashMap<String,Integer>();
        Connection conn = jdbc_connector.getConnection();
        String sql = "select date(update_date), old_status, current_status, count(*) as SymptomTransfer " +
                "from update_history where old_status <> current_status and date(update_date) = ? " +
                "group by date(update_date), old_status, current_status";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setDate(1, dateChoose);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next())
            {
                String Transfer = rs.getString(2) + " -> " + rs.getString(3);
                Total.put(Transfer, rs.getInt(4));
            }
        }
        catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Total;
    }

}
