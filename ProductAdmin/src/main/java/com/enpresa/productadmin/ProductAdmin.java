package com.enpresa.productadmin;

import com.enpresa.productadmin.modelo.Rol;
import com.enpresa.productadmin.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejo
 */
public class ProductAdmin {
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        List<Rol> roles = generarRoles();
        List<Usuario> usuarios = generarUsuarios(roles);
        
        System.out.println(roles);
        System.out.println(usuarios);
    }
    
    private static List<Rol> generarRoles() {
        Rol rolAdmin = new Rol(1, "Administrador", "Todos los permisos (Configuración)");
        Rol rolGerente = new Rol(2, "Gerente", "Todos los permisos");
        Rol rolEmpleado = new Rol(3, "Empleado", "Permisos reducidos (Registrar Compra/Venta)");
        
        List<Rol> roles = new ArrayList<>();
        roles.add(rolAdmin);
        roles.add(rolGerente);
        roles.add(rolEmpleado);
        
        return roles;
    }
    
    private static List<Usuario> generarUsuarios(List<Rol> roles) {
        Usuario admin = new Usuario(1, "admin", "admin", "admin", "admin", roles.get(0));
        
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(admin);
        
        return usuarios;
    }
}
