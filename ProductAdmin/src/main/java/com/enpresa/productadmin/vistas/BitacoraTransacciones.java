/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.enpresa.productadmin.vistas;

/**
 *
 * @author Alejo
 */
public class BitacoraTransacciones extends javax.swing.JPanel {

    /**
     * Creates new form BitacoraAcceso
     */
    public BitacoraTransacciones() {
        initComponents();
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
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField5 = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jCheckBox4 = new javax.swing.JCheckBox();
        jTextField10 = new javax.swing.JTextField();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jTextField11 = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(980, 505));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Fecha", "Hora", "Objeto", "Usuario", "Acción", "Módulo"
            }
        ));
        jTable1.setToolTipText("");
        jTable1.setPreferredSize(new java.awt.Dimension(578, 448));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 41, 578, 448));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("¿Filtrar?"));
        jPanel1.setLayout(null);

        jTextField6.setPreferredSize(new java.awt.Dimension(30, 22));
        jPanel1.add(jTextField6);
        jTextField6.setBounds(70, 105, 30, 27);

        jLabel1.setText("De:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(17, 65, 17, 16);

        jTextField7.setPreferredSize(new java.awt.Dimension(30, 22));
        jPanel1.add(jTextField7);
        jTextField7.setBounds(106, 105, 30, 27);

        jLabel2.setText("Hasta:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(16, 108, 33, 16);

        jLabel7.setText("De:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(235, 65, 17, 16);

        jTextField2.setPreferredSize(new java.awt.Dimension(30, 22));
        jPanel1.add(jTextField2);
        jTextField2.setBounds(71, 62, 30, 27);

        jLabel8.setText("Hasta:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(234, 108, 33, 16);

        jTextField3.setPreferredSize(new java.awt.Dimension(30, 22));
        jPanel1.add(jTextField3);
        jTextField3.setBounds(107, 62, 30, 27);

        jTextField8.setPreferredSize(new java.awt.Dimension(50, 22));
        jPanel1.add(jTextField8);
        jTextField8.setBounds(288, 62, 50, 27);

        jTextField4.setPreferredSize(new java.awt.Dimension(50, 22));
        jPanel1.add(jTextField4);
        jTextField4.setBounds(143, 62, 50, 27);

        jTextField9.setPreferredSize(new java.awt.Dimension(50, 22));
        jPanel1.add(jTextField9);
        jTextField9.setBounds(288, 105, 50, 27);

        jLabel3.setText("/");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(101, 65, 5, 16);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(21, 147, 314, 10);

        jLabel4.setText("/");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(137, 65, 5, 16);

        jCheckBox1.setText("Fecha");
        jPanel1.add(jCheckBox1);
        jCheckBox1.setBounds(17, 30, 54, 20);

        jTextField5.setPreferredSize(new java.awt.Dimension(50, 22));
        jPanel1.add(jTextField5);
        jTextField5.setBounds(142, 105, 50, 27);

        jCheckBox2.setText("Hora");
        jPanel1.add(jCheckBox2);
        jCheckBox2.setBounds(234, 30, 49, 20);

        jLabel5.setText("/");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(100, 108, 5, 16);

        jLabel6.setText("/");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(136, 108, 5, 16);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setPreferredSize(new java.awt.Dimension(150, 22));
        jPanel1.add(jComboBox2);
        jComboBox2.setBounds(153, 305, 150, 27);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setPreferredSize(new java.awt.Dimension(150, 22));
        jPanel1.add(jComboBox3);
        jComboBox3.setBounds(153, 262, 150, 27);

        jCheckBox4.setText("Objeto");
        jPanel1.add(jCheckBox4);
        jCheckBox4.setBounds(51, 176, 59, 20);

        jTextField10.setPreferredSize(new java.awt.Dimension(150, 22));
        jPanel1.add(jTextField10);
        jTextField10.setBounds(153, 219, 150, 27);

        jCheckBox5.setText("Usuario");
        jPanel1.add(jCheckBox5);
        jCheckBox5.setBounds(51, 221, 63, 20);

        jCheckBox6.setText("Accion");
        jPanel1.add(jCheckBox6);
        jCheckBox6.setBounds(51, 264, 60, 20);

        jCheckBox7.setText("Módulo");
        jPanel1.add(jCheckBox7);
        jCheckBox7.setBounds(51, 307, 65, 20);

        jTextField11.setPreferredSize(new java.awt.Dimension(150, 22));
        jPanel1.add(jTextField11);
        jTextField11.setBounds(153, 174, 150, 27);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 41, 354, 448));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
