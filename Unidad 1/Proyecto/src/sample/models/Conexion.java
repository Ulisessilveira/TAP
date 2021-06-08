package sample.models;

import java.sql.*;

public class Conexion {
    private String usuario="root";
    private String password="";
    private String bd="caminar";
    private String servidor="";
    public Connection conexion;

    public Conexion(){
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://"+servidor+":3306/"+bd+"?useUnicode=true&useJDBCCompliantTimeZoneShift=useLegacyDatetimeCode&serverTimeZone=UTC",usuario,password);
            System.out.println("CONECTADO EXITOSAMENTE");
        }catch (Exception e){
            System.out.println("No se pudo conectar al servidor");
        }
    }//Llave constructor
    public ResultSet consultar(String consulta){
        ResultSet resultado=null;
        try {
            Statement st = conexion.createStatement();
            resultado = st.executeQuery(consulta);
        }catch (Exception e){
            System.out.println("Error en la consulta" + e.getMessage());
        }
        return resultado;
    }
    //insertar modificar o eliminar
    public void insmodel(String consulta){
        try {
            Statement st=conexion.createStatement();
            st.executeUpdate(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
