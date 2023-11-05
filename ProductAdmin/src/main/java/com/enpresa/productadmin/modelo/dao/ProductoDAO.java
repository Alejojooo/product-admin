package com.enpresa.productadmin.modelo.dao;

import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.utils.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alejo
 */
public class ProductoDAO implements DAO<Producto> {

    @Override
    public void crear(Producto producto) {
        String sql = "{CALL dbo.pCrearProducto(?, ?, ?, ?, ?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setNString(1, producto.getNombre());
            cs.setInt(2, producto.getCantidad());
            cs.setBigDecimal(3, producto.getPrecioCompra());
            cs.setBigDecimal(4, producto.getPrecioVenta());
            cs.setNString(5, producto.getDescripcion());

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
            cs.setNString(2, producto.getNombre());
            cs.setInt(3, producto.getCantidad());
            cs.setBigDecimal(4, producto.getPrecioCompra());
            cs.setBigDecimal(5, producto.getPrecioVenta());
            cs.setNString(6, producto.getDescripcion());

            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "{CALL dbo.pEliminarProducto(?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setInt(1, id);

            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> buscar(Map<String, String> campos) {
        List<Producto> productos = new ArrayList<>();
        String sql = "{CALL dbo.pBuscarProducto(?, ?, ?, ?, ?, ?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setString(1, campos.get("id"));
            cs.setNString(2, campos.get("nombre"));
            cs.setString(3, campos.get("cantidad"));
            cs.setString(4, campos.get("precioCompra"));
            cs.setString(5, campos.get("precioVenta"));
            cs.setNString(6, campos.get("descripcion"));

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getNString(2));
                producto.setCantidad(rs.getInt(3));
                producto.setPrecioCompra(rs.getBigDecimal(4));
                producto.setPrecioVenta(rs.getBigDecimal(5));
                producto.setDescripcion(rs.getNString(6));

                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public Producto consultarUno(int id) {
        Producto producto = new Producto();
        String sql = "{CALL dbo.pConsultarProducto(?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getNString(2));
                producto.setCantidad(rs.getInt(3));
                producto.setPrecioCompra(rs.getBigDecimal(4));
                producto.setPrecioVenta(rs.getBigDecimal(5));
                producto.setDescripcion(rs.getNString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public List<Producto> consultarTodos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM vProductos";

        try (Connection c = new Conexion().establecerConexion(); Statement st = c.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getNString(2));
                producto.setCantidad(rs.getInt(3));
                producto.setPrecioCompra(rs.getBigDecimal(4));
                producto.setPrecioVenta(rs.getBigDecimal(5));
                producto.setDescripcion(rs.getNString(6));

                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
