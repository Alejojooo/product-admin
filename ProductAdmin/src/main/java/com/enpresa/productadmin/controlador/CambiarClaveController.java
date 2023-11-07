package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.ProductAdmin;
import com.enpresa.productadmin.modelo.dao.UsuarioDAO;
import com.enpresa.productadmin.modelo.dto.ClaveDTO;
import com.enpresa.productadmin.vistas.gui.CambiarClaveVista;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jmdub
 */
public class CambiarClaveController implements Controller {

    private final UsuarioDAO modelo;
    private final CambiarClaveVista vista;

    public CambiarClaveController(UsuarioDAO modelo, CambiarClaveVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    @Override
    public void start() {
        vista.mostrarVista("Cambiar clave de usuario");
        mapearAcciones();
    }

    private void mapearAcciones() {
        vista.mapearAccion("Confirmar", (e) -> cambiarClave());
    }

    private int cambiarClave() {
        ClaveDTO campos = vista.obtenerCampos();
        try {
            comprobarClaveActual(campos.getClaveActual());
            String claveNueva = comprobarClaveNueva(campos.getClaveNueva());
            comprobarClavesIguales(claveNueva, campos.getClaveConfirmacion());

            modelo.cambiarClave(ProductAdmin.usuarioActivo.getId(), claveNueva);
            vista.mostrarMensaje("Se ha cambiado la contraseña");
            return 1;
        } catch (ClaveInvalidaException e) {
            return -1;
        }
    }

    private String comprobarClaveActual(String clave) throws ClaveInvalidaException {
        if (!modelo.comprobarClave(ProductAdmin.usuarioActivo.getId(), clave)) {
            vista.mostrarError("La contraseña introducida no coincide con la actual.");
            throw new ClaveInvalidaException();
        }
        return clave;
    }

    private String comprobarClaveNueva(String clave) throws ClaveInvalidaException {
        String patronRegex = "^(?=.*[0-9])(?=.*[@#$%^&+=!])(.{10,})$";
        Pattern patron = Pattern.compile(patronRegex);

        Matcher matcher = patron.matcher(clave);
        if (!matcher.matches()) {
            vista.mostrarError("La contraseña introducida no cumple con los requerimientos.");
            throw new ClaveInvalidaException();
        }
        return clave;
    }

    private boolean comprobarClavesIguales(String clave1, String clave2) throws ClaveInvalidaException {
        if (!clave1.equals(clave2)) {
            vista.mostrarError("Las contraseñas introducidas no coinciden.");
            throw new ClaveInvalidaException();
        }
        return true;
    }
    
    private char[] declararClaveACero(char[] clave) {
        for (int i = 0; i < clave.length; i++) {
            clave[i] = 0;
        }
        return clave;
    }
}

class ClaveInvalidaException extends Exception {
}
