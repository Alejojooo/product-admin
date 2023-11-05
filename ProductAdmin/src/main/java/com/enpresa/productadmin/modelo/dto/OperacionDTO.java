package com.enpresa.productadmin.modelo.dto;

import com.enpresa.productadmin.modelo.Producto;

/**
 *
 * @author Alejo
 */
public class OperacionDTO {

    private String id;
    private Producto producto;
    private String cantidad;
    private String tipoOperacion;
    private String precio;

    public OperacionDTO() {
        this.id = "";
        this.producto = null;
        this.cantidad = "";
        this.tipoOperacion = "";
        this.precio = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

}
