package com.enpresa.productadmin.vistas;

import com.enpresa.productadmin.modelo.dto.DTO;
import java.util.List;

/**
 *
 * @author Alejo
 */
public interface MostrarRegistros {

    void mostrarRegistros(List<? extends DTO> registros);
}
