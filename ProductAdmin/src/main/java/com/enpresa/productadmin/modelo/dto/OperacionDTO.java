package com.enpresa.productadmin.modelo.dto;

/**
 *
 * @author Alejo
 */
public class OperacionDTO extends DTO {

    private String idProducto;
    private String tipoOperacion;
    private String cantidad;

    public OperacionDTO() {
        this.idProducto = "";
        this.tipoOperacion = "";
        this.cantidad = "";
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
