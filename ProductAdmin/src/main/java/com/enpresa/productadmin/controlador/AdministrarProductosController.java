package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.dao.ProductoDAO;
import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.vistas.AdministrarProductos;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Alejo
 */
public class AdministrarProductosController {

    private final ProductoDAO modelo;
    private final AdministrarProductos vista;

    public AdministrarProductosController(ProductoDAO modelo, AdministrarProductos vista) {
        this.modelo = modelo;
        this.vista = vista;

        addActionListeners();
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

    /* --- Métodos auxiliares --- */
    private void mostrarProductos(List<Producto> productos) {
        if (productos == null) {
            productos = modelo.consultarTodos();
        }
        vista.mostrarProductos(productos);
    }

    private void mostrarProductos() {
        mostrarProductos(null);
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

        vista.mostrarMensaje("Se ha creado un nuevo producto.");

        mostrarProductos();
        vista.limpiarCampos();
    }

    private void modificarProducto() {
        if (idInvalida() || nombreVacio() || cantidadInvalida() || preciosInvalidos()) {
            return;
        }

        int id = Integer.parseInt(vista.getTxtID().getText());
        boolean modificar = vista.mostrarConfirmacion(String.format("¿Está seguro de modificar el producto con ID [%d] ?", id));
        if (!modificar) {
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

        vista.mostrarMensaje("Se ha modificado el producto.");

        mostrarProductos();
        vista.limpiarCampos();
    }

    private void eliminarProducto() {
        if (idInvalida()) {
            return;
        }

        int id = Integer.parseInt(vista.getTxtID().getText());

        boolean modificar = vista.mostrarConfirmacion(String.format("¿Está seguro de eliminar el producto con ID [%d] ?", id));
        if (!modificar) {
            return;
        }

        modelo.eliminar(id);

        vista.mostrarMensaje("Se ha eliminado el producto.");

        mostrarProductos();
        vista.limpiarCampos();
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
            vista.mostrarError("La ID del producto no es válida.");
            return true;
        }
        return false;
    }

    private boolean nombreVacio() {
        if ("".equals(vista.getTxtNombre().getText())) {
            vista.mostrarError("El nombre introducido no es válido.");
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
            vista.mostrarError("La cantidad introducida no es válida.");
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
            vista.mostrarError("Los precios introducidos no son válidos.");
            return true;
        }
        return false;
    }
}
