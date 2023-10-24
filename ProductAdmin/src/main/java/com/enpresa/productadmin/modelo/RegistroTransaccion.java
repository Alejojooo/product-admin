package com.enpresa.productadmin.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Alejo
 */
public class RegistroTransaccion {

    private int id;
    private LocalDate fecha;
    private LocalTime hora;
    private String objeto;
    private Usuario usuario;
    private Accion accion;
    private Modulo modulo;

    public RegistroTransaccion() {
    }

    public RegistroTransaccion(int id, LocalDate fecha, LocalTime hora,
            String objeto, Usuario usuario, Accion accion, Modulo modulo) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.objeto = objeto;
        this.usuario = usuario;
        this.accion = accion;
        this.modulo = modulo;
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

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

}
