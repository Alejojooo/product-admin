package com.enpresa.productadmin.modelo.dao;

import com.enpresa.productadmin.modelo.dto.DTO;
import java.util.List;

/**
 *
 * @author Alejo
 * @param <E>
 * @param <D>
 */
public interface DAO<E, D extends DTO> {

    void crear(E entidad);

    void modificar(E entidad);

    void eliminar(int id);

    List<? extends DTO> buscar(D campos);

    E consultarUno(int id);

    List<? extends DTO> consultarTodos();
}
