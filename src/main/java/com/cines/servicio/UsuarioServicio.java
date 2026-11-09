// Bruno Mercado

package com.cines.servicio;

public interface UsuarioServicio {

    // Valida las credenciales de un usuario contra la base de datos
    boolean validarUsuario(String usuario, String clavePlana);
}
