package com.enpresa.productadmin.modelo;

import java.math.BigDecimal;

/**
 *
 * @author Alejo
 */
public class Producto {

    private int id;
    private String nombre;
    private int cantidad;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private String descripcion;

    public Producto() {
    }
    
    public Producto(int id, String nombre, int cantidad, BigDecimal precioCompra, BigDecimal precioVenta, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.descripcion = descripcion;
    }
    
    public Producto(String nombre, int cantidad, BigDecimal precioCompra, BigDecimal precioVenta, String descripcion) {
        this(0, nombre, cantidad, precioCompra, precioVenta, descripcion);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append("[").append(nombre).append("]");
        return sb.toString();
    }
}
