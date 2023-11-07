package com.enpresa.productadmin.modelo.dao;

import com.enpresa.productadmin.modelo.RegistroAcceso;
import com.enpresa.productadmin.modelo.dto.DTO;
import com.enpresa.productadmin.modelo.dto.RegistroAccesoBusquedaDTO;
import com.enpresa.productadmin.modelo.dto.RegistroAccesoDTO;
import com.enpresa.productadmin.utils.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejo
 */
public class BitacoraAccesoDAO implements DAO<RegistroAcceso, RegistroAccesoBusquedaDTO> {

    @Override
    public void crear(RegistroAcceso entidad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modificar(RegistroAcceso entidad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RegistroAccesoDTO> buscar(RegistroAccesoBusquedaDTO campos) {
        List<RegistroAccesoDTO> registros = new ArrayList<>();
        String sql = "{CALL dbo.pBuscarRegistroTransaccion(?, ?, ?, ?, ?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setString(1, campos.getFechaInicial());
            cs.setString(2, campos.getFechaFinal());
            cs.setString(3, campos.getHoraInicial());
            cs.setString(4, campos.getHoraFinal());
            cs.setString(5, campos.getUsuario());

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                RegistroAccesoDTO registro = new RegistroAccesoDTO();
                registro.setFecha(rs.getString(1));
                registro.setHora(rs.getString(2));
                registro.setUsuario(rs.getString(3));

                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    @Override
    public RegistroAcceso consultarUno(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RegistroAccesoBusquedaDTO> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
