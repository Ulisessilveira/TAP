package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class Controller {
    @FXML Label lblPorcentaje, lblTotalPagar;
    @FXML Button btnStart;
    @FXML ProgressBar barra;
    @FXML TextField txtLitros;
    Hilo hilo;

    public void start (ActionEvent event){
        if(hilo != null) hilo.stop(); hilo=null;
        hilo = new Hilo(barra,txtLitros,lblPorcentaje,lblTotalPagar);
        hilo.start();
    }
}
