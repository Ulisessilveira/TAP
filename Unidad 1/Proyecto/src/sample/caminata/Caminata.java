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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javafx.util.Duration;
import sample.models.Conexion;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Caminata {
    @FXML AnchorPane anchorPane;
    @FXML StackPane stackPane;
    @FXML TableView tableView;
    @FXML TextField txtFecha, txtDistancia, txtPasos, txtTiempo, txtLatidos;
    @FXML Button btnInsertar,btnCancelar;
    Conexion conexion;
    TableColumn columnId = new TableColumn("ID");
    TableColumn columnFecha = new TableColumn("Fecha");
    TableColumn columnDistancia = new TableColumn("Distancia(km)");
    TableColumn columnPasos = new TableColumn("Pasos");
    TableColumn columnTiempo = new TableColumn("Tiempo (min)");
    TableColumn columnLatidos = new TableColumn("Latidos promedio");
    TableColumn columnEdit = new TableColumn("     ");
    TableColumn columnEliminar = new TableColumn("     ");
    Recorrido recorridoEdit;
    ObservableList<Recorrido> list = FXCollections.observableArrayList();
    Callback<TableColumn<Recorrido, String >, TableCell<Recorrido,String>> celdaEdita = new Callback<TableColumn<Recorrido, String>, TableCell<Recorrido, String>>() {
        @Override
        public TableCell<Recorrido, String> call(TableColumn<Recorrido, String> param) {
            TableCell<Recorrido,String> cell = new TableCell<Recorrido,String>(){
                Button btnEdit = new Button("Editar");

                @Override
                protected void updateItem(String item, boolean empty) {
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else {
                        btnEdit.getStyleClass().add("btnEditar");
                        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                recorridoEdit = getTableView().getItems().get(getIndex());
                                txtFecha.setText(recorridoEdit.getFecha());
                                txtDistancia.setText(recorridoEdit.getDistancia());
                                txtPasos.setText(recorridoEdit.getPasos());
                                txtTiempo.setText(recorridoEdit.getTiempo());
                                txtLatidos.setText(recorridoEdit.getLatidos());
                                btnCancelar.setVisible(true);
                                btnInsertar.setText("Actualizar");
                            }//LLave event
                        });
                        setGraphic(btnEdit);
                        setText(null);
                    }
                }
            };
            return cell;
        }
    };

    Callback<TableColumn<Recorrido, String >, TableCell<Recorrido,String>> celdaEliminar = new Callback<TableColumn<Recorrido, String>, TableCell<Recorrido, String>>() {
        @Override
        public TableCell<Recorrido, String> call(TableColumn<Recorrido, String> param) {
            TableCell<Recorrido,String> cell = new TableCell<Recorrido,String>(){
                Button btEliminar = new Button("Eliminar");

                @Override
                protected void updateItem(String item, boolean empty) {
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else {
                        btEliminar.getStyleClass().add("btnEliminar");
                        btEliminar.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Eliminar registro");
                                alert.setContentText("¿Desea eliminar el registro?");
                                Optional<ButtonType> resultado = alert.showAndWait();
                                if(resultado.get() == ButtonType.OK){
                                    Recorrido recorrido = getTableView().getItems().get(getIndex());
                                    conexion.insmodel("DELETE FROM recorrido WHERE id = " + recorrido.getId());
                                    try {
                                        recargar();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }//LLave event
                        });
                        setGraphic(btEliminar);
                        setText(null);
                    }
                }
            };
            return cell;
        }
    };
    @FXML protected void  initialize() throws SQLException {
        conexion = new Conexion();
        columnId.setMinWidth(40);
        columnFecha.setMinWidth(70 );
        columnDistancia.setMinWidth(100);
        columnPasos.setMinWidth(65);
        columnTiempo.setMinWidth(100);
        columnLatidos.setMinWidth(125);
        columnId.setCellValueFactory(new PropertyValueFactory<Recorrido,String>("id"));
        columnFecha.setCellValueFactory(new PropertyValueFactory<Recorrido,String>("fecha"));
        columnDistancia.setCellValueFactory(new PropertyValueFactory<Recorrido,String>("distancia"));
        columnPasos.setCellValueFactory(new PropertyValueFactory<Recorrido,String>("pasos"));
        columnTiempo.setCellValueFactory(new PropertyValueFactory<Recorrido,String>("tiempo"));
        columnLatidos.setCellValueFactory(new PropertyValueFactory<Recorrido,String>("latidos"));
        columnEdit.setCellFactory(celdaEdita);
        columnEliminar.setCellFactory(celdaEliminar);
        tableView.getColumns().addAll(columnId, columnFecha,columnDistancia,columnPasos,columnTiempo,columnLatidos, columnEdit, columnEliminar);
        tableView.setItems(list);
        recargar();
    }

    public void recargar() throws SQLException {
        //Insertar en la tabla
        ResultSet resultSet = conexion.consultar("SELECT * FROM recorrido ORDER BY id ASC");
        list.clear();
        if (resultSet != null){
            while (resultSet.next()){
                list.add(new Recorrido(
                        resultSet.getObject("id").toString(),
                        resultSet.getObject("fecha").toString(),
                        resultSet.getObject("distancia").toString(),
                        resultSet.getObject("pasos").toString(),
                        resultSet.getObject("tiempo").toString(),
                        resultSet.getObject("latidos_prom").toString()));
            }
        }
    }
    public void insertarRecorrido(ActionEvent event) throws SQLException {
        if(btnInsertar.getText().equals("Actualizar")){
            String f=txtFecha.getText();
            Double d=Double.parseDouble(txtDistancia.getText());
            String p=txtPasos.getText();
            String t=txtTiempo.getText();
            String l=txtLatidos.getText();
            conexion.insmodel("UPDATE recorrido SET " +
                    "fecha='"+f+"'," +
                    "distancia="+d+"," +
                    "pasos='"+p+"', " +
                    "tiempo='"+t+"', " +
                    "latidos_prom='"+l+"' " +
                    "WHERE id =" + recorridoEdit.getId());
            btnInsertar.setText("Insertar");
            txtFecha.setText(""); txtDistancia.setText(""); txtPasos.setText(""); txtTiempo.setText(""); txtLatidos.setText("");
            btnCancelar.setVisible(false);
            recargar();
        }else {
            if (!txtFecha.getText().trim().equals("") && !txtDistancia.getText().trim().equals("") && !txtPasos.getText().trim().equals("") &&
                    !txtTiempo.getText().trim().equals("") && !txtLatidos.getText().trim().equals("")){
                //Insertar en la base de datos
                String f=txtFecha.getText();
                Double d=Double.parseDouble(txtDistancia.getText());
                String p=txtPasos.getText();
                String t=txtTiempo.getText();
                String l=txtLatidos.getText();
                conexion.insmodel("INSERT INTO recorrido(fecha, distancia, pasos, tiempo, latidos_prom) VALUES ('"+f+"', "+d+", "+p+", "+t+", "+l+")");
                System.out.println("INSERT INTO recorrido(fecha, distancia, pasos, tiempo, latidos_prom) VALUES ('"+f+"', "+d+", "+p+", "+t+", "+l+")");
                txtFecha.setText(""); txtDistancia.setText(""); txtPasos.setText(""); txtTiempo.setText(""); txtLatidos.setText("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Bien hecho");
                alert.setContentText("Registro insertado correctamente");
                alert.show();
                recargar();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Favor de llenar todos los campos");
                alert.show();
            }
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
    public void cancelar(ActionEvent event){
        if(recorridoEdit != null){
            btnInsertar.setText("Insertar");
            txtFecha.setText(""); txtDistancia.setText(""); txtPasos.setText(""); txtTiempo.setText(""); txtLatidos.setText("");
            btnCancelar.setVisible(false);
        }
    }
}
