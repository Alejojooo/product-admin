package com.enpresa.productadmin.modelo.dto;

import java.math.BigDecimal;

/**
 *
 * @author Alejo
 */
public class OperacionReporteDTO extends DTO {

    private String nombreProducto;
    private BigDecimal precio;
    private Integer cantidad;
    private BigDecimal total;

    public OperacionReporteDTO() {
        this.nombreProducto = "";
        this.precio = null;
        this.cantidad = null;
        this.total = null;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
