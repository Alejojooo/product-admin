package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.modelo.dao.UsuarioDAO;
import com.enpresa.productadmin.vistas.gui.CambiarClaveVista;
import javax.swing.JFrame;

/**
 *
 * @author jmdub
 */
public class CambiarClaveController {

    private final UsuarioDAO modelo;
    private final CambiarClaveVista vista;

    public CambiarClaveController(UsuarioDAO modelo, CambiarClaveVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    
    
}
