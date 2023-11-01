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

        frame = new JFrame();
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
            agregarProducto();
        });
        vista.getBtnEditar().addActionListener((ActionEvent e) -> {
            modificarProducto();
        });
    }

    private void addMouseListeners() {
        vista.getTbProductos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
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

    private void agregarProducto() {
        String nombre;
        int cantidad;
        BigDecimal precioCompra;
        BigDecimal precioVenta;
        String descripcion = vista.getTxtDescripcion().getText();

        nombre = vista.getTxtNombre().getText();
        if ("".equals(nombre)) {
            JOptionPane.showMessageDialog(frame, "Ingrese un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            cantidad = Integer.parseInt(vista.getTxtCantidad().getText());
            if (cantidad < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            precioCompra = new BigDecimal(vista.getTxtPrecioCompra().getText());
            precioVenta = new BigDecimal(vista.getTxtPrecioVenta().getText());
            if (BigDecimal.ZERO.compareTo(precioCompra) > 0 || BigDecimal.ZERO.compareTo(precioVenta) > 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Ingrese precios válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Producto producto = new Producto(nombre, cantidad, precioCompra, precioVenta, descripcion);
        modelo.crear(producto);

        JOptionPane.showMessageDialog(frame, "Se ha creado un nuevo producto.", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        mostrarProductos();
    }

    private void seleccionarProducto() {
        JTable tbProductos = vista.getTbProductos();
        int fila = tbProductos.getSelectedRow();
        System.out.println("Fila: " + fila);

        if (fila < 0) {
            JOptionPane.showMessageDialog(frame, "No se seleccionó un producto.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        vista.getTxtNombre().setText(tbProductos.getValueAt(fila, 1).toString());
        vista.getTxtCantidad().setText(tbProductos.getValueAt(fila, 2).toString());
        vista.getTxtPrecioCompra().setText(tbProductos.getValueAt(fila, 3).toString());
        vista.getTxtPrecioVenta().setText(tbProductos.getValueAt(fila, 4).toString());
        vista.getTxtDescripcion().setText(tbProductos.getValueAt(fila, 5).toString());

        System.out.println("Ejecutado método seleccionarProducto");
    }

    private void modificarProducto() {

    }
}
