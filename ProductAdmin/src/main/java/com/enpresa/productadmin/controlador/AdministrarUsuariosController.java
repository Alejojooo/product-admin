/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.dao.UsuarioDAO;
import com.enpresa.productadmin.modelo.Usuario;
import com.enpresa.productadmin.vistas.AdministrarUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author jmdub
 */
public class AdministrarUsuariosController {
    private final UsuarioDAO modelo;
    private final AdministrarUsuarios vista;
    private final JFrame frame;

    public AdministrarUsuariosController(UsuarioDAO modelo, AdministrarUsuarios vista) {
        this.modelo = modelo;
        this.vista = vista;

        frame = new JFrame("Administrar Usuarios");
        frame.setContentPane(vista);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        addActionListeners();
        addMouseListeners();
        
        mostrarUsuarios();
    }

    private void addActionListeners() {
        vista.getBtnAgregar().addActionListener((ActionEvent e) -> {
            crearUsuario();
        });
        vista.getBtnModificar().addActionListener((ActionEvent e) -> {
            modificarUsuario();
        });
    }

    private void addMouseListeners() {
        vista.getTbUsuarios().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                seleccionarUsuario();
            }
        });
    }
    
    /* --- Métodos auxiliares --- */
    private void mostrarUsuarios() {
        DefaultTableModel tabla = vista.getModelo();

        tabla.setNumRows(0);

        List<Usuario> usuarios = modelo.consultarTodos();
        for (Usuario usuario : usuarios) {
            String[] row = {
                String.valueOf(usuario.getId()),
                usuario.getUsuario(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getRol().toString(),
            };
            tabla.addRow(row);
        }
    }
    
    private void seleccionarUsuario() {
        JTable tbUsuarios = vista.getTbUsuarios();
        int fila = tbUsuarios.getSelectedRow();
        System.out.println("Fila: " + fila);

        if (fila < 0) { 
            JOptionPane.showMessageDialog(
                    frame,
                    "No se seleccionó el usuario.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        vista.getTxtId().setText(tbUsuarios.getValueAt(fila, 0).toString());
        vista.getTxtUsuario().setText(tbUsuarios.getValueAt(fila, 1).toString());
        vista.getTxtNombres().setText(tbUsuarios.getValueAt(fila, 2).toString());
        vista.getTxtApellidos().setText(tbUsuarios.getValueAt(fila, 3).toString());
        //vista.getTxtRol().setText(tbUsuarios.getValueAt(fila, 4).toString());
        
        
        System.out.println("Ejecutado método seleccionarUsuario");
    }
    
    private void limpiarCampos() {
        vista.getTxtId().setText("");
        vista.getTxtUsuario().setText("");
        vista.getTxtNombres().setText("");
        vista.getTxtApellidos().setText("");
    }
    
    /* --- Métodos para CRUD --- */
    private void crearUsuario() {
        if (usuarioVacio() || nombreVacio() || apellidoVacio()) {
            return;
        }

        Usuario usuario = new Usuario();

        modelo.crear(usuario);

        JOptionPane.showMessageDialog(frame,
                "Se ha creado un nuevo usuario.",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);

        mostrarUsuarios();
        limpiarCampos();
    }

    private void modificarUsuario() {
        if (idInvalida() || usuarioVacio() || nombreVacio() || apellidoVacio()) {
            return;
        }

        int id = Integer.parseInt(vista.getTxtId().getText());
        int confirmar = JOptionPane.showConfirmDialog(
                frame,
                String.format("¿Está seguro de modificar el usuario con ID [%d]", id),
                "Advertencia",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (confirmar == JOptionPane.NO_OPTION) {
            return;
        }

        Usuario usuario = new Usuario();

        modelo.modificar(usuario);

        JOptionPane.showMessageDialog(frame,
                "Se ha modificado el usuario.",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);

        mostrarUsuarios();
        limpiarCampos();
    }

    private void eliminarUsuario() {
        if (idInvalida()) {
            return;
        }

        int id = Integer.parseInt(vista.getTxtId().getText());

        int confirmar = JOptionPane.showConfirmDialog(
                frame,
                String.format("¿Está seguro de eliminar el usuario con ID [%d]", id),
                "Advertencia",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (confirmar == JOptionPane.NO_OPTION) {
            return;
        }

        modelo.eliminar(id);

        JOptionPane.showMessageDialog(frame,
                "Se ha eliminado el usuario.",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);

        mostrarUsuarios();
        limpiarCampos();
    }

    private void buscarUsuario() {
        // TODO
        Usuario usuario = new Usuario();
        
    }
    
    /* --- Métodos de comprobación --- */
    private boolean idInvalida() {
        try {
            int id = Integer.parseInt(vista.getTxtId().getText());
            if (id < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,
                    "La ID del usuario no es válida.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
    
    private boolean usuarioVacio() {
        if ("".equals(vista.getTxtUsuario().getText())) {
            JOptionPane.showMessageDialog(frame,
                    "El usuario introducido no es válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
    
    private boolean nombreVacio() {
        if ("".equals(vista.getTxtNombres().getText())) {
            JOptionPane.showMessageDialog(frame,
                    "El nombre introducido no es válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
    
    private boolean apellidoVacio() {
        if ("".equals(vista.getTxtApellidos().getText())) {
            JOptionPane.showMessageDialog(frame,
                    "El apellido introducido no es válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
}
