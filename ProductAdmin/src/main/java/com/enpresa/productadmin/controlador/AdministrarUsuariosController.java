package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.dao.UsuarioDAO;
import com.enpresa.productadmin.modelo.Rol;
import com.enpresa.productadmin.modelo.Usuario;
import com.enpresa.productadmin.vistas.AdministrarUsuariosVista;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 *
 * @author jmdub
 */
public class AdministrarUsuariosController {

    private final UsuarioDAO modelo;
    private final AdministrarUsuariosVista vista;

    public AdministrarUsuariosController(UsuarioDAO modelo, AdministrarUsuariosVista vista) {
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

    private String generarClaveNuevoUsuario() {
        String letras = "abcdefghijklmnopqrstuvwxyz";
        String numeros = "0123456789";
        String clave = "";
        Random rand = new Random();
        for (int i = 0; i < 8; i++) {
            double randNum = rand.nextDouble();
            if (randNum < 0.5) {
                clave += letras.charAt(rand.nextInt(0, letras.length()));
            } else {
                clave += numeros.charAt(rand.nextInt(0, numeros.length()));
            }
        }
        return clave;
    }

    /* --- Métodos para CRUD --- */
    private String crearUsuario() {
        Map<String, String> campos = vista.getCampos();
        Usuario usuario = new Usuario();
        String clave = generarClaveNuevoUsuario();
        try {
            usuario.setUsuario(comprobarUsuario(campos.get("usuario")));
            usuario.setNombres(comprobarNombres(campos.get("nombres")));
            usuario.setClave(clave);
            usuario.setApellidos(comprobarApellidos(campos.get("apellidos")));
            usuario.setRol(comprobarRol(campos.get("rol")));
        } catch (UsuarioInvalidoException e) {
            return null;
        }
        modelo.crear(usuario);
        vista.mostrarMensaje("Se ha creado un nuevo usuario.");
        mostrarRegistros();
        return clave;
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
            usuario.setApellidos(comprobarApellidos(campos.get("apellidos")));
            usuario.setRol(comprobarRol(campos.get("rol")));
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
        if (!Pattern.matches("\\w{8,}", usuario)) {
            vista.mostrarError("El usuario introducido no es válido.");
            throw new UsuarioInvalidoException();
        }
        System.out.println("Usuario legit.");
        return usuario;
    }

    private String comprobarNombres(String nombre) throws UsuarioInvalidoException {
        if ("".equals(nombre)) {
            vista.mostrarError(String.format("Los nombres introducidos no son válidos."));
            throw new UsuarioInvalidoException();
        }
        return nombre;
    }

    private String comprobarApellidos(String apellido) throws UsuarioInvalidoException {
        if ("".equals(apellido)) {
            vista.mostrarError(String.format("Los apellidos introducidos no son válidos."));
            throw new UsuarioInvalidoException();
        }
        return apellido;
    }

    private Rol comprobarRol(String rolString) throws UsuarioInvalidoException {
        boolean isValidRol = Arrays.stream(Rol.values())
                .map(rol -> rol.toString())
                .filter(rol -> rol.equals(rolString))
                .findAny()
                .isPresent();

        if (!isValidRol) {
            vista.mostrarError("Seleccione un rol para el usuario.");
            throw new UsuarioInvalidoException();
        }
        return Rol.valueOf(rolString);
    }
}

class UsuarioInvalidoException extends Exception {
}
