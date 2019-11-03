package first.task;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyDate {
    private int day, month, year;
    GregorianCalendar gc = new GregorianCalendar();

    public MyDate() {
        day = gc.get(Calendar.DAY_OF_MONTH);
        month = gc.get(Calendar.MONTH);
        year = gc.get(Calendar.YEAR);
    }

    public MyDate(long date) {
        gc.setTimeInMillis(date);
        day = gc.get(Calendar.DAY_OF_MONTH);
        month = gc.get(Calendar.MONTH);
        year = gc.get(Calendar.YEAR);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDate(long elapsedTime) {
        gc.setTimeInMillis(elapsedTime);
        day = gc.get(Calendar.DAY_OF_MONTH);
        month = gc.get(Calendar.MONTH);
        year = gc.get(Calendar.YEAR);
    }
}
