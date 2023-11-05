package com.enpresa.productadmin.controlador;
import com.enpresa.productadmin.vistas.gui.CambiarClaveVista;
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
