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
                supplyinfo.setLimit_day(rs.getInt(3));
                supplyinfo.setLimit_week(rs.getInt(4));
                supplyinfo.setLimit_month(rs.getInt(5));
                supplyinfo.setPrice(rs.getInt(6));
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
                supplyinfo.setLimit_day(rs.getInt(3));
                supplyinfo.setPrice(rs.getInt(4));
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

    static public boolean CheckExisted(String supplyid){
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply WHERE supply_id = ?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1,supplyid);
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

    static public supply GetInfoSupply(String supplyID)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply WHERE supply_id = ?";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1,supplyID);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply supplyinfo = new supply();
                supplyinfo.setSupply_id(rs.getString(1));
                supplyinfo.setSupply_name(rs.getString(2));
                supplyinfo.setLimit_day(rs.getInt(3));
                supplyinfo.setLimit_week(rs.getInt(4));
                supplyinfo.setLimit_month(rs.getInt(5));
                supplyinfo.setPrice(rs.getInt(6));
                return supplyinfo;
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return null;
    }

    static public void AddNewSupply(supply newsupply)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql  = "INSERT INTO supply (supply_id, supply_name, limit_day, limit_week, limit_month, limit_per_person, price)"
                + "VALUE(?, ?, ?, ? , ?, ? ,?)";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);

            PrSt.setString(1, newsupply.getSupply_id());
            PrSt.setString(2, newsupply.getSupply_name());
            PrSt.setInt(3, newsupply.getLimit_day());
            PrSt.setInt(4,newsupply.getLimit_week());
            PrSt.setInt(5,newsupply.getLimit_month());
            PrSt.setInt(6,newsupply.getPrice());
            PrSt.executeUpdate();
        }catch(SQLException err)
        {
            err.printStackTrace();
        }
    }

    static public void UpdateSupply(supply newinfosupply)
    {
        Connection conn = jdbc_connector.getConnection();
        String sql = "UPDATE supply SET supply_name = ?, limit_day = ? ,limit_week = ?, " +
                "limit_month =?, limit_per_person = ?, price =? " +
                "WHERE supply_id = ?";
        try {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            PrSt.setString(1, newinfosupply.getSupply_name());
            PrSt.setInt(2, newinfosupply.getLimit_day());
            PrSt.setInt(3, newinfosupply.getLimit_week());
            PrSt.setInt(4, newinfosupply.getLimit_month());
            PrSt.setInt(5, newinfosupply.getPrice());
            PrSt.setString(6, newinfosupply.getSupply_id());
            PrSt.executeUpdate();
        }catch(SQLException err)
        {
            err.printStackTrace();
        }
    }

    static public ArrayList<supply> GetAllSupplyIDSort()
    {
        ArrayList<supply> Supplylist = new ArrayList<supply>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply ORDER BY supply_id";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply supplyinfo = new supply();
                supplyinfo.setSupply_id(rs.getString(1));
                supplyinfo.setSupply_name(rs.getString(2));
                supplyinfo.setLimit_day(rs.getInt(3));
                supplyinfo.setLimit_week(rs.getInt(4));
                supplyinfo.setLimit_month(rs.getInt(5));
                supplyinfo.setPrice(rs.getInt(6));
                Supplylist.add(supplyinfo);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Supplylist;
    }

    static public ArrayList<supply> GetAllSupplySupplyNameSort()
    {
        ArrayList<supply> Supplylist = new ArrayList<supply>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply ORDER BY supply_name";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply supplyinfo = new supply();
                supplyinfo.setSupply_id(rs.getString(1));
                supplyinfo.setSupply_name(rs.getString(2));
                supplyinfo.setLimit_day(rs.getInt(3));
                supplyinfo.setLimit_week(rs.getInt(4));
                supplyinfo.setLimit_month(rs.getInt(5));
                supplyinfo.setPrice(rs.getInt(6));
                Supplylist.add(supplyinfo);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Supplylist;
    }

    static public ArrayList<supply> GetAllSupplyLimitDaySort()
    {
        ArrayList<supply> Supplylist = new ArrayList<supply>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply ORDER BY limit_day";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply supplyinfo = new supply();
                supplyinfo.setSupply_id(rs.getString(1));
                supplyinfo.setSupply_name(rs.getString(2));
                supplyinfo.setLimit_day(rs.getInt(3));
                supplyinfo.setLimit_week(rs.getInt(4));
                supplyinfo.setLimit_month(rs.getInt(5));
                supplyinfo.setPrice(rs.getInt(6));
                Supplylist.add(supplyinfo);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Supplylist;
    }

    static public ArrayList<supply> GetAllSupplyLimitWeekSort()
    {
        ArrayList<supply> Supplylist = new ArrayList<supply>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply ORDER BY limit_week";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply supplyinfo = new supply();
                supplyinfo.setSupply_id(rs.getString(1));
                supplyinfo.setSupply_name(rs.getString(2));
                supplyinfo.setLimit_day(rs.getInt(3));
                supplyinfo.setLimit_week(rs.getInt(4));
                supplyinfo.setLimit_month(rs.getInt(5));
                supplyinfo.setPrice(rs.getInt(6));
                Supplylist.add(supplyinfo);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Supplylist;
    }

    static public ArrayList<supply> GetAllSupplyLimitMonthSort()
    {
        ArrayList<supply> Supplylist = new ArrayList<supply>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply ORDER BY limit_month";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply supplyinfo = new supply();
                supplyinfo.setSupply_id(rs.getString(1));
                supplyinfo.setSupply_name(rs.getString(2));
                supplyinfo.setLimit_day(rs.getInt(3));
                supplyinfo.setLimit_week(rs.getInt(4));
                supplyinfo.setLimit_month(rs.getInt(5));
                supplyinfo.setPrice(rs.getInt(6));
                Supplylist.add(supplyinfo);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Supplylist;
    }

    static public ArrayList<supply> GetAllSupplyLimitPersonSort()
    {
        ArrayList<supply> Supplylist = new ArrayList<supply>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply ORDER BY limit_per_person";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply supplyinfo = new supply();
                supplyinfo.setSupply_id(rs.getString(1));
                supplyinfo.setSupply_name(rs.getString(2));
                supplyinfo.setLimit_day(rs.getInt(3));
                supplyinfo.setLimit_week(rs.getInt(4));
                supplyinfo.setLimit_month(rs.getInt(5));
                supplyinfo.setPrice(rs.getInt(6));
                Supplylist.add(supplyinfo);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Supplylist;
    }

    static public ArrayList<supply> GetAllSupplyPriceSort()
    {
        ArrayList<supply> Supplylist = new ArrayList<supply>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply ORDER BY Price";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply supplyinfo = new supply();
                supplyinfo.setSupply_id(rs.getString(1));
                supplyinfo.setSupply_name(rs.getString(2));
                supplyinfo.setLimit_day(rs.getInt(3));
                supplyinfo.setLimit_week(rs.getInt(4));
                supplyinfo.setLimit_month(rs.getInt(5));
                supplyinfo.setPrice(rs.getInt(6));
                Supplylist.add(supplyinfo);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Supplylist;
    }

    public static ArrayList<supply> GetAllSupplyInfoSort(String sortby) {
        ArrayList<supply> Supplylist = new ArrayList<supply>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply ";
        if(sortby.equals("Sort id")) sql  +=  "ORDER BY supply_id";
        else if(sortby.equals("Sort name")) sql  += "ORDER BY supply_name";
        else sql  += "ORDER BY price";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply supplyinfo = new supply();
                supplyinfo.setSupply_id(rs.getString(1));
                supplyinfo.setSupply_name(rs.getString(2));
                supplyinfo.setLimit_day(rs.getInt(3));
                supplyinfo.setLimit_week(rs.getInt(4));
                supplyinfo.setLimit_month(rs.getInt(5));
                supplyinfo.setPrice(rs.getInt(6));
                Supplylist.add(supplyinfo);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Supplylist;
    }

    public static ArrayList<supply> GetAllSupplyInfoFilter50k() {
        ArrayList<supply> Supplylist = new ArrayList<supply>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply where price<50000 ";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply supplyinfo = new supply();
                supplyinfo.setSupply_id(rs.getString(1));
                supplyinfo.setSupply_name(rs.getString(2));
                supplyinfo.setLimit_day(rs.getInt(3));
                supplyinfo.setLimit_week(rs.getInt(4));
                supplyinfo.setLimit_month(rs.getInt(5));
                supplyinfo.setPrice(rs.getInt(6));
                Supplylist.add(supplyinfo);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Supplylist;
    }

    public static ArrayList<supply> GetAllSupplyInfoFilter100k() {
        ArrayList<supply> Supplylist = new ArrayList<supply>();
        Connection conn = jdbc_connector.getConnection();
        String sql  = "SELECT * FROM supply where price<100000 ";
        try
        {
            PreparedStatement PrSt = conn.prepareStatement(sql);
            ResultSet rs = PrSt.executeQuery();
            while(rs.next())
            {
                supply supplyinfo = new supply();
                supplyinfo.setSupply_id(rs.getString(1));
                supplyinfo.setSupply_name(rs.getString(2));
                supplyinfo.setLimit_day(rs.getInt(3));
                supplyinfo.setLimit_week(rs.getInt(4));
                supplyinfo.setLimit_month(rs.getInt(5));
                supplyinfo.setPrice(rs.getInt(6));
                Supplylist.add(supplyinfo);
            }
        }catch (SQLException err)
        {
            err.printStackTrace();
        }
        return Supplylist;
    }
}
