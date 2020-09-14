package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader ldr = new FXMLLoader();
        ldr.setLocation(new URL("file:src/sample/main.fxml"));
        Scene scene = ldr.load();
        Controller c = ldr.getController();
        c.setPrimaryStage(primaryStage);
        //Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
