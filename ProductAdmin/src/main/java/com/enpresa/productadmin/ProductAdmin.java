package com.enpresa.productadmin;

import com.enpresa.productadmin.controlador.AdministrarProductosController;
import com.enpresa.productadmin.controlador.AdministrarUsuariosController;
import com.enpresa.productadmin.controlador.BitacoraAccesoController;
import com.enpresa.productadmin.controlador.BitacoraTransaccionesController;
import com.enpresa.productadmin.controlador.CambiarClaveController;
import com.enpresa.productadmin.controlador.InicioDeSesionController;
import com.enpresa.productadmin.controlador.MenuPrincipalController;
import com.enpresa.productadmin.controlador.RegistrarCompraVentaController;
import com.enpresa.productadmin.modelo.dao.ProductoDAO;
import com.enpresa.productadmin.modelo.dao.UsuarioDAO;
import com.enpresa.productadmin.modelo.Usuario;
import com.enpresa.productadmin.modelo.dao.BitacoraAccesoDAO;
import com.enpresa.productadmin.modelo.dao.BitacoraTransaccionesDAO;
import com.enpresa.productadmin.modelo.dao.OperacionDAO;
import com.enpresa.productadmin.vistas.VistaGrafica;
import com.enpresa.productadmin.vistas.gui.AcercaDeVista;
import com.enpresa.productadmin.vistas.gui.AdministrarProductosVista;
import com.enpresa.productadmin.vistas.gui.AdministrarUsuariosVista;
import com.enpresa.productadmin.vistas.gui.BitacoraAccesoVista;
import com.enpresa.productadmin.vistas.gui.BitacoraTransaccionesVista;
import com.enpresa.productadmin.vistas.gui.CambiarClaveVista;
import com.enpresa.productadmin.vistas.gui.IniciarSesionVista;
import com.enpresa.productadmin.vistas.gui.MenuPrincipalVista;
import com.enpresa.productadmin.vistas.gui.RegistrarCompraVentaVista;

/**
 *
 * @author Alejo
 */
public class ProductAdmin {

    public static Usuario usuarioActivo;

    public static void main(String[] args) {
        goToIniciarSesion(null);
    }

    public static void goToIniciarSesion(VistaGrafica origin) {
        UsuarioDAO modelo = new UsuarioDAO();
        IniciarSesionVista vista = new IniciarSesionVista();
        InicioDeSesionController controller = new InicioDeSesionController(modelo, vista);
        controller.start();
        if (origin != null) {
            origin.cerrarVista();
        }
    }

    public static void goToCambiarClave(VistaGrafica origin) {
        UsuarioDAO modelo = new UsuarioDAO();
        CambiarClaveVista vista = new CambiarClaveVista();
        CambiarClaveController controller = new CambiarClaveController(modelo, vista);
        controller.start();
        if (origin != null) {
            origin.cerrarVista();
        }
    }

    public static void goToMenuPrincipal(VistaGrafica origin) {
        MenuPrincipalVista vista = new MenuPrincipalVista();
        MenuPrincipalController controller = new MenuPrincipalController(vista);
        controller.start();
        if (origin != null) {
            origin.cerrarVista();
        }
    }

    public static void goToAdministrarProductos(VistaGrafica origin) {
        ProductoDAO modelo = new ProductoDAO();
        AdministrarProductosVista vista = new AdministrarProductosVista();
        AdministrarProductosController controller = new AdministrarProductosController(modelo, vista);
        controller.start();
        if (origin != null) {
            origin.cerrarVista();
        }
    }

    public static void goToRegistrarCompraVenta(VistaGrafica origin) {
        OperacionDAO modelo = new OperacionDAO();
        RegistrarCompraVentaVista vista = new RegistrarCompraVentaVista();
        RegistrarCompraVentaController controller = new RegistrarCompraVentaController(modelo, vista);
        controller.start();
        if (origin != null) {
            origin.cerrarVista();
        }
    }

    public static void goToAdministrarUsuarios(VistaGrafica origin) {
        UsuarioDAO modelo = new UsuarioDAO();
        AdministrarUsuariosVista vista = new AdministrarUsuariosVista();
        AdministrarUsuariosController controller = new AdministrarUsuariosController(modelo, vista);
        controller.start();
        if (origin != null) {
            origin.cerrarVista();
        }
    }

    public static void goToConsultarBitacoraTransacciones(VistaGrafica origin) {
        BitacoraTransaccionesDAO modelo = new BitacoraTransaccionesDAO();
        BitacoraTransaccionesVista vista = new BitacoraTransaccionesVista();
        BitacoraTransaccionesController controller = new BitacoraTransaccionesController(modelo, vista);
        controller.start();
        if (origin != null) {
            origin.cerrarVista();
        }
    }

    public static void goToConsultarBitacoraAcceso(VistaGrafica origin) {
        BitacoraAccesoDAO modelo = new BitacoraAccesoDAO();
        BitacoraAccesoVista vista = new BitacoraAccesoVista();
        BitacoraAccesoController controller = new BitacoraAccesoController(modelo, vista);
        controller.start();
        if (origin != null) {
            origin.cerrarVista();
        }
    }

    public static void goToReporteInventario() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void goToReporteGastosGanacias() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void goToAcercaDe(VistaGrafica origin) {
        AcercaDeVista vista = new AcercaDeVista();
        vista.mostrarVista("Acerca de");
        if (origin != null) {
            origin.cerrarVista();
        }
    }
}
