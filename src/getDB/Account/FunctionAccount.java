package getDB.Account;

import jdbc.connect.jdbc_connector;
import table.account;
import table.covid_user;
import table.related_user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FunctionAccount {

    static public ArrayList<related_user> GetRealatedUser(String username)
    {
        ArrayList<related_user> RealatedUserList = new ArrayList<related_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM related_user WHERE username = ?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1,username);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                related_user user = new related_user();
                user.setUsername(rs.getString(1));
                user.setRelated_username(rs.getString(2));
                RealatedUserList.add(user);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return RealatedUserList;
    }

    static public boolean CheckExisted(String username){
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM account_table WHERE username = ?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1,username);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                return true;
            }
        }catch(SQLException err){
            err.printStackTrace();
        }
        return false;
    }
    static public void AddAccount(account acc)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "INSERT INTO account_table (username, pass, user_role, ban_unban)"
                + "VALUE(?, ?, ?, ? )";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);

            PrSt.setString(1, acc.getUsername());
            PrSt.setString(2, acc.getPass());
            PrSt.setInt(3, acc.getUser_role());
            PrSt.setInt(4,acc.getBan_unban());
            PrSt.executeUpdate();
        }catch(SQLException err)
        {
            err.printStackTrace();
        }

    }

    static public void AddInfoAccount(covid_user user)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "INSERT INTO covid_user (username, full_name, id, dob, house_number, address_id, patient_status, facility_id)"
                + "VALUE(?, ?, ?, ?, ?, ?, ?, ? )";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, user.getUsername());
            PrSt.setString(2, user.getFull_name());
            PrSt.setString(3, user.getId());
            PrSt.setDate(4, user.getDob());
            PrSt.setString(5, user.getHouse_number());
            PrSt.setString(6, user.getAddress_id());
            PrSt.setString(7, user.getPatient_status());
            PrSt.setString(8, user.getFacility_id());
            PrSt.executeUpdate();
        }catch(SQLException err)
        {
            err.printStackTrace();
        }
    }

    static public account GetAccountToLogin(String username, String password)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM account_table WHERE username = ? AND pass = ?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1,username);
            PrSt.setString(2,password);
            ResultSet rs = PrSt.executeQuery();

            while(rs.next())
            {
                account login = new account();
                login.setUsername(rs.getString(1));
                login.setPass(rs.getString(2));
                login.setUser_role(rs.getInt(3));
                login.setBan_unban(rs.getInt(4));
                return login;
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return null;
    }

    static public ArrayList<covid_user> GetAllCovidUserInfoFacilitySort()
    {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM covid_user ORDER BY facility_id";
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
                CovidUser.add(user);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserInfoStatusSort()
    {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM covid_user ORDER BY patient_status";
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
                CovidUser.add(user);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserInfoUserNameSort()
    {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM covid_user ORDER BY username";
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
                CovidUser.add(user);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserInfoFullNameSort()
    {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM covid_user ORDER BY full_name";
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
                CovidUser.add(user);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserCitySort()
    {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM covid_user ORDER BY address_id";
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
                CovidUser.add(user);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserDoBSort()
    {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM covid_user ORDER BY dob";
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
                CovidUser.add(user);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserIDSort()
    {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM covid_user ORDER BY id";
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
                CovidUser.add(user);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserInfo()
    {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM covid_user";
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
                CovidUser.add(user);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public covid_user GetCovidUserInfoByUserName(String username)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM covid_user WHERE username = ?";
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
                return user;
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return null;
    }

    static public void UpdateCovidUser(covid_user user)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql = "UPDATE covid_user SET" +
                " username = ?, full_name = ?, id = ?, dob = ?, house_number = ?, address_id = ?" +
                    ",patient_status = ?, facility_id = ?  WHERE USERNAME = ?";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);

            PrSt.setString(1, user.getUsername());
            PrSt.setString(2, user.getFull_name());
            PrSt.setString(3, user.getId());
            PrSt.setDate(4, user.getDob());
            PrSt.setString(5, user.getHouse_number());
            PrSt.setString(6, user.getAddress_id());
            PrSt.setString(7, user.getPatient_status());
            PrSt.setString(8, user.getFacility_id());
            PrSt.setString(9, user.getUsername());

            PrSt.executeUpdate();
        }catch(SQLException err)
        {
            err.printStackTrace();
        }
    }

}
