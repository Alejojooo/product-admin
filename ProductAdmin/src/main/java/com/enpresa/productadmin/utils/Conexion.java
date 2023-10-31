package com.enpresa.productadmin.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejo
 */
public class Conexion {
    //Conectarse a la base de datos
    //Remplazar nombre servidor, usuario, y contraseña.

    Connection conectar = null;

    String usuario = "usuario";
    String pass = "pass";
    String bd = "";
    String ip = "localhost";
    String puerto = "1433";

    public Connection establecerConexion() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String cadena = "jdbc:sqlserver://" + ip + ":" + puerto + ";"
                    + "databaseName=" + bd + ";"
                    + "encrypt=true;trustServerCertificate=true";

            conectar = DriverManager.getConnection(cadena, usuario, pass);
            JOptionPane.showMessageDialog(null, "Se conectó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se conectó correctamente");
        }
        
        return conectar;

    }

    
    /*
    public static void main(String[] args) {
        String connectionUrl
                = "jdbc:sqlserver://yourserver.database.windows.net:1433;"
                + "database=AdventureWorks;"
                + "user=yourusername@yourserver;"
                + "password=yourpassword;"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";

        try (Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement();) {
            // Code here.
            
            //
            String selectSql = "SELECT * FROM tabla";
            resultSet = statement.executeQuery(selectSql);
            
            while(result.next()){
                //Esto no lo entendí
                System.out.println(result.getString(2) + " " + resultSet.getString(3));
            } 
        } // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    } */
}
