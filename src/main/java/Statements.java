import sun.awt.image.ImageWatched;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by DarthVader on 4/29/17.
 */
public class Statements {


    static LinkedList<Assignment> assignments = new LinkedList<>();

    public static LinkedList<Assignment> loadHomework(){

        try{
            Connection.connect();

            String allHomeworkSQL = "SELECT * FROM Assignment";

            PreparedStatement homeworkQuery = Connection.connection.prepareStatement(allHomeworkSQL);

            ResultSet allHomeworkRS = homeworkQuery.executeQuery(allHomeworkSQL);



            while(allHomeworkRS.next()){

                String classRoom = allHomeworkRS.getString("nameOfClass");
                String title = allHomeworkRS.getString("titleOfAssignment");
                String description = allHomeworkRS.getString("descriptionOfAssignment");
                String date = allHomeworkRS.getString("dueDate");

                Assignment assignment = new Assignment(classRoom, title, description, date);

                assignments.add(assignment);
            }

            Connection.disconnect();
            homeworkQuery.close();
            allHomeworkRS.close();

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

            Assignment assignment = new Assignment(classRoom, title, description, date);

            assignments.add(assignment);

            insertQuery.executeUpdate();

            Connection.disconnect();

        }catch (SQLException iHe){
            iHe.printStackTrace();
        }

    }

    public static void deleteHomework(JList<Assignment> assignmentJList){

        try{
            Connection.connect();

            String deleteStatement = "DELETE FROM Assignment VALUES (?, ?, ?, ?)";

            PreparedStatement deleteQuery = Connection.connection.prepareStatement(deleteStatement);

            deleteQuery.setString(1, assignmentJList.getSelectedValue().getNameOfClass());
            deleteQuery.setString(2, assignmentJList.getSelectedValue().getTitle());
            deleteQuery.setString(3, assignmentJList.getSelectedValue().getDescription());
            deleteQuery.setString(4, assignmentJList.getSelectedValue().getDate());

            deleteQuery.executeUpdate();

        }catch (SQLException dHe){
            dHe.printStackTrace();
        }

    }
}
