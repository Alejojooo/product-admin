package com.enpresa.productadmin.modelo.dto;

/**
 *
 * @author Alejo
 */
public class UsuarioDTO extends DTO {

    private String id;
    private String usuario;
    private String nombres;
    private String apellidos;
    private String rol;

    public UsuarioDTO() {
        this.id = "";
        this.usuario = "";
        this.nombres = "";
        this.apellidos = "";
        this.rol = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
