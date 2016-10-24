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

        usersData = FXCollections.observableArrayList(new BookWorm() {
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
    private Button SearchKey;

    @FXML
    private Button tOrR;

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
    @FXML
    private javafx.scene.control.TextField query;

    public void gotoCreateTable() throws IOException {


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
    public void onSearch() throws IOException {
        String search = query.getText();
        if(search==null){
            setPrevStage((Stage) Login.getScene().getWindow());
            initData();
            gotoCreateTable();
        }
        else {
            usersData = FXCollections.observableArrayList(new BookWorm() {
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
            }.AllBookToListBD(new SQLGenerator().BookByKey(search)));
            setPrevStage((Stage) Login.getScene().getWindow());
            gotoCreateTable();
        }

    }
    @FXML
    public void onClickMethod() throws IOException {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        String alertResult = new EnterThLib() {
            @Override
            public int TookBook(int userId, int bookId) {
                return 0;
            }

            @Override
            public String[][] SearchBook(String keyword) {
                return  null ;
            }

            @Override
            public int Return(int userId, int bookId, String all, Map myBookList) {
                return 0;
            }

            @Override
            public ObservableList<ListDB> AllBookToListBD(String[][] allBook) {
                return null;
            }
        }.Registration(email.getText(), id.getText());
        if (alertResult == null) {
            alert.setContentText("Invalid values");
            alert.showAndWait();
        } else {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("your id: " + alertResult + ".Do not forget it");
            alert.showAndWait();
            setPrevStage((Stage) Login.getScene().getWindow());
            initData();
            gotoCreateTable();
        }
    }



    @FXML
    public void onLogon() throws IOException {
        String result = new EnterThLib() {
            @Override
            public int TookBook(int userId, int bookId) {
                return 0;
            }

            @Override
            public String[][] SearchBook(String keyword) {
                return null;
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

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText(null);
        if (result == null) {
            alert.setContentText("User does not exist");
            alert.showAndWait();
            ;
        } else {
            Login.setText(result);
            setPrevStage((Stage) Login.getScene().getWindow());
            initData();
            gotoCreateTable();
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}

