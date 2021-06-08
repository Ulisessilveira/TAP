package sample.caminata;

import javafx.beans.property.SimpleStringProperty;

public class Recorrido {
    SimpleStringProperty id;
    SimpleStringProperty fecha;
    SimpleStringProperty distancia;
    SimpleStringProperty pasos;
    SimpleStringProperty tiempo;
    SimpleStringProperty latidos;

    public Recorrido(String id, String fecha, String distancia, String pasos, String tiempo, String latidos) {
        this.id = new SimpleStringProperty(id);
        this.fecha = new SimpleStringProperty(fecha);
        this.distancia = new SimpleStringProperty(distancia);
        this.pasos = new SimpleStringProperty(pasos);
        this.tiempo = new SimpleStringProperty(tiempo);
        this.latidos = new SimpleStringProperty(latidos);
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

    public String getFecha() {
        return fecha.get();
    }

    public SimpleStringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public String getDistancia() {
        return distancia.get();
    }

    public SimpleStringProperty distanciaProperty() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia.set(distancia);
    }

    public String getPasos() {
        return pasos.get();
    }

    public SimpleStringProperty pasosProperty() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos.set(pasos);
    }

    public String getTiempo() {
        return tiempo.get();
    }

    public SimpleStringProperty tiempoProperty() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo.set(tiempo);
    }

    public String getLatidos() {
        return latidos.get();
    }

    public SimpleStringProperty latidosProperty() {
        return latidos;
    }

    public void setLatidos(String latidos) {
        this.latidos.set(latidos);
    }
}