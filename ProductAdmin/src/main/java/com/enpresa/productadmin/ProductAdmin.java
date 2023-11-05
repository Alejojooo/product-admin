package com.enpresa.productadmin;

import com.enpresa.productadmin.controlador.AdministrarProductosController;
import com.enpresa.productadmin.controlador.AdministrarUsuariosController;
import com.enpresa.productadmin.controlador.InicioDeSesionController;
import com.enpresa.productadmin.controlador.MenuPrincipalController;
import com.enpresa.productadmin.modelo.dao.ProductoDAO;
import com.enpresa.productadmin.modelo.dao.UsuarioDAO;
import com.enpresa.productadmin.modelo.Usuario;
import com.enpresa.productadmin.vistas.gui.AdministrarProductosVista;
import com.enpresa.productadmin.vistas.gui.AdministrarUsuariosVista;
import com.enpresa.productadmin.vistas.gui.IniciarSesionVista;
import com.enpresa.productadmin.vistas.gui.MenuPrincipalVista;

/**
 *
 * @author Alejo
 */
public class ProductAdmin {

    public static Usuario usuarioActivo;

    public static void main(String[] args) {
        goToIniciarSesion();
    }
    
    // Podría hacer los métodos públicos y estáticos también
    public static void goToIniciarSesion() {
        UsuarioDAO modelo = new UsuarioDAO();
        IniciarSesionVista vista = new IniciarSesionVista();
        InicioDeSesionController controller = new InicioDeSesionController(modelo, vista);
    }
    
    public static void goToMenuPrincipal() {
        MenuPrincipalVista vista = new MenuPrincipalVista();
        MenuPrincipalController controller = new MenuPrincipalController(vista);
    }

    public static void goToAdministrarProductos() {
        ProductoDAO modelo = new ProductoDAO();
        AdministrarProductosVista vista = new AdministrarProductosVista();
        AdministrarProductosController controller = new AdministrarProductosController(modelo, vista);
    }

    public static void goToRegistrarCompraVenta() {
        
    }

    public static void goToAdministrarUsuarios() {
        UsuarioDAO modelo = new UsuarioDAO();
        AdministrarUsuariosVista vista = new AdministrarUsuariosVista();
        AdministrarUsuariosController controller = new AdministrarUsuariosController(modelo, vista);
    }

    public static void goToConsultarBitacoraTransacciones() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void goToConsultarBitacoraAcceso() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void goToReporteInventario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void goToReporteGastosGanacias() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void goToAcercaDe() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}