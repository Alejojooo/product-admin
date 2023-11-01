package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.ProductAdmin;
import com.enpresa.productadmin.vistas.MenuPrincipal;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

/**
 *
 * @author Alejo
 */
public class MenuPrincipalController {

    private final MenuPrincipal vista;
    private final JFrame frame;

    public MenuPrincipalController(MenuPrincipal vista) {
        this.vista = vista;

        frame = new JFrame();
        frame.setContentPane(vista);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        addActionListeners();
    }

    private void addActionListeners() {
        vista.getBtnAdministrarProductos().addActionListener((ActionEvent e) -> {
            ProductAdmin.goToAdministrarProductos();
        });
    }
}
