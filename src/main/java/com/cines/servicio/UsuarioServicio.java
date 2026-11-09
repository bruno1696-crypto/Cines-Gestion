// Bruno Mercado

package com.cines.servicio;

public interface UsuarioServicio {

    // Valida las credenciales contra la base.
    boolean validarUsuario(String usuario, String clavePlana);
}
