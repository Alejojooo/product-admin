package com.enpresa.productadmin.vistas;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejo
 */
public class VistaGraficaConRegistros extends VistaGraficaConNotificador implements MostrarRegistrosEnTabla, MostrarRegistrosEnLista {

    @Override
    public void mostrarRegistrosEnTabla(JTable tabla, List<String[]> registros) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setNumRows(0);
        for (String[] registro : registros) {
            modelo.addRow(registro);
        }
    }

    @Override
    public void mostrarRegistrosEnLista(JComboBox lista, List<?> registros) {
        lista.removeAllItems();
        String opcionNula = "---";
        lista.addItem(opcionNula);
        for (var registro : registros) {
            lista.addItem(registro.toString());
        }
    }
}
