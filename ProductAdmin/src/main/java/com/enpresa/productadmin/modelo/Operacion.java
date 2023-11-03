package com.enpresa.productadmin.modelo;

import java.math.BigDecimal;

/**
 *
 * @author Alejo
 */
public class Operacion {
    
    private Integer id;
    private String nombreProducto;
    private TipoOperacion tipoOperacion;
    private Integer cantidad;
    private BigDecimal precio;

    public Operacion() {
    }
    
    public Operacion(String nombreProducto, TipoOperacion tipoOperacion, Integer cantidad, BigDecimal precio) {
        this(0, nombreProducto, tipoOperacion, cantidad, precio);
    }

    public Operacion(Integer id, String nombreProducto, TipoOperacion tipoOperacion, Integer cantidad, BigDecimal precio) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.tipoOperacion = tipoOperacion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    
}
