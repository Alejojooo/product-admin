package com.enpresa.productadmin.dao;

import java.util.List;

/**
 *
 * @author Alejo
 * @param <Entidad>
 */
public interface DAO<Entidad> {

    void crear(Entidad e);

    void modificar(Entidad e);

    void eliminar(Entidad e);

    List<Entidad> consultarTodos();
}
