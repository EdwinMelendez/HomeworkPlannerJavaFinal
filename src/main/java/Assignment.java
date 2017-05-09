import java.util.Date;

/**
* Assignment class
* Getters & Setters
* Constructor
* toString
 **/
public class Assignment {

    private String nameOfClass;
    private String title;
    private String description;
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Assignment(String nameOfClass, String title, String description, Date date) {
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
