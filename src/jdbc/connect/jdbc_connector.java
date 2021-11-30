package jdbc.connect;
import java.sql.*;

public class jdbc_connector {
    public static Connection getConnection()
    {
        final String JBDC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/covid19_management";
        final String USER = "root";
        final String PASSWORD = "";
        Connection conn = null;
        try{
            Class.forName(JBDC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        } catch(SQLException throwable)
        {
            throwable.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return conn;
    }
}
