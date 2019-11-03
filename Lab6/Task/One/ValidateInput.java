package Task.One;

import javafx.scene.control.TextField;

public class ValidateInput {

    public static boolean validateInt(TextField field) {
        try {
            Integer.parseInt(field.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validateDouble(TextField field) {
        try {
            Double.parseDouble(field.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
