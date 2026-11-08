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

    

    @Override
    public boolean validarUsuario(String nombreUsuario, String claveUsuario) {
        UsuarioDAO usuariosDAO = new UsuarioDAO();
        Usuario usuario = usuariosDAO.leerUsuario(nombreUsuario);
        
        // Verificar si el usuario existe y si la clave coincide
        // adicionalmente verificar que el ID del usuario no sea cero y que el nombre y la clave no sean nulos
            if (usuario.getIdUsuario() != 0 && usuario.getUsuario() != null && usuario.getClave() != null) {
                // Comparar la clave ingresada (convertida a MD5) con la clave almacenada en la base de datos
                String claveMD5 = convertirMD5(claveUsuario);
            
                return usuario.getClave().equalsIgnoreCase(claveMD5);
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