/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
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
