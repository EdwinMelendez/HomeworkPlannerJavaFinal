import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DarthVader on 4/29/17.
 */
public class Assignment {

    private String nameOfClass;
    private String title;
    private String description;
    private String date;

//

    public String getNameOfClass() {
        return nameOfClass;
    }

    public void setNameOfClass(String nameOfClass) {
        this.nameOfClass = nameOfClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Assignment(String nameOfClass, String title, String description, String date) {
        this.nameOfClass = nameOfClass;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Assignment [" +
                "Class: '" + nameOfClass + '\'' +
                ", Title: '" + title + '\'' +
                ", Description: '" + description + '\'' +
                ", Due date: " + date + " " +
                ']';
    }
}
