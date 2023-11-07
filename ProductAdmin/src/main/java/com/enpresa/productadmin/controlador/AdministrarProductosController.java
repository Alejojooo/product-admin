package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.modelo.dao.ProductoDAO;
import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.modelo.dto.ProductoDTO;
import com.enpresa.productadmin.vistas.gui.AdministrarProductosVista;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Alejo
 */
public class AdministrarProductosController {

    private final ProductoDAO modelo;
    private final AdministrarProductosVista vista;

    public AdministrarProductosController(ProductoDAO modelo, AdministrarProductosVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.mostrarVista("Administrar Productos");
        mostrarRegistros();
        mapearAcciones();
    }

    /* --- Métodos auxiliares --- */
    private void mapearAcciones() {
        vista.mapearAccion("Agregar", (e) -> crearProducto());
        vista.mapearAccion("Modificar", (e) -> modificarProducto());
        vista.mapearAccion("Eliminar", (e) -> eliminarProducto());
        vista.mapearAccion("Buscar", (e) -> buscarProducto());
    }

    private void mostrarRegistros(List<ProductoDTO> productos) {
        if (productos == null) {
            productos = modelo.consultarTodos();
        }
        vista.mostrarRegistros(productos);
    }

    private void mostrarRegistros() {
        vista.mostrarRegistros(null);
    }

    /* --- Métodos para CRUD --- */
    private int crearProducto() {
        ProductoDTO campos = vista.obtenerCampos();
        Producto producto = new Producto();
        try {
            producto.setNombre(comprobarNombre(campos.getNombre()));
            producto.setCantidad(comprobarCantidad(campos.getCantidad()));
            producto.setPrecioCompra(comprobarPrecio(campos.getPrecioCompra()));
            producto.setPrecioVenta(comprobarPrecio(campos.getPrecioVenta()));
            producto.setDescripcion(campos.getDescripcion());

            modelo.crear(producto);
            vista.mostrarMensaje("Se ha creado un nuevo producto.");
            mostrarRegistros();
            return 1;
        } catch (ProductoInvalidoException e) {
            return -1;
        }
    }

    private int modificarProducto() {
        ProductoDTO campos = vista.obtenerCampos();
        Producto producto = new Producto();
        Integer id;
        try {
            id = comprobarId(campos.getId());
            producto.setId(id);
            producto.setNombre(comprobarNombre(campos.getNombre()));
            producto.setCantidad(comprobarCantidad(campos.getCantidad()));
            producto.setPrecioCompra(comprobarPrecio(campos.getPrecioCompra()));
            producto.setPrecioVenta(comprobarPrecio(campos.getPrecioVenta()));
            producto.setDescripcion(campos.getDescripcion());

            boolean modificar = vista.mostrarConfirmacion(String.format("¿Está seguro de modificar el producto con ID [%d]?", id));
            if (!modificar) {
                return -1;
            }

            modelo.modificar(producto);
            vista.mostrarMensaje("Se ha modificado el producto.");
            mostrarRegistros();
            return 1;
        } catch (ProductoInvalidoException e) {
            return -1;
        }
    }

    private int eliminarProducto() {
        ProductoDTO campos = vista.obtenerCampos();
        Integer id;
        try {
            id = comprobarId(campos.getId());

            boolean eliminar = vista.mostrarConfirmacion(String.format("¿Está seguro de eliminar el producto con ID [%d]?", id));
            if (!eliminar) {
                return -1;
            }

            modelo.eliminar(id);
            vista.mostrarMensaje("Se ha eliminado el producto.");
            mostrarRegistros();
            return 1;
        } catch (ProductoInvalidoException e) {
            return -1;
        }
    }

    private int buscarProducto() {
        ProductoDTO campos = vista.obtenerCampos();
        List<ProductoDTO> productos = modelo.buscar(campos);
        mostrarRegistros(productos);
        return 1;
    }

    /* --- Métodos de comprobación --- */
    private Integer comprobarId(String idString) throws ProductoInvalidoException {
        Integer id = null;
        try {
            id = Integer.valueOf(idString);
            if (id <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            vista.mostrarError("La ID del producto no es válida.");
            throw new ProductoInvalidoException();
        }
        return id;
    }

    private String comprobarNombre(String nombre) throws ProductoInvalidoException {
        if ("".equals(nombre)) {
            vista.mostrarError("El nombre introducido no es válido.");
            throw new ProductoInvalidoException();
        }
        return nombre;
    }

    private Integer comprobarCantidad(String cantidadString) throws ProductoInvalidoException {
        Integer cantidad = null;
        try {
            cantidad = Integer.valueOf(cantidadString);
            if (cantidad < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            vista.mostrarError("La cantidad introducida no es válida.");
            throw new ProductoInvalidoException();
        }
        return cantidad;
    }

    private BigDecimal comprobarPrecio(String precioString) throws ProductoInvalidoException {
        BigDecimal precio = null;
        try {
            precio = new BigDecimal(precioString);
            if (BigDecimal.ZERO.compareTo(precio) > 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            vista.mostrarError("Los precios introducidos no son válidos.");
            throw new ProductoInvalidoException();
        }
        return precio;
    }
}

class ProductoInvalidoException extends Exception {
}
