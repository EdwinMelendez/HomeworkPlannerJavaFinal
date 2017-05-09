import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;

/**
 *Statements Class
 *Method that loads homework assignments into a LinkedList and returns that LinkedList
 *Method that inserts assignments into database
 *Method that deletes assignments from database
 * */

public class Statements {

    public static LinkedList<Assignment> loadHomework(){

        try{
            //establish connection
            Connection.connect();

            //sql string
            String allHomeworkSQL = "SELECT * FROM Assignment";

            //creating connection statement
            Statement statement = Connection.connection.createStatement();

            //result set for statement executed with sql string
            ResultSet allHomeworkRS = statement.executeQuery(allHomeworkSQL);

            //new LinkedList to hold assignment objects
            LinkedList<Assignment> assignments = new LinkedList<>();

            //while result set has next
            while(allHomeworkRS.next()){

                //assign row values from result set to variables
                String classRoom = allHomeworkRS.getString("nameOfClass");
                String title = allHomeworkRS.getString("titleOfAssignment");
                String description = allHomeworkRS.getString("descriptionOfAssignment");
                String date = allHomeworkRS.getString("dueDate");

                //converting string variable to date type variable
                Date dueDate = new Date(date);

                //creating a new assignment object and assigning variables to constructor
                Assignment assignment = new Assignment(classRoom, title, description, dueDate);

                //adds newly created assignment object to LinkedList of assignments
                assignments.add(assignment);
            }
            //disconnect
            Connection.disconnect();

            //returns LinkedList of assignment objects
            return assignments;

        //catch prints stack trace
        }catch (SQLException lHe){
            lHe.printStackTrace();
        }

        //returns null if nothing found
       return null;
    }

    public static void insertHomework(String classRoom, String title, String description, Date date){

        try{

            Connection.connect();

            //sql insert string
            String insertStatement = "INSERT INTO Assignment VALUES (?, ?, ?, ?)";

            //prepared statement using insert string
            PreparedStatement insertQuery = Connection.connection.prepareStatement(insertStatement);

            //set db values based on the fed parameters of method
            insertQuery.setString(1, classRoom);
            insertQuery.setString(2, title);
            insertQuery.setString(3, description);
            insertQuery.setString(4, date.toString()); //converts date value to string

            //creates new LinkedList of new assignment objects
            LinkedList<Assignment> newAssignment = new LinkedList<>();

            //constructs new assignment
            Assignment assignment = new Assignment(classRoom, title, description, date);

            //adds new assignment to new assignment LinkedList
            newAssignment.add(assignment);

            //execute update the prepared statement
            insertQuery.executeUpdate();

            //disconnect
            Connection.disconnect();

        }catch (SQLException iHe){
            iHe.printStackTrace();
        }
    }

    public static void deleteHomework(Assignment assignment){

        try{
            Connection.connect();

            //delete sql string
            String deleteStatement = "DELETE FROM Assignment WHERE nameOfClass = ? AND titleOfAssignment = ? AND " +
                    "descriptionOfAssignment = ? AND dueDate = ?";

            //prepared statement
            PreparedStatement deleteQuery = Connection.connection.prepareStatement(deleteStatement);

            //deletes row based on values
            deleteQuery.setString(1, assignment.getNameOfClass());
            deleteQuery.setString(2, assignment.getTitle());
            deleteQuery.setString(3, assignment.getDescription());
            deleteQuery.setString(4, assignment.getDate().toString());

            //executes prepared statement
            deleteQuery.executeUpdate();

        }catch (SQLException dHe){
            dHe.printStackTrace();
        }
    }
}
