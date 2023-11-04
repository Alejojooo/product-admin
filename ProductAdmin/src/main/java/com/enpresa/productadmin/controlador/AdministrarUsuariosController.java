/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.dao.UsuarioDAO;
import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.modelo.Rol;
import com.enpresa.productadmin.modelo.Usuario;
import com.enpresa.productadmin.vistas.AdministrarUsuarios;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jmdub
 */
public class AdministrarUsuariosController {

    private final UsuarioDAO modelo;
    private final AdministrarUsuarios vista;

    public AdministrarUsuariosController(UsuarioDAO modelo, AdministrarUsuarios vista) {
        this.modelo = modelo;
        this.vista = vista;

        mostrarRegistros();
        mapearAcciones();
    }

    /* --- Métodos auxiliares --- */
    private void mapearAcciones() {
        vista.mapearAccion("Agregar", (e) -> crearUsuario());
        vista.mapearAccion("Modificar", (e) -> modificarUsuario());
        vista.mapearAccion("Eliminar", (e) -> eliminarUsuario());
        vista.mapearAccion("Buscar", (e) -> buscarUsuario());
    }

    private void mostrarRegistros(List<Usuario> usuarios) {
        if (usuarios == null) {
            usuarios = modelo.consultarTodos();
        }
        vista.mostrarRegistros(getRegistros(usuarios));
    }

    private void mostrarRegistros() {
        mostrarRegistros(null);
    }

    private List<String[]> getRegistros(List<Usuario> usuarios) {
        List<String[]> registros = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            String[] registro = {
                String.valueOf(usuario.getId()),
                usuario.getUsuario(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getRol().toString()
            };
            registros.add(registro);
        }
        return registros;
    }

    /* --- Métodos para CRUD --- */
    private int crearUsuario() {
        Map<String, String> campos = vista.getCampos();
        Usuario usuario = new Usuario();
        try {
            usuario.setUsuario(comprobarUsuario(campos.get("usuario")));
            usuario.setNombres(comprobarNombres(campos.get("nombres")));
            usuario.setApellidos(comprobarApellidos(campos.get("precioCompra")));
            usuario.setRol(Rol.valueOf(campos.get("rol")));
        } catch (UsuarioInvalidoException e) {
            return -1;
        }
        modelo.crear(usuario);
        vista.mostrarMensaje("Se ha creado un nuevo usuario.");
        mostrarRegistros();
        return 1;
    }

    private int modificarUsuario() {
        Map<String, String> campos = vista.getCampos();
        Usuario usuario = new Usuario();
        Integer id;
        try {
            id = comprobarId(campos.get("id"));
            usuario.setId(id);
            usuario.setUsuario(comprobarUsuario(campos.get("usuario")));
            usuario.setNombres(comprobarNombres(campos.get("nombres")));
            usuario.setApellidos(comprobarApellidos(campos.get("precioCompra")));
            usuario.setRol(Rol.valueOf(campos.get("rol")));
        } catch (UsuarioInvalidoException e) {
            return -1;
        }

        boolean modificar = vista.mostrarConfirmacion(String.format("¿Está seguro de modificar el usuario con ID [%d]?", id));
        if (!modificar) {
            return -1;
        }

        modelo.modificar(usuario);
        vista.mostrarMensaje("Se ha modificado el usuario.");
        mostrarRegistros();
        return 1;
    }

    private int eliminarUsuario() {
        Map<String, String> campos = vista.getCampos();
        Integer id;
        try {
            id = comprobarId(campos.get("id"));
        } catch (UsuarioInvalidoException e) {
            return -1;
        }
        
        boolean modificar = vista.mostrarConfirmacion(String.format("¿Está seguro de modificar el usuario con ID [%d]?", id));
        if (!modificar) {
            return -1;
        }

        modelo.eliminar(id);
        vista.mostrarMensaje("Se ha modificado el usuario.");
        mostrarRegistros();
        return 1;
    }

    private int buscarUsuario() {
        Map<String, String> campos = vista.getCampos();
        List<Usuario> usuarios = modelo.buscar(campos);
        mostrarRegistros(usuarios);
        return 1;
    }

    /* --- Métodos de comprobación --- */
    private Integer comprobarId(String idString) throws UsuarioInvalidoException {
        Integer id = null;
        try {
            id = Integer.valueOf(idString);
            if (id < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            vista.mostrarError("La ID del usuario no es válida.");
            throw new UsuarioInvalidoException();
        }
        return id;
    }

    private String comprobarUsuario(String usuario) throws UsuarioInvalidoException {
        if ("".equals(usuario) || Pattern.matches("\\W", usuario)) {
            vista.mostrarError("El usuario introducido no es válido.");
            throw new UsuarioInvalidoException();
        }
        return usuario;
    }

    private String comprobarVacio(String nombre, String tipo) throws UsuarioInvalidoException {
        if ("".equals(nombre)) {
            vista.mostrarError(String.format("Los %s introducido no es válido.", tipo));
            throw new UsuarioInvalidoException();
        }
        return nombre;
    }

    private String comprobarNombres(String nombre) throws UsuarioInvalidoException {
        return comprobarVacio(nombre, "nombres");
    }

    private String comprobarApellidos(String apellido) throws UsuarioInvalidoException {
        return comprobarVacio(apellido, "apellidos");
    }
}

class UsuarioInvalidoException extends Exception {
}
