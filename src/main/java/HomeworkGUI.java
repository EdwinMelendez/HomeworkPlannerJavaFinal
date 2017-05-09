import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.LinkedList;

/**
 *GUI CODE
 *This contains all of the listeners and some validation
 * */

public class HomeworkGUI extends JFrame{

    //swing objects
    private JPanel rootPanel;
    private JTextField classOfAssignmenttxt;
    private JTextField titleOfAssignmenttxt;
    private JTextField descriptionOfAssignmenttxt;
    private JList<Assignment> homeworkList;
    private JButton addButton;
    private JButton deleteButton;
    private JButton exitButton;
    private JSpinner dateSpinner;
    private JButton updateButton;
    private DefaultListModel<Assignment> listModel;

    //LinkedList of db assignments made from loadHomework returned LinkedList
    private static LinkedList<Assignment> databaseAssignments = Statements.loadHomework();


    protected HomeworkGUI(){

    try {

        //gui stuff
        setContentPane(rootPanel);
        setSize(new Dimension(700, 300));
        setTitle("Homework Planner");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //new DefaultListModel of Assignment type
        listModel = new DefaultListModel<Assignment>();

        //sets listModel as the homeworkList model
        homeworkList.setModel(listModel);

        //restricts to single selection on JList
        homeworkList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //dateSpinner model & editor set up
        dateSpinner.setModel(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "MM-dd-yyyy"));

    //for each assignment in databaseAssignments static LinkedList
    for (Assignment assignment : databaseAssignments) {

        //if assignment is not null
        if (assignment != null) {

            //adds assignment to listModel
            listModel.addElement(assignment);
        }
    }

}catch (NullPointerException npe){
    npe.printStackTrace();
    JOptionPane.showMessageDialog(HomeworkGUI.this, "Null Reference");
}

/*
* ---------------------------------Listeners---------------------------------------
*/

        //Add Button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //variables from text fields and dateSpinner
                String cOA = classOfAssignmenttxt.getText();
                String tOA = titleOfAssignmenttxt.getText();
                String dOA = descriptionOfAssignmenttxt.getText();
                Date dD = (Date) dateSpinner.getValue();

                //creates new assignment object with text field values
                Assignment assignment = new Assignment(cOA, tOA, dOA, dD);

                //if any text fields are empty
                if(classOfAssignmenttxt.getText().equals("") || titleOfAssignmenttxt.getText().equals("") || descriptionOfAssignmenttxt.getText().equals("")) {

                    //runs validator method
                    validateText();

                }else {

                    //inserts text field values into insert method to store in db
                    Statements.insertHomework(cOA, tOA, dOA, dD);

                    //adds assignment to listModel
                    listModel.addElement(assignment);

                    //clears text fields
                    clearText();
                }
            }
        });

        //Delete Button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //creates new assignment from the JList selected value
                Assignment assignmentToDelete = homeworkList.getSelectedValue();

                //if assignment is null
                if (assignmentToDelete == null){

                    //pop up message
                    JOptionPane.showMessageDialog(HomeworkGUI.this, "Please select" +
                            " homework assignment to delete");

                }else {

                    //sends assignment to delete method to delete from db
                    Statements.deleteHomework(assignmentToDelete);

                    //removes assignment from listModel
                    listModel.removeElement(assignmentToDelete);

                    //clears text fields
                    clearText();
                }
            }
        });

        //Update Button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //creates assignment from JList selected value
                Assignment assignmentToUpdate = homeworkList.getSelectedValue();

                //assigns text fields and dateSpinner to variables
                String cOA = classOfAssignmenttxt.getText();
                String tOA = titleOfAssignmenttxt.getText();
                String dOA = descriptionOfAssignmenttxt.getText();
                Date dD = (Date) dateSpinner.getValue();

                //constructs new Assignment that will replace old
                Assignment update = new Assignment(cOA, tOA, dOA, dD);

                //if assignment is null
                if(assignmentToUpdate == null){
                    JOptionPane.showMessageDialog(HomeworkGUI.this, "Please select" +
                            " homework assignment to update");

                }else {

                    //if text fields are empty
                    if(classOfAssignmenttxt.getText().equals("") || titleOfAssignmenttxt.getText().equals("") || descriptionOfAssignmenttxt.getText().equals("")) {

                        //run validator method
                        validateText();

                    }else {

                        //deletes the select JList value from the database
                        Statements.deleteHomework(assignmentToUpdate);

                        //inserts text field values into new assignment to be created
                        Statements.insertHomework(cOA, tOA, dOA, dD);

                        //removes previous assignment value from JList
                        listModel.removeElement(assignmentToUpdate);

                        //adds newly updated assignment to listModel
                        listModel.addElement(update);

                        //clears text fields
                        clearText();
                    }
                }
            }
        });

        //Text Field Populate
        homeworkList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //creates assignment from selected value in JList
                Assignment assignmentToPopulate = homeworkList.getSelectedValue();

                //sets the text fields and dateSpinner to the selected assignment
                classOfAssignmenttxt.setText(assignmentToPopulate.getNameOfClass());
                titleOfAssignmenttxt.setText(assignmentToPopulate.getTitle());
                descriptionOfAssignmenttxt.setText(assignmentToPopulate.getDescription());
                dateSpinner.setValue(assignmentToPopulate.getDate());
            }
        });

        //Exit Button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    //clear text method empties text fields
    void clearText(){

        classOfAssignmenttxt.setText("");
        titleOfAssignmenttxt.setText("");
        descriptionOfAssignmenttxt.setText("");
    }

    //validate text method prompts user to enter a value if text field is empty
    void validateText(){

        if(classOfAssignmenttxt.getText().equals("")){
            classOfAssignmenttxt.grabFocus();
            JOptionPane.showMessageDialog(HomeworkGUI.this, "Please enter the name of class");
        }

        if(titleOfAssignmenttxt.getText().equals("")){
            titleOfAssignmenttxt.grabFocus();
            JOptionPane.showMessageDialog(HomeworkGUI.this, "Please enter the title of assignment");
        }

        if(descriptionOfAssignmenttxt.getText().equals("")){
            descriptionOfAssignmenttxt.grabFocus();
            JOptionPane.showMessageDialog(HomeworkGUI.this, "Please enter the description of assignment");
        }
    }
}
