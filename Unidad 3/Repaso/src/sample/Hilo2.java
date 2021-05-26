package sample;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

import java.util.Random;


public class Hilo2 extends Thread {
    private ImageView[] imagen;
    private int r;

    public Hilo2(ImageView[] imagen, int r){
        this.imagen=imagen;
        this.r = r;
    }

    @Override
    public void run() {
        while (true){
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0; i<r; i++){
                            Random random = new Random();
                            int u = random.nextInt(10);
                            imagen[i].setLayoutY(imagen[i].getLayoutY()-u);
                        }
                    }
                });
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
