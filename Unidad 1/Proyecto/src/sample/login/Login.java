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
import java.io.IOException;

public class Login {
    @FXML JFXTextField txtUsuario;
    @FXML JFXPasswordField txtPassword;
    String[][] arrayUsuarios = new String[2][3];
    @FXML protected void initialize() {
        arrayUsuarios[0][0]="Ulises Silveira";arrayUsuarios[0][1]="Josu";arrayUsuarios[0][2]="123";
        arrayUsuarios[1][0]="Luis Alberto";arrayUsuarios[1][1]="Luis";arrayUsuarios[1][2]="123";
    }
    public void cambiarPantalla(ActionEvent event) {ingresar();}
    public void ingresar() {
        String u=txtUsuario.getText();
        String p=txtPassword.getText();
        Busqueda busqueda= new Busqueda();
        int indice = busqueda.secuencial(arrayUsuarios,u,p);
        if(indice>= 0){
            try {
                Main.nombreUsuario=arrayUsuarios[indice][0];
                System.out.println("Bienvenido " + arrayUsuarios[indice][0]);
                Parent root = FXMLLoader.load(getClass().getResource("../principal/principal.fxml"));
                Scene scene = new Scene(root);
                Main.stage.setScene(scene);
                new FadeInUp(root).play();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hike");
            alert.setContentText("DATOS INVALIDOS");
            alert.show();
        }
    }
}
