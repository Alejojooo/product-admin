package com.enpresa.productadmin.modelo.dto;

/**
 *
 * @author Alejo
 */
public class ProductoDTO extends DTO {

    private String id;
    private String nombre;
    private String cantidad;
    private String precioCompra;
    private String precioVenta;
    private String descripcion;

    public ProductoDTO() {
        this.id = "";
        this.nombre = "";
        this.cantidad = "";
        this.precioCompra = "";
        this.precioVenta = "";
        this.descripcion = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(String precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
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
        sb.append(String.format("[%s] ", id));
        sb.append(nombre);
        return sb.toString();
    }

}
