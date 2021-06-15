package sample.login;

import animatefx.animation.*;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import sample.Main;
import sample.models.Conexion;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private Conexion conexion;
    @FXML JFXTextField txtUsuario;
    @FXML JFXPasswordField txtPassword;
    String[][] arrayUsuarios = new String[2][3];
    @FXML protected void initialize() {
        conexion=new Conexion();
    }
    public void cambiarPantalla(ActionEvent event) throws SQLException, IOException {ingresar();}
    public void crear(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("crear.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        new FadeInDown(root).play();
    }
    public void ingresar() throws SQLException, IOException {
        String email = txtUsuario.getText();
        String pass = txtPassword.getText();
        ResultSet resultSet = conexion.consultar("SELECT * FROM `users` WHERE email='"+email+"' AND password='"+pass+"'");
        System.out.println("SELECT * FROM `users` WHERE email='"+email+"' AND password='"+pass+"'");
        if(resultSet != null){
            int cont=0;
            while (resultSet.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Bienvenido");
                alert.setContentText("Bienvenido " + resultSet.getObject("nombre"));
                alert.show();
                cont++;
                Main.nombreUsuario= (String) resultSet.getObject("nombre");

            }
            if (cont==0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Credenciales no validas");
                alert.show();
            }else {
                Parent root = FXMLLoader.load(getClass().getResource("../principal/principal.fxml"));
                Scene scene = new Scene(root);
                Main.stage.setScene(scene);
            }
        }
    }
}
