package getDB.Account;

import jdbc.connect.jdbc_connector;
import table.account;
import table.covid_user;
import table.related_user;

import java.sql.*;
import java.util.ArrayList;

public class FunctionAccount {

    static public ArrayList<related_user> GetRealatedUser(String username) {
        ArrayList<related_user> RealatedUserList = new ArrayList<related_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM related_user rl join account_table ac on (rl.username=ac.username) where ac.user_role = 3 and rl.username = ?";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, username);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
                related_user user = new related_user();
                user.setUsername(rs.getString(1));
                user.setRelated_username(rs.getString(2));
                RealatedUserList.add(user);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return RealatedUserList;
    }

    static public void registerAdmin(String username, String password)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql = "INSERT INTO account_table (username, pass, user_role, ban_unban)"
                + "VALUE(?, ?, ?, ? )";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);

            PrSt.setString(1, username);
            PrSt.setString(2, password);
            PrSt.setInt(3, 2);
            PrSt.setInt(4, 0);
            PrSt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    static public boolean CheckDB() {
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM account_table";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
                return false;
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return true;
    }

    static public boolean CheckExisted(String username) {
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM account_table WHERE username = ?";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, username);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return false;
    }

    static public void AddAccount(account acc) {
        Connection conn = jdbc_connector.getConnection();
        String sql = "INSERT INTO account_table (username, pass, user_role, ban_unban)"
                + "VALUE(?, ?, ?, ? )";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);

            PrSt.setString(1, acc.getUsername());
            PrSt.setString(2, acc.getPass());
            PrSt.setInt(3, acc.getUser_role());
            PrSt.setInt(4, acc.getBan_unban());
            PrSt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        }

    }

    static public void AddInfoAccount(covid_user user) {
        Connection conn = jdbc_connector.getConnection();
        String sql = "INSERT INTO covid_user (username, full_name, id, dob, house_number, address_id, patient_status, facility_id)"
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
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    static public account GetAccountToLogin(String username, String password) {
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM account_table WHERE username = ? AND pass = ?";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, username);
            PrSt.setString(2, password);
            ResultSet rs = PrSt.executeQuery();

            while (rs.next()) {
                account login = new account();
                login.setUsername(rs.getString(1));
                login.setPass(rs.getString(2));
                login.setUser_role(rs.getInt(3));
                login.setBan_unban(rs.getInt(4));
                return login;
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return null;
    }

    static public ArrayList<covid_user> GetAllCovidUserInfoFacilitySort() {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM covid_user cu join account_table ac on (cu.username=ac.username) where ac.user_role = 3 ORDER BY facility_id";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserInfoStatusSort() {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM covid_user cu join account_table ac on (cu.username=ac.username) where ac.user_role = 3 ORDER BY patient_status";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserInfoUserNameSort() {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM covid_user cu join account_table ac on (cu.username=ac.username) where ac.user_role = 3 ORDER BY cu.username";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserInfoFullNameSort() {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM covid_user cu join account_table ac on (cu.username=ac.username) where ac.user_role = 3 ORDER BY full_name";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserCitySort() {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM covid_user cu join account_table ac on (cu.username=ac.username) where ac.user_role = 3 ORDER BY address_id";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserDoBSort() {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM covid_user cu join account_table ac on (cu.username=ac.username) where ac.user_role = 3 ORDER BY dob";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserIDSort() {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM covid_user cu join account_table ac on (cu.username=ac.username) where ac.user_role = 3 ORDER BY id";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public ArrayList<covid_user> GetAllCovidUserInfo() {
        ArrayList<covid_user> CovidUser = new ArrayList<covid_user>();
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM covid_user cu join account_table ac on (cu.username=ac.username) where ac.user_role = 3";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return CovidUser;
    }

    static public covid_user GetCovidUserInfoByUserName(String username) {
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM covid_user cu join account_table ac on (cu.username=ac.username) where ac.user_role = 3 and cu.username = ?";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, username);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return null;
    }

    static public void UpdateCovidUser(covid_user user) {
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
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    static public int validateChangePass(String username, String oldPass, String newPass, String rePass) {

        Connection conn = jdbc_connector.getConnection();
        String sql = "select pass from account_table where username ='" + username + "';";

        try {
            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery(sql);
            String pas = null;
            while (rs.next()) {
                pas = rs.getString("pass");
            }

            int pass = Integer.parseInt(pas);

            if(oldPass.equals("") || newPass.equals("") || rePass.equals("")) return 1; //missing field
            if (oldPass.hashCode() != pass) return 2; //not match old pass
            else if (oldPass.equals(newPass)) return 3; //same pass
            else if (!newPass.equals(rePass)) return 4; //not match retype password
            else return 0; //all valid
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 5; //error connect db
    }

    static public void UpdatePassword(String username, String newPass) {
        Connection conn = jdbc_connector.getConnection();
        String sql = "update account_table set pass = '" + newPass.hashCode() + "' where username = '" + username + "';";

        try {
            Statement sta = conn.createStatement();
            sta.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void DeleteRelated(String username, String relatedUsername)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "DELETE FROM related_user WHERE username =? and related_username =?";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, username);
            PrSt.setString(2, relatedUsername);
            PrSt.executeUpdate();
        }catch(SQLException err)
        {
            err.printStackTrace();
        }
    }

    static public void AddRelatedAccount(String username, String relatedUsername) {
        Connection conn = jdbc_connector.getConnection();
        String sql = "INSERT INTO related_user (username, related_username)"
                + "VALUE(?, ?)";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);

            PrSt.setString(1, username);
            PrSt.setString(2, relatedUsername);
            PrSt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    static public ArrayList<String> GetUserNameNotInRelated() {
        ArrayList<String> RealatedUserList = new ArrayList<String>();
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT cu.username FROM covid_user cu join account_table ac on (cu.username=ac.username)\n" +
                " where ac.user_role = 3" +
                " AND NOT EXISTS (SELECT * from related_user rl " +
                " Where rl.related_username = cu.username)";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
                String user = rs.getString(1);
                RealatedUserList.add(user);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return RealatedUserList;
    }

}
