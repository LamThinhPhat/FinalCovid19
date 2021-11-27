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

    static public String getIdFacility(String facilityname)
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
}
