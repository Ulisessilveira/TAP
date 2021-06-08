package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class Hilo extends Thread {
    //Propiedad de Ulises Silveira
    private ProgressBar progressBar;
    private TextField textField;
    private Label label;
    private Label label2;
    private int porcentaje=0;
    private double litrosExpedidos = 2, precioGasolina=20, litrosGasolina=0, totalLitros=0, totalPrecioGasolina=0, porcentajeBarra = 0, p=0;

    public Hilo(ProgressBar progressBar, TextField textField, Label label, Label label2){
        this.progressBar=progressBar;
        this.textField=textField;
        this.label=label;
        this.label2=label2;
    }
    @Override
    public void run() {
        while (true){
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        totalLitros = Double.parseDouble(textField.getText());
                        totalPrecioGasolina = totalLitros*precioGasolina;
                        porcentajeBarra = litrosExpedidos/totalLitros;
                        if (p<=1) {
                            progressBar.setProgress(p);
                            double p100 = p * 100;
                            porcentaje = (int) p100;
                            label.setText(porcentaje + "% de gasolina cargada");
                            p+=porcentajeBarra;
                            label2.setText("$"+litrosGasolina);
                            litrosGasolina+=(precioGasolina*litrosExpedidos);
                        }else {
                            progressBar.setProgress(1);
                            label.setText(100+"% de gasolina cargada");
                            label2.setText("$"+totalPrecioGasolina);
                        }
                    }
                });
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
