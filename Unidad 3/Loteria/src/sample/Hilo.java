package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Random;

public class Hilo extends Thread{
    Label label;
    public Hilo(Label label){
        this.label = label;
    }

    @Override
    public void run() {
        while (true){
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Random random = new Random();
                        int r = random.nextInt(5);
                        label.setText(r+"");
                    }
                });
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
