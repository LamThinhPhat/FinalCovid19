package getDB.Facility;

import jdbc.connect.jdbc_connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FunctionFacility {

    static public Set<String> getNameFacility()
    {
        Set<String> facilities = new HashSet<String>();
        Connection conn = jdbc_connector.getConnection();
        facilities.add("");
        String sql  = "SELECT * FROM facility";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                String facility = rs.getString(2);
                facilities.add(facility);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return facilities;
    }

    static public int GetCurrentQuantity(String IdFacility)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM facility where facility_id = ?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, IdFacility);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                int quantity = rs.getInt(3);
                return quantity;
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return 0;
    }

    static public int GetCapacity(String IdFacility)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM facility where facility_id = ?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, IdFacility);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                int capacity = rs.getInt(4);
                return capacity;
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return 0;
    }



    static public String getIdFacilityByName(String facilityname)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM facility where facility_name = ?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1,facilityname);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                return rs.getString(1);
            }
        }catch(SQLException err){
            err.printStackTrace();
        }
        return null;
    }



    static public String GetNameFacilityById(String ID)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM facility where facility_id = ?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1,ID);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                return rs.getString(2);
            }
        }catch(SQLException err){
            err.printStackTrace();
        }
        return null;
    }

    static public void SetCurrentQuantity(String ID, int quantity)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "UPDATE facility SET current_quantity = ? WHERE facility_id = ?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setInt(1,quantity);
            PrSt.setString(2,ID);
            PrSt.executeUpdate();
        }catch(SQLException err){
            err.printStackTrace();
        }
    }
}
