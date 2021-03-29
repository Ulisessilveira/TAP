package sample.covid;


import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;


import java.io.IOException;
import java.util.Optional;

public class Covid {
    @FXML BorderPane borderPane;
    @FXML AnchorPane anchorPane;
    @FXML StackPane stackPane;
    @FXML Button btnPreguntas;
    @FXML JFXButton btnRegresar,btnVerdadero,btnFalso,btnReintentar,btnTerminar;
    @FXML ImageView mal,bien,comprension,aplaudir,triste;
    @FXML Label label,label2;
    @FXML HBox hBox,hBoxExxplicacion,hBox2,hBVerdad,hBFalso, hBoxRegresar, hBoxReintentar, hBoxTerminar;
    int contador = 0;
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
    public void preguntas(ActionEvent event){
        borderPane.setVisible(false);
        new ZoomInDown(anchorPane).play();

        btnVerdadero = new JFXButton();
        btnVerdadero.setMaxSize(300,300);
        btnVerdadero.setText("SI");
        btnVerdadero.setLayoutX(200);
        btnVerdadero.setLayoutY(400);
        btnVerdadero.setFont(Font.font("Barlow Condensed Medium",25));
        btnVerdadero.setTextFill(Color.web("c2fb3b"));

        btnFalso = new JFXButton();
        btnFalso.setMaxSize(300,300);
        btnFalso.setText("NO");
        btnFalso.setLayoutX(400);
        btnFalso.setLayoutY(400);
        btnFalso.setFont(Font.font("Barlow Condensed Medium",25));
        btnFalso.setTextFill(Color.web("D80032"));

        btnRegresar = new JFXButton();
        btnRegresar.setMaxSize(300,300);
        btnRegresar.setText("<");
        btnRegresar.setFont(Font.font("Barlow Condensed Medium",20));
        btnRegresar.setTextFill(Color.WHITE);
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("D80032"),new CornerRadii(20), Insets.EMPTY);
        btnRegresar.setBackground(new Background(backgroundFill));

        btnReintentar = new JFXButton();
        btnReintentar.setMaxSize(300,300);
        btnReintentar.setText("Reitentar");
        btnReintentar.setFont(Font.font("Barlow Condensed Medium",20));
        btnReintentar.setTextFill(Color.WHITE);
        btnReintentar.setBackground(new Background(backgroundFill));
        btnReintentar.setVisible(false);

        btnTerminar = new JFXButton();
        btnTerminar.setMaxSize(300,300);
        btnTerminar.setFont(Font.font("Barlow Condensed Medium",20));
        btnTerminar.setTextFill(Color.WHITE);
        btnTerminar.setBackground(new Background(backgroundFill));
        btnTerminar.setVisible(false);

        label = new Label("¿Estas listo para empezar?");
        label.setMaxSize(700,300);
        label.setFont(Font.font("Verdana",20));
        label.setTextFill(Color.WHITE);

        label2 = new Label();
        label2.setMaxSize(700,300);
        label2.setFont(Font.font("Verdana",15));
        label2.setTextFill(Color.WHITE);
        BackgroundFill backgroundFill2 = new BackgroundFill(Color.web("2B2D42"),new CornerRadii(5), Insets.EMPTY);
        label2.setBackground(new Background(backgroundFill2));

        bien = new ImageView();
        bien.setImage(new Image(getClass().getResourceAsStream("../img/covid/bien.png")));
        bien.setFitHeight(200);
        bien.setFitWidth(200);
        bien.setVisible(false);

        mal = new ImageView();
        mal.setImage(new Image(getClass().getResourceAsStream("../img/covid/mal.png")));
        mal.setFitHeight(200);
        mal.setFitWidth(200);
        mal.setVisible(false);

        aplaudir = new ImageView();
        aplaudir.setImage(new Image(getClass().getResourceAsStream("../img/covid/aplaudir.png")));
        aplaudir.setFitHeight(200);
        aplaudir.setFitWidth(200);
        aplaudir.setVisible(false);

        triste = new ImageView();
        triste.setImage(new Image(getClass().getResourceAsStream("../img/covid/llorar.png")));
        triste.setFitHeight(200);
        triste.setFitWidth(200);
        triste.setVisible(false);

        comprension = new ImageView();
        comprension.setImage(new Image(getClass().getResourceAsStream("../img/covid/comprension.png")));
        comprension.setFitHeight(200);
        comprension.setFitWidth(200);
        comprension.setVisible(false);

        anchorPane.getChildren().addAll(btnVerdadero, btnFalso, btnRegresar);
        hBox.getChildren().add(label);
        hBVerdad.getChildren().add(btnVerdadero);
        hBFalso.getChildren().add(btnFalso);
        hBoxRegresar.getChildren().add(btnRegresar);
        hBoxReintentar.getChildren().add(btnReintentar);
        hBoxTerminar.getChildren().add(btnTerminar);

        btnVerdadero.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (contador==0){
                    btnVerdadero.setText("VERDADERO");
                    btnFalso.setText("FALSO");
                    label.setText("El cubrebocas no es necesario y menos en lugares conglomerados");
                    new FadeIn(label).play();
                    contador++;
                }else if (contador==1){
                    hBox2.getChildren().add(mal);
                    label2.setTextFill(Color.web("c2fb3b"));
                    label2.setText("El cubrebocas es esencial en esta pademia y mas en lugares conglomerados, tonto");
                    hBoxExxplicacion.getChildren().add(label2);
                    new Shake(label2).play();
                    mal.setVisible(true);
                    new Tada(mal).play();
                    btnFalso.setDisable(true);
                    btnVerdadero.setDisable(true);
                    btnReintentar.setVisible(true);
                }else if(contador==2){
                    hBox2.getChildren().add(bien);
                    bien.setVisible(true);
                    new Tada(bien).play();
                    btnFalso.setDisable(true);
                    btnVerdadero.setDisable(true);
                    btnTerminar.setText("Siguiente");
                    btnTerminar.setVisible(true);
                    new FadeIn(btnTerminar).play();
                    contador++;
                }else if(contador==3){
                    hBox2.getChildren().add(bien);
                    bien.setVisible(true);
                    new Tada(bien).play();
                    btnFalso.setDisable(true);
                    btnVerdadero.setDisable(true);
                    btnTerminar.setText("Siguiente");
                    btnTerminar.setVisible(true);
                    new FadeIn(btnTerminar).play();
                    contador++;
                }else if(contador==4){
                    hBox2.getChildren().add(mal);
                    label2.setTextFill(Color.web("c2fb3b"));
                    label2.setText("Salir a caminar al aire libre no es peligros, sal sin miedo y recuerda llevar cubrebocas");
                    hBoxExxplicacion.getChildren().add(label2);
                    new FadeIn(label2).play();
                    mal.setVisible(true);
                    new Tada(mal).play();
                    btnFalso.setDisable(true);
                    btnVerdadero.setDisable(true);
                    btnReintentar.setVisible(true);
                }else if(contador==5){
                    hBox2.getChildren().add(aplaudir);
                    label.setText("Felicidades, ya estás listo para salir a caminar y disfrutar :D");
                    label2.setTextFill(Color.web("c2fb3b"));
                    label2.setText("No olvide las medidadas de prevención y disfruta de tus caminatas :)");
                    hBoxExxplicacion.getChildren().add(label2);
                    new FadeIn(label2).play();
                    aplaudir.setVisible(true);
                    new Tada(aplaudir).play();
                    btnFalso.setDisable(true);
                    btnVerdadero.setDisable(true);
                    btnTerminar.setText("Terminar");
                    btnTerminar.setVisible(true);
                    new FadeIn(btnTerminar).play();
                    contador++;
                }
            }
        });
        btnFalso.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(contador==0){
                    label.setText("Okay, vuelve cuando te sientas preparado");
                    new FadeIn(label).play();
                    hBox2.getChildren().add(comprension);
                    new Tada(comprension).play();
                    comprension.setVisible(true);
                    btnVerdadero.setDisable(true);
                    btnReintentar.setVisible(true);
                }else if(contador==1){
                    hBox2.getChildren().addAll(bien);
                    bien.setVisible(true);
                    new Tada(bien).play();
                    btnFalso.setDisable(true);
                    btnVerdadero.setDisable(true);
                    btnTerminar.setText("Siguiente");
                    btnTerminar.setVisible(true);
                    contador++;
                }else if(contador==2){
                    hBox2.getChildren().add(mal);
                    label2.setTextFill(Color.web("c2fb3b"));
                    label2.setText("Es verdad que las personas se deben lavar por lo menos 20 segundos");
                    hBoxExxplicacion.getChildren().add(label2);
                    new FadeIn(label2).play();
                    mal.setVisible(true);
                    new Tada(mal).play();
                    btnFalso.setDisable(true);
                    btnVerdadero.setDisable(true);
                    btnReintentar.setVisible(true);
                }else if(contador==3){
                    hBox2.getChildren().add(mal);
                    label2.setTextFill(Color.web("c2fb3b"));
                    label2.setText("Efectivamente, la sana distancia es de uno a dos metros");
                    hBoxExxplicacion.getChildren().add(label2);
                    new FadeIn(label2).play();
                    mal.setVisible(true);
                    new Tada(mal).play();
                    btnFalso.setDisable(true);
                    btnVerdadero.setDisable(true);
                    btnReintentar.setVisible(true);
                }else if(contador==4){
                    hBox2.getChildren().addAll(bien);
                    bien.setVisible(true);
                    new Tada(bien).play();
                    btnFalso.setDisable(true);
                    btnVerdadero.setDisable(true);
                    btnTerminar.setText("Siguiente");
                    btnTerminar.setVisible(true);
                    contador++;
                }else if(contador==5){
                    hBox2.getChildren().add(triste);
                    label.setText("COMO!!!!! Acaso haz hecho trampa bueno vuelve a intentarlo :C");
                    label2.setTextFill(Color.web("c2fb3b"));
                    label2.setText("Si no te has aprendido las medidas de prevención, evita salir, no quiero que mueras :(");
                    hBoxExxplicacion.getChildren().add(label2);
                    new FadeIn(label2).play();
                    triste.setVisible(true);
                    new Tada(triste).play();
                    btnFalso.setDisable(true);
                    btnVerdadero.setDisable(true);
                    btnTerminar.setText("Terminar");
                    btnTerminar.setVisible(true);
                    new FadeIn(btnTerminar).play();
                    btnReintentar.setVisible(true);
                    new FadeIn(btnReintentar).play();
                    contador++;
                }
            }
        });
        btnReintentar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                contador = 0;
                btnVerdadero.setText("SI");
                btnFalso.setText("NO");
                label.setText("¿Estás listo para empezar?");
                new FadeIn(label).play();
                btnFalso.setDisable(false);
                btnVerdadero.setDisable(false);
                hBox2.getChildren().clear();
                hBoxExxplicacion.getChildren().clear();
                btnReintentar.setVisible(false);
                btnTerminar.setVisible(false);
            }
        });
        btnTerminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(contador==2){
                    btnVerdadero.setText("VERDADERO");
                    btnFalso.setText("FALSO");
                    label.setText("Las personas deben lavarse las manos por lo menos 20 segundos");
                    new FadeIn(label).play();
                    btnVerdadero.setDisable(false);
                    btnFalso.setDisable(false);
                    hBox2.getChildren().clear();
                    btnTerminar.setVisible(false);
                }else if (contador == 3){
                    btnVerdadero.setText("SI");
                    btnFalso.setText("NO");
                    label.setText("¿La sana distancia es de uno a dos metros de las otras prsonas?");
                    new FadeIn(label).play();
                    btnVerdadero.setDisable(false);
                    btnFalso.setDisable(false);
                    hBox2.getChildren().clear();
                    btnTerminar.setVisible(false);
                }else if(contador==4){
                    btnVerdadero.setText("SI");
                    btnFalso.setText("NO");
                    label.setText("¿Salir a caminar al parque o cualquier lugar al aire libre es peligroso?");
                    new FadeIn(label).play();
                    btnVerdadero.setDisable(false);
                    btnFalso.setDisable(false);
                    hBox2.getChildren().clear();
                    btnTerminar.setVisible(false);
                }else if (contador==5){
                    btnVerdadero.setText("SI");
                    btnFalso.setText("NO");
                    label.setText("¿Te has aprendido todas las medidas de prevención?");
                    new FadeIn(label).play();
                    btnVerdadero.setDisable(false);
                    btnFalso.setDisable(false);
                    hBox2.getChildren().clear();
                    btnTerminar.setVisible(false);
                }else {
                    btnVerdadero.setVisible(false);
                    btnFalso.setVisible(false);
                    bien.setVisible(false);
                    mal.setVisible(false);
                    label.setVisible(false);
                    borderPane.setVisible(true);
                    new ZoomInUp(borderPane).play();
                    hBox.getChildren().clear();
                    hBoxExxplicacion.getChildren().clear();
                    hBox2.getChildren().clear();
                    hBVerdad.getChildren().clear();
                    hBFalso.getChildren().clear();
                    hBoxRegresar.getChildren().clear();
                    hBoxReintentar.getChildren().clear();
                    hBoxTerminar.getChildren().clear();
                    contador=0;
                }
            }
        });
        btnRegresar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Hike");
                alert.setContentText("¿Estás seguro? perderás tus avances");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    btnVerdadero.setVisible(false);
                    btnFalso.setVisible(false);
                    bien.setVisible(false);
                    mal.setVisible(false);
                    label.setVisible(false);
                    borderPane.setVisible(true);
                    new ZoomInUp(borderPane).play();
                    hBox.getChildren().clear();
                    hBoxExxplicacion.getChildren().clear();
                    hBox2.getChildren().clear();
                    hBVerdad.getChildren().clear();
                    hBFalso.getChildren().clear();
                    hBoxRegresar.getChildren().clear();
                    hBoxReintentar.getChildren().clear();
                    hBoxTerminar.getChildren().clear();
                    contador=0;
                }else{
                    System.out.println("No se fue");
                }
            }
        });
    }
    public void temaO(){
        BackgroundFill backgroundFill2 = new BackgroundFill(Color.web("2B2D42"),CornerRadii.EMPTY, Insets.EMPTY);
        anchorPane.setBackground(new Background(backgroundFill2));
    }
}
