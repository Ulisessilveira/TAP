package sample;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class HiloCarrera extends Thread{
    private ImageView objeto;
    private long tiempo=1000;
    private int cont=0;
    private int contConejo=0;
    public HiloCarrera(ImageView objeto, long tiempo){
        this.objeto=objeto;
        this.tiempo=tiempo;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(cont==0){
                            objeto.setLayoutX(objeto.getLayoutX()+10);
                            if(objeto.getLayoutX()>=410){
                                cont=1;
                            }
                        }else if(cont==1){
                            objeto.setLayoutY(objeto.getLayoutY()+10);
                            if(objeto.getLayoutY()>=410){
                                cont=2;
                            }
                        }else if(cont==2){
                            if(tiempo==200 && contConejo<160){
                                objeto.setLayoutX(objeto.getLayoutX()+0);
                                contConejo++;
                            }else {
                                objeto.setLayoutX(objeto.getLayoutX()-10);
                            }
                            if(objeto.getLayoutX()<=20){
                                cont=3;
                            }
                        }else if(cont==3){
                            objeto.setLayoutX(objeto.getLayoutX()+0);
                        }
                    }
                });
                Thread.sleep(tiempo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
