/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enpresa.productadmin.modelo.dao;

import com.enpresa.productadmin.modelo.Operacion;
import com.enpresa.productadmin.utils.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alejo
 */
public class OperacionDAO implements DAO<Operacion> {

    @Override
    public void crear(Operacion operacion) {
        String sql = "{CALL dbo.RegistrarOperacion(?, ?, ?, ?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setNString(1, operacion.getNombreProducto());
            cs.setString(2, operacion.getTipoOperacion().toString());
            cs.setBigDecimal(3, operacion.getPrecio());
            cs.setInt(4, operacion.getCantidad());

            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(Operacion e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Operacion> buscar(Map<String, String> campos) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Operacion consultarUno(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Operacion> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
