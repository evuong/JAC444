package Task.One;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class AssertTest {
    private static double num1;
    private static double num2;
    private static List<Double> answers = new ArrayList<>();

    public static void setNumbers(int first, int second, List<Double> a) {
        num1 = first;
        num2 = second;
        answers = a;
    }

    @Test
    public void plusTest() {
        assertEquals(num1+num2, answers.get(0),0);
    }

    @Test
    public void minusTest() {
        assertEquals(num1-num2, answers.get(1),0);
    }

    @Test
    public void multiplyTest() {
        assertEquals((num1*num2), answers.get(2),0);
    }

    @Test
    public void divideTest() {
        assertEquals((num1/num2), answers.get(3),0.01);
    }

}
