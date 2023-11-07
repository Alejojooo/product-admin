package com.enpresa.productadmin.modelo.dto;

/**
 *
 * @author Alejo
 */
public class RegistroTransaccionDTO extends DTO {

    private String id;
    private String fechaInicial;
    private String fechaFinal;
    private String horaInicial;
    private String horaFinal;
    private String objeto;
    private String usuario;
    private String accion;
    private String modulo;

    public RegistroTransaccionDTO() {
        this.id = "";
        this.fechaInicial = "";
        this.fechaFinal = "";
        this.horaInicial = "";
        this.horaFinal = "";
        this.objeto = "";
        this.usuario = "";
        this.accion = "";
        this.modulo = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
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
