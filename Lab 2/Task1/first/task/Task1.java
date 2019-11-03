package first.task;

import java.time.Month;

public class Task1 {
    public static void main(String[] args) {
        System.out.println("TASK 1\n--------------------");
        MyDate today = new MyDate();
        System.out.println(today.getYear() + " " + Month.of(today.getMonth()) + " " + today.getDay());

        MyDate millis = new MyDate(561555550000L);
        System.out.println(millis.getYear() + " " + Month.of(millis.getMonth()) + " " + millis.getDay());
    }
}
