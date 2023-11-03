package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.dao.ProductoDAO;
import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.vistas.AdministrarProductos;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejo
 */
public class AdministrarProductosController {

    private final ProductoDAO modelo;
    private final AdministrarProductos vista;
    private final JFrame frame;

    public AdministrarProductosController(ProductoDAO modelo, AdministrarProductos vista) {
        this.modelo = modelo;
        this.vista = vista;

        frame = new JFrame("Administrar Productos");
        frame.setContentPane(vista);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        addActionListeners();
        addMouseListeners();

        mostrarProductos();
    }

    private void addActionListeners() {
        vista.getBtnAgregar().addActionListener((ActionEvent e) -> {
            if (!camposInvalidos()) {
                agregarProducto();
            }
        });
        vista.getBtnEditar().addActionListener((ActionEvent e) -> {
            if (!camposInvalidos(true)) {
                modificarProducto();
            }
        });
    }

    private void addMouseListeners() {
        vista.getTbProductos().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                seleccionarProducto();
            }
        });
    }

    private void mostrarProductos() {
        DefaultTableModel tabla = vista.getModelo();

        tabla.setNumRows(0);

        List<Producto> productos = modelo.consultarTodos();
        for (Producto producto : productos) {
            String[] row = {
                String.valueOf(producto.getId()),
                producto.getNombre(),
                String.valueOf(producto.getCantidad()),
                producto.getPrecioCompra().toString(),
                producto.getPrecioVenta().toString(),
                producto.getDescripcion()
            };
            tabla.addRow(row);
        }
    }

    private void seleccionarProducto() {
        JTable tbProductos = vista.getTbProductos();
        int fila = tbProductos.getSelectedRow();
        System.out.println("Fila: " + fila);

        if (fila < 0) {
            JOptionPane.showMessageDialog(frame, "No se seleccionó un producto.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        vista.getTxtID().setText(tbProductos.getValueAt(fila, 0).toString());
        vista.getTxtNombre().setText(tbProductos.getValueAt(fila, 1).toString());
        vista.getTxtCantidad().setText(tbProductos.getValueAt(fila, 2).toString());
        vista.getTxtPrecioCompra().setText(tbProductos.getValueAt(fila, 3).toString());
        vista.getTxtPrecioVenta().setText(tbProductos.getValueAt(fila, 4).toString());
        vista.getTxtDescripcion().setText(tbProductos.getValueAt(fila, 5).toString());

        System.out.println("Ejecutado método seleccionarProducto");
    }

    /* Métodos para CRUD */
    private void agregarProducto() {
        String nombre = vista.getTxtNombre().getText();
        int cantidad = Integer.parseInt(vista.getTxtCantidad().getText());
        BigDecimal precioCompra = new BigDecimal(vista.getTxtPrecioCompra().getText());
        BigDecimal precioVenta = new BigDecimal(vista.getTxtPrecioVenta().getText());
        String descripcion = vista.getTxtDescripcion().getText();

        Producto producto = new Producto(nombre, cantidad, precioCompra, precioVenta, descripcion);
        modelo.crear(producto);

        JOptionPane.showMessageDialog(frame, "Se ha creado un nuevo producto.", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        mostrarProductos();
        limpiarCampos();
    }

    private void modificarProducto() {
        int id = Integer.parseInt(vista.getTxtID().getText());
        String nombre = vista.getTxtNombre().getText();
        int cantidad = Integer.parseInt(vista.getTxtCantidad().getText());
        BigDecimal precioCompra = new BigDecimal(vista.getTxtPrecioCompra().getText());
        BigDecimal precioVenta = new BigDecimal(vista.getTxtPrecioVenta().getText());
        String descripcion = vista.getTxtDescripcion().getText();

        Producto producto = new Producto(nombre, cantidad, precioCompra, precioVenta, descripcion);
        modelo.modificar(producto);

        JOptionPane.showMessageDialog(frame, "Se ha modificado el producto.", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        mostrarProductos();
        limpiarCampos();
    }

    /* Métodos de comprobación */
    private boolean camposInvalidos() {
        return camposInvalidos(false);
    }

    private boolean camposInvalidos(boolean validacionDeId) {
        if (validacionDeId && idInvalida()) {
            JOptionPane.showMessageDialog(frame, "La ID del producto no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        if (nombreVacio()) {
            JOptionPane.showMessageDialog(frame, "Ingrese un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        if (cantidadInvalida()) {
            JOptionPane.showMessageDialog(frame, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        if (preciosInvalidos()) {
            JOptionPane.showMessageDialog(frame, "Ingrese precios válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private boolean idInvalida() {
        try {
            int id = Integer.parseInt(vista.getTxtID().getText());
            if (id < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    private boolean nombreVacio() {
        return "".equals(vista.getTxtNombre().getText());
    }

    private boolean cantidadInvalida() {
        try {
            int cantidad = Integer.parseInt(vista.getTxtCantidad().getText());
            if (cantidad < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    private boolean preciosInvalidos() {
        try {
            BigDecimal precioCompra = new BigDecimal(vista.getTxtPrecioCompra().getText());
            BigDecimal precioVenta = new BigDecimal(vista.getTxtPrecioVenta().getText());
            if (BigDecimal.ZERO.compareTo(precioCompra) > 0 || BigDecimal.ZERO.compareTo(precioVenta) > 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    /* Funciones adicionales */
    private void limpiarCampos() {
        vista.getTxtID().setText("");
        vista.getTxtNombre().setText("");
        vista.getTxtCantidad().setText("");
        vista.getTxtPrecioCompra().setText("");
        vista.getTxtPrecioVenta().setText("");
        vista.getTxtDescripcion().setText("");
    }

}
