// Bruno Mercado

package com.cines.servicio.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;


import com.cines.dao.UsuarioDAO;
import com.cines.modelo.Usuario;
import com.cines.servicio.UsuarioServicio;

public class UsuarioServicioImpl implements UsuarioServicio {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public boolean validarUsuario(String nombreUsuario, String claveUsuario) {
    Usuario usuario = usuarioDAO.leerUsuario(nombreUsuario);
    
    // Verificamos que el usuario exista
    if (usuario != null && usuario.getIdUsuario() != 0) {
        // Convertimos la clave ingresada (ej: cines123) a MD5
        String claveMD5Ingresada = convertirMD5(claveUsuario);
        
        // Comparamos el hash generado con el hash que está en la base de datos
        return usuario.getClave().equalsIgnoreCase(claveMD5Ingresada);
    }
    
    return false;
}

    private String convertirMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar en MD5", e);
        }
    }

}