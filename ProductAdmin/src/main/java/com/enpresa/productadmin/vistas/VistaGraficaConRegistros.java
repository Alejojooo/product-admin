package com.enpresa.productadmin.vistas;

import com.enpresa.productadmin.modelo.dto.DTO;
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
    public void mostrarRegistrosEnTabla(JTable tabla, List<? extends DTO> registros) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setNumRows(0);
        if (registros == null) {
            return;
        }
        for (DTO registro : registros) {
            modelo.addRow(registro.getAttributeValues());
        }
    }

    @Override
    public void mostrarRegistrosEnLista(JComboBox lista, List<?> registros) {
        lista.removeAllItems();
        String opcionNula = "---";
        lista.addItem(opcionNula);
        if (registros == null) {
            return;
        }
        for (var registro : registros) {
            lista.addItem(registro.toString());
        }
    }
}
