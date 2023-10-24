package com.enpresa.productadmin.modelo;

/**
 *
 * @author Alejo
 */
public class Usuario {

    private int id;
    private String usuario;
    private String password;
    private String nombres;
    private String apellidos;
    private Rol rol;

    public Usuario() {
    }

    public Usuario(int id, String usuario, String password, String nombres, String apellidos, Rol rol) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + usuario + "(" + rol + ")";
    }
}
