package getDB.SupplyHistory;

import jdbc.connect.jdbc_connector;
import table.supply_history;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FunctionSupplyHistory {

    static public ArrayList<supply_history> GetSupplyHistory(String username) {
        ArrayList<supply_history> SupplyHistoryList = new ArrayList<supply_history>();
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM supply_history WHERE username = ? ORDER BY sh_id DESC";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, username);
            ResultSet rs = PrSt.executeQuery();
            while (rs.next()) {
                supply_history history = new supply_history();

                history.setSHId(rs.getInt(1));
                history.setSupply_id(rs.getString(2));
                history.setUsername(rs.getString(3));
                history.setCreate_date(rs.getDate(4));
                history.setQuantity(rs.getInt(5));
                SupplyHistoryList.add(history);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return SupplyHistoryList;
    }

    static public int GetPrice(String id, String username, int sh_id) {
        System.out.println(username);
        ArrayList<supply_history> SupplyHistoryList = new ArrayList<supply_history>();
        Connection conn = jdbc_connector.getConnection();
        int result=0;
        String sql = "select (SH.quantity*SP.price)  " +
                "from supply_history SH " +
                "join supply SP on (SP.supply_id=SH.supply_id) where SH.supply_id=? and SH.username=? and SH.sh_id=?;";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, id);
            PrSt.setString(2, username);
            PrSt.setInt(3, sh_id);
            ResultSet rs = PrSt.executeQuery();
            rs.next();
            result= rs.getInt(1);
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return result;
    }

    static public void AddNewSupplyHistory(supply_history new_history)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "INSERT INTO supply_history (sh_id,supply_id, username, create_date, quantity) "
                + "VALUE(?, ?, ?, ?, ?)";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);

            PrSt.setInt(1,new_history.getSHId());
            PrSt.setString(2, new_history.getSupply_id());
            PrSt.setString(3, new_history.getUsername());
            PrSt.setDate(4, new_history.getCreate_date());
            PrSt.setInt(5,new_history.getQuantity());

            PrSt.executeUpdate();
        }catch(SQLException err)
        {
            err.printStackTrace();
        }
    }

    static public int GetNewID(supply_history sh) {
        Connection conn = jdbc_connector.getConnection();
        String sql = "SELECT * FROM supply_history WHERE username = ? and supply_id = ? ORDER BY sh_id DESC";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, sh.getSupply_id());
            PrSt.setString(2, sh.getUsername());
            ResultSet rs = PrSt.executeQuery();
            while(rs.next()){
                if(rs.wasNull()) return -1;
            else return rs.getInt(1)+1;}


        } catch (SQLException err) {
            err.printStackTrace();
        }
        return -1;
    }
}
