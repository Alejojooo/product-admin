package com.enpresa.productadmin.modelo.dto;

/**
 *
 * @author Alejo
 */
public class RegistroAccesoBusquedaDTO extends DTO {

    private String fechaInicial;
    private String fechaFinal;
    private String horaInicial;
    private String horaFinal;
    private String usuario;

    public RegistroAccesoBusquedaDTO() {
        this.fechaInicial = "";
        this.fechaFinal = "";
        this.horaInicial = "";
        this.horaFinal = "";
        this.usuario = "";
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
