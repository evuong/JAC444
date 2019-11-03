package second.task;

import first.task.MyDate;
import java.time.Month;

public class Employee extends Person {
    int office;
    int salary;
    MyDate hiredate = new MyDate(561555550000L);
    String hire = hiredate.getYear() + " " + Month.of(hiredate.getMonth()) + " " + hiredate.getDay();

    public Employee() {
        office = (int)(Math.random()*1000);
        salary = 49999;
    }

    public String toString() {
        return "Employee " + name + " works in office number " + office + " with a salary of " + salary + " and has been working since "  + hire;
    }
}