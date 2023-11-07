package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.ProductAdmin;
import com.enpresa.productadmin.vistas.gui.MenuPrincipalVista;
import java.awt.event.ActionEvent;

/**
 *
 * @author Alejo
 */
public class MenuPrincipalController implements Controller {

    private final MenuPrincipalVista vista;

    public MenuPrincipalController(MenuPrincipalVista vista) {
        this.vista = vista;
        this.vista.getLabelUsuario().setText(ProductAdmin.usuarioActivo.getNombres());
    }

    @Override
    public void start() {
        vista.mostrarVista("Menú Principal");
        addActionListeners();
    }

    private void addActionListeners() {
        // Panel lateral
        vista.getBtnCambiarClave().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToCambiarClave();
        });

        vista.getBtnCerrarSesion().addActionListener((ActionEvent e) -> {
            ProductAdmin.usuarioActivo = null;
            ProductAdmin.goToIniciarSesion();
        });

        vista.getBtnSalir().addActionListener((ActionEvent e) -> {
            vista.cerrarVista();
        });

        // Productos
        vista.getBtnAdministrarProductos().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToAdministrarProductos();
        });

        vista.getBtnRegistrarCompraVenta().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToRegistrarCompraVenta();
        });

        // Usuarios
        vista.getBtnAdministrarUsuarios().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToAdministrarUsuarios();
        });

        vista.getBtnConsultarBitacoraTransacciones().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToConsultarBitacoraTransacciones();
        });

        vista.getBtnConsultarBitacoraAcceso().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToConsultarBitacoraAcceso();
        });

        // Reportes
        vista.getBtnReporteInventario().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToReporteInventario();
        });

        vista.getBtnReporteGastosGanacias().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToReporteGastosGanacias();
        });

        // Ayuda
        vista.getBtnAcercaDe().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToAcercaDe();
        });

    }
}
