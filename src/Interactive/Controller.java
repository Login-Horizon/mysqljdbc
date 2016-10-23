package Interactive;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import JDBC.ListDB;
import JDBC.SQLGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Ruslanq on 22.10.2016.
 */
public class Controller implements Initializable {
    Stage prevStage;
    private ObservableList<ListDB> usersData;

    private void initData() {

        usersData =FXCollections.observableArrayList( new BookWorm() {
            @Override
            public String LogOn(String userEmail, String userId) {
                return null;
            }

            @Override
            public String Registration(String userEmail, String userName) {
                return null;
            }

            @Override
            public void LogOff() {

            }
        }.AllBookToListBD(new SQLGenerator().AllBooks()));


    }

    public void setPrevStage(Stage stage) {
        this.prevStage = stage;

    }

    @FXML
    private Button Reg;
    @FXML
    private Button Login;

    @FXML
    private ListView myl;
    @FXML
    private AnchorPane content;
    @FXML
    private TableView<ListDB> tableUsers = new TableView<>();

    @FXML
    private TableColumn useridcol = new TableColumn("userid");

    @FXML
    private TableColumn genrecol = new TableColumn("genre");

    @FXML
    private TableColumn authorcol = new TableColumn("author");

    @FXML
    private TableColumn namecol = new TableColumn("name");

    @FXML
    private javafx.scene.control.TextField email;
    @FXML
    private javafx.scene.control.TextField id;

    public void gotoCreateTable() throws IOException {
        initData();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/TableBook.FXML"));
        Stage stage = new Stage();
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());


        useridcol.setCellValueFactory(new PropertyValueFactory<ListDB, String>("userid"));
        namecol.setCellValueFactory(new PropertyValueFactory<ListDB, String>("name"));
        authorcol.setCellValueFactory(new PropertyValueFactory<ListDB, String>("author"));
        genrecol.setCellValueFactory(new PropertyValueFactory<ListDB, String>("genre"));

        useridcol.setPrefWidth(250.0);
        namecol.setPrefWidth(150.0);
        genrecol.setPrefWidth(80.0);
        authorcol.setPrefWidth(150.0);
        tableUsers = (TableView) scene.lookup("#tableUsers");

        tableUsers.setItems(usersData);
        tableUsers.getColumns().addAll(namecol, authorcol, genrecol, useridcol);


        tableUsers.setEditable(true);
        System.out.println(namecol.getCellData(1));
        prevStage.setScene(scene);
        prevStage.getScene().getStylesheets().add("css/JMetroDarkTheme.css");

        prevStage.show();
    }

    @FXML
    public void onClickMethod() throws IOException {
        setPrevStage((Stage) Login.getScene().getWindow());
        gotoCreateTable();
    }


    public void gotoCreateCategory() throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/test.FXML"));
        Stage stage = new Stage();
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());

        ListView listView = (ListView) scene.lookup("#myl");


        listView.getItems().add("Item 1");
        listView.getItems().add("Item 2");
        listView.getItems().add("Item ff");


        // Scene scene = new Scene(hbox, 300, 120);


        prevStage.setScene(scene);
        prevStage.getScene().getStylesheets().add("css/JMetroDarkTheme.css");

        prevStage.show();
    }


    @FXML
    public void onLogon() throws IOException {
        String result = new EnterThLib() {
            @Override
            public int TookBook(int userId, int bookId) {
                return 0;
            }

            @Override
            public int SearchBook(String keyword) {
                return 0;
            }

            @Override
            public int Return(int userId, int bookId, String all, Map myBookList) {
                return 0;
            }

            @Override
            public ObservableList<ListDB> AllBookToListBD(String[][] allBook) {
                return null;
            }
        }.LogOn(email.getText(), id.getText());
        System.out.println(result);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Error");
        alert.setHeaderText(null);
        if (result == null) {
            alert.setContentText("User does not exist");
            alert.showAndWait();
            ;
        } else {
            Login.setText(result);
            setPrevStage((Stage) Login.getScene().getWindow());
            gotoCreateCategory();
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}

