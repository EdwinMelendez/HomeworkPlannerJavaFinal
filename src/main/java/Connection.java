import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *This Connection class was derived from http://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
 * Considering there are only so many ways to connect to a db this is fairly standard from what I've seen online
 * */

public class Connection {

    static java.sql.Connection connection; //static connection

    public static void connect() {  //connection method

        try {

            try {
                Class.forName("org.sqlite.JDBC");

            } catch (ClassNotFoundException cnfe) {

                cnfe.printStackTrace();
            }

            String url = "jdbc:sqlite:Planner.db"; //url pointing to database path

            connection = DriverManager.getConnection(url); //connects using url

        }catch (SQLException sqle){

            sqle.printStackTrace();
        }
    }

    public static void disconnect(){

        if(connection != null){ //if connection is not null
            try{
                connection.close(); //close connection

            }catch (SQLException dsqle){

                dsqle.printStackTrace();
            }
        }
    }
}
