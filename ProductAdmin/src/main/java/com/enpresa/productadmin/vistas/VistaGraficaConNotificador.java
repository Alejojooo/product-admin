package com.enpresa.productadmin.vistas;

import javax.swing.JOptionPane;

/**
 *
 * @author Alejo
 */
public class VistaGraficaConNotificador extends VistaGrafica implements Notificador {

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(getFrame(),
                mensaje,
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(getFrame(),
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(getFrame(),
                mensaje,
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public boolean mostrarConfirmacion(String mensaje) {
        int opcion = JOptionPane.showConfirmDialog(getFrame(),
                mensaje,
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        return opcion == JOptionPane.YES_OPTION;
    }
}
