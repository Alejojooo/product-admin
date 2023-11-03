/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.enpresa.productadmin.vistas;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oscar
 */
public class AdministrarProductos extends javax.swing.JPanel implements Vista {

    private JFrame frame;

    /**
     * Creates new form AdministrarProductos
     */
    public AdministrarProductos() {
        initComponents();
        initFrame();
    }

    private void initFrame() {
        frame = new JFrame("Administrar Productos");
        frame.setContentPane(this);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private void seleccionarProducto() {
        int fila = tbProductos.getSelectedRow();
        if (fila < 0) {
            mostrarError("No se seleccionó un producto.");
            return;
        }
        txtId.setText(tbProductos.getValueAt(fila, 0).toString());
        txtNombre.setText(tbProductos.getValueAt(fila, 1).toString());
        txtCantidad.setText(tbProductos.getValueAt(fila, 2).toString());
        txtPrecioCompra.setText(tbProductos.getValueAt(fila, 3).toString());
        txtPrecioVenta.setText(tbProductos.getValueAt(fila, 4).toString());
        txtDescripcion.setText(tbProductos.getValueAt(fila, 5).toString());
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        txtDescripcion.setText("");
    }

    @Override
    public Map<String, String> getCampos() {
        Map<String, String> campos = new HashMap<>();
        campos.put("id", txtId.getText());
        campos.put("nombre", txtNombre.getText());
        campos.put("cantidad", txtCantidad.getText());
        campos.put("precioCompra", txtPrecioCompra.getText());
        campos.put("precioVenta", txtPrecioVenta.getText());
        campos.put("descripcion", txtDescripcion.getText());

        return campos;
    }

    @Override
    public void mostrarRegistros(List<String[]> productos) {
        DefaultTableModel modelo = (DefaultTableModel) tbProductos.getModel();
        modelo.setNumRows(0);
        for (String[] producto : productos) {
            modelo.addRow(producto);
        }
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(frame,
                mensaje,
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(frame,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(frame,
                mensaje,
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public boolean mostrarConfirmacion(String mensaje) {
        int opcion = JOptionPane.showConfirmDialog(frame,
                mensaje,
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        return opcion == JOptionPane.YES_OPTION;
    }

    public void mapearAccion(String accion, Function funcion) {
        switch (accion) {
            case "Agregar" -> {
                btnAgregar.addActionListener((ActionEvent e) -> {
                    funcion.apply(null);
                });
            }
            case "Modificar" -> {
                btnModificar.addActionListener((ActionEvent e) -> {
                    funcion.apply(null);
                });
            }
            case "Eliminar" -> {
                btnEliminar.addActionListener((ActionEvent e) -> {
                    funcion.apply(null);
                });
            }
            case "Buscar" -> {
                btnBuscar.addActionListener((ActionEvent e) -> {
                    funcion.apply(null);
                });
            }
        }
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
        btnModificar = new javax.swing.JButton();
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
        txtId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(980, 575));
        setLayout(null);

        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Producto", "Nombre", "Cantidad", "Precio Compra", "Precio Venta", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProductos.setColumnSelectionAllowed(true);
        tbProductos.getTableHeader().setReorderingAllowed(false);
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbProductosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbProductos);
        tbProductos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbProductos.getColumnModel().getColumnCount() > 0) {
            tbProductos.getColumnModel().getColumn(0).setResizable(false);
            tbProductos.getColumnModel().getColumn(1).setResizable(false);
            tbProductos.getColumnModel().getColumn(2).setResizable(false);
            tbProductos.getColumnModel().getColumn(3).setResizable(false);
            tbProductos.getColumnModel().getColumn(4).setResizable(false);
            tbProductos.getColumnModel().getColumn(5).setResizable(false);
        }

        add(jScrollPane1);
        jScrollPane1.setBounds(16, 41, 578, 525);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Comandos"));
        jPanel1.setLayout(null);

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar");
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

        txtId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtId);
        txtId.setBounds(44, 177, 100, 27);

        jLabel6.setText("ID:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(44, 155, 17, 21);

        add(jPanel1);
        jPanel1.setBounds(610, 41, 354, 525);
    }// </editor-fold>//GEN-END:initComponents

    private void tbProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMousePressed
        seleccionarProducto();
    }//GEN-LAST:event_tbProductosMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
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
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtPrecioVenta;
    // End of variables declaration//GEN-END:variables
}
