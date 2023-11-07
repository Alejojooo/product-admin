package com.enpresa.productadmin.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alejo
 */
public class Conexion {

    Connection conexion = null;

    String ip = "localhost";
    String puerto = "1433";
    String baseDatos = "bdProductAdmin";
    String usuario = "productadmin";
    String clave = "productadmin";

    public Connection establecerConexion() {

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("jdbc:sqlserver://").append(ip).append(":").append(puerto).append(";");
            sb.append("databaseName=").append(baseDatos).append(";");
            sb.append("encrypt=true;trustServerCertificate=true");

            conexion = DriverManager.getConnection(sb.toString(), usuario, clave);
            System.out.println("Conexión hecha.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }
}
