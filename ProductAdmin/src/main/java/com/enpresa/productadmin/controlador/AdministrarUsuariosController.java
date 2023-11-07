package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.modelo.dao.UsuarioDAO;
import com.enpresa.productadmin.modelo.Rol;
import com.enpresa.productadmin.modelo.Usuario;
import com.enpresa.productadmin.modelo.dto.UsuarioDTO;
import com.enpresa.productadmin.vistas.gui.AdministrarUsuariosVista;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 *
 * @author jmdub
 */
public class AdministrarUsuariosController implements Controller {

    private final UsuarioDAO modelo;
    private final AdministrarUsuariosVista vista;

    public AdministrarUsuariosController(UsuarioDAO modelo, AdministrarUsuariosVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    @Override
    public void start() {
        vista.mostrarVista("Administrar Usuarios");
        vista.mostrarRegistros(modelo.consultarTodos());
        mapearAcciones();
    }

    /* --- Métodos auxiliares --- */
    private void mapearAcciones() {
        vista.mapearAccion("Agregar", (e) -> crearUsuario());
        vista.mapearAccion("Modificar", (e) -> modificarUsuario());
        vista.mapearAccion("Eliminar", (e) -> eliminarUsuario());
        vista.mapearAccion("Buscar", (e) -> buscarUsuario());
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
        UsuarioDTO campos = vista.obtenerCampos();
        Usuario usuario = new Usuario();
        String clave = generarClaveNuevoUsuario();
        try {
            usuario.setUsuario(comprobarUsuario(campos.getUsuario()));
            usuario.setNombres(comprobarNombres(campos.getNombres()));
            usuario.setClave(clave);
            usuario.setApellidos(comprobarApellidos(campos.getApellidos()));
            usuario.setRol(comprobarRol(campos.getRol()));

            modelo.crear(usuario);
            vista.mostrarMensaje("Se ha creado un nuevo usuario.");
            vista.mostrarRegistros(modelo.consultarTodos());
            return clave;
        } catch (UsuarioInvalidoException e) {
            return null;
        }
    }

    private int modificarUsuario() {
        UsuarioDTO campos = vista.obtenerCampos();
        Usuario usuario = new Usuario();
        Integer id;
        try {
            id = comprobarId(campos.getId());
            usuario.setId(id);
            usuario.setUsuario(comprobarUsuario(campos.getUsuario()));
            usuario.setNombres(comprobarNombres(campos.getNombres()));
            usuario.setApellidos(comprobarApellidos(campos.getApellidos()));
            usuario.setRol(comprobarRol(campos.getRol()));

            boolean modificar = vista.mostrarConfirmacion(String.format("¿Está seguro de modificar el usuario con ID [%d]?", id));
            if (!modificar) {
                return -1;
            }

            modelo.modificar(usuario);
            vista.mostrarMensaje("Se ha modificado el usuario.");
            vista.mostrarRegistros(modelo.consultarTodos());
            return 1;
        } catch (UsuarioInvalidoException e) {
            return -1;
        }
    }

    private int eliminarUsuario() {
        UsuarioDTO campos = vista.obtenerCampos();
        Integer id;
        try {
            id = comprobarId(campos.getId());

            boolean modificar = vista.mostrarConfirmacion(String.format("¿Está seguro de modificar el usuario con ID [%d]?", id));
            if (!modificar) {
                return -1;
            }

            modelo.eliminar(id);
            vista.mostrarMensaje("Se ha modificado el usuario.");
            vista.mostrarRegistros(modelo.consultarTodos());
            return 1;
        } catch (UsuarioInvalidoException e) {
            return -1;
        }
    }

    private int buscarUsuario() {
        UsuarioDTO campos = vista.obtenerCampos();
        List<UsuarioDTO> usuarios = modelo.buscar(campos);
        vista.mostrarRegistros(usuarios);
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
