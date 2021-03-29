package sample.caminata;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class Caminata {
    @FXML AnchorPane anchorPane;
    @FXML StackPane stackPane;
    @FXML TableView tableView;
    @FXML TextField txtDia, txtDistancia, txtPasos, txtTiempo, txtLatidos,txtKilometrosRecorridos;
    TableColumn columnDia = new TableColumn("Día");
    TableColumn columnDistancia = new TableColumn("Distancia(km)");
    TableColumn columnPasos = new TableColumn("Pasos");
    TableColumn columnTiempo = new TableColumn("Tiempo");
    TableColumn columnLatidos = new TableColumn("Latidos");
    ObservableList<Recorrido> list = FXCollections.observableArrayList();
    @FXML protected void  initialize(){
        columnDia.setCellValueFactory(new PropertyValueFactory<Recorrido,String>("Dia"));
        columnDistancia.setCellValueFactory(new PropertyValueFactory<Recorrido,String>("Distancia"));
        columnPasos.setCellValueFactory(new PropertyValueFactory<Recorrido,String>("Pasos"));
        columnTiempo.setCellValueFactory(new PropertyValueFactory<Recorrido,String>("Tiempo"));
        columnLatidos.setCellValueFactory(new PropertyValueFactory<Recorrido,String>("Latidos"));
        tableView.getColumns().addAll(columnDia,columnDistancia,columnPasos,columnTiempo,columnLatidos);
        tableView.setItems(list);
    }

    public void insertarRecorrido(ActionEvent event){
        try {
            int dia = Integer.parseInt(txtDia.getText());
            int distancia = Integer.parseInt(txtDistancia.getText());
            int pasos = Integer.parseInt(txtPasos.getText());
            int tiempo = Integer.parseInt(txtTiempo.getText());
            int latidos = Integer.parseInt(txtLatidos.getText());
            list.addAll(new Recorrido(dia,distancia,pasos,tiempo,latidos));
            System.out.println(dia);
            System.out.println(distancia);
            System.out.println(pasos);
            System.out.println(tiempo);
            System.out.println(latidos);
            txtDia.setText("");
            txtDistancia.setText("");
            txtPasos.setText("");
            txtTiempo.setText("");
            txtLatidos.setText("");
            int klRecorridosFinal = Integer.parseInt(txtKilometrosRecorridos.getText()) + distancia;
            txtKilometrosRecorridos.setText(""+ klRecorridosFinal);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hike");
            alert.setContentText("Inserte un dato valido");
            alert.show();
        }
    }

    public void regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../principal/principal.fxml"));
        Scene scene=anchorPane.getScene();
        root.translateYProperty().set(scene.getWidth());
        stackPane.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue ky = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kf= new KeyFrame(Duration.seconds(1), ky);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stackPane.getChildren().remove(anchorPane);
            }
        });
        timeline.play();
    }
}
