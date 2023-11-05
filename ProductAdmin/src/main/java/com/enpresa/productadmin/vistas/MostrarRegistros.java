package com.enpresa.productadmin.vistas;

import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Alejo
 */
public interface MostrarRegistros {

    void mostrarRegistrosEnTabla(JTable tabla, List<String[]> registros);
}
