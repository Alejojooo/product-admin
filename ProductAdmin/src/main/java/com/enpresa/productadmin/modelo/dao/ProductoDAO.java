package com.enpresa.productadmin.modelo.dao;

import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.modelo.dto.ProductoDTO;
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
public class ProductoDAO implements DAO<Producto, ProductoDTO> {

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
    public List<ProductoDTO> buscar(ProductoDTO dto) {
        List<ProductoDTO> productos = new ArrayList<>();
        String sql = "{CALL dbo.pBuscarProducto(?, ?, ?, ?, ?, ?)}";

        try (Connection c = new Conexion().establecerConexion(); CallableStatement cs = c.prepareCall(sql)) {
            cs.setString(1, dto.getId());
            cs.setNString(2, dto.getNombre());
            cs.setString(3, dto.getCantidad());
            cs.setString(4, dto.getPrecioCompra());
            cs.setString(5, dto.getPrecioVenta());
            cs.setNString(6, dto.getDescripcion());

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ProductoDTO producto = new ProductoDTO();
                    producto.setId(rs.getString(1));
                    producto.setNombre(rs.getNString(2));
                    producto.setCantidad(rs.getString(3));
                    producto.setPrecioCompra(rs.getString(4));
                    producto.setPrecioVenta(rs.getString(5));
                    producto.setDescripcion(rs.getNString(6));
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
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
    public List<ProductoDTO> consultarTodos() {
        List<ProductoDTO> productos = new ArrayList<>();
        String sql = "SELECT * FROM vProductos";

        try (Connection c = new Conexion().establecerConexion(); Statement st = c.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ProductoDTO producto = new ProductoDTO();
                producto.setId(rs.getString(1));
                producto.setNombre(rs.getNString(2));
                producto.setCantidad(rs.getString(3));
                producto.setPrecioCompra(rs.getString(4));
                producto.setPrecioVenta(rs.getString(5));
                producto.setDescripcion(rs.getNString(6));

                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
