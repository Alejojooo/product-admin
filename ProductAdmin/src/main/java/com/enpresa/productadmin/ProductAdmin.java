package com.enpresa.productadmin;

import com.enpresa.productadmin.dao.ProductoDAO;
import com.enpresa.productadmin.modelo.Producto;
import java.math.BigDecimal;

/**
 *
 * @author Alejo
 */
public class ProductAdmin {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Producto producto = new Producto();
        producto.setNombre("Fanta");
        producto.setCantidad(5);
        producto.setPrecioCompra(new BigDecimal(2.00));
        producto.setPrecioVenta(new BigDecimal(8.00));
        producto.setDescripcion("Bebida efervescente");

        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.crear(producto);
    }
}
