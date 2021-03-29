package sample.login;

public class Busqueda {
    public int secuencial(String[][] array, String dato1, String dato2){
        int indice = -1;
        for(int i=0; i<array.length; i++){
            if (array[i][1].equals(dato1) && array[i][2].equals(dato2)){
                indice = i;
                return indice;
            }
        }
        return indice;
    }
}
