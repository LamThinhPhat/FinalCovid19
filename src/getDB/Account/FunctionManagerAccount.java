package getDB.Account;

import jdbc.connect.jdbc_connector;
import table.covid_user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FunctionManagerAccount {
    static public ArrayList<covid_user> GetAllManagerInfo()
    {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "select cu.username,cu.full_name,cu.id,cu.dob,cu.house_number,cu.address_id,cu.patient_status,cu.facility_id,ac.ban_unban" +
                " from covid_user cu join account_table ac on (cu.username=ac.username)"+
                " where ac.user_role=1";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                covid_user user = new covid_user();
                user.setUsername(rs.getString(1));
                user.setFull_name(rs.getString(2));
                user.setId(rs.getString(3));
                user.setDob(rs.getDate(4));
                user.setHouse_number(rs.getString(5));
                user.setAddress_id(rs.getString(6));
                user.setPatient_status(rs.getString(7));
                user.setFacility_id(rs.getString(8));
                user.setBan_unban(rs.getInt(9));
                CovidUser.add(user);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllManagerSort(String sortby)
    {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql="select cu.username,cu.full_name,cu.id,cu.dob,cu.house_number,cu.address_id,cu.patient_status,cu.facility_id,ac.ban_unban" +
                " from covid_user cu join account_table ac on (cu.username=ac.username)"+
                " where ac.user_role=1 ";;
        if(sortby.equals("Sort Facility")) sql  +=  "ORDER BY facility_id";
        else if(sortby.equals("Sort DoB")) sql  += "ORDER BY dob";
        else if(sortby.equals("Sort City")) sql  += "ORDER BY address_id";
        else if(sortby.equals("Sort fullname")) sql  += "ORDER BY full_name";
        else if(sortby.equals("Sort ID")) sql  += " ORDER BY id";
        else if(sortby.equals("Sort status")) sql  += " ORDER BY patient_status";
        else sql  += "ORDER BY username";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                covid_user user = new covid_user();
                user.setUsername(rs.getString(1));
                user.setFull_name(rs.getString(2));
                user.setId(rs.getString(3));
                user.setDob(rs.getDate(4));
                user.setHouse_number(rs.getString(5));
                user.setAddress_id(rs.getString(6));
                user.setPatient_status(rs.getString(7));
                user.setFacility_id(rs.getString(8));
                user.setBan_unban(rs.getInt(9));
                CovidUser.add(user);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return CovidUser;
    }
    static public covid_user GetManagerInfoByUserName(String username)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql="select cu.username,cu.full_name,cu.id,cu.dob,cu.house_number,cu.address_id,cu.patient_status,cu.facility_id,ac.ban_unban" +
                " from covid_user cu join account_table ac on (cu.username=ac.username)"+
                " where ac.user_role=1";
        sql+=" and cu.username=?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1,username);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                covid_user user = new covid_user();
                user.setUsername(rs.getString(1));
                user.setFull_name(rs.getString(2));
                user.setId(rs.getString(3));
                user.setDob(rs.getDate(4));
                user.setHouse_number(rs.getString(5));
                user.setAddress_id(rs.getString(6));
                user.setPatient_status(rs.getString(7));
                user.setFacility_id(rs.getString(8));
                user.setBan_unban(rs.getInt(9));
                return user;
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return null;
    }
    static public void Ban_Manager_DB(covid_user user)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql = "UPDATE account_table SET" +
                " ban_unban=?"+"  WHERE USERNAME = ?";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);

            PrSt.setInt(1,user.getBan_unban());
            PrSt.setString(2, user.getUsername());

            PrSt.executeUpdate();
        }catch(SQLException err)
        {
            err.printStackTrace();
        }
    }
}
