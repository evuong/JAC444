package Task.One;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends Application {
    private Label numbers = new Label("Two randomly generated numbers are " + num1 + " and " + num2);
    private Label addition = new Label("What is the addition of "  + num1 + " and " + num2 + "?");
    private Label subtraction = new Label("What is the subtraction of "  + num1 + " and " + num2 + "?");
    private Label multiply = new Label("What is the multiplication of "  + num1 + " and " + num2 + "?");
    private Label division = new Label("What is the division of "  + num1 + " and " + num2 + "?");
    private Label correct = new Label();
    private Label wrong = new Label();
    private Label status = new Label();

    private final Button btnSubmit = new Button("Submit");
    private final Button btnReset = new Button("Reset");

    private TextField sum = new TextField();
    private TextField subtract = new TextField();
    private TextField product = new TextField();
    private TextField divided = new TextField();

    private static List<Double> answers = new ArrayList<>();
    private static int num1 = (int)(Math.random()*10)+1;
    private static int num2 = (int)(Math.random()*10)+1;

    public void start(Stage primaryStage) {
        VBox main = new VBox(10);
        main.setAlignment(Pos.CENTER);

        HBox add = new HBox(10);
        add.setAlignment(Pos.CENTER);
        add.getChildren().addAll(addition, sum);

        HBox minus = new HBox(10);
        minus.setAlignment(Pos.CENTER);
        minus.getChildren().addAll(subtraction, subtract);

        HBox times = new HBox(10);
        times.setAlignment(Pos.CENTER);
        times.getChildren().addAll(multiply, product);

        HBox divide = new HBox(10);
        divide.setAlignment(Pos.CENTER);
        divide.getChildren().addAll(division, divided);

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(btnSubmit,btnReset);

        sum.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) { btnSubmit.fire(); }
        });

        subtract.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) { btnSubmit.fire(); }
        });

        product.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) { btnSubmit.fire(); }
        });

        divided.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) { btnSubmit.fire(); }
        });

        btnSubmit.setOnAction(e-> {
            answers.clear();
            status.setText("");

            answers = Validator.validateInput(sum.getText(), subtract.getText(), product.getText(), divided.getText());

            if(!answers.isEmpty()) {
                AssertTest.setNumbers(num1, num2, answers);
                Result result = JUnitCore.runClasses(AssertTest.class);
                correct.setText("Number of correct answers: " + (4-result.getFailureCount()));
                wrong.setText("Number of wrong answers: " + result.getFailureCount());
            }
            else {
                correct.setText("");
                wrong.setText("");
                status.setText("Please enter valid doubles.");
            }

        });

        btnReset.setOnAction(e -> {
            num1 = (int)(Math.random()*10)+1;
            num2 = (int)(Math.random()*10)+1;
            answers.clear();

            sum.setText("");
            subtract.setText("");
            product.setText("");
            divided.setText("");

            correct.setText("");
            wrong.setText("");
            status.setText("");

            numbers.setText("Two randomly generated numbers are " + num1 + " and " + num2);
            addition.setText("What is the addition of "  + num1 + " and " + num2 + "?");
            subtraction.setText("What is the subtraction of "  + num1 + " and " + num2 + "?");
            multiply.setText("What is the multiplication of "  + num1 + " and " + num2 + "?");
            division.setText("What is the division of "  + num1 + " and " + num2 + "?");
        });

        main.getChildren().addAll(numbers,add,minus,times,divide,correct,wrong,status,buttons);


        Scene scene = new Scene(main, 500, 300);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Quiz Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { launch (args); }
}
