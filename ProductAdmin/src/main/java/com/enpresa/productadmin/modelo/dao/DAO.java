package com.enpresa.productadmin.modelo.dao;

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

    List<T> buscar(Map<String, String> campos);

    T consultarUno(int id);

    List<T> consultarTodos();
}
