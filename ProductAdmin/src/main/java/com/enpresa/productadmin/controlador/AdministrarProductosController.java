package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.dao.ProductoDAO;
import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.vistas.AdministrarProductos;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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

        addActionListeners();
    }

    private void addActionListeners() {
        vista.getBtnAgregar().addActionListener((ActionEvent e) -> {
            agregarProducto();
        });
        vista.getBtnEditar().addActionListener((ActionEvent e) -> {
            modificarProducto();
        });
    }

    private void agregarProducto() {
        String nombre = vista.getNombre();
        int cantidad;
        BigDecimal precioCompra;
        BigDecimal precioVenta;
        String descripcion = vista.getDescripcion();

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
    }

    private void modificarProducto() {
    }
}
