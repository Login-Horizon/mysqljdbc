package Interactive;

import java.awt.*;
import java.awt.TextField;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

import javax.management.Notification;

/**
 * Created by Ruslanq on 22.10.2016.
 */
public class Controller {
    @FXML
    private Button Reg;
    @FXML
    private Button Login;

    @FXML
    private ListView myl;
    @FXML
    private AnchorPane content;

    @FXML
    private javafx.scene.control.TextField email;
    @FXML
    private javafx.scene.control.TextField id;

    @FXML
    public void onClickMethod() {
        //email.getText();
//        Reg.setText(new EnterThLib() {
//            @Override
//            public int TookBook(int userId, int bookId) {
//                return 0;
//            }
//
//            @Override
//            public int SearchBook(String keyword) {
//                return 0;
//            }
//
//            @Override
//            public int Return(int userId, int bookId, String all, Map myBookList) {
//                return 0;
//            }
//        }.LogOn(email.getText(),id.getText()));
        Reg.setText(id.getText().toString());
    }

    @FXML
    public void onLogon() {
        String result =new EnterThLib() {
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
        }.LogOn(email.getText(),id.getText());
        System.out.println(result);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Error");
        alert.setHeaderText(null);
        if(result==null){
        alert.setContentText("User does not exist");
            alert.showAndWait();
        ;}

        else
            Login.setText(result);


    }

}
