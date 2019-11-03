package Two;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ConnectFour extends Application {

    private static final int TILE_SIZE = 50;
    private static final int WIDTH = 7;
    private static final int HEIGHT = 6;
    private static int[][] game = new int[HEIGHT][WIDTH];
    private static int turn = 0;
    private static int player = 0;

    private static final Button btnEnter = new Button("Enter");
    private static final Button btnReset = new Button("Reset");
    private static final Label msg1 = new Label("Drop a disc into column: ");
    private static Label status = new Label();
    private static ChoiceBox<Character> choice = new ChoiceBox<>();

    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #6497b1");

        HBox hbox = new HBox(10);
        hbox.setTranslateX(25);
        hbox.setTranslateY(360);

        HBox hbox2 = new HBox(10);
        hbox2.setTranslateX(35);
        hbox2.setTranslateY(390);

        btnReset.setTranslateX(318);
        btnReset.setTranslateY(400);

        msg1.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        status.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        choice.getItems().addAll('1','2','3','4','5','6','7');

        createGrid(pane);

        btnEnter.setOnAction(e-> {
            status.setText("");
            try {
                dropCircle(pane, Character.getNumericValue(choice.getValue())-1);
            } catch (NullPointerException x) {
                System.out.println("Try again.");
            }

        });

        btnReset.setOnAction(e-> {
            status.setText("");
            turn = 0;
            player = 0;
            btnEnter.setDisable(false);
            choice.getSelectionModel().clearSelection();
            choice.setDisable(false);

            for (int i = 0; i<HEIGHT; i++) for (int j = 0; j < WIDTH; j++) {
                game[i][j] = 0;
            }
            createGrid(pane);
        });

        pane.setOnMouseClicked(e -> {
            double x = e.getX();

            if(x > 12 && x < 63) {
                choice.setValue('1');
                btnEnter.fire();
            }
            else if(x > 66 && x < 118) {
                choice.setValue('2');
                btnEnter.fire();
            }
            else if(x > 123 && x < 172) {
                choice.setValue('3');
                btnEnter.fire();
            }
            else if(x > 177 && x < 228) {
                choice.setValue('4');
                btnEnter.fire();
            }
            else if(x > 231 && x < 282) {
                choice.setValue('5');
                btnEnter.fire();
            }
            else if(x > 287 && x < 337) {
                choice.setValue('6');
                btnEnter.fire();
            }
            else if(x > 341 && x < 393) {
                choice.setValue('7');
                btnEnter.fire();
            }
        });

        hbox.getChildren().addAll(msg1,choice,btnEnter);
        hbox2.getChildren().add(status);
        pane.getChildren().addAll(hbox,hbox2, btnReset);

        Scene scene = new Scene(pane, 400,450);
        primaryStage.setTitle("Connect Four");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void createGrid(Pane p) {
        for(int i = 0; i < WIDTH; i++) {
            for(int j = 0; j < HEIGHT; j++) {
                Circle circle = createCircle();
                circle.setTranslateX(i*(TILE_SIZE+5) + TILE_SIZE/4);
                circle.setTranslateY(j*(TILE_SIZE+5) + TILE_SIZE/4);
                circle.setFill(Color.WHITE);

                p.getChildren().add(circle);
            }
        }
    }

    private static void dropCircle(Pane p, int pos) {

        try {
            int x = getYPosition(pos);

            Circle circle = createCircle();
            circle.setTranslateX((pos)*(TILE_SIZE+5) + TILE_SIZE/4);
            circle.setTranslateY(x*(TILE_SIZE+5) + TILE_SIZE/4);

            if (turn%2 == 0) {
                circle.setFill(Color.RED);
                game[x][pos] = 1;
                player = 1;
            }
            else {
                circle.setFill(Color.YELLOW);
                game[x][pos] = 2;
                player = 2;
            }
            p.getChildren().add(circle);
            turn++;

            for (int i = 0; i<HEIGHT; i++) {
                System.out.println();
                for (int j = 0; j < WIDTH; j++) {
                    System.out.print(game[i][j] + " ");
                }
            }
            System.out.println("\n\n\n\n\n\n\n\nBOARD ");


            if (checkWin(x, pos, player)) {
                status.setText(player == 1 ? "Red is the winner!" : "Yellow is the winner!");
                btnEnter.setDisable(true);
                choice.setDisable(true);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            status.setText("Column is full!");
        }
    }

    private static Circle createCircle() {
        Circle circle = new Circle(TILE_SIZE/2);
        circle.setCenterX(TILE_SIZE/2);
        circle.setCenterY(TILE_SIZE/2);
        return circle;
    }

    private static int getYPosition(int x) {
        for(int i = HEIGHT-1; i >= 0; i--) {
            if (game[i][x] == 0) {
                return i;
            }
        }
        return -1;
    }

    private static boolean checkWin(int x, int y, int p) {
        return checkLeftRight(x,p) || checkUpDown(y,p) || checkDiagonalLeftRight(p) || checkDiagonalRightLeft(p);
    }

    private static boolean checkLeftRight(int x, int p) {
        for(int i = 0; i < 4; i++ ) {
            if(game[x][i] == p && game[x][i+1] == p && game[x][i+2] == p && game[x][i+3] == p) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkUpDown(int y, int p) {
        for(int i = 0; i < 3; i++) {
            if(game[i][y] == p && game[i+1][y] == p && game[i+2][y] == p && game[i+3][y] == p) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonalLeftRight(int p) {
        for(int i = 5; i >= 3; i--) {
            for (int j = 0; j < 4; j++) {
                if(game[i][j] == p && game[i-1][j+1] == p && game[i-2][j+2] == p && game[i-3][j+3] == p) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkDiagonalRightLeft(int p) {
        for(int i = 5; i >= 3; i--) {
            for(int j = 6; j >= 3; j--) {
                if(game[i][j] == p && game[i-1][j-1] == p && game[i-2][j-2] == p && game[i-3][j-3] == p) {
                    return true;
                }
            }
        }
        return false;
    }



    public static void main(String[] args) { launch(args); }
}