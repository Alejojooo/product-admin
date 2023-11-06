package com.enpresa.productadmin.modelo.dao;

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
import java.util.Map;

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
            cs.setNString(3, usuario.getNombres());
            cs.setNString(4, usuario.getApellidos());
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
            cs.setNString(3, usuario.getNombres());
            cs.setNString(4, usuario.getApellidos());
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
    public List<Usuario> buscar(Map<String, String> campos) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "{CALL dbo.pBuscarUsuario(?, ?, ?, ?, ?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setString(1, campos.get("id"));
            cs.setString(2, campos.get("usuario"));
            cs.setNString(3, campos.get("nombres"));
            cs.setNString(4, campos.get("apellidos"));
            cs.setString(5, campos.get("rol"));

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setUsuario(rs.getString(2));
                usuario.setNombres(rs.getNString(3));
                usuario.setApellidos(rs.getNString(4));
                usuario.setRol(Rol.valueOf(rs.getNString(5)));

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario consultarUno(int id) {
        Usuario usuario = new Usuario();
        String sql = "{CALL dbo.pConsultarUsuario(?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
                usuario.setUsuario(rs.getString(2));
                usuario.setNombres(rs.getNString(3));
                usuario.setApellidos(rs.getNString(4));
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
                usuario.setNombres(rs.getNString(3));
                usuario.setApellidos(rs.getNString(4));
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
            ps.setNString(2, clave);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean comprobarClave(int idUsuario, String clave) {
        String sql = "SELECT dbo.fComprobarClave(?, ?)";

        try (Connection c = new Conexion().establecerConexion(); PreparedStatement ps = c.prepareCall(sql)) {
            ps.setInt(1, idUsuario);
            ps.setNString(2, clave);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cambiarClave(int idUsuario, String claveNueva) {
        String sql = "{CALL dbo.pCambiarClave(?, ?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setInt(1, idUsuario);
            cs.setNString(2, claveNueva);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}