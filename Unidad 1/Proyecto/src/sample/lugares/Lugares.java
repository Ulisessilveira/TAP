package sample.lugares;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class Lugares {
    @FXML AnchorPane anchorPane;
    @FXML StackPane stackPane;
    @FXML HBox hBoxParques, hBoxRestaurantes, hBoxInteresantes;
    @FXML VBox vBoxParques, vBoxRestaurantes, vBoxInteresantes;
    //Parques-----------------------------------------------------------------------------------------------------------------------------
    String[] parques = {"Macro Plaza", "Plaza Benito Juárez", "Plaza Héroes"};
    String[] direccion = {"5 de Febrero 1702, Centro, 31700 Nuevo Casas Grandes, Chih.",
            "Minerva 106, Centro, 31700 Nuevo Casas Grandes, Chih.",
            "Constitución Oriente, Centro, 31700 Nuevo Casas Grandes, Chih."};
    ImageView[] arrayIParque={new ImageView(new Image(getClass().getResourceAsStream("../img/lugares/macroplaza.jpg"))),
            new ImageView(new Image(getClass().getResourceAsStream("../img/lugares/plazaGrande.jpg"))),
            new ImageView(new Image(getClass().getResourceAsStream("../img/lugares/plazaHéroes.jpg")))};
    //Restaurantes-------------------------------------------------------------------------------------------------------------------------
    String[] restaurantes = {"Burrotote", "Pon Pon", "El Piporro"};
    String[] direccion2 = {"El Burrotote, Campestre 536, Los Arcos, 31770 Nuevo Casas Grandes, Chih.",
            "Av Benito Juárez 1305, Centro, 31700 Nuevo Casas Grandes, Chih.",
            "Avenida Benito Juárez 31700 Nuevo Casas Grandes, México "};
    ImageView[] arrayIRestaurante={new ImageView(new Image(getClass().getResourceAsStream("../img/lugares/burrotote.jpg"))),
            new ImageView(new Image(getClass().getResourceAsStream("../img/lugares/ponpon.jpg"))),
            new ImageView(new Image(getClass().getResourceAsStream("../img/lugares/piporro.jpg")))};
    //Interesantes-------------------------------------------------------------------------------------------------------------------------
    String[] interesantes = {"Corredor Deportivo", "Plaza China", "Torre Parque Extremo"};
    String[] direccion3 = {"Corredor Deportivo \"Las Vias De NCG\", Obrera, 31750 Nuevo Casas Grandes, Chih.",
            "Plaza China, Centro, 31700 Nuevo Casas Grandes, Chih.",
            "Álvaro Obregón 1909, Obrera, 31750 Nuevo Casas Grandes, Chih."};
    ImageView[] arrayIInteresantes={new ImageView(new Image(getClass().getResourceAsStream("../img/lugares/corredorDeportivo.png"))),
            new ImageView(new Image(getClass().getResourceAsStream("../img/lugares/plazaChina.jpg"))),
            new ImageView(new Image(getClass().getResourceAsStream("../img/lugares/torre.jpg")))};
    @FXML protected void initialize(){
        //Parques-----------------------------------------------------------------------------------------------------------------------------
        Random random = new Random();
        int aleatorio = random.nextInt(3);
        String parque = parques[aleatorio].toLowerCase();
        System.out.println(parque);
        arrayIParque[aleatorio].setFitHeight(110);
        arrayIParque[aleatorio].setFitWidth(110);
        arrayIParque[aleatorio].setVisible(true);
        Label par = new Label(parques[aleatorio]);
        Label direcciones = new Label(direccion[aleatorio]);
        vBoxParques.getChildren().addAll(par,arrayIParque[aleatorio],direcciones);
        par.setTextFill(Color.WHITE);
        direcciones.setTextFill(Color.WHITE);
        //Restaurantes-------------------------------------------------------------------------------------------------------------------------
        Random random2 = new Random();
        int aleatorio2 = random2.nextInt(3);
        String restaurante = restaurantes[aleatorio2].toLowerCase();
        System.out.println(restaurante);
        arrayIRestaurante[aleatorio2].setFitHeight(110);
        arrayIRestaurante[aleatorio2].setFitWidth(110);
        arrayIRestaurante[aleatorio2].setVisible(true);
        Label rest = new Label(restaurantes[aleatorio2]);
        Label direcciones2 = new Label(direccion2[aleatorio2]);
        vBoxRestaurantes.getChildren().addAll(rest,arrayIRestaurante[aleatorio2],direcciones2);
        rest.setTextFill(Color.WHITE);
        direcciones2.setTextFill(Color.WHITE);
        //Interesantes-------------------------------------------------------------------------------------------------------------------------
        Random random3 = new Random();
        int aleatorio3 = random3.nextInt(3);
        String interesante = interesantes[aleatorio3].toLowerCase();
        System.out.println(interesante);
        arrayIInteresantes[aleatorio3].setFitHeight(110);
        arrayIInteresantes[aleatorio3].setFitWidth(110);
        arrayIInteresantes[aleatorio3].setVisible(true);
        Label inte = new Label(interesantes[aleatorio3]);
        Label direcciones3 = new Label(direccion3[aleatorio3]);
        vBoxInteresantes.getChildren().addAll(inte,arrayIInteresantes[aleatorio3],direcciones3);
        inte.setTextFill(Color.WHITE);
        direcciones3.setTextFill(Color.WHITE);
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
}
