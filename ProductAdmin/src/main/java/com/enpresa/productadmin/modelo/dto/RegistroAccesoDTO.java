package com.enpresa.productadmin.modelo.dto;

/**
 *
 * @author Alejo
 */
public class RegistroAccesoDTO extends DTO {

    private String fecha;
    private String hora;
    private String usuario;

    public RegistroAccesoDTO() {
        this.fecha = "";
        this.hora = "";
        this.usuario = "";
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
