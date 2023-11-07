package com.enpresa.productadmin.vistas;

import com.enpresa.productadmin.modelo.dto.DTO;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Alejo
 */
public interface MostrarRegistrosEnTabla {

    void mostrarRegistrosEnTabla(JTable tabla, List<? extends DTO> registros);
}
