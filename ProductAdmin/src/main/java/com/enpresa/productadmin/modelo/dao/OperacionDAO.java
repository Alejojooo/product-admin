package com.enpresa.productadmin.modelo.dao;

import com.enpresa.productadmin.modelo.Operacion;
import com.enpresa.productadmin.modelo.TipoOperacion;
import com.enpresa.productadmin.modelo.dto.OperacionDTO;
import com.enpresa.productadmin.modelo.dto.OperacionReporteDTO;
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
public class OperacionDAO implements DAO<Operacion, OperacionDTO> {

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
    public void modificar(Operacion entidad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<OperacionDTO> buscar(OperacionDTO dto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Operacion consultarUno(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<OperacionDTO> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private List<OperacionReporteDTO> consultarTodos(String sql) {
        List<OperacionReporteDTO> registros = new ArrayList<>();

        try (Connection c = new Conexion().establecerConexion(); Statement st = c.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                OperacionReporteDTO registro = new OperacionReporteDTO();
                registro.setNombreProducto(rs.getString(1));
                registro.setPrecio(rs.getBigDecimal(2));
                registro.setCantidad(rs.getInt(3));
                registro.setTotal(rs.getBigDecimal(4));

                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    public List<OperacionReporteDTO> consultar(TipoOperacion tabla) {
        String sql = "SELECT * FROM vCompras";
        if (tabla == TipoOperacion.Venta) {
            sql = "SELECT * FROM vVentas";
        }
        return consultarTodos(sql);
    }
}
