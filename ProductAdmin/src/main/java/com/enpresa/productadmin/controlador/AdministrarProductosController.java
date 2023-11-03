package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.dao.ProductoDAO;
import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.vistas.AdministrarProductos;
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
    private final AdministrarProductos vista;

    public AdministrarProductosController(ProductoDAO modelo, AdministrarProductos vista) {
        this.modelo = modelo;
        this.vista = vista;

        mostrarProductos();
        mapearAcciones();
    }
    
    private void mapearAcciones() {
        vista.mapearAccion("Agregar", (e) -> {
            crearProducto();
            return null;
        });
        vista.mapearAccion("Modificar", (e) -> {
            modificarProducto();
            return null;
        });
        vista.mapearAccion("Eliminar", (e) -> {
            eliminarProducto();
            return null;
        });
        vista.mapearAccion("Buscar", (e) -> {
            buscarProducto();
            return null;
        });
    }

    /* --- Métodos auxiliares --- */
    private void mostrarProductos(List<Producto> productos) {
        if (productos == null) {
            productos = modelo.consultarTodos();
        }
        vista.mostrarRegistros(getRegistros(productos));
    }

    private void mostrarProductos() {
        mostrarProductos(null);
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
    private void crearProducto() {
        Map<String, String> campos = vista.getCampos();
        Producto producto = new Producto();
        try {
            producto.setId(comprobarId(campos.get("id")));
            producto.setNombre(comprobarNombre(campos.get("nombre")));
            producto.setCantidad(comprobarCantidad(campos.get("cantidad")));
            producto.setPrecioCompra(comprobarPrecio(campos.get("precioCompra")));
            producto.setPrecioVenta(comprobarPrecio(campos.get("precioVenta")));
            producto.setDescripcion(campos.get("descripcion"));
        } catch (ProductoInvalidoException e) {
            return;
        }
        modelo.crear(producto);
        vista.mostrarMensaje("Se ha creado un nuevo producto.");
        mostrarProductos();
    }

    private void modificarProducto() {
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
            return;
        }

        boolean modificar = vista.mostrarConfirmacion(String.format("¿Está seguro de modificar el producto con ID [%d] ?", id));
        if (!modificar) {
            return;
        }

        modelo.modificar(producto);
        vista.mostrarMensaje("Se ha modificado el producto.");
        mostrarProductos();
    }

    private void eliminarProducto() {
        Map<String, String> campos = vista.getCampos();
        Integer id;
        try {
            id = comprobarId(campos.get("id"));
        } catch (ProductoInvalidoException e) {
            return;
        }

        boolean eliminar = vista.mostrarConfirmacion(String.format("¿Está seguro de eliminar el producto con ID [%d] ?", id));
        if (!eliminar) {
            return;
        }

        modelo.eliminar(id);
        vista.mostrarMensaje("Se ha eliminado el producto.");
        mostrarProductos();
    }

    private void buscarProducto() {
        Map<String, String> campos = vista.getCampos();
        List<Producto> productos = modelo.buscar(campos);
        mostrarProductos(productos);
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
