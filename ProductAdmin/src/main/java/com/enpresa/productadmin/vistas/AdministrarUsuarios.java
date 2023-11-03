/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.enpresa.productadmin.vistas;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oscar
 */
public class AdministrarUsuarios extends javax.swing.JPanel {

    private DefaultTableModel modelo;

    /**
     * Creates new form AdministrarProductos
     */
    public AdministrarUsuarios() {
        initComponents();
        initTable();
    }

    private void initTable() {
        modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID Usuario", "Usuario", "Nombres", "Apellidos", "Rol"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public boolean isCelEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        tbUsuarios.setModel(modelo);

        if (tbUsuarios.getColumnModel().getColumnCount() > 0) {
            tbUsuarios.getColumnModel().getColumn(0).setResizable(false);
            tbUsuarios.getColumnModel().getColumn(1).setResizable(false);
            tbUsuarios.getColumnModel().getColumn(2).setResizable(false);
            tbUsuarios.getColumnModel().getColumn(3).setResizable(false);
            tbUsuarios.getColumnModel().getColumn(4).setResizable(false);
        }
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JComboBox<String> getcBoxRol() {
        return cBoxRol;
    }

    public JTable getTbUsuarios() {
        return tbUsuarios;
    }

    public JTextField getTxtApellidos() {
        return txtApellidos;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtNombres() {
        return txtNombres;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cBoxRol = new javax.swing.JComboBox<>();
        txtId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(980, 531));
        setLayout(null);

        tbUsuarios.setModel(tbUsuarios.getModel());
        jScrollPane1.setViewportView(tbUsuarios);

        add(jScrollPane1);
        jScrollPane1.setBounds(16, 41, 578, 474);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Comandos"));
        jPanel1.setLayout(null);

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAgregar.setText("Crear");
        jPanel1.add(btnAgregar);
        btnAgregar.setBounds(44, 23, 125, 50);

        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnModificar.setText("Modificar");
        jPanel1.add(btnModificar);
        btnModificar.setBounds(185, 23, 125, 50);

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        jPanel1.add(btnEliminar);
        btnEliminar.setBounds(44, 89, 125, 50);

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        jPanel1.add(btnBuscar);
        btnBuscar.setBounds(185, 89, 125, 50);

        jLabel1.setText("Usuario:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(77, 219, 49, 21);

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtUsuario);
        txtUsuario.setBounds(77, 240, 200, 27);

        txtApellidos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtApellidos);
        txtApellidos.setBounds(77, 368, 200, 27);

        jLabel2.setText("Apellidos:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(77, 347, 56, 21);

        txtNombres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtNombres);
        txtNombres.setBounds(77, 304, 200, 27);

        jLabel3.setText("Nombres:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(77, 283, 57, 21);

        jLabel4.setText("Rol:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(77, 411, 23, 21);

        cBoxRol.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cBoxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cBoxRol);
        cBoxRol.setBounds(77, 431, 200, 27);

        txtId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtId);
        txtId.setBounds(77, 176, 200, 27);

        jLabel5.setText("ID:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(77, 155, 17, 21);

        add(jPanel1);
        jPanel1.setBounds(610, 41, 354, 474);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cBoxRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
