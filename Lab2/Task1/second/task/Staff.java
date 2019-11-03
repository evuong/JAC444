package second.task;

public class Staff extends Employee {
    String title;

    public Staff() {
        title = "Intern";
    }

    public String toString() {
        return "Staff member " + name + " is an " + title;
    }
}

