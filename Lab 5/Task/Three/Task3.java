package Task.Three;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Task3 extends Application {

    public void start(Stage primaryStage) throws Exception {
        Button txt = new Button("Welcome to Java");

        Scene scene = new Scene(txt, 200,200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome to Java");
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
