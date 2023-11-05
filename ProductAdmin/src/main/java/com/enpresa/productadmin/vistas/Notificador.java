package com.enpresa.productadmin.vistas;

/**
 *
 * @author Alejo
 */
public interface Notificador {

    void mostrarMensaje(String mensaje);

    void mostrarError(String mensaje);

    void mostrarAdvertencia(String mensaje);

    boolean mostrarConfirmacion(String mensaje);
}
