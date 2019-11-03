package One;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BabyNameRanking extends Application {
    private int year;
    private char gender;
    private String name;

    private TextField txtName = new TextField();

    private Label status = new Label();

    private Button btnSubmit = new Button("Submit");
    private Button btnReset = new Button("Reset");
    private Button btnExit = new Button("Exit");

    private ChoiceBox<String> cbYear = new ChoiceBox<>();
    private ChoiceBox<Character> cbGender = new ChoiceBox<>();

    public void start(Stage primaryStage) {
        final GridPane search = new GridPane();
        search.setPadding(new Insets(30,30,30,30));
        search.setVgap(10);
        search.setHgap(10);

        final ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(50);
        search.getColumnConstraints().addAll(cc,cc);

        search.add(new Label("Enter the Year"),0,0);
        search.add(cbYear,1,0,2,1);
        cbYear.getItems().addAll("2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010");

        search.add(new Label("Enter the Gender"),0,1);
        search.add(cbGender,1,1);
        cbGender.getItems().addAll('M', 'F');

        search.add(new Label("Enter the Name"),0,2);
        search.add(txtName,1,2);

        search.add(status, 0,3,2,1);

        HBox buttons = new HBox(10);

        btnSubmit.setPrefWidth(75);
        btnReset.setPrefWidth(75);
        btnExit.setPrefWidth(75);

        buttons.getChildren().addAll(btnSubmit, btnReset, btnExit);
        search.add(buttons, 0,4,2,1);


        btnSubmit.setOnAction(e -> {
            status.setText("");
            try {
                year = Integer.valueOf(cbYear.getValue());
                gender = cbGender.getValue();
                name = txtName.getText().substring(0,1).toUpperCase() + txtName.getText().substring(1).toLowerCase();

                if(name.isEmpty()) {
                    status.setText("Please enter a name.");
                }
                else {
                    int rank = Function.findName(year, gender, name);

                    if(rank == 0) {
                        status.setText("Name not found.");
                    }
                    else {
                        status.setText(name + "(" + gender + ") found at rank #" + rank + " in " + year);
                    }
                }
            } catch (NumberFormatException x) {
                status.setText("Please choose a year.");
            } catch(NullPointerException y) {
                status.setText("Please choose a gender.");
            }
        });

        btnReset.setOnAction(e-> {
            txtName.setText("");
            cbYear.getSelectionModel().clearSelection();
            cbGender.getSelectionModel().clearSelection();
            status.setText("");
        });


        btnExit.setOnAction(e -> Platform.exit() );

        txtName.setOnKeyPressed(e-> {
            if(e.getCode() == KeyCode.ENTER) {
                btnSubmit.fire();
            }
        });

        Scene scene = new Scene(search,350,230);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search Name Ranking Application");
        primaryStage.show();
    }

    public static void main(String[] args) { launch (args); }
}
