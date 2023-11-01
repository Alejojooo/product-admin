package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.ProductAdmin;
import com.enpresa.productadmin.dao.UsuarioDAO;
import com.enpresa.productadmin.vistas.IniciarSesion;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejo
 */
public class InicioDeSesionController {

    private final UsuarioDAO modelo;
    private final IniciarSesion vista;
    private final JFrame frame;

    public InicioDeSesionController(UsuarioDAO modelo, IniciarSesion vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        frame = new JFrame();
        frame.setContentPane(vista);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        
        addActionListeners();
    }

    private void addActionListeners() {
        vista.getBtnIniciarSesion().addActionListener((ActionEvent e) -> {
            login();
        });
    }

    private void login() {
        int id = modelo.login(vista.getUsuario(), vista.getClave());
        if (id < 1) {
            JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(frame, "Inicio de sesión correcto.", "Información", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
        ProductAdmin.usuarioActivo = modelo.consultarUno(id);
        ProductAdmin.goToMenuPrincipal();
    }
}