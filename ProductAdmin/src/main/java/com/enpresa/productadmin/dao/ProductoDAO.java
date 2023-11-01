package com.enpresa.productadmin.dao;

import com.enpresa.productadmin.modelo.Producto;
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
public class ProductoDAO implements DAO<Producto> {

    @Override
    public void crear(Producto producto) {
        String sql = "{CALL dbo.pCrearProducto(?, ?, ?, ?, ?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {

            cs.setString(1, producto.getNombre());
            cs.setInt(2, producto.getCantidad());
            cs.setBigDecimal(3, producto.getPrecioCompra());
            cs.setBigDecimal(4, producto.getPrecioVenta());
            cs.setString(5, producto.getDescripcion());

            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(Producto producto) {
        String sql = "{CALL dbo.pModificarProducto(?, ?, ?, ?, ?, ?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {

            cs.setInt(1, producto.getId());
            cs.setString(2, producto.getNombre());
            cs.setInt(3, producto.getCantidad());
            cs.setBigDecimal(4, producto.getPrecioCompra());
            cs.setBigDecimal(5, producto.getPrecioVenta());
            cs.setString(6, producto.getDescripcion());

            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Producto producto) {
        String sql = "{CALL dbo.pEliminarProducto(?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {

            cs.setInt(1, producto.getId());

            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> consultarTodos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM vProductos";

        try (Connection c = new Conexion().establecerConexion(); Statement st = c.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            Producto producto;
            while (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setCantidad(rs.getInt(3));
                producto.setPrecioCompra(rs.getBigDecimal(4));
                producto.setPrecioVenta(rs.getBigDecimal(5));
                producto.setDescripcion(rs.getString(6));
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public void buscar() {

    }
}
