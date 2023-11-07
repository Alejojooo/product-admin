package com.enpresa.productadmin.modelo.dao;

import com.enpresa.productadmin.modelo.Accion;
import com.enpresa.productadmin.modelo.Modulo;
import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.modelo.RegistroTransaccion;
import com.enpresa.productadmin.utils.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alejo
 */
public class BitacoraTransaccionesDAO implements DAO<RegistroTransaccion> {

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
    public List buscar(Map<String, String> campos) {
        List<RegistroTransaccion> registros = new ArrayList<>();
        String sql = "{CALL dbo.pBuscarRegistroTransaccion(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setString(1, campos.get("fechaInicial"));
            cs.setString(2, campos.get("fechaFinal"));
            cs.setString(3, campos.get("horaInicial"));
            cs.setString(4, campos.get("horaFinal"));
            cs.setNString(5, campos.get("objeto"));
            cs.setString(6, campos.get("usuario"));
            cs.setString(6, campos.get("accion"));
            cs.setString(6, campos.get("modulo"));

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                RegistroTransaccion registro = new RegistroTransaccion();
                registro.setFecha(LocalDate.parse(rs.getString(1)));
                registro.setHora(LocalTime.parse(rs.getString(2)));
                registro.setObjeto(rs.getNString(3));
                registro.setUsuario(rs.getString(4));
                registro.setAccion(Accion.valueOf(rs.getString(5)));
                registro.setModulo(Modulo.valueOf(rs.getString(6)));

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
    public List<RegistroTransaccion> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
