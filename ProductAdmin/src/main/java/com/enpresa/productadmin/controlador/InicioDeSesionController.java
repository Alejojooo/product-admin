package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.ProductAdmin;
import com.enpresa.productadmin.modelo.dao.UsuarioDAO;
import com.enpresa.productadmin.vistas.gui.IniciarSesionVista;
import java.util.Map;

/**
 *
 * @author Alejo
 */
public class InicioDeSesionController implements Controller{

    private final UsuarioDAO modelo;
    private final IniciarSesionVista vista;

    public InicioDeSesionController(UsuarioDAO modelo, IniciarSesionVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    
    public void start() {
        vista.mostrarVista("Inicio de Sesión");
        mapearAcciones();
    }

    private void mapearAcciones() {
        vista.mapearAccion("Iniciar Sesion", (e) -> login());
    }

    private int login() {
        Map<String, String> campos = vista.getCampos();
        
        int id = modelo.login(campos.get("usuario"), campos.get("clave"));
        if (id < 1) {
            vista.mostrarError("Usuario o contraseña incorrectos");
            return -1;
        }
        vista.mostrarMensaje("Inicio de sesión correcto.");
        vista.cerrarVista();
        ProductAdmin.usuarioActivo = modelo.consultarUno(id);
        ProductAdmin.goToMenuPrincipal();
        return 1;
    }
}
