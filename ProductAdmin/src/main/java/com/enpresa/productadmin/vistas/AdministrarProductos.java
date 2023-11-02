/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.enpresa.productadmin.vistas;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oscar
 */
public class AdministrarProductos extends javax.swing.JPanel {
    
    private DefaultTableModel modelo;

    /**
     * Creates new form AdministrarProductos
     */
    public AdministrarProductos() {
        initComponents();
        initTable();
    }
    
    private void initTable() {
        modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID Producto", "Nombre", "Cantidad", "Precio Compra", "Precio Venta", "Descripción"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };
            
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        tbProductos.setModel(modelo);
        
        if (tbProductos.getColumnModel().getColumnCount() > 0) {
            tbProductos.getColumnModel().getColumn(0).setResizable(false);
            tbProductos.getColumnModel().getColumn(1).setResizable(false);
            tbProductos.getColumnModel().getColumn(2).setResizable(false);
            tbProductos.getColumnModel().getColumn(3).setResizable(false);
            tbProductos.getColumnModel().getColumn(4).setResizable(false);
            tbProductos.getColumnModel().getColumn(5).setResizable(false);
        }
    }
    
    public JTable getTbProductos() {
        return tbProductos;
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
    
    public JButton getBtnEditar() {
        return btnEditar;
    }
    
    public JButton getBtnEliminar() {
        return btnEliminar;
    }
    
    public JTextField getTxtCantidad() {
        return txtCantidad;
    }
    
    public JTextArea getTxtDescripcion() {
        return txtDescripcion;
    }
    
    public JTextField getTxtNombre() {
        return txtNombre;
    }
    
    public JTextField getTxtPrecioCompra() {
        return txtPrecioCompra;
    }
    
    public JTextField getTxtPrecioVenta() {
        return txtPrecioVenta;
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
        tbProductos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(980, 575));
        setLayout(null);

        tbProductos.setModel(tbProductos.getModel());
        tbProductos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbProductos);

        add(jScrollPane1);
        jScrollPane1.setBounds(16, 41, 578, 525);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Comandos"));
        jPanel1.setLayout(null);

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        jPanel1.add(btnAgregar);
        btnAgregar.setBounds(44, 23, 125, 50);

        btnEditar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        jPanel1.add(btnEditar);
        btnEditar.setBounds(185, 23, 125, 50);

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        jPanel1.add(btnEliminar);
        btnEliminar.setBounds(44, 89, 125, 50);

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        jPanel1.add(btnBuscar);
        btnBuscar.setBounds(185, 89, 125, 50);

        jLabel1.setText("Nombre:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(44, 220, 51, 21);

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtNombre);
        txtNombre.setBounds(44, 241, 150, 27);

        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtCantidad);
        txtCantidad.setBounds(210, 241, 100, 27);

        jLabel2.setText("Cantidad inicial:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(210, 220, 93, 21);

        txtPrecioCompra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtPrecioCompra);
        txtPrecioCompra.setBounds(44, 305, 100, 27);

        jLabel3.setText("Precio de compra:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(44, 284, 107, 21);

        txtPrecioVenta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtPrecioVenta);
        txtPrecioVenta.setBounds(210, 305, 100, 27);

        jLabel4.setText("Precio para venta:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(210, 284, 95, 21);

        jLabel5.setText("Descripción:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(44, 348, 73, 21);

        jScrollPane2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(44, 369, 266, 140);

        txtID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtID);
        txtID.setBounds(44, 177, 100, 27);

        jLabel6.setText("ID:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(44, 155, 17, 21);

        add(jPanel1);
        jPanel1.setBounds(610, 41, 354, 525);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtPrecioVenta;
    // End of variables declaration//GEN-END:variables
}
