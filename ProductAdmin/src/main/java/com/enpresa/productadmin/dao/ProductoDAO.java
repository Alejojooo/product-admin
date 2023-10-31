package com.enpresa.productadmin.dao;

import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.utils.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Alejo
 */
public class ProductoDAO {

    public void crear(Producto producto) {
        Conexion conexion = new Conexion(); // Bien

        String sql = "{CALL dbo.pCrearProducto(?, ?, ?, ?, ?)}";

        try {

            Connection c = conexion.establecerConexion(); // Mal, no hace la conexión
            
            CallableStatement cs = c.prepareCall(sql);

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

    public void modificar() {

    }

    public void eliminar() {

    }

    public void buscar() {

    }
}
