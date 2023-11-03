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
            crearProducto();
        });
        vista.getBtnModificar().addActionListener((ActionEvent e) -> {
            modificarProducto();
        });
        vista.getBtnEliminar().addActionListener((ActionEvent e) -> {
            eliminarProducto();
        });
        vista.getBtnBuscar().addActionListener((ActionEvent e) -> {
            buscarProducto();
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

    /* --- Métodos auxiliares --- */
    private void mostrarProductos(List<Producto> productos) {
        DefaultTableModel tabla = vista.getModelo();

        tabla.setNumRows(0);

        if (productos == null) {
            productos = modelo.consultarTodos();
        }
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

    private void mostrarProductos() {
        mostrarProductos(null);
    }

    private void seleccionarProducto() {
        JTable tbProductos = vista.getTbProductos();
        int fila = tbProductos.getSelectedRow();
        System.out.println("Fila: " + fila);

        if (fila < 0) {
            JOptionPane.showMessageDialog(
                    frame,
                    "No se seleccionó un producto.",
                    "Error", JOptionPane.ERROR_MESSAGE);
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

    private void limpiarCampos() {
        vista.getTxtID().setText("");
        vista.getTxtNombre().setText("");
        vista.getTxtCantidad().setText("");
        vista.getTxtPrecioCompra().setText("");
        vista.getTxtPrecioVenta().setText("");
        vista.getTxtDescripcion().setText("");
    }

    /* --- Métodos para CRUD --- */
    private void crearProducto() {
        if (nombreVacio() || cantidadInvalida() || preciosInvalidos()) {
            return;
        }

        Producto producto = new Producto(
                vista.getTxtNombre().getText(),
                Integer.parseInt(vista.getTxtCantidad().getText()),
                new BigDecimal(vista.getTxtPrecioCompra().getText()),
                new BigDecimal(vista.getTxtPrecioVenta().getText()),
                vista.getTxtDescripcion().getText()
        );

        modelo.crear(producto);

        JOptionPane.showMessageDialog(frame,
                "Se ha creado un nuevo producto.",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);

        mostrarProductos();
        limpiarCampos();
    }

    private void modificarProducto() {
        if (idInvalida() || nombreVacio() || cantidadInvalida() || preciosInvalidos()) {
            return;
        }

        int id = Integer.parseInt(vista.getTxtID().getText());
        int confirmar = JOptionPane.showConfirmDialog(
                frame,
                String.format("¿Está seguro de modificar el producto con ID [%d]", id),
                "Advertencia",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (confirmar == JOptionPane.NO_OPTION) {
            return;
        }

        Producto producto = new Producto(
                id,
                vista.getTxtNombre().getText(),
                Integer.parseInt(vista.getTxtCantidad().getText()),
                new BigDecimal(vista.getTxtPrecioCompra().getText()),
                new BigDecimal(vista.getTxtPrecioVenta().getText()),
                vista.getTxtDescripcion().getText()
        );

        modelo.modificar(producto);

        JOptionPane.showMessageDialog(frame,
                "Se ha modificado el producto.",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);

        mostrarProductos();
        limpiarCampos();
    }

    private void eliminarProducto() {
        if (idInvalida()) {
            return;
        }

        int id = Integer.parseInt(vista.getTxtID().getText());

        int confirmar = JOptionPane.showConfirmDialog(
                frame,
                String.format("¿Está seguro de eliminar el producto con ID [%d]", id),
                "Advertencia",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (confirmar == JOptionPane.NO_OPTION) {
            return;
        }

        modelo.eliminar(id);

        JOptionPane.showMessageDialog(frame,
                "Se ha eliminado el producto.",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);

        mostrarProductos();
        limpiarCampos();
    }

    private void buscarProducto() {
        Producto producto = new Producto(
                vista.getTxtID().getText(),
                vista.getTxtNombre().getText(),
                vista.getTxtCantidad().getText(),
                vista.getTxtPrecioCompra().getText(),
                vista.getTxtPrecioVenta().getText(),
                vista.getTxtDescripcion().getText()
        );

        List<Producto> productos = modelo.buscar(producto);

        mostrarProductos(productos);
    }

    /* --- Métodos de comprobación --- */
    private boolean idInvalida() {
        try {
            int id = Integer.parseInt(vista.getTxtID().getText());
            if (id < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,
                    "La ID del producto no es válida.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private boolean nombreVacio() {
        if ("".equals(vista.getTxtNombre().getText())) {
            JOptionPane.showMessageDialog(frame,
                    "El nombre introducido no es válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private boolean cantidadInvalida() {
        try {
            int cantidad = Integer.parseInt(vista.getTxtCantidad().getText());
            if (cantidad < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,
                    "La cantidad introducida no es válida.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(frame,
                    "Los precios introducidos no son válidos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
}
