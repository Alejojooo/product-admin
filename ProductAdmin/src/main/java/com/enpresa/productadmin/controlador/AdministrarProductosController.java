package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.dao.ProductoDAO;
import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.vistas.gui.AdministrarProductosVista;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private void mostrarRegistros(List<Producto> productos) {
        if (productos == null) {
            productos = modelo.consultarTodos();
        }
        vista.mostrarRegistros(getRegistros(productos));
    }

    private void mostrarRegistros() {
        mostrarRegistros(null);
    }

    private List<String[]> getRegistros(List<Producto> productos) {
        List<String[]> registros = new ArrayList<>();
        for (Producto producto : productos) {
            String[] registro = {
                String.valueOf(producto.getId()),
                producto.getNombre(),
                String.valueOf(producto.getCantidad()),
                String.valueOf(producto.getPrecioCompra()),
                String.valueOf(producto.getPrecioVenta()),
                producto.getDescripcion()
            };
            registros.add(registro);
        }
        return registros;
    }

    /* --- Métodos para CRUD --- */
    private int crearProducto() {
        Map<String, String> campos = vista.getCampos();
        Producto producto = new Producto();
        try {
            producto.setNombre(comprobarNombre(campos.get("nombre")));
            producto.setCantidad(comprobarCantidad(campos.get("cantidad")));
            producto.setPrecioCompra(comprobarPrecio(campos.get("precioCompra")));
            producto.setPrecioVenta(comprobarPrecio(campos.get("precioVenta")));
            producto.setDescripcion(campos.get("descripcion"));
        } catch (ProductoInvalidoException e) {
            return -1;
        }
        modelo.crear(producto);
        vista.mostrarMensaje("Se ha creado un nuevo producto.");
        mostrarRegistros();
        return 1;
    }

    private int modificarProducto() {
        Map<String, String> campos = vista.getCampos();
        Producto producto = new Producto();
        Integer id;
        try {
            id = comprobarId(campos.get("id"));
            producto.setId(id);
            producto.setNombre(comprobarNombre(campos.get("nombre")));
            producto.setCantidad(comprobarCantidad(campos.get("cantidad")));
            producto.setPrecioCompra(comprobarPrecio(campos.get("precioCompra")));
            producto.setPrecioVenta(comprobarPrecio(campos.get("precioVenta")));
            producto.setDescripcion(campos.get("descripcion"));
        } catch (ProductoInvalidoException e) {
            return -1;
        }

        boolean modificar = vista.mostrarConfirmacion(String.format("¿Está seguro de modificar el producto con ID [%d]?", id));
        if (!modificar) {
            return -1;
        }

        modelo.modificar(producto);
        vista.mostrarMensaje("Se ha modificado el producto.");
        mostrarRegistros();
        return 1;
    }

    private int eliminarProducto() {
        Map<String, String> campos = vista.getCampos();
        Integer id;
        try {
            id = comprobarId(campos.get("id"));
        } catch (ProductoInvalidoException e) {
            return -1;
        }

        boolean eliminar = vista.mostrarConfirmacion(String.format("¿Está seguro de eliminar el producto con ID [%d]?", id));
        if (!eliminar) {
            return -1;
        }

        modelo.eliminar(id);
        vista.mostrarMensaje("Se ha eliminado el producto.");
        mostrarRegistros();
        return 1;
    }

    private int buscarProducto() {
        Map<String, String> campos = vista.getCampos();
        List<Producto> productos = modelo.buscar(campos);
        mostrarRegistros(productos);
        return 1;
    }

    /* --- Métodos de comprobación --- */
    private Integer comprobarId(String idString) throws ProductoInvalidoException {
        Integer id = null;
        try {
            id = Integer.valueOf(idString);
            if (id < 0) {
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
