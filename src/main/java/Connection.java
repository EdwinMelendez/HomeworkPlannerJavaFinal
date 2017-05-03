import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Connection {


    static java.sql.Connection connection;


    public static void connect() {

        try {

            try {
                Class.forName("org.sqlite.JDBC");

            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }

            String url = "jdbc:sqlite:Planner.db";
            connection = DriverManager.getConnection(url);

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public static void disconnect(){

        if(connection != null){
            try{
                connection.close();

            }catch (SQLException dsqle){
                dsqle.printStackTrace();
            }
        }
    }




}
