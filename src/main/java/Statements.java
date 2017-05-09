import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


public class Statements {



    public static LinkedList<Assignment> loadHomework(){

        try{
            Connection.connect();

            String allHomeworkSQL = "SELECT * FROM Assignment";

            Statement statement = Connection.connection.createStatement();

            ResultSet allHomeworkRS = statement.executeQuery(allHomeworkSQL);

            LinkedList<Assignment> assignments = new LinkedList<>();

            while(allHomeworkRS.next()){

                String classRoom = allHomeworkRS.getString("nameOfClass");
                String title = allHomeworkRS.getString("titleOfAssignment");
                String description = allHomeworkRS.getString("descriptionOfAssignment");
                String date = allHomeworkRS.getString("dueDate");

                Assignment assignment = new Assignment(classRoom, title, description, date);

                assignments.add(assignment);
            }

            Connection.disconnect();


            return assignments;


        }catch (SQLException lHe){

            lHe.printStackTrace();
        }

       return null;
    }

    public static void insertHomework(String classRoom, String title, String description, String date){

        try{
            Connection.connect();

            String insertStatement = "INSERT INTO Assignment VALUES (?, ?, ?, ?)";

            PreparedStatement insertQuery = Connection.connection.prepareStatement(insertStatement);

            insertQuery.setString(1, classRoom);
            insertQuery.setString(2, title);
            insertQuery.setString(3, description);
            insertQuery.setString(4, date);

            LinkedList<Assignment> newAssignment = new LinkedList<>();

            Assignment assignment = new Assignment(classRoom, title, description, date);

            newAssignment.add(assignment);

            insertQuery.executeUpdate();

            Connection.disconnect();

        }catch (SQLException iHe){
            iHe.printStackTrace();
        }

    }

    public static void deleteHomework(Assignment assignment){

        try{
            Connection.connect();

            String deleteStatement = "DELETE FROM Assignment WHERE nameOfClass = ? AND titleOfAssignment = ? AND " +
                    "descriptionOfAssignment = ? AND dueDate = ?";

            PreparedStatement deleteQuery = Connection.connection.prepareStatement(deleteStatement);

            deleteQuery.setString(1, assignment.getNameOfClass());
            deleteQuery.setString(2, assignment.getTitle());
            deleteQuery.setString(3, assignment.getDescription());
            deleteQuery.setString(4, assignment.getDate());

            deleteQuery.executeUpdate();

        }catch (SQLException dHe){
            dHe.printStackTrace();
        }

    }




}
