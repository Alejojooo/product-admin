package com.enpresa.productadmin.dao;

import com.enpresa.productadmin.modelo.Usuario;
import com.enpresa.productadmin.utils.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author Alejo
 */
public class UsuarioDAO {

    public void crear(Usuario usuario) {
        String sql = "{CALL dbo.pCrearUsuario(?, ?, ?, ?, ?)}";
        
        try (Connection conexion = new Conexion().establecerConexion(); CallableStatement cs = conexion.prepareCall(sql)) {
            
            cs.setString(1, usuario.getUsuario());
            cs.setString(2, usuario.getPassword());
            cs.setString(3, usuario.getNombres());
            cs.setString(4, usuario.getApellidos());
            cs.setString(5, usuario.getRol().toString());
            
            cs.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void modificar(Usuario usuario) {
        String sql = "{CALL dbo.pModificarUsuario(?, ?, ?, ?, ?)}";
        
        try (Connection conexion = new Conexion().establecerConexion(); CallableStatement cs = conexion.prepareCall(sql)) {
            
            cs.setInt(1, usuario.getId());
            cs.setString(2, usuario.getUsuario());
            cs.setString(3, usuario.getNombres());
            cs.setString(4, usuario.getApellidos());
            cs.setString(5, usuario.getRol().toString());
            
            cs.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void eliminar(Usuario usuario) {
        String sql = "{CALL dbo.pEliminarUsuario(?)}";
        
        try (Connection conexion = new Conexion().establecerConexion(); CallableStatement cs = conexion.prepareCall(sql)) {
            
            cs.setInt(1, usuario.getId());
            
            cs.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buscar() {
        
    }
}
