package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class Controller {
    @FXML Button btnSi, btnNo;
    @FXML Label lb1;
    @FXML AnchorPane anchorPane;
    Hilo hilo1;
    Hilo2 hilo2;
    public void si(ActionEvent event){
        Random random = new Random();
        int r = random.nextInt(100);
        ImageView[] view=new ImageView[r];
        for (int i=0; i<r; i++){
            view[i] = new ImageView(new Image("./sample/corazon.png"));
            view[i].setFitWidth(32);
            view[i].setFitHeight(32);
            view[i].setLayoutY(500);
            view[i].setLayoutX(random.nextInt(500));
            anchorPane.getChildren().add(view[i]);
        }
        if(hilo2 != null) hilo2.stop(); hilo2=null;
        hilo2 = new Hilo2(view,r);
        hilo2.start();
        lb1.setText("VIVA LOS NOVIOS");
    }
    public void no(MouseEvent event){
        if(hilo1 != null) hilo1.stop(); hilo1=null;
        Random random = new Random();
        int rx = random.nextInt(480);
        int ry = random.nextInt(480);
        hilo1 =new Hilo(btnNo, rx,ry);
        hilo1.start();
        System.out.println("Cordenada x: " + rx + "\nCordenada y: " + ry);
        System.out.println("");
    }
}
