package sample;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class Hilo extends Thread {
    private Button button;
    private int cordenadaX, cordenadaY;
    public Hilo(Button button, int cordenadaX, int cordenadaY){
        this.button = button;
        this.cordenadaX = cordenadaX;
        this.cordenadaY = cordenadaY;
    }

    @Override
    public void run() {
        while (true){
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(cordenadaX != button.getLayoutX()){
                            if(cordenadaX > button.getLayoutX()){
                                button.setLayoutX(button.getLayoutX()+1);
                            }else if(cordenadaX < button.getLayoutX()){
                                button.setLayoutX(button.getLayoutX()-1);
                            }
                        }
                        if (cordenadaY != button.getLayoutY()){
                            if(cordenadaY > button.getLayoutY()){
                                button.setLayoutY(button.getLayoutY()+1);
                            }else {
                                button.setLayoutY(button.getLayoutY()-1);
                            }
                        }
                    }
                });
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
