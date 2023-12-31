package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.modelo.Operacion;
import com.enpresa.productadmin.modelo.Producto;
import com.enpresa.productadmin.modelo.TipoOperacion;
import com.enpresa.productadmin.modelo.dao.OperacionDAO;
import com.enpresa.productadmin.modelo.dao.ProductoDAO;
import com.enpresa.productadmin.modelo.dto.OperacionDTO;
import com.enpresa.productadmin.modelo.dto.ProductoDTO;
import com.enpresa.productadmin.vistas.gui.RegistrarCompraVentaVista;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jmdub
 */
public class RegistrarCompraVentaController implements Controller {

    private final OperacionDAO modelo;
    private final RegistrarCompraVentaVista vista;

    public RegistrarCompraVentaController(OperacionDAO modelo, RegistrarCompraVentaVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    
    @Override
    public void start() {
        mapearAcciones();
    }

    private void mapearAcciones() {
        vista.mapearAccion("Confirmar", (e) -> crearOperacion());
        vista.mapearAccion("Buscar", (e) -> buscarProducto());
    }

    private int buscarProducto() {
        ProductoDTO camposBusqueda = vista.obtenerCamposBusqueda();
        ProductoDAO productoDAO = new ProductoDAO();
        List<ProductoDTO> productos = productoDAO.buscar(camposBusqueda);
        vista.mostrarRegistros(productos);
        return 1;
    }

    private int crearOperacion() {
        OperacionDTO campos = vista.obtenerCampos();

        String nombreProducto;
        TipoOperacion tipoOperacion;
        BigDecimal precio;
        Integer cantidad;

        Operacion operacion = new Operacion();
        try {
            // Obtener nombreProducto
            Integer idProducto = comprobarIdProducto(campos.getIdProducto());

            ProductoDAO productoDAO = new ProductoDAO();
            Producto producto = productoDAO.consultarUno(idProducto);
            nombreProducto = producto.getNombre();

            // Obtener tipoOperacion
            tipoOperacion = TipoOperacion.valueOf(campos.getTipoOperacion());

            // Obtener precio
            if (tipoOperacion == TipoOperacion.Compra) {
                precio = producto.getPrecioCompra();
            } else {
                precio = producto.getPrecioVenta();
            }
            // Obtener cantidad
            cantidad = comprobarCantidad(campos.getCantidad());
            Integer productoCantidad = comprobarCantidadConProducto(cantidad, producto);

            operacion.setNombreProducto(nombreProducto);
            operacion.setTipoOperacion(tipoOperacion);
            operacion.setPrecio(precio);
            operacion.setCantidad(cantidad);

            producto.setCantidad(productoCantidad);
            productoDAO.modificar(producto);

            modelo.crear(operacion);
            vista.mostrarMensaje("Se ha registrado la operación correctamente.");
            return 1;
        } catch (OperacionInvalidaException e) {
            return -1;
        }
    }

    /* --- Métodos de comprobación --- */
    private Integer comprobarIdProducto(String idString) throws OperacionInvalidaException {
        Integer id = null;
        try {
            id = Integer.valueOf(idString);
            if (id < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            vista.mostrarError("El producto no es válido.");
            throw new OperacionInvalidaException();
        }
        return id;
    }

    private Integer comprobarCantidad(String cantidadString) throws OperacionInvalidaException {
        Integer cantidad = null;
        try {
            cantidad = Integer.valueOf(cantidadString);
            if (cantidad <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            vista.mostrarError("La cantidad introducida no es válida.");
            throw new OperacionInvalidaException();
        }
        return cantidad;
    }

    private Integer comprobarCantidadConProducto(Integer cantidad, Producto producto) throws OperacionInvalidaException {
        Integer nuevaCantidad = null;
        Integer productoCantidad = producto.getCantidad();
        if (cantidad < 0 && productoCantidad < Math.abs(cantidad)) {
            throw new OperacionInvalidaException();
        }
        return cantidad + productoCantidad;
    }
}

class OperacionInvalidaException extends Exception {
}
