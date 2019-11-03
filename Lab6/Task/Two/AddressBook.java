package Task.Two;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class AddressBook extends Application {

    private final int fnSize = 15;
    private final int lnSize = 25;
    private final int citySize = 30;
    private final int provSize = 26;
    private final int pcSize = 6;
    private final int maxSize = fnSize+lnSize+citySize+provSize+pcSize;

    private static int count = 0;
    private static int view = 0;

    private String firstname;
    private String lastname;
    private String cityString;
    private String province;
    private String postal;

    private Label fn = new Label("First Name");
    private Label ln = new Label("Last Name");
    private Label city = new Label("City");
    private Label prov = new Label("Province");
    private Label pc = new Label("Postal Code");
    private Label viewing = new Label();
    private Label status = new Label();

    private TextField fnField = new TextField();
    private TextField lnField = new TextField();
    private TextField cField = new TextField();
    private TextField pcField = new TextField();

    private Button btnAdd = new Button("Add");
    private Button btnFirst = new Button("First");
    private Button btnNext = new Button("Next");
    private Button btnPrev = new Button("Previous");
    private Button btnLast = new Button("Last");
    private Button btnUpdate = new Button("Update");

    private ChoiceBox<String> cbprov = new ChoiceBox<>();

    public void start (Stage primaryStage) {
        Address.openFile();     //opens file to clear it

        //setValues(new String(Address.readFile(0)));

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10,10,10,10));
        pane.setHgap(3);
        pane.setVgap(10);

        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(16.67);

        pane.getColumnConstraints().addAll(col,col,col,col,col,col);

        fn.setAlignment(Pos.CENTER_RIGHT);
        pane.add(fn, 0, 0);
        pane.add(fnField,1,0,5,1);

        pane.add(ln, 0,1);
        pane.add(lnField, 1, 1,5 ,1);

        pane.add(city,0,2);
        GridPane.setHalignment(city, HPos.CENTER);
        pane.add(cField, 1, 2);

        cbprov.getItems().addAll("Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador",
                "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Yukon");

        pane.add(prov, 2,2);
        GridPane.setHalignment(prov, HPos.CENTER);
        pane.add(cbprov, 3,2);

        pane.add(pc, 4,2);
        GridPane.setHalignment(pc, HPos.CENTER);
        pane.add(pcField, 5,2);

        btnAdd.setPrefWidth(100);
        pane.add(btnAdd, 0,3);

        btnFirst.setPrefWidth(100);
        pane.add(btnFirst, 1,3);

        btnNext.setPrefWidth(100);
        pane.add(btnNext, 2,3);

        btnPrev.setPrefWidth(100);
        pane.add(btnPrev, 3,3);

        btnLast.setPrefWidth(100);
        pane.add(btnLast, 4,3);

        btnUpdate.setPrefWidth(100);
        pane.add(btnUpdate, 5,3);

        pane.add(viewing, 0,4,5,1);

        status.setAlignment(Pos.CENTER);
        pane.add(status,1,4,5,1);



        btnAdd.setOnAction(actionEvent -> {
            if (addAddress(count)) {
                count++;
                setView();
            }
        });

        btnFirst.setOnAction(actionEvent -> {
            if (view == 0) {
                status.setText("Already on first record.");
            }
            else {
                status.setText("");
                view = 0;
                setValues(new String(Address.readFile(view*maxSize)));
                setView();
            }
        });

        btnNext.setOnAction(actionEvent -> {
            if(view >= count) {
                status.setText("Currently last record.");
            }
            else {
                status.setText("");
                view++;
                setValues(new String(Address.readFile(view*maxSize)));
                setView();
            }
        });

        btnPrev.setOnAction(actionEvent -> {
            if(view == 0) {
                status.setText("Currently first record.");
            }
            else {
                status.setText("");
                view--;
                setValues(new String(Address.readFile(view*maxSize)));
                setView();
            }

        });

        btnLast.setOnAction(actionEvent -> {
            if (view == count) {
                status.setText("Already on last record.");
            }
            else {
                status.setText("");
                view = count;
                setValues(new String(Address.readFile(view*maxSize)));
                setView();
            }
        });

        btnUpdate.setOnAction(actionEvent -> {
            if (view == count) {
                status.setText("No record to update.");
            }
            else {
                addAddress(view);
            }
        });

        Scene scene = new Scene(pane,500,200);

        primaryStage.setTitle("Address Book");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setValues(String address) {
        if(!address.isEmpty()) {
            String delete = "\\*";

            firstname = address.substring(0, 14);
            fnField.setText(firstname.replaceAll(delete,""));

            lastname = address.substring(15,39);
            lnField.setText(lastname.replaceAll(delete,""));

            cityString = address.substring(40, 69);
            cField.setText(cityString.replaceAll(delete,""));

            province = address.substring(70, 95);
            cbprov.getSelectionModel().clearSelection();
            cbprov.getSelectionModel().select(province.replaceAll(delete,""));

            postal = address.substring(96);
            pcField.setText(postal.replaceAll(delete,""));
        }
    }

    private static boolean checkLength(String value, int length) {
        if (value.length() < length) {
            return false;
        }
        return true;
    }

    public static boolean checkPostal(String value) {
        String regex = "[A-Za-z][0-9][A-Za-z][0-9][A-Za-z][0-9]";

        if(value.matches(regex)) {
            return true;
        }
        return false;
    }

    private boolean addAddress(int pos) {
        status.setText("");
        String address;

        if (fnField.getText().isEmpty() || lnField.getText().isEmpty() || cField.getText().isEmpty() || cbprov.getSelectionModel().isEmpty() || pcField.getText().isEmpty()) {
            status.setText("All fields must have a value.");
        }
        else {
            if (checkLength(fnField.getText(), fnSize)) {
                status.setText("First name length is too long.");
            }
            else if(checkLength(lnField.getText(), lnSize)) {
                status.setText("Last name length is too long.");
            }
            else if(checkLength(cField.getText(), citySize)) {
                status.setText("City name is too long.");
            }
            else if(pcField.getText().length() != 6 || !checkPostal(pcField.getText())) {
                System.out.println(pcField.getText().length());
                status.setText("Postal code needs to have length of 6 and look like 'A1A2B2'.");
            }
            else {
                char fill = '*';

                String paddedFN = fnField.getText() + new String(new char[fnSize-fnField.getText().length()]).replace('\0',fill);
                String paddedLN = lnField.getText() + new String(new char[lnSize-lnField.getText().length()]).replace('\0',fill);
                String paddedC = cField.getText() + new String(new char[citySize-cField.getText().length()]).replace('\0',fill);
                String paddedP = cbprov.getValue() + new String(new char[provSize-cbprov.getValue().length()]).replace('\0',fill);

                address = paddedFN + paddedLN + paddedC + paddedP + pcField.getText();

                Address.writeAddress(pos*maxSize, address);
                return true;
            }
        }
        return false;
    }

    public void setView() {
        if (view < count) {
            btnAdd.setDisable(true);
            btnUpdate.setDisable(false);
        }
        else {
            btnAdd.setDisable(false);
            btnUpdate.setDisable(true);
        }

        int number = view+1;
        viewing.setText("Viewing " + number + "/" + count);
    }

    public static void main(String[] args) { launch(args); }
}
