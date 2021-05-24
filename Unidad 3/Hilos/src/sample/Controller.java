package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Controller {
    @FXML Label lblNumero;
    @FXML ImageView conejo, tortuga, meta;
    Hilo hilo1;
    HiloCarrera hiloCarreraConejo, hiloCarreraTortuga;
    public void iniciar(ActionEvent event){
        /*hilo1= new Hilo(lblNumero);
        hilo1.start();*/
        hiloCarreraConejo = new HiloCarrera(conejo,200);
        hiloCarreraTortuga = new HiloCarrera(tortuga,500);
        hiloCarreraConejo.start();
        hiloCarreraTortuga.start();
    }
    public void detener(ActionEvent event){
        hilo1.stop();
    }
}
