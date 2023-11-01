package com.enpresa.productadmin.dao;

import com.enpresa.productadmin.modelo.Rol;
import com.enpresa.productadmin.modelo.Usuario;
import com.enpresa.productadmin.utils.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
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

    @Override
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

    @Override
    public void eliminar(Usuario usuario) {
        String sql = "{CALL dbo.pEliminarUsuario(?)}";

        try (Connection conexion = new Conexion().establecerConexion(); CallableStatement cs = conexion.prepareCall(sql)) {

            cs.setInt(1, usuario.getId());

            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> consultarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM vUsuarios";

        try (Connection conexion = new Conexion().establecerConexion(); Statement st = conexion.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
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

    public void buscar() {

    }
}
