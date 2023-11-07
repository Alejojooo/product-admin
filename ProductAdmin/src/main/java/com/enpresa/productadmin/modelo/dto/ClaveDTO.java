package com.enpresa.productadmin.modelo.dto;

/**
 *
 * @author Alejo
 */
public class ClaveDTO extends DTO {

    String claveActual;
    String claveNueva;
    String claveConfirmacion;

    public ClaveDTO() {
        this.claveActual = "";
        this.claveNueva = "";
        this.claveConfirmacion = "";
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public String getClaveConfirmacion() {
        return claveConfirmacion;
    }

    public void setClaveConfirmacion(String claveConfirmacion) {
        this.claveConfirmacion = claveConfirmacion;
    }

}
