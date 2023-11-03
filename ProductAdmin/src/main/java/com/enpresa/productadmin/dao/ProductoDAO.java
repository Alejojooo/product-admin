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
    public List<Producto> buscar(Producto producto) {
        List<Producto> productos = new ArrayList<>();
        String sql = "{CALL dbo.pBuscarProducto(?, ?, ?, ?, ?, ?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            String id = (producto.getId() != null) ? String.valueOf(producto.getId()) : "";
            String nombre = producto.getNombre();
            String cantidad = (producto.getCantidad() != null) ? String.valueOf(producto.getCantidad()) : "";
            String precioCompra = (producto.getPrecioCompra() != null) ? String.valueOf(producto.getPrecioCompra()) : "";
            String precioVenta = (producto.getPrecioVenta() != null) ? String.valueOf(producto.getPrecioVenta()) : "";
            String descripcion = producto.getDescripcion();

            if ("".equals(id)
                    && "".equals(nombre)
                    && "".equals(cantidad)
                    && "".equals(precioCompra)
                    && "".equals(precioVenta)
                    && "".equals(descripcion)) {
                return null;
            }

            cs.setString(1, id);
            cs.setString(2, nombre);
            cs.setString(3, cantidad);
            cs.setString(4, precioCompra);
            cs.setString(5, precioVenta);
            cs.setString(6, descripcion);

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Producto productoEncontrado = new Producto();
                productoEncontrado.setId(rs.getInt(1));
                productoEncontrado.setNombre(rs.getString(2));
                productoEncontrado.setCantidad(rs.getInt(3));
                productoEncontrado.setPrecioCompra(rs.getBigDecimal(4));
                productoEncontrado.setPrecioVenta(rs.getBigDecimal(5));
                productoEncontrado.setDescripcion(rs.getString(6));
                
                productos.add(productoEncontrado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
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
}
