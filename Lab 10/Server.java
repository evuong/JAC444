import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server extends Application {
    private static TextArea txtArea = new TextArea("MultiThreadServer started at " + new Date() + "\n");
    private List<ServerClient> client = new ArrayList<>();

    public void start(Stage primaryStage) {
        txtArea.setEditable(false);

        new Thread(new setupServer()).start();

        Scene scene = new Scene(txtArea, 450,450);
        primaryStage.setTitle("Multi-threaded Server");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class setupServer implements Runnable {
        public void run() {
            try {
                ServerSocket ss = new ServerSocket(8080);
                while (true) {
                    Socket s = ss.accept();
                    client.add(new ServerClient(s));
                }
            } catch(IOException e) {
                System.out.println("Error starting server.");
            }
        }
    }

    class ServerClient implements Runnable {
        private Socket socket;
        private PrintWriter msg;

        ServerClient(Socket s) {
            socket = s;
            new Thread(this).start();
        }

        public void run() {
            try {
                Scanner fromClient = new Scanner(socket.getInputStream());
                msg = new PrintWriter(socket.getOutputStream());

                txtArea.appendText("Connection from " + socket + "\n");

                while(true) {
                    String txt = fromClient.nextLine();
                    txtArea.appendText(txt + "\n");

                    for(ServerClient s : client) {
                        s.send(txt);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error in server-client class: " + e.getMessage());
            }
        }

        private void send(String txt) {
            msg.println(txt);
            msg.flush();
        }
    }

    public static void main(String[] args) { launch(args); }

}
