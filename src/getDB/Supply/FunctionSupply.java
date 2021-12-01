package getDB.Supply;

import jdbc.connect.jdbc_connector;
import table.supply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FunctionSupply {
    static public ArrayList<supply> GetAllSupplyInfo()
    {
        ArrayList<supply> Supplylist = new ArrayList<supply>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply supplyinfo = new supply();
                supplyinfo.setSupply_id(rs.getString(1));
                supplyinfo.setSupply_name(rs.getString(2));
                supplyinfo.setLimit_period(rs.getInt(3));
                supplyinfo.setLimit_per_person(rs.getInt(4));
                supplyinfo.setPrice(rs.getInt(5));
                Supplylist.add(supplyinfo);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Supplylist;
    }

    static public ArrayList<supply>  GetSupplyInfoByName(String supplyname)
    {
        Connection conn = jdbc_connector.getConnection();
        ArrayList<supply> Supplylist = new ArrayList<supply>();

        String sql  = "SELECT * FROM supply WHERE supply_name LIKE '%" + supplyname + "%'";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply supplyinfo = new supply();
                supplyinfo.setSupply_id(rs.getString(1));
                supplyinfo.setSupply_name(rs.getString(2));
                supplyinfo.setLimit_period(rs.getInt(3));
                supplyinfo.setLimit_per_person(rs.getInt(4));
                supplyinfo.setPrice(rs.getInt(5));
                Supplylist.add(supplyinfo);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Supplylist;
    }

    static public void DeleteSupply(String supplyid)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "DELETE FROM supply WHERE supply_id =?";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, supplyid);
            PrSt.executeUpdate();
        }catch(SQLException err)
        {
            err.printStackTrace();
        }
    }
}
