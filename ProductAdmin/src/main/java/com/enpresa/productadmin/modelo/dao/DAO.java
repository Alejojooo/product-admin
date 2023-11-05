package com.enpresa.productadmin.modelo.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Alejo
 * @param <Entidad>
 */
public interface DAO<Entidad> {

    void crear(Entidad e);

    void modificar(Entidad e);

    void eliminar(int id);

    List<Entidad> buscar(Map<String, String> campos);

    List<Entidad> consultarTodos();
}
