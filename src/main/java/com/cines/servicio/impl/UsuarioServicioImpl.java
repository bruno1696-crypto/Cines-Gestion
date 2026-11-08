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
    
    System.out.println(">>> SERVICIO - Usuario buscado: " + nombreUsuario);
    System.out.println(">>> SERVICIO - Clave en BD (Largo " + (usuario != null ? usuario.getClave().length() : 0) + "): [" + (usuario != null ? usuario.getClave() : "null") + "]");
    
    String claveMD5Ingresada = convertirMD5(claveUsuario);
    System.out.println(">>> SERVICIO - Hash generado de '" + claveUsuario + "': [" + claveMD5Ingresada + "]");

    if (usuario != null && usuario.getIdUsuario() != 0) {
        boolean coincide = usuario.getClave().trim().equalsIgnoreCase(claveMD5Ingresada.trim());
        System.out.println(">>> SERVICIO - ¿Coinciden?: " + coincide);
        return coincide;
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