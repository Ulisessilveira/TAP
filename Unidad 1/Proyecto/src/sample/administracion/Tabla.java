package sample.administracion;

import javafx.beans.property.SimpleStringProperty;

public class Tabla {
    SimpleStringProperty id;
    SimpleStringProperty nombre;
    SimpleStringProperty apellido;
    SimpleStringProperty password;
    SimpleStringProperty email;
    SimpleStringProperty img;

    public Tabla(String id, String nombre, String apellido, String password, String email, String img) {
        this.id = new SimpleStringProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.password = new SimpleStringProperty(password);
        this.email = new SimpleStringProperty(email);
        this.img = new SimpleStringProperty(img);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public SimpleStringProperty apellidoProperty() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getImg() {
        return img.get();
    }

    public SimpleStringProperty imgProperty() {
        return img;
    }

    public void setImg(String img) {
        this.img.set(img);
    }
}