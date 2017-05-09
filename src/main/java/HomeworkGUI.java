import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by DarthVader on 4/29/17.
 */
public class HomeworkGUI extends JFrame{
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
    private static LinkedList<Assignment> databaseAssignments = Statements.loadHomework();

    public static void main(String[] args) {

        HomeworkGUI gui = new HomeworkGUI();
    }

    protected HomeworkGUI(){
    try {
        setContentPane(rootPanel);
        setSize(new Dimension(700, 300));
        setTitle("Homework Planner");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        listModel = new DefaultListModel<Assignment>();

        homeworkList.setModel(listModel);

        homeworkList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        dateSpinner.setModel(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "MM-dd-yyyy"));

try {
    for (Assignment assignment : databaseAssignments) {

        if (assignment != null) {

            listModel.addElement(assignment);
        }
    }
}catch (NullPointerException npe){
    npe.printStackTrace();
    JOptionPane.showMessageDialog(HomeworkGUI.this, "Null Reference");

}



    }catch (NullPointerException npe){
        npe.printStackTrace();
    }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cOA = classOfAssignmenttxt.getText();
                String tOA = titleOfAssignmenttxt.getText();
                String dOA = descriptionOfAssignmenttxt.getText();
                String dD = dateSpinner.getValue().toString();




                Assignment assignment = new Assignment(cOA, tOA, dOA, dD);

                if(classOfAssignmenttxt.getText().equals("") || titleOfAssignmenttxt.getText().equals("") || descriptionOfAssignmenttxt.getText().equals("")) {

                    validateText();


                }else {

                    Statements.insertHomework(cOA, tOA, dOA, dD);

                    listModel.addElement(assignment);

                    clearText();
                }
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Assignment assignmentToDelete = homeworkList.getSelectedValue();

                if (assignmentToDelete == null){
                    JOptionPane.showMessageDialog(HomeworkGUI.this, "Please select" +
                            " homework assignment to delete");
                }else{

                    Statements.deleteHomework(assignmentToDelete);

                    listModel.removeElement(assignmentToDelete);

                    clearText();
                }


            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Assignment assignmentToUpdate = homeworkList.getSelectedValue();

                String cOA = classOfAssignmenttxt.getText();
                String tOA = titleOfAssignmenttxt.getText();
                String dOA = descriptionOfAssignmenttxt.getText();
                String dD = dateSpinner.getValue().toString();

                Assignment update = new Assignment(cOA, tOA, dOA, dD);



                if(assignmentToUpdate == null){
                    JOptionPane.showMessageDialog(HomeworkGUI.this, "Please select" +
                            "homework assignment to update");

                }else{

                    if(classOfAssignmenttxt.getText().equals("") || titleOfAssignmenttxt.getText().equals("") || descriptionOfAssignmenttxt.getText().equals("")) {

                        validateText();


                    }else {
                        Statements.deleteHomework(assignmentToUpdate);

                        Statements.insertHomework(cOA, tOA, dOA, dD);

                        listModel.removeElement(assignmentToUpdate);

                        listModel.addElement(update);

                        clearText();
                    }
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });


    }

    void clearText(){
        classOfAssignmenttxt.setText("");
        titleOfAssignmenttxt.setText("");
        descriptionOfAssignmenttxt.setText("");

    }

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
