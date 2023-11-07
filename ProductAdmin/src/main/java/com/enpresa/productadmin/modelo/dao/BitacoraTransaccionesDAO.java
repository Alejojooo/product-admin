package com.enpresa.productadmin.modelo.dao;

import com.enpresa.productadmin.modelo.RegistroTransaccion;
import com.enpresa.productadmin.modelo.dto.RegistroAccesoDTO;
import com.enpresa.productadmin.modelo.dto.RegistroTransaccionBusquedaDTO;
import com.enpresa.productadmin.modelo.dto.RegistroTransaccionDTO;
import com.enpresa.productadmin.utils.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejo
 */
public class BitacoraTransaccionesDAO implements DAO<RegistroTransaccion, RegistroTransaccionBusquedaDTO> {

    @Override
    public void crear(RegistroTransaccion entidad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modificar(RegistroTransaccion entidad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RegistroTransaccionDTO> buscar(RegistroTransaccionBusquedaDTO campos) {
        List<RegistroTransaccionDTO> registros = new ArrayList<>();
        String sql = "{CALL dbo.pBuscarRegistroTransaccion(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setString(1, campos.getFechaInicial());
            cs.setString(2, campos.getFechaFinal());
            cs.setString(3, campos.getHoraInicial());
            cs.setString(4, campos.getHoraFinal());
            cs.setNString(5, campos.getObjeto());
            cs.setString(6, campos.getUsuario());
            cs.setString(7, campos.getAccion());
            cs.setString(8, campos.getModulo());

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                RegistroTransaccionDTO registro = new RegistroTransaccionDTO();
                registro.setFecha(rs.getString(1));
                registro.setHora(rs.getString(2));
                registro.setObjeto(rs.getNString(3));
                registro.setUsuario(rs.getString(4));
                registro.setAccion(rs.getString(5));
                registro.setModulo(rs.getString(6));

                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    @Override
    public RegistroTransaccion consultarUno(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RegistroTransaccionDTO> consultarTodos() {
        List<RegistroTransaccionDTO> registros = new ArrayList<>();
        String sql = "SELECT * FROM vBitacoraTransacciones";

        try (Connection c = new Conexion().establecerConexion(); Statement st = c.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                RegistroTransaccionDTO registro = new RegistroTransaccionDTO();
                registro.setFecha(rs.getString(1));
                registro.setHora(rs.getString(2));
                registro.setObjeto(rs.getNString(3));
                registro.setUsuario(rs.getString(4));
                registro.setAccion(rs.getString(5));
                registro.setModulo(rs.getString(6));

                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }
}
