package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class Controller {
    @FXML Label lb1, lb2, lb3, lb4;
    @FXML Button btnGenerar;
    Hilo h1,h2,h3,h4;
    int l1,l2,l3,l4;
    public void generar(ActionEvent event){
        if(btnGenerar.getText().equals("Generar")){
            h1 = new Hilo(lb1);
            h2 = new Hilo(lb2);
            h3 = new Hilo(lb3);
            h4 = new Hilo(lb4);
            h1.start();
            h2.start();
            h3.start();
            h4.start();
            btnGenerar.setText("Detener");
            btnGenerar.setStyle("-fx-background-color: blue");
        }else if(btnGenerar.getText().equals("Detener")){
            //Primer numero--------------------------------------------------------------------------------
            h1.stop();
            l1 = Integer.parseInt(lb1.getText());
            System.out.println("Soy l1: " +l1);
            System.out.println("Soy el numero del label 1: " + lb1.getText());
            //Segundo numero--------------------------------------------------------------------------------
            h2.stop();
            l2 = Integer.parseInt(lb2.getText());
            System.out.println("Soy l2: " + l2);
            while (l2==l1){
                if(l2==l1){
                    System.out.println("Me repeti en el primer numero " + l2);
                }
                Random random = new Random();
                l2=random.nextInt(5);
                System.out.println("Soy el nuevo l2: " + l2);
            }
            lb2.setText(l2+"");
            System.out.println("Soy el numero del label 2: " + lb2.getText());
            //Tercer numero--------------------------------------------------------------------------------
            h3.stop();
            l3 = Integer.parseInt(lb3.getText());
            System.out.println("Soy l3: " + l3);
            while (l3==l1 || l3==l2){
                if(l3==l1){
                    System.out.println("Me repeti en el primer numero " + l3);
                }else if(l3==l2){
                    System.out.println("Me repeti en el segundo numero " + l3);
                }
                Random random = new Random();
                l3=random.nextInt(5);
                System.out.println("Soy el nuevo l3: " + l3);
            }
            lb3.setText(l3+"");
            System.out.println("Soy el numero del label 3: " + lb3.getText());
            //Cuarto numero--------------------------------------------------------------------------------
            h4.stop();
            l4 = Integer.parseInt(lb4.getText());
            System.out.println("Soy l4: " + l4);
            while (l4==l1 || l4==l2 || l4==l3){
                if(l4==l1){
                    System.out.println("Me repeti en el primer numero " + l4);
                }else if(l4==l2){
                    System.out.println("Me repeti en el segundo numero " + l4);
                }else if(l4==l3){
                    System.out.println("Me repeti en el tercer numero " + l4);
                }
                Random random = new Random();
                l4=random.nextInt(5);
                System.out.println("Soy el nuevo l4: " + l4);
            }
            lb4.setText(l4+"");
            System.out.println("Soy el numero del label 4: " + lb4.getText());
            System.out.println("Numero bueno consola: " + l1 + l2 + l3 + l4);
            System.out.println("Numero bueno label: " + lb1.getText() + lb2.getText() + lb3.getText() + lb4.getText());
            System.out.println("");
            lb1.setText(l1+"");
            lb2.setText(l2+"");
            lb3.setText(l3+"");
            lb4.setText(l4+"");
            btnGenerar.setText("Generar");
            btnGenerar.setStyle("-fx-background-color: gray");
            /*A veces como que tiene un bug visual porque en el panel se ve un numero pero deberia verse otro, ¿Por qué sucede esto?
            No lo se, trate de mandarle otra vez los l1,l2... pero se quedaba igual, se lo digo porque es raro que pase pero pasa
            No le haga caso al detenerModoDesesperacion, lo hice en un momento de desesperacion en donde los malditos label se quedaban
            iguales no importa cuanto lo intera asi que acudi a una mexicanada pero para no verme tan vago hice otra mexicanada xD */
        }
    }
    public void detenerModoDesesperacion(){
        System.out.println(lb1.getText()+ lb2.getText()+ lb3.getText()+ lb4.getText());
        if (!lb1.getText().equals(lb2.getText()) && !lb1.getText().equals(lb3.getText()) && !lb2.getText().equals(lb3.getText()) && !lb1.getText().equals(lb4.getText()) && !lb2.getText().equals(lb4.getText()) && !lb3.getText().equals(lb4.getText())){
            System.out.println("Funciona perron :D " + lb1.getText()+ lb2.getText()+ lb3.getText()+ lb4.getText());
            btnGenerar.setText("Generar");
            btnGenerar.setStyle("-fx-background-color: gray");
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("REPITE");
            alert.setContentText("Vuelve a dar el boton porfavor");
            alert.show();
        }

    }
}
