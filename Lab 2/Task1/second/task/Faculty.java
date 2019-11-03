package second.task;

public class Faculty extends Employee {
    String office_times;
    String office_dates;
    String rank;

    public Faculty() {
        office_times = "11:30 to 1:30";
        office_dates = "Monday to Wednesday";
        rank = "Professor";
    }

    public String toString() {
        return "Faculty member " + name + " is a " + rank + " with office times of " + office_times + " on " + office_dates;
    }
}