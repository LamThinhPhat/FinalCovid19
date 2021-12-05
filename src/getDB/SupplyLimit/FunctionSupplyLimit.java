package getDB.SupplyLimit;

import jdbc.connect.jdbc_connector;
import table.supply_limit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FunctionSupplyLimit {
    static public supply_limit GetInfoSupplyLimit(String supplyID, String username)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply_limit WHERE supply_id = ? and username=?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1,supplyID);
            PrSt.setString(2,username);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply_limit info = new supply_limit();
                info.setSupply_id(rs.getString(1));
                info.setUsername(rs.getString(2));
                info.setStart_date(rs.getDate(3));
                info.setUpdate_date(rs.getDate(4));
                info.setUse_day(rs.getInt(5));
                info.setUse_week(rs.getInt(6));
                info.setUse_month(rs.getInt(7));
                return info;
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return null;
    }

    static public void AddNewSupplyLimit(supply_limit new_limit)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "INSERT INTO supply_limit (supply_id, username, start_day, update_day, use_day, use_week, use_month)"
                + "VALUE(?, ?, ?, ? , ?, ? ,?)";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);

            PrSt.setString(1, new_limit.getSupply_id());
            PrSt.setString(2, new_limit.getUsername());
            PrSt.setDate(3, new_limit.getStart_date());
            PrSt.setDate(4,new_limit.getUpdate_date());
            PrSt.setInt(5,new_limit.getUse_day());
            PrSt.setInt(6,new_limit.getUse_week());
            PrSt.setInt(7,new_limit.getUse_month());

            PrSt.executeUpdate();
        }catch(SQLException err)
        {
            err.printStackTrace();
        }
    }

    static public void UpdateSupplyLimit(supply_limit new_limit)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql = "UPDATE supply_limit SET start_date = ?, update_date = ? ,use_day = ?, " +
                "use_week =?, use_month = ? " +
                "WHERE supply_id = ? and username=?";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setDate(1, new_limit.getStart_date());
            PrSt.setDate(2, new_limit.getUpdate_date());
            PrSt.setInt(3, new_limit.getUse_day());
            PrSt.setInt(4, new_limit.getUse_month());
            PrSt.setInt(5, new_limit.getUse_week());
            PrSt.setString(6, new_limit.getSupply_id());
            PrSt.setString(7, new_limit.getUsername());

            PrSt.executeUpdate();
        }catch(SQLException err)
        {
            err.printStackTrace();
        }
    }
}
