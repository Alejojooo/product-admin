package com.enpresa.productadmin.dao;

import com.enpresa.productadmin.utils.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Alejo
 */
public class UsuarioDAO {

    private Conexion conexion = null;

    public UsuarioDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    public void crear() {
        String sql = "";
        try (CallableStatement cs = conexion.establecerConexion().prepareCall(sql)) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificar() {

    }

    public void eliminar() {

    }

    public void buscar() {

    }
}
