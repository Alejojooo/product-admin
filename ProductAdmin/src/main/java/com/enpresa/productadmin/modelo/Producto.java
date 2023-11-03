package com.enpresa.productadmin.modelo;

import java.math.BigDecimal;

/**
 *
 * @author Alejo
 */
public class Producto {

    private Integer id;
    private String nombre;
    private Integer cantidad;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private String descripcion;

    public Producto() {
    }

    public Producto(int id) {
        this(id, null, 0, null, null, null);
    }

    public Producto(String nombre, int cantidad, BigDecimal precioCompra, BigDecimal precioVenta, String descripcion) {
        this(0, nombre, cantidad, precioCompra, precioVenta, descripcion);
    }

    public Producto(String id, String nombre, String cantidad, String precioCompra, String precioVenta, String descripcion) {
        try {
            this.id = Integer.valueOf(id);
        } catch (NumberFormatException e) {
            this.id = null;
        }
        try {
            this.cantidad = Integer.valueOf(cantidad);
        } catch (NumberFormatException e) {
            this.cantidad = null;
        }
        try {
            this.precioCompra = new BigDecimal(precioCompra);
        } catch (NumberFormatException e) {
            this.precioCompra = null;
        }
        try {
            this.precioVenta = new BigDecimal(precioVenta);
        } catch (NumberFormatException e) {
            this.precioVenta = null;
        }

        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Producto(int id, String nombre, int cantidad, BigDecimal precioCompra, BigDecimal precioVenta, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.descripcion = descripcion;
    }

    public Integer getId() {
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

    public Integer getCantidad() {
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
