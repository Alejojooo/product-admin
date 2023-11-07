package com.enpresa.productadmin.modelo.dto;

/**
 *
 * @author Alejo
 */
public class RegistroTransaccionDTO extends DTO {

    private String fecha;
    private String hora;
    private String objeto;
    private String usuario;
    private String accion;
    private String modulo;

    public RegistroTransaccionDTO() {
        this.fecha = "";
        this.hora = "";
        this.objeto = "";
        this.usuario = "";
        this.accion = "";
        this.modulo = "";
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

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
}
