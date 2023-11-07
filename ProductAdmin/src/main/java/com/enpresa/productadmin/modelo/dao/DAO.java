package com.enpresa.productadmin.modelo.dao;

import com.enpresa.productadmin.modelo.dto.DTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alejo
 * @param <T>
 */
public interface DAO<T> {

    void crear(T entidad);

    void modificar(T entidad);

    void eliminar(int id);

    List<? extends DTO> buscar(<? extends DTO> campos);

    T consultarUno(int id);

    List<T> consultarTodos();
}
