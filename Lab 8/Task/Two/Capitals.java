package Task.Two;

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

import java.util.HashMap;
import java.util.Map;

public class Capitals extends Application {
    private final Label lblState = new Label("Choose a state:");
    private Label lblAnswer = new Label();

    private TextField txtState = new TextField();

    private final Button btnSubmit = new Button("Submit");

    private static Map<String,String> capitals = new HashMap<>();

    public void start(Stage primaryStage) {
        VBox main = new VBox(10);
        main.setAlignment(Pos.CENTER);

        HBox states = new HBox(10);
        states.setAlignment(Pos.CENTER);
        states.getChildren().addAll(lblState,txtState);

        main.getChildren().addAll(states,lblAnswer,btnSubmit);

        initialize();

        txtState.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                btnSubmit.fire();
            }
        });

        btnSubmit.setOnAction(e -> {
            lblAnswer.setText("");
            String query = txtState.getText();

            if(capitals.containsKey(query.toUpperCase())) {
                lblAnswer.setText("The capital of " + query + " is " + capitals.get(query.toUpperCase()));
            }
            else if(query.isEmpty()) {
                lblAnswer.setText("Please enter a state.");
            }
            else {
                lblAnswer.setText("State not found.");
            }
        });

        Scene scene = new Scene(main,300,200);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Capital Finder");
        primaryStage.show();
    }

    private static void initialize() {
        capitals.put("ALABAMA", "Montgomery");
        capitals.put("ALASKA", "Juneau");
        capitals.put("ARIZONA", "Phoenix");
        capitals.put("ARKANSAS", "Little Rock");
        capitals.put("CALIFORNIA", "Sacramento");
        capitals.put("COLORADO", "Denver");
        capitals.put("CONNECTICUT", "Hartford");
        capitals.put("DELAWARE", "Dover");
        capitals.put("FLORIDA", "Tallahassee");
        capitals.put("GEORGIA", "Atlanta");
        capitals.put("HAWAII", "Honolulu");
        capitals.put("IDAHO", "Boise");
        capitals.put("ILLINOIS", "Springfield");
        capitals.put("INDIANA", "Indianapolis");
        capitals.put("IOWA", "Des Moines");
        capitals.put("KANSAS", "Topeka");
        capitals.put("KENTUCKY", "Frankfort");
        capitals.put("LOUISIANA", "Baton Rouge");
        capitals.put("MAINE", "Augusta");
        capitals.put("MARYLAND", "Annapolis");
        capitals.put("MASSACHUSETTS", "Boston");
        capitals.put("MICHIGAN", "Lansing");
        capitals.put("MINNESOTA", "St. Paul");
        capitals.put("MISSISSIPPI", "Jackson");
        capitals.put("MISSOURI", "Jefferson City");
        capitals.put("MONTANA", "Helena");
        capitals.put("NEBRASKA", "Lincoln");
        capitals.put("NEVADA", "Carson City");
        capitals.put("NEW HAMPSHIRE", "Concord");
        capitals.put("NEW JERSEY", "Trenton");
        capitals.put("NEW MEXICO", "Santa Fe");
        capitals.put("NEW YORK", "Albany");
        capitals.put("NORTH CAROLINA", "Raleigh");
        capitals.put("NORTH DAKOTA", "Bismarck");
        capitals.put("OHIO", "Columbus");
        capitals.put("OKLAHOMA", "Oklahoma City");
        capitals.put("OREGON", "Salem");
        capitals.put("PENNSYLVANIA", "Harrisburg");
        capitals.put("RHODE ISLAND", "Providence");
        capitals.put("SOUTH CAROLINA", "Columbia");
        capitals.put("SOUTH DAKOTA", "Pierre");
        capitals.put("TENNESSEE", "Nashville");
        capitals.put("TEXAS", "Austin");
        capitals.put("UTAH", "Salt Lake City");
        capitals.put("VERMONT", "Montpelier");
        capitals.put("VIRGINIA", "Richmond");
        capitals.put("WASHINGTON", "Olympia");
        capitals.put("WEST VIRGINIA", "Charleston");
        capitals.put("WISCONSIN", "Madison");
        capitals.put("WYOMING", "Cheyenne");
    }

    public static void main(String[] args) { launch(args); }
}
