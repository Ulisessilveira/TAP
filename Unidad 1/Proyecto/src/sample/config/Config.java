package sample.config;

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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;

public class Config {
    @FXML AnchorPane anchorPane;
    @FXML StackPane stackPane;
    @FXML ToggleButton tButtonTemaO;
    @FXML RadioButton rBP, rBM, rBG;
    @FXML Label labelNombre, labelTamanio, labelTamanio2;
    @FXML MediaView mediaView;
    Font fontpequenio = new Font(12);
    Font fontMediano = new Font(25);
    Font fontGrande = new Font(33);
    public void cambiar(ActionEvent event){
        if (tButtonTemaO.isSelected() == true){
            temaO();
        }else {
            temaC();
        }
    }
    public void pequenio(ActionEvent event){
        rBM.setSelected(false);
        rBG.setSelected(false);
        labelNombre.setFont(fontpequenio);
        tButtonTemaO.setFont(fontpequenio);
        labelTamanio.setFont(fontpequenio);
        labelTamanio2.setFont(fontpequenio);
    }
    public void mediano(ActionEvent event){
        rBP.setSelected(false);
        rBG.setSelected(false);
        labelNombre.setFont(fontMediano);
        tButtonTemaO.setFont(fontMediano);
        labelTamanio.setFont(fontMediano);
        labelTamanio2.setFont(fontMediano);
    }
    public void grande(ActionEvent event){
        rBP.setSelected(false);
        rBM.setSelected(false);
        labelNombre.setFont(fontGrande);
        tButtonTemaO.setFont(fontGrande);
        labelTamanio.setFont(fontGrande);
        labelTamanio2.setFont(fontGrande);
    }

    public void regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../principal/principal.fxml"));
        Scene scene=anchorPane.getScene();
        root.translateYProperty().set(scene.getWidth());
        stackPane.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue ky = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_BOTH);
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
    //Quise hacerlo con todas las ventanas pero no pude asi que haga de ejemplo que esto es una beta :)
    public void temaO(){
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("2B2D42"),CornerRadii.EMPTY, Insets.EMPTY);
        anchorPane.setBackground(new Background(backgroundFill));
    }
    public void temaC(){
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("8D99AE"),CornerRadii.EMPTY, Insets.EMPTY);
        anchorPane.setBackground(new Background(backgroundFill));
    }


}
