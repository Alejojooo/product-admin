package com.enpresa.productadmin.modelo.dto;

/**
 *
 * @author Alejo
 */
public class ProductoDTO {

    public String id;
    public String nombre;
    public String cantidad;
    public String precioCompra;
    public String precioVenta;
    public String descripcion;

    public ProductoDTO() {
        this.id = "";
        this.nombre = "";
        this.cantidad = "";
        this.precioCompra = "";
        this.precioVenta = "";
        this.descripcion = "";
    }
}
