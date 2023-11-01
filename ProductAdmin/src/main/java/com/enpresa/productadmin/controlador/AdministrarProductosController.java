package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.dao.ProductoDAO;
import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.vistas.AdministrarProductos;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

    private void mostrarProductos() {
        DefaultTableModel tabla = new DefaultTableModel();

        tabla.addColumn("ID Producto");
        tabla.addColumn("Nombre");
        tabla.addColumn("Cantidad");
        tabla.addColumn("Precio Compra");
        tabla.addColumn("Precio Venta");
        tabla.addColumn("Descripcion");

        List<Producto> productos = modelo.consultarTodos();
        for (Producto producto : productos) {
            String[] row = {
                String.valueOf(producto.getId()),
                producto.getNombre(),
                String.valueOf(producto.getNombre()),
                producto.getPrecioCompra().toString(),
                producto.getPrecioVenta().toString(),
                producto.getDescripcion()
            };
            tabla.addRow(row);
        }

        vista.getTbProductos().setModel(tabla);
    }

    private void agregarProducto() {
        String nombre;
        int cantidad;
        BigDecimal precioCompra;
        BigDecimal precioVenta;
        String descripcion = vista.getDescripcion();

        nombre = vista.getNombre();
        if ("".equals(nombre)) {
            JOptionPane.showMessageDialog(frame, "Ingrese un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            cantidad = Integer.parseInt(vista.getCantidad());
            if (cantidad < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            precioCompra = new BigDecimal(vista.getPrecioCompra());
            precioVenta = new BigDecimal(vista.getPrecioVenta());
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

    private void modificarProducto() {
    }
}
