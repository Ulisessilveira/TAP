package sample.principal;

import animatefx.animation.*;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Main;
import sample.covid.Covid;

import java.io.IOException;

public class Principal {
    @FXML AnchorPane anchorPane;
    @FXML StackPane stackPane;
    @FXML Label txtUsuario;
    @FXML Button btnCaminata, btnCovid, btnLugar, btnAjustes;
    @FXML protected void initialize(){
        txtUsuario.setText(Main.nombreUsuario);
    }
    public  void caminata(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../caminata/caminata.fxml"));
        Scene scene=anchorPane.getScene();
        root.translateXProperty().set(scene.getWidth());
        stackPane.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue ky = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf= new KeyFrame(Duration.seconds(1), ky);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stackPane.getChildren().remove(anchorPane);
            }
        });
        timeline.play();
        //new Hinge(btnCaminata).play();//Se cae y desaparece;
    }

    public  void covid(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../covid/covid.fxml"));
        Scene scene=anchorPane.getScene();
        root.translateXProperty().set(scene.getWidth());
        stackPane.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue ky = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf= new KeyFrame(Duration.seconds(1), ky);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stackPane.getChildren().remove(anchorPane);
            }
        });
        timeline.play();
    }

    public  void lugar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../lugares/lugares.fxml"));
        Scene scene=anchorPane.getScene();
        root.translateXProperty().set(scene.getWidth());
        stackPane.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue ky = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf= new KeyFrame(Duration.seconds(1), ky);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stackPane.getChildren().remove(anchorPane);
            }
        });
        timeline.play();
    }

    public void config(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../config/config.fxml"));
        Scene scene=anchorPane.getScene();
        root.translateXProperty().set(scene.getWidth());
        stackPane.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue ky = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf= new KeyFrame(Duration.seconds(1), ky);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stackPane.getChildren().remove(anchorPane);
            }
        });
        timeline.play();
    }
    public void salir(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        new FadeInDown(root).play();
    }
    public void temaO(){
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("2B2D42"), CornerRadii.EMPTY, Insets.EMPTY);
        stackPane.setBackground(new Background(backgroundFill));
    }
}
