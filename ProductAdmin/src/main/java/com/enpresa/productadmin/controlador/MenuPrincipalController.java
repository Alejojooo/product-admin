package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.ProductAdmin;
import com.enpresa.productadmin.modelo.Rol;
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
        alternarBotones();
    }

    public void alternarBotones() {
        boolean enabled = Rol.Empleado != ProductAdmin.usuarioActivo.getRol();
        vista.getBtnAdministrarProductos().setEnabled(enabled);
        vista.getBtnAdministrarUsuarios().setEnabled(enabled);
        vista.getBtnConsultarBitacoraTransacciones().setEnabled(enabled);
        vista.getBtnConsultarBitacoraAcceso().setEnabled(enabled);
        vista.getBtnReporteGastosGanacias().setEnabled(enabled);
    }

    private void addActionListeners() {
        // Panel lateral
        vista.getBtnCambiarClave().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToCambiarClave(vista);
        });

        vista.getBtnCerrarSesion().addActionListener((ActionEvent e) -> {
            ProductAdmin.usuarioActivo = null;
            ProductAdmin.goToIniciarSesion(vista);
        });

        vista.getBtnSalir().addActionListener((ActionEvent e) -> {
            vista.cerrarVista();
        });

        // Productos
        vista.getBtnAdministrarProductos().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToAdministrarProductos(vista);
        });

        vista.getBtnRegistrarCompraVenta().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToRegistrarCompraVenta(vista);
        });

        // Usuarios
        vista.getBtnAdministrarUsuarios().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToAdministrarUsuarios(vista);
        });

        vista.getBtnConsultarBitacoraTransacciones().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToConsultarBitacoraTransacciones(vista);
        });

        vista.getBtnConsultarBitacoraAcceso().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToConsultarBitacoraAcceso(vista);
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
            ProductAdmin.goToAcercaDe(vista);
        });

    }
}
