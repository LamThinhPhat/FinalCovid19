package getDB.Address;

import jdbc.connect.jdbc_connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FunctionAddress {
    static public Set<String> GetAllProvince()
    {
        Set<String> provinces = new HashSet<String>();
        Connection conn = jdbc_connector.getConnection();
        provinces.add("");
        String sql  = "SELECT * FROM address";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                String province = rs.getString(2);
                provinces.add(province);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return provinces;
    }

    static public Set<String> GetAllDistrict(String Province)
    {
        Set<String> districts = new HashSet<String>();
        Connection conn = jdbc_connector.getConnection();
        districts.add("");
        String sql  = "SELECT * FROM address where province = ?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, Province);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                String district = rs.getString(3);
                districts.add(district);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return districts;
    }

    static public Set<String> GetAllWard(String District, String Province)
    {
        Set<String> wards = new HashSet<String>();
        Connection conn = jdbc_connector.getConnection();
        wards.add("");
        String sql  = "SELECT * FROM address where district = ? and province = ?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, District);
            PrSt.setString(2, Province);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                String ward = rs.getString(4);
                wards.add(ward);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return wards;
    }

    static public String GetAddressId(String Province, String Ward, String District)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM address where province = ? and ward = ? and district = ?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, Province);
            PrSt.setString(2, Ward);
            PrSt.setString(3, District);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                return rs.getString(1);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return null;
    }
}
