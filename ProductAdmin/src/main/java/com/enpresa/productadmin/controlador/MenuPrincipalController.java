package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.ProductAdmin;
import com.enpresa.productadmin.vistas.gui.MenuPrincipalVista;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

/**
 *
 * @author Alejo
 */
public class MenuPrincipalController {

    private final MenuPrincipalVista vista;
    private final JFrame frame;

    public MenuPrincipalController(MenuPrincipalVista vista) {
        this.vista = vista;
        this.vista.getLabelUsuario().setText(ProductAdmin.usuarioActivo.getNombres());

        frame = new JFrame();
        frame.setContentPane(vista);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        addActionListeners();
    }

    private void addActionListeners() {
        //Productos
        vista.getBtnAdministrarProductos().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToAdministrarProductos();
        });
        
        vista.getBtnRegistrarCompraVenta().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToRegistrarCompraVenta();
        });
        
        //Usuarios
        vista.getBtnAdministrarUsuarios().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToAdministrarUsuarios();
        });
        
        vista.getBtnConsultarBitacoraTransacciones().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToConsultarBitacoraTransacciones();
        });
        
        vista.getBtnConsultarBitacoraAcceso().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToConsultarBitacoraAcceso();
        });
        
        //Reportes
        vista.getBtnReporteInventario().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToReporteInventario();
        });
        
        vista.getBtnReporteGastosGanacias().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToReporteGastosGanacias();
        });
        
        //Ayuda
        vista.getBtnAcercaDe().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToAcercaDe();
        });
        
    }    
}
