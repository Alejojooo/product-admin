package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.vistas.VistaGrafica;
import com.enpresa.productadmin.vistas.gui.AcercaDeVista;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

/**
 *
 * @author jmdub
 */
public class AcercaDeController {

    private final AcercaDeVista vista;

    public AcercaDeController(AcercaDeVista vista) {
        this.vista = vista;
        vista.mostrarVista("Acerca de");
    }
}
