/**
 * Created by Ruslanq on 22.10.2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LibRoom extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
       //Parent root = FXMLLoader.load(getClass().getResource("views/test.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("views/LogOn.FXML"));


        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
      //  primaryStage.setTitle("ListView Experiment 1");
        primaryStage.setTitle("LogOn ");

       ListView listView = (ListView) scene.lookup("#myl");


       // listView.getItems().add("Item 1");
       // listView.getItems().add("Item 2");
       // listView.getItems().add("Item ff");


       // Scene scene = new Scene(hbox, 300, 120);
        primaryStage.setScene(scene);
       // primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
       // primaryStage.getScene().getStylesheets().add("css/JMetroDarkTheme.css");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

