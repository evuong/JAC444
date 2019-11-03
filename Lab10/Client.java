import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Application {
    private static TextArea txtArea = new TextArea();

    private static TextField txtField = new TextField();
    private static TextField txtName = new TextField();

    private final Button btnSend = new Button("Send");

    private final Label lblName = new Label("     Name:");
    private final Label lblStatus = new Label("Name cannot be empty");

    private PrintWriter msg;

    public void start(Stage primaryStage) {
        lblStatus.setVisible(false);

        HBox setName = new HBox(10, lblName, txtName,lblStatus);
        lblName.setStyle("-fx-font-size: 15");
        txtName.setPrefWidth(300);

        HBox hBtn = new HBox(10,txtField,btnSend);
        hBtn.setAlignment(Pos.CENTER);

        VBox root = new VBox(15,txtArea,setName,hBtn);

        txtArea.setEditable(false);
        txtArea.setPrefHeight(400);

        txtField.setPrefHeight(50);
        txtField.setPrefWidth(430);

        txtField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                btnSend.fire();
            }
        });

        btnSend.setOnAction(e -> {
            if(txtName.getText().isEmpty()) {
                lblStatus.setVisible(true);
            }
            else {
                lblStatus.setVisible(false);
                msg.println(txtName.getText() + ": " + txtField.getText());
                msg.flush();

                primaryStage.setTitle(txtName.getText());
                txtField.setText("");
            }
        });

        try {
            Socket socket = new Socket("localhost", 8080);
            msg = new PrintWriter(socket.getOutputStream());
            new Receive(socket);
        } catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        Scene scene = new Scene(root, 550,515);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    class Receive implements Runnable {
        private Socket socket;

        Receive(Socket s) {
            socket = s;
            new Thread(this).start();
        }

        public void run() {
            try {
                Scanner receiveMsg = new Scanner(socket.getInputStream());
                while(true) {
                    String txt = receiveMsg.nextLine();
                    txtArea.appendText(txt + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error from client: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) { launch(args); }
}
