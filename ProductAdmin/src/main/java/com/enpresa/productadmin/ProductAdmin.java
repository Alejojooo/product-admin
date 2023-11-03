package com.enpresa.productadmin;

import com.enpresa.productadmin.controlador.AdministrarProductosController;
import com.enpresa.productadmin.controlador.InicioDeSesionController;
import com.enpresa.productadmin.controlador.MenuPrincipalController;
import com.enpresa.productadmin.dao.ProductoDAO;
import com.enpresa.productadmin.dao.UsuarioDAO;
import com.enpresa.productadmin.modelo.Usuario;
import com.enpresa.productadmin.vistas.AdministrarProductos;
import com.enpresa.productadmin.vistas.IniciarSesion;
import com.enpresa.productadmin.vistas.MenuPrincipal;

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
        IniciarSesion vista = new IniciarSesion();
        InicioDeSesionController controller = new InicioDeSesionController(modelo, vista);
    }
    
    public static void goToMenuPrincipal() {
        MenuPrincipal vista = new MenuPrincipal();
        MenuPrincipalController controller = new MenuPrincipalController(vista);
    }

    public static void goToAdministrarProductos() {
        ProductoDAO modelo = new ProductoDAO();
        AdministrarProductos vista = new AdministrarProductos();
        AdministrarProductosController controller = new AdministrarProductosController(modelo, vista);
    }

    public static void goToRegistrarCompraVenta() {
        
    }

    public static void goToAdministrarUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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