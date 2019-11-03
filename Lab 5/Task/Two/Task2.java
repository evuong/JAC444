package Task.Two;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Task2 extends Application {
    public void start (Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10,10,10,10));
        pane.setHgap(3);
        pane.setVgap(10);

        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(16.67);

        pane.getColumnConstraints().addAll(col,col,col,col,col,col);

        pane.add(new Label("First Name"), 0, 0);
        pane.add(new TextField(),1,0,5,1);

        pane.add(new Label("Last Name"), 0,1);
        pane.add(new TextField(), 1, 1,5 ,1);

        Label city = new Label("City");
        pane.add(city,0,2);
        GridPane.setHalignment(city, HPos.CENTER);
        pane.add(new TextField(), 1, 2);

        ChoiceBox<String> cbprov = new ChoiceBox<>();
        cbprov.getItems().addAll("Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador",
                "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Yukon");

        Label prov = new Label("Province");
        pane.add(prov, 2,2);
        GridPane.setHalignment(prov, HPos.CENTER);
        pane.add(cbprov, 3,2);

        Label pc = new Label("Postal Code");
        pane.add(pc, 4,2);
        GridPane.setHalignment(pc, HPos.CENTER);
        pane.add(new TextField(), 5,2);

        Button btnAdd = new Button("Add");
        btnAdd.setPrefWidth(100);
        pane.add(btnAdd, 0,3);

        Button btnFirst = new Button("First");
        btnFirst.setPrefWidth(100);
        pane.add(btnFirst, 1,3);

        Button btnNext = new Button("Next");
        btnNext.setPrefWidth(100);
        pane.add(btnNext, 2,3);

        Button btnPrev = new Button("Previous");
        btnPrev.setPrefWidth(100);
        pane.add(btnPrev, 3,3);

        Button btnLast = new Button("Last");
        btnLast.setPrefWidth(100);
        pane.add(btnLast, 4,3);

        Button btnUpdate = new Button("Update");
        btnUpdate.setPrefWidth(100);
        pane.add(btnUpdate, 5,3);

        Scene scene = new Scene(pane,500,175);

        primaryStage.setTitle("Address Book");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
