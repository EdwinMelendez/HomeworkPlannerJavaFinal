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
    private JList homeworkList;
    private JButton addButton;
    private JButton deleteButton;
    private JButton exitButton;
    private JTextField dateFieldtxt;
    private DefaultListModel<Assignment> listModel;

    public static void main(String[] args) {

        HomeworkGUI gui = new HomeworkGUI();
    }

    protected HomeworkGUI(){
    try {
        setContentPane(rootPanel);
        setSize(new Dimension(550, 550));
        setTitle("Homework Planner");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        listModel = new DefaultListModel<Assignment>();

        homeworkList.setModel(listModel);

        LinkedList<Assignment> databaseAssignments = Statements.assignments;



        for (Assignment assignment : databaseAssignments) {
            if(assignment != null) {
                listModel.addElement(assignment);
            }
        }

    }catch (NullPointerException npe){
        npe.printStackTrace();
    }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Statements.insertHomework(classOfAssignmenttxt.getText(),titleOfAssignmenttxt.getText(),
                        descriptionOfAssignmenttxt.getText(), dateFieldtxt.getText());

                Statements.loadHomework();


            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Statements.deleteHomework(homeworkList);
            }
        });


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });
    }





}
