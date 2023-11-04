/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.dao.OperacionDAO;
import com.enpresa.productadmin.vistas.RegistrarCompraVentaVista;
import javax.swing.JFrame;

/**
 *
 * @author jmdub
 */
public class RegistrarCompraVentaController {
    
    private final OperacionDAO modelo;
    private final RegistrarCompraVentaVista vista;
    private final JFrame frame;

    public RegistrarCompraVentaController(OperacionDAO modelo, RegistrarCompraVentaVista vista) {
        this.modelo = modelo;
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
    
    private void buscarProducto() {
        
    }
    
    /* --- Métodos de comprobación --- */
    private void idInvalida() {
        
    }
    
    private void nombreInvalido() {
        
    }
}
