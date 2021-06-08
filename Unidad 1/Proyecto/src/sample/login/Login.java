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
        arrayUsuarios[0][0]="Ulises Silveira";arrayUsuarios[0][1]="uli";arrayUsuarios[0][2]="123";
        arrayUsuarios[1][0]="Luis Alberto";arrayUsuarios[1][1]="Luis";arrayUsuarios[1][2]="123";
        conexion=new Conexion();
    }
    public void cambiarPantalla(ActionEvent event) throws SQLException, IOException {ingresar();}
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
                //System.out.println(resultSet.getObject("nombre"));
                //System.out.println(resultSet.getObject("password"));

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
                new FadeInUp(root).play();
            }
        }
        /*String u=txtUsuario.getText();
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
        }*/
    }
}
