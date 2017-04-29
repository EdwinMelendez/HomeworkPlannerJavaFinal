import javax.swing.*;
import java.awt.*;

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


    protected HomeworkGUI(){

        setContentPane(rootPanel);
        setSize(new Dimension(550, 550));
        setTitle("Homework Planner");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        listModel = new DefaultListModel<Assignment>();
        homeworkList.setModel(listModel);
    }



}
