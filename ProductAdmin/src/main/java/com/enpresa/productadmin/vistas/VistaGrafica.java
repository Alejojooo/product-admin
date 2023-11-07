package com.enpresa.productadmin.vistas;

import com.enpresa.productadmin.ProductAdmin;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarVista();
                if (ProductAdmin.usuarioActivo != null) {
                    ProductAdmin.goToMenuPrincipal(null);
                }
            }
        });
    }

    public void cerrarVista() {
        frame.dispose();
    }
}
