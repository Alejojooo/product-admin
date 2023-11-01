package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.dao.DAO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alejo
 */
public abstract class Controller {

    private final DAO modelo;
    private final JPanel vista;
    private final JFrame frame;

    public Controller(DAO modelo, JPanel vista) {
        this.modelo = modelo;
        this.vista = vista;

        frame = new JFrame();
        frame.setContentPane(vista);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
