/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enpresa.productadmin.controlador;
import com.enpresa.productadmin.vistas.gui.CambiarClaveVista;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

/**
 *
 * @author jmdub
 */
public class CambiarClaveController {
    private final CambiarClaveVista vista;
    private final JFrame frame;

    public CambiarClaveController(CambiarClaveVista vista) {
        this.vista = vista;

        frame = new JFrame();
        frame.setContentPane(vista);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        addActionListeners();
    }

    private void addActionListeners() {
    }
}
