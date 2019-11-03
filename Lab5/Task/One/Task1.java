package Task.One;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.layout.Pane;

public class Task1 extends Application {
    public void start(Stage primaryStage) throws Exception {
        final int ROWSIZE = 4;
        final int BOARDSIZE = 32;

        Rectangle[] r = new Rectangle[BOARDSIZE];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < ROWSIZE; j++) {
                if ((i%2) == 0) {
                    r[i+8*j] = new Rectangle(j*80+40, i*40,40,40);
                }
                else {
                    r[i+8*j] = new Rectangle(j*80, i*40,40,40);
                }

            }
        }

        Pane p = new Pane(r);

        Scene scene = new Scene(p, 320, 320);

        primaryStage.setTitle("Chess Board");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) { launch(args); }
}