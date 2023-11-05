package com.enpresa.productadmin.vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alejo
 */
public class VistaGrafica extends JPanel {

    private JFrame frame;
    
    public JFrame getFrame() {
        return frame;
    }

    public void mostrarVista(String titulo) {
        frame = new JFrame(titulo);
        frame.setContentPane(this);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
    
    public void cerrarVista() {
        frame.dispose();
    }
}
