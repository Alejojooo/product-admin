package com.enpresa.productadmin.vistas.gui;

import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.utils.Mapper;
import com.enpresa.productadmin.vistas.MapearAccion;
import com.enpresa.productadmin.vistas.VistaGraficaConRegistros;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;

/**
 *
 * @author Oscar
 */
public class RegistrarCompraVentaVista extends VistaGraficaConRegistros implements MapearAccion {

    private final ButtonGroup buttonGroup;

    /**
     * Creates new form RegistrarCompraVenta
     */
    public RegistrarCompraVentaVista() {
        this.buttonGroup = new ButtonGroup();
        initComponents();

        rbtnCompra.doClick();
        mostrarVista("Registrar Compra/Venta");
    }

    private ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        cBoxProducto = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        rbtnCompra = new javax.swing.JRadioButton();
        rbtnVenta = new javax.swing.JRadioButton();
        txtTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(380, 333));
        setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar producto"));
        jPanel1.setLayout(null);

        jLabel1.setText("ID:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(16, 23, 15, 21);

        txtId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtId);
        txtId.setBounds(16, 44, 150, 27);

        cBoxProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cBoxProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));
        jPanel1.add(cBoxProducto);
        cBoxProducto.setBounds(182, 44, 150, 27);

        jLabel2.setText("Escoger producto:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(182, 23, 108, 21);

        jLabel3.setText("Nombre:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(16, 87, 51, 21);

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtNombre);
        txtNombre.setBounds(16, 108, 150, 27);

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        jPanel1.add(btnBuscar);
        btnBuscar.setBounds(207, 108, 100, 27);

        add(jPanel1);
        jPanel1.setBounds(16, 42, 348, 151);

        jLabel4.setText("Tipo de transferencia:");
        add(jLabel4);
        jLabel4.setBounds(36, 209, 128, 21);

        getButtonGroup().add(rbtnCompra);
        rbtnCompra.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rbtnCompra.setText("Compra");
        add(rbtnCompra);
        rbtnCompra.setBounds(36, 230, 67, 23);

        getButtonGroup().add(rbtnVenta);
        rbtnVenta.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rbtnVenta.setText("Venta");
        add(rbtnVenta);
        rbtnVenta.setBounds(119, 230, 54, 23);

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtTotal);
        txtTotal.setBounds(228, 230, 100, 27);

        jLabel5.setText("Total:");
        add(jLabel5);
        jLabel5.setBounds(228, 209, 31, 21);

        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtCantidad);
        txtCantidad.setBounds(36, 290, 100, 27);

        jLabel6.setText("Cantidad:");
        add(jLabel6);
        jLabel6.setBounds(36, 269, 56, 21);

        btnConfirmar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnConfirmar.setText("Confirmar");
        add(btnConfirmar);
        btnConfirmar.setBounds(228, 290, 100, 27);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JComboBox<String> cBoxProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbtnCompra;
    private javax.swing.JRadioButton rbtnVenta;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    public Map<String, String> getCampos() {
        Map<String, String> campos = new HashMap<>();
        String productoComoString = (String) cBoxProducto.getSelectedItem();
        Pattern patron = Pattern.compile("\\[(\\d+)\\]");
        Matcher matcher = patron.matcher(productoComoString);
        String idProducto = "";
        if (matcher.find()) {
            idProducto = matcher.group(1);
        }

        campos.put("idProducto", idProducto);
        campos.put("tipoOperacion", buttonGroup.getSelection().toString());
        campos.put("cantidad", txtCantidad.getText());

        return campos;
    }

    public Map<String, String> getCamposBusqueda() {
        Map<String, String> campos = Mapper.getMap(Producto.class);
        campos.put("id", txtId.getText());
        campos.put("nombre", txtNombre.getText());
        
        return campos;
    }

    public void mostrarRegistros(List<Producto> productos) {
        mostrarRegistrosEnLista(cBoxProducto, productos);
    }

    @Override
    public void mapearAccion(String accion, Function funcion) {
        switch (accion) {
            case "Confirmar" -> {
                btnConfirmar.addActionListener((ActionEvent e) -> {
                    funcion.apply(null);
                });
            }
            case "Buscar" -> {
                btnBuscar.addActionListener((ActionEvent e) -> {
                    funcion.apply(null);
                });
            }
            default -> {
                mostrarError("Operacion no implementada.");
            }
        }
    }
}
