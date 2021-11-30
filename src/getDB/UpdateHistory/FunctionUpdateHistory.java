package getDB.UpdateHistory;

import jdbc.connect.jdbc_connector;
import table.covid_user;
import table.update_history;

import java.sql.*;
import java.util.ArrayList;

public class FunctionUpdateHistory {

    static public ArrayList<update_history> GetUpdateHistory(String username)
    {
        ArrayList<update_history> UpdateHistoryList = new ArrayList<update_history>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM update_history WHERE username = ? ORDER BY update_date DESC";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1,username);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                update_history history = new update_history();

                history.setUsername(rs.getString(1));
                history.setUpdate_date(rs.getDate(2));
                history.setOld_status(rs.getString(3));
                history.setCurrent_status(rs.getString(4));
                history.setOld_facility(rs.getString(5));
                history.setCurrent_facility(rs.getString(6));

                UpdateHistoryList.add(history);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return UpdateHistoryList;
    }

    static public void UpdateHistoryList(covid_user user, String old_status,String old_facility)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "INSERT INTO update_history (username, update_date, old_status, current_status, old_facility, current_facility)"
                + "VALUE(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);

            PrSt.setString(1, user.getUsername());
            PrSt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            PrSt.setString(3, old_status);
            PrSt.setString(4,user.getPatient_status());
            PrSt.setString(5, old_facility);
            PrSt.setString(6,user.getFacility_id());
            PrSt.executeUpdate();
        }catch(SQLException err)
        {
            err.printStackTrace();
        }
    }
}
