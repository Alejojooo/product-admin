package com.enpresa.productadmin.vistas;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejo
 */
public class VistaGraficaDeRegistros extends VistaGraficaConNotificador implements MostrarRegistros {

    @Override
    public void mostrarRegistrosEnTabla(JTable tabla, List<String[]> registros) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setNumRows(0);
        for (String[] registro : registros) {
            modelo.addRow(registro);
        }
    }
}
