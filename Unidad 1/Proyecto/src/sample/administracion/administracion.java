package sample.administracion;

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

public class administracion {
    @FXML AnchorPane anchorPane;
    @FXML StackPane stackPane;
    @FXML TextField txtNombre, txtApellido, txtPassword, txtEmail;
    @FXML TableView table;
    @FXML Button btnActualizar, btnCancelar;
    Conexion conexion;
    TableColumn columnId = new TableColumn("ID");
    TableColumn columnNombre = new TableColumn("Nombre");
    TableColumn columnApellido = new TableColumn("Apellido");
    TableColumn columnPassword = new TableColumn("Password");
    TableColumn columnEmail = new TableColumn("Email");
    TableColumn columnImg = new TableColumn("Imagen");
    TableColumn columnEdit = new TableColumn("     ");
    TableColumn columnEliminar = new TableColumn("     ");
    Tabla tablaEdit;
    ObservableList<Tabla> list = FXCollections.observableArrayList();
    Callback<TableColumn<Tabla, String >, TableCell<Tabla,String>> celdaEditar = new Callback<TableColumn<Tabla, String>, TableCell<Tabla, String>>() {
        @Override
        public TableCell<Tabla, String> call(TableColumn<Tabla, String> param) {
            TableCell<Tabla,String> cell = new TableCell<Tabla,String>(){
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
                                tablaEdit = getTableView().getItems().get(getIndex());
                                txtNombre.setText(tablaEdit.getNombre());
                                txtApellido.setText(tablaEdit.getApellido());
                                txtPassword.setText(tablaEdit.getPassword());
                                txtEmail.setText(tablaEdit.getEmail());
                                btnActualizar.setVisible(true);
                                btnCancelar.setVisible(true);
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

    Callback<TableColumn<Tabla, String >, TableCell<Tabla,String>> celdaEliminar = new Callback<TableColumn<Tabla, String>, TableCell<Tabla, String>>() {
        @Override
        public TableCell<Tabla, String> call(TableColumn<Tabla, String> param) {
            TableCell<Tabla,String> cell = new TableCell<Tabla,String>(){
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
                                    Tabla tabla = getTableView().getItems().get(getIndex());
                                    conexion.insmodel("DELETE FROM users WHERE id = " + tabla.getId());
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
        @FXML protected void initialize() throws SQLException {
        conexion = new Conexion();
        columnId.setMinWidth(30);
        columnNombre.setMinWidth(60);
        columnApellido.setMinWidth(85);
        columnPassword.setMinWidth(65);
        columnEmail.setMinWidth(140);
        columnImg.setMinWidth(90);
        columnId.setCellValueFactory(new PropertyValueFactory<Tabla,String>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<Tabla,String>("nombre"));
        columnApellido.setCellValueFactory(new PropertyValueFactory<Tabla,String>("apellido"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<Tabla,String>("password"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<Tabla,String>("email"));
        columnImg.setCellValueFactory(new PropertyValueFactory<Tabla,String>("img"));
        columnEdit.setCellFactory(celdaEditar);
        columnEliminar.setCellFactory(celdaEliminar);
        table.getColumns().addAll(columnId, columnNombre,columnApellido,columnPassword,columnEmail,columnImg,columnEdit,columnEliminar);
        table.setItems(list);
        recargar();
    }

    public void recargar() throws SQLException {
        ResultSet resultSet=conexion.consultar("SELECT * FROM users ORDER BY id ASC");
        list.clear();
        if (resultSet != null){
            while (resultSet.next()){
                list.add(new Tabla(
                        resultSet.getObject("id").toString(),
                        resultSet.getObject("nombre").toString(),
                        resultSet.getObject("apellido").toString(),
                        resultSet.getObject("password").toString(),
                        resultSet.getObject("email").toString(),
                        resultSet.getObject("img_perfil").toString()
                ));
            }
        }
    }

    public void regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../principal/principal.fxml"));
        Scene scene=anchorPane.getScene();
        root.translateXProperty().set(scene.getWidth());
        stackPane.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue ky = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
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
    public void actualizar(ActionEvent event) throws SQLException {
        String n = txtNombre.getText();
        String a = txtApellido.getText();
        String p = txtPassword.getText();
        String e = txtEmail.getText();
        conexion.insmodel("UPDATE users SET " +
                "nombre='"+n+"'," +
                "apellido='"+a+"'," +
                "password='"+p+"'," +
                "email='"+e+"' " +
                "WHERE id =" + tablaEdit.getId());
        txtNombre.setText(""); txtApellido.setText(""); txtPassword.setText(""); txtEmail.setText("");
        btnActualizar.setVisible(false);
        btnCancelar.setVisible(false);
        recargar();
    }
    public void cancelar(ActionEvent event){
        if(tablaEdit != null){
            txtNombre.setText(""); txtApellido.setText(""); txtPassword.setText(""); txtEmail.setText("");
            btnActualizar.setVisible(false);
            btnCancelar.setVisible(false);
        }
    }
}
