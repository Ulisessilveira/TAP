package sample.caminata;

import javafx.beans.property.SimpleIntegerProperty;

public class Recorrido {
    SimpleIntegerProperty dia;
    SimpleIntegerProperty distancia;
    SimpleIntegerProperty pasos;
    SimpleIntegerProperty tiempo;
    SimpleIntegerProperty latidos;

    public Recorrido(int dia, int distancia, int pasos, int tiempo, int latidos) {
        this.dia = new SimpleIntegerProperty(dia);
        this.distancia = new  SimpleIntegerProperty(distancia);
        this.pasos = new SimpleIntegerProperty(pasos);
        this.tiempo = new SimpleIntegerProperty(tiempo);
        this.latidos = new SimpleIntegerProperty(latidos);
    }

    public int getDia() {
        return dia.get();
    }

    public SimpleIntegerProperty diaProperty() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia.set(dia);
    }

    public int getDistancia() {
        return distancia.get();
    }

    public SimpleIntegerProperty distanciaProperty() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia.set(distancia);
    }

    public int getPasos() {
        return pasos.get();
    }

    public SimpleIntegerProperty pasosProperty() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos.set(pasos);
    }

    public int getTiempo() {
        return tiempo.get();
    }

    public SimpleIntegerProperty tiempoProperty() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo.set(tiempo);
    }

    public int getLatidos() {
        return latidos.get();
    }

    public SimpleIntegerProperty latidosProperty() {
        return latidos;
    }

    public void setLatidos(int latidos) {
        this.latidos.set(latidos);
    }
}
