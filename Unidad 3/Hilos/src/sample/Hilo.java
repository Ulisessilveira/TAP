package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Hilo extends Thread{
    /* 1)Crear la clase y heredar de thread
       2)Sobreescribir el metodo Run
       3)Crear el while infinito
       4)Crear el Plattform
       5)Programar la logica y poner Thread para que no sea infinito
     */
    private int cont=0;
    private Label label;
    public Hilo(Label label){
        this.label=label;
    }
    @Override
    public void run() {
        //Se ejecuta cuando se inica el hilo o proceso
        while(true){
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //Muchas veces
                        System.out.println(cont);
                        label.setText(cont+"");
                        cont++;
                    }
                });
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
