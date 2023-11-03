package com.enpresa.productadmin.modelo;

/**
 *
 * @author Alejo
 */
public class Usuario {

    private int id;
    private String usuario;
    private String clave;
    private String nombres;
    private String apellidos;
    private Rol rol;

    public Usuario() {
    }

    public Usuario(int id) {
        this(id, null, null, null, null, null);
    }

    public Usuario(String usuario, String clave, String nombres, String apellidos, Rol rol) {
        this(0, usuario, clave, nombres, apellidos, rol);
    }

    public Usuario(int id, String usuario, String password, String nombres, String apellidos, Rol rol) {
        this.id = id;
        this.usuario = usuario;
        this.clave = password;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String password) {
        this.clave = password;
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
