package com.enpresa.productadmin.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Alejo
 */
public class RegistroAcceso {

    private int id;
    private LocalDate fecha;
    private LocalTime hora;
    private Usuario usuario;

    public RegistroAcceso() {
    }

    public RegistroAcceso(int id, LocalDate fecha, LocalTime hora, Usuario usuario) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
