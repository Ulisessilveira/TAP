package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {
    @FXML Canvas lienzo;
    GraphicsContext context;
    @FXML ColorPicker colorPicker;
    @FXML Slider slider;
    @FXML ComboBox comboOpciones;
    Color colorPincel = Color.BLACK;
    @FXML protected void initialize(){
        context = lienzo.getGraphicsContext2D();
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                pintarDibujos((newValue.intValue()));
            }
        });
        comboOpciones.getItems().addAll("Cuadricula", "Ajedrez", "Estrella", "Curva","Estrella Doble", "Estrella Tapiz");
        //Traer pixeles para manipularles

        /*context.setFill(Color.BLUE);//fill relleno color a pintar
        context.fillRect(10,20,100,50);
        context.strokeRect(200,150,200,100);
        context.strokeRect(400,250,200,100);
        context.fillOval(375,375,50,50);
        context.strokeLine(0,0,lienzo.getWidth(),lienzo.getHeight());*/
    }
    public void pintarDibujos(int valor){
        context.setFill(Color.WHITESMOKE);
        context.fillRect(0,0,lienzo.getWidth(),lienzo.getHeight());
        context.setFill(colorPincel);
        context.setStroke(colorPincel);
        System.out.println(valor);
        if(comboOpciones.getSelectionModel().getSelectedIndex() == 0) {
            //Cuadricula
            for(int x=1; x<valor+1; x++){
                int divisiones = (int) (lienzo.getWidth() / valor);
                context.strokeLine(0,divisiones * x,lienzo.getWidth(),divisiones*x);//Linea horizontal
                context.strokeLine(divisiones * x,0,divisiones * x,lienzo.getWidth());//Linea vertical
            }
        }else if(comboOpciones.getSelectionModel().getSelectedIndex() == 1) {
            //Ajedrez
            context.fillRect(0,0,lienzo.getWidth(), lienzo.getHeight());
            int separacion = 2*valor;
            for(int x=0; x<=lienzo.getWidth(); x+=separacion){
                for(int y=0; y<=lienzo.getWidth(); y+=separacion){
                    context.clearRect(x,y,valor,valor);
                }
            }
            for(int x=valor; x<=lienzo.getWidth(); x+=separacion){
                for(int y=valor; y<=lienzo.getWidth(); y+=separacion){
                    context.clearRect(x,y,valor,valor);
                }
            }
        }else if(comboOpciones.getSelectionModel().getSelectedIndex() == 2) {
            //Estrella
            int mitadAncho = (int) lienzo.getWidth() / 2;
            int mitadAlto = (int) lienzo.getHeight() / 2;
            context.strokeLine(mitadAncho,0,mitadAncho,lienzo.getHeight());
            context.strokeLine(0,mitadAlto,lienzo.getWidth(),mitadAlto);
            int divisiones=mitadAncho/valor;
            for (int j=1; j<valor+1;j++){
                context.strokeLine(mitadAncho,divisiones*j,mitadAncho+(divisiones*j),mitadAlto);
                context.strokeLine(mitadAncho,divisiones*j,mitadAncho-(divisiones*j),mitadAlto);
                context.strokeLine(mitadAncho,lienzo.getHeight()-(divisiones*j),mitadAncho-(divisiones*j),mitadAlto);
                context.strokeLine(mitadAncho,lienzo.getHeight()-(divisiones*j),mitadAncho+(divisiones*j),mitadAlto);
            }
        }else if(comboOpciones.getSelectionModel().getSelectedIndex() == 3){
            //Curva
            int divisiones=(int)lienzo.getWidth()/valor;
            for (int j=1; j<valor+1;j++){
                context.strokeLine(0,divisiones*j,divisiones*j,lienzo.getHeight());
                context.strokeLine(lienzo.getWidth(),divisiones*j,lienzo.getWidth()-(divisiones*j),lienzo.getHeight());
                context.strokeLine(0,lienzo.getWidth()-(divisiones*j),divisiones*j,0);
                context.strokeLine(lienzo.getWidth(),lienzo.getWidth()-(divisiones*j),lienzo.getWidth()-(divisiones*j),0);
            }
        }else if(comboOpciones.getSelectionModel().getSelectedIndex() == 4){
            //Estrella doble
            int mitadAncho = (int) lienzo.getWidth() / 2;
            int mitadAlto = (int) lienzo.getHeight() / 2;
            int diagonal=(int)Math.sqrt(Math.pow(mitadAncho,2)+(Math.pow(mitadAlto,2)));
            int divisiones=mitadAncho/valor;
            for (int j=1; j<valor+1;j++){
                //Curvas
                context.strokeLine(0,mitadAncho+(divisiones*j),divisiones*j,lienzo.getHeight());
                context.strokeLine(lienzo.getWidth(),mitadAncho+(divisiones*j),lienzo.getWidth()-(divisiones*j),lienzo.getHeight());
                context.strokeLine(0,mitadAncho-(divisiones*j),divisiones*j,0);
                context.strokeLine(lienzo.getWidth(),mitadAlto-(divisiones*j),lienzo.getWidth()-(divisiones*j),0);
                //Estrella 1
                context.strokeLine(mitadAncho,divisiones*j,mitadAncho+(divisiones*j),mitadAlto);
                context.strokeLine(mitadAncho,divisiones*j,mitadAncho-(divisiones*j),mitadAlto);
                context.strokeLine(mitadAncho,lienzo.getHeight()-(divisiones*j),mitadAncho-(divisiones*j),mitadAlto);
                context.strokeLine(mitadAncho,lienzo.getHeight()-(divisiones*j),mitadAncho+(divisiones*j),mitadAlto);
                //Estrella 2
                context.strokeLine(divisiones*j,divisiones*j, mitadAncho+(divisiones*j),mitadAlto-(divisiones*j));
                context.strokeLine(divisiones*j,divisiones*j, mitadAncho-(divisiones*j),mitadAlto+(divisiones*j));
                context.strokeLine(lienzo.getWidth()-(divisiones*j),lienzo.getHeight()-divisiones*j, mitadAncho+(divisiones*j),mitadAlto-(divisiones*j));
                context.strokeLine(lienzo.getWidth()-(divisiones*j),lienzo.getHeight()-divisiones*j, mitadAncho-(divisiones*j),mitadAlto+(divisiones*j));


            }
        }else if(comboOpciones.getSelectionModel().getSelectedIndex() == 5){
            //Estrella Tapiz
            int mitadAncho = (int) lienzo.getWidth() / 2;
            int mitadAlto = (int) lienzo.getHeight() / 2;
            context.strokeLine(mitadAncho,0,mitadAncho,lienzo.getHeight());
            context.strokeLine(0,mitadAlto,lienzo.getWidth(),mitadAlto);
            int divisiones=mitadAncho/valor;
            for (int j=1; j<valor+1;j++){
                //Curvas
                context.strokeLine(mitadAncho+(divisiones*j),0,mitadAncho,mitadAlto-(divisiones*j));
                context.strokeLine(lienzo.getWidth(),divisiones*j,lienzo.getWidth()-(divisiones*j),mitadAlto);

                context.strokeLine(mitadAncho-(divisiones*j),0,mitadAncho,mitadAlto-(divisiones*j));
                context.strokeLine(0,divisiones*j,divisiones*j,mitadAlto);

                context.strokeLine(0,lienzo.getHeight()-(divisiones*j),divisiones*j,mitadAlto);
                context.strokeLine(mitadAncho,mitadAlto+(divisiones*j),mitadAncho-(divisiones*j),lienzo.getHeight());

                context.strokeLine(mitadAncho,mitadAlto+(divisiones*j),mitadAncho+(divisiones*j),lienzo.getHeight());
                context.strokeLine(lienzo.getWidth(),lienzo.getHeight()-(divisiones*j),lienzo.getWidth()-(divisiones*j),mitadAlto);

                //Estrella 1
                context.strokeLine(mitadAncho,divisiones*j,mitadAncho+(divisiones*j),mitadAlto);
                context.strokeLine(mitadAncho,divisiones*j,mitadAncho-(divisiones*j),mitadAlto);
                context.strokeLine(mitadAncho,lienzo.getHeight()-(divisiones*j),mitadAncho-(divisiones*j),mitadAlto);
                context.strokeLine(mitadAncho,lienzo.getHeight()-(divisiones*j),mitadAncho+(divisiones*j),mitadAlto);
            }
        }
    }
    public void cambiarColor(ActionEvent event){
        colorPincel = colorPicker.getValue();
    }
    public void borrar(ActionEvent event){
        context.setFill(Color.WHITESMOKE);
        context.fillRect(0,0,lienzo.getWidth(),lienzo.getHeight());
        //colorPincel = Color.WHITESMOKE;
    }
    public void arrastar(MouseEvent event){
        context.setFill(colorPincel);
        context.fillOval(event.getX(),event.getY(),10,10);
    }
}
