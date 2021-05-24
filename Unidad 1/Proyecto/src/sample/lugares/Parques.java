package sample.lugares;

import javax.swing.text.html.ImageView;

public class Parques {
    ImageView imagen;
    String ubicacion;

    public Parques(ImageView imagen, String ubicacion) {
        this.imagen = imagen;
        this.ubicacion = ubicacion;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
