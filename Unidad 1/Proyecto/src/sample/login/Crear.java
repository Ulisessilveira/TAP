package sample.login;

import animatefx.animation.FadeInDown;
import animatefx.animation.FadeInUp;
import animatefx.animation.FadeInUpBig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.Main;
import sample.models.Conexion;

import java.io.IOException;

public class Crear {
    @FXML TextField txtNombre, txtApellido, txtPassword, txtEmail;
    Conexion conexion;


    public void crear(ActionEvent event) throws IOException {
        conexion = new Conexion();
        if(!txtNombre.getText().trim().equals("") && !txtApellido.getText().trim().equals("") &&
                !txtPassword.getText().trim().equals("") && !txtEmail.getText().trim().equals("")){
            String n = txtNombre.getText();
            String a = txtApellido.getText();
            String p = txtPassword.getText();
            String e = txtEmail.getText();
            conexion.insmodel("INSERT INTO users(nombre, apellido, password, email, img_perfil) VALUES ('"+n+
                    "','"+a+"','"+p+"','"+e+"','default.jpg')");
            txtNombre.setText(""); txtApellido.setText(""); txtPassword.setText(""); txtEmail.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setContentText("Cuenta creada correctamente");
            alert.show();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            new FadeInUp(root).play();
        }
    }
    public void regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        new FadeInUp(root).play();
    }
}
