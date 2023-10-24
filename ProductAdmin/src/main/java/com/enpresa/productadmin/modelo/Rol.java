package com.enpresa.productadmin.modelo;

/**
 *
 * @author Alejo
 */
public class Rol {

    private int id;
    private String rol;
    private String descripcion;

    public Rol() {
    }

    public Rol(int id, String rol, String descripcion) {
        this.id = id;
        this.rol = rol;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + rol;
    }
}
