package second.task;

public class Student extends Person {
    final String status;

    public Student() {
        status = "Freshman";
    }

    public String toString() {
        return "Student " + name + " is a " + status;
    }
}
