package Task.One;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    private static Double plus;
    private static Double minus;
    private static Double multiply;
    private static Double divide;

    private static List<Double> answers = new ArrayList<>();

    public static List<Double> validateInput(String add, String sub, String mul, String div) {
        try {
            plus = Double.valueOf(add);
            minus = Double.valueOf(sub);
            multiply = Double.valueOf(mul);
            divide = Double.valueOf(div);

            answers.add(0, plus);
            answers.add(1, minus);
            answers.add(2, multiply);
            answers.add(3, divide);
            return answers;
        } catch(NumberFormatException e) {
            return answers;
        }
    }
}