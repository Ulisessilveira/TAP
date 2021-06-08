package sample;

import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage;
    public static String nombreUsuario = "";
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("login/login.fxml"));
        primaryStage.setTitle("Hike");
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.getIcons().add(new Image("/sample/img/icon.png"));
        primaryStage.show();
        new FadeIn(root).play();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
