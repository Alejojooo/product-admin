package com.enpresa.productadmin.vistas;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Alejo
 */
public interface Vista extends Notificador {

    Map<String, String> getCampos();
    
    void mostrarRegistros(List<String[]> registros);
}
