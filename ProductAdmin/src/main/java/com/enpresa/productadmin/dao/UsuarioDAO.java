package com.enpresa.productadmin.dao;

import com.enpresa.productadmin.modelo.Rol;
import com.enpresa.productadmin.modelo.Usuario;
import com.enpresa.productadmin.utils.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejo
 */
public class UsuarioDAO implements DAO<Usuario> {
    
    @Override
    public void crear(Usuario usuario) {
        String sql = "{CALL dbo.pCrearUsuario(?, ?, ?, ?, ?)}";
        
        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            
            cs.setString(1, usuario.getUsuario());
            cs.setString(2, usuario.getClave());
            cs.setString(3, usuario.getNombres());
            cs.setString(4, usuario.getApellidos());
            cs.setString(5, usuario.getRol().toString());
            
            cs.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void modificar(Usuario usuario) {
        String sql = "{CALL dbo.pModificarUsuario(?, ?, ?, ?, ?)}";
        
        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            
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
    
    @Override
    public void eliminar(int id) {
        String sql = "{CALL dbo.pEliminarUsuario(?)}";
        
        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Usuario> buscar(Usuario usuario) {
        return null;
    }
    
    public Usuario consultarUno(int id) {
        Usuario usuario = new Usuario();
        String sql = "{CALL dbo.pConsultarUsuario(?)}";
        
        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
                usuario.setUsuario(rs.getString(2));
                usuario.setNombres(rs.getString(3));
                usuario.setApellidos(rs.getString(4));
                usuario.setRol(Rol.valueOf(rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
    
    @Override
    public List<Usuario> consultarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM vUsuarios";
        
        try (Connection c = new Conexion().establecerConexion(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            Usuario usuario;
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setUsuario(rs.getString(2));
                usuario.setNombres(rs.getString(3));
                usuario.setApellidos(rs.getString(4));
                usuario.setRol(Rol.valueOf(rs.getString(5)));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return usuarios;
    }
    
    public int login(String usuario, String clave) {
        String sql = "SELECT dbo.fLogin(?, ?)";
        
        try (Connection c = new Conexion().establecerConexion(); PreparedStatement ps = c.prepareCall(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
