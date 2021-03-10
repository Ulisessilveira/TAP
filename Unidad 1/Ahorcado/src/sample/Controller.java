package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

import java.util.Random;

public class Controller {
    @FXML HBox contenedor;
    @FXML AnchorPane padre;
    String[] palabras={"PELOTA", "MICROFONO", "FUNKO", "CUBO", "MOUSE", "PASTILLAS", "WII"};
    TextField[] arrayTxt=null;
    int ayudamal=0; //LETRAS DE AYUDA MAL
    int ayudabien=0; //LETRAS DE AYUDA BIEN OWO
    @FXML protected void initialize(){
        Random random= new Random();
        int aleatorio = random.nextInt(6);
        String palabra = palabras[aleatorio].toLowerCase();
        System.out.println(palabra);
        arrayTxt= new TextField[palabra.length()];
        for(int x=0; x<palabra.length(); x++){
            arrayTxt[x] = new TextField();
            arrayTxt[x].setPrefWidth(50);
            arrayTxt[x].setPrefHeight(50);
            arrayTxt[x].setId("txt-" + x + "-" + palabra.charAt(x));
            arrayTxt[x].setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    TextField textField=(TextField) event.getTarget();
                    String[] nombre = textField.getId().split("-");
                    if(nombre[2].equals(textField.getText().toLowerCase())){
                        System.out.println("CORRECTO" + textField.getId());
                        textField.setDisable(true);
                        ayudabien++;
                        if (ayudabien==palabra.length()) victoria();
                    }else {
                        System.out.println("INCORRECTO" + textField.getId());
                        textField.setText("");
                        ayudamal++;
                        if (ayudamal==1){
                            pintarcabeza();
                        }else if(ayudamal==2){
                            pintartorso();
                        }else if(ayudamal==3){
                            pintarbrazoder();
                        }else if(ayudamal==4){
                            pintarbrazoizq();
                        }else if(ayudamal==5){
                            pintarpiernader();
                        }else if(ayudamal==6){
                            pintarpiernaizq();
                        }else if(ayudamal>6){
                            perdedor();
                            textField.setDisable(true);
                        }
                    }
                }
            });
            contenedor.getChildren().add(arrayTxt[x]);
        }
        ImageView horca= new ImageView(new Image("./sample/img/horca.png"));
        horca.setFitWidth(300);
        horca.setFitHeight(300);
        horca.setLayoutX(0);
        horca.setLayoutY(200);
        padre.getChildren().add(horca);
    }

    public void pintarcabeza(){
        ImageView cabeza= new ImageView(new Image("./sample/img/cabeza.png"));
        cabeza.setFitWidth(40);
        cabeza.setFitHeight(40);
        cabeza.setLayoutX(212);
        cabeza.setLayoutY(345);
        padre.getChildren().add(cabeza);
    }
    public void pintartorso(){
        ImageView torso= new ImageView(new Image("./sample/img/torso.png"));
        torso.setFitWidth(40);
        torso.setFitHeight(40);
        torso.setLayoutX(212);
        torso.setLayoutY(385);
        padre.getChildren().add(torso);
    }
    public void pintarbrazoizq(){
        ImageView brazoizq= new ImageView(new Image("./sample/img/Brazo izq.png"));
        brazoizq.setFitWidth(40);
        brazoizq.setFitHeight(40);
        brazoizq.setLayoutX(252);
        brazoizq.setLayoutY(365);
        padre.getChildren().add(brazoizq);
    }
    public void pintarbrazoder(){
        ImageView brazoder= new ImageView(new Image("./sample/img/Brazo der.png"));
        brazoder.setFitWidth(40);
        brazoder.setFitHeight(40);
        brazoder.setLayoutX(172);
        brazoder.setLayoutY(365);
        padre.getChildren().add(brazoder);
    }
    public void pintarpiernaizq(){
        ImageView piernaizq= new ImageView(new Image("./sample/img/pierna izq.png"));
        piernaizq.setFitWidth(30);
        piernaizq.setFitHeight(30);
        piernaizq.setLayoutX(228);
        piernaizq.setLayoutY(425);
        padre.getChildren().add(piernaizq);
    }
    public void pintarpiernader(){
        ImageView piernader= new ImageView(new Image("./sample/img/pierna der.png"));
        piernader.setFitWidth(30);
        piernader.setFitHeight(30);
        piernader.setLayoutX(202);
        piernader.setLayoutY(425);
        padre.getChildren().add(piernader);
    }
    public void perdedor(){
        ImageView perder= new ImageView(new Image("./sample/img/perder.png"));
        perder.setFitWidth(250);
        perder.setFitHeight(120);
        perder.setLayoutX(260);
        perder.setLayoutY(360);
        padre.getChildren().add(perder);
    }
    public void victoria(){
        ImageView vic= new ImageView(new Image("./sample/img/victoria.png"));
        vic.setFitWidth(500);
        vic.setFitHeight(400);
        vic.setLayoutX(0);
        vic.setLayoutY(0);
        padre.getChildren().add(vic);
    }
}