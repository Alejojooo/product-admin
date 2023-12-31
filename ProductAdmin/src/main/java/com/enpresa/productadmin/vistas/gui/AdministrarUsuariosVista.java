package com.enpresa.productadmin.vistas.gui;

import com.enpresa.productadmin.modelo.Rol;
import com.enpresa.productadmin.modelo.dto.DTO;
import com.enpresa.productadmin.modelo.dto.UsuarioDTO;
import com.enpresa.productadmin.vistas.EntradaUsuario;
import com.enpresa.productadmin.vistas.MapearAccion;
import com.enpresa.productadmin.vistas.MostrarRegistros;
import com.enpresa.productadmin.vistas.VistaGraficaConRegistros;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author Oscar
 */
public class AdministrarUsuariosVista extends VistaGraficaConRegistros implements MapearAccion, EntradaUsuario, MostrarRegistros {

    /**
     * Creates new form AdministrarProductos
     */
    public AdministrarUsuariosVista() {
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

        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Usuario", "Nombres", "Apellidos", "Rol"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUsuarios.setColumnSelectionAllowed(true);
        tbUsuarios.getTableHeader().setReorderingAllowed(false);
        tbUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbUsuariosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbUsuarios);
        tbUsuarios.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbUsuarios.getColumnModel().getColumnCount() > 0) {
            tbUsuarios.getColumnModel().getColumn(0).setResizable(false);
            tbUsuarios.getColumnModel().getColumn(1).setResizable(false);
            tbUsuarios.getColumnModel().getColumn(2).setResizable(false);
            tbUsuarios.getColumnModel().getColumn(3).setResizable(false);
            tbUsuarios.getColumnModel().getColumn(4).setResizable(false);
        }

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
        cBoxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un rol...", "Empleado", "Gerente", "Administrador" }));
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

    private void tbUsuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuariosMousePressed
        seleccionarUsuario();
    }//GEN-LAST:event_tbUsuariosMousePressed

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

    @Override
    public UsuarioDTO obtenerCampos() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(txtId.getText());
        usuario.setUsuario(txtUsuario.getText());
        usuario.setNombres(txtNombres.getText());
        usuario.setApellidos(txtApellidos.getText());
        usuario.setRol((String) cBoxRol.getSelectedItem());
        
        return usuario;
    }

    @Override
    public void mostrarRegistros(List<? extends DTO> usuarios) {
        mostrarRegistrosEnTabla(tbUsuarios, usuarios);
    }

    @Override
    public void mapearAccion(String accion, Function funcion) {
        switch (accion) {
            case "Agregar" -> {
                btnAgregar.addActionListener((ActionEvent e) -> {
                    String clave = (String) funcion.apply(null);
                    if (clave != null) {
                        mostrarClaveNuevoUsuario(clave);
                        limpiarCampos();
                    }
                });
            }
            case "Modificar" -> {
                btnModificar.addActionListener((ActionEvent e) -> {
                    int exitCode = (int) funcion.apply(null);
                    if (exitCode > 0) {
                        limpiarCampos();
                    }
                });
            }
            case "Eliminar" -> {
                btnEliminar.addActionListener((ActionEvent e) -> {
                    int exitCode = (int) funcion.apply(null);
                    if (exitCode > 0) {
                        limpiarCampos();
                    }
                });
            }
            case "Buscar" -> {
                btnBuscar.addActionListener((ActionEvent e) -> {
                    int exitCode = (int) funcion.apply(null);
                    if (exitCode > 0) {
                    }
                });
            }
        }
    }

    private void mostrarClaveNuevoUsuario(String clave) {
        ClaveNuevoUsuarioVista claveNuevoUsuarioVista = new ClaveNuevoUsuarioVista();
        claveNuevoUsuarioVista.mostrarVista(clave);
    }

    private void seleccionarUsuario() {
        int fila = tbUsuarios.getSelectedRow();
        if (fila < 0) {
            mostrarError("No se seleccionó un usuario.");
            return;
        }
        txtId.setText(tbUsuarios.getValueAt(fila, 0).toString());
        txtUsuario.setText(tbUsuarios.getValueAt(fila, 1).toString());
        txtNombres.setText(tbUsuarios.getValueAt(fila, 2).toString());
        txtApellidos.setText(tbUsuarios.getValueAt(fila, 3).toString());
        Rol rol = Rol.valueOf(tbUsuarios.getValueAt(fila, 4).toString());
        cBoxRol.setSelectedIndex(rol.ordinal());
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtUsuario.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        cBoxRol.setSelectedIndex(0);
    }
}
