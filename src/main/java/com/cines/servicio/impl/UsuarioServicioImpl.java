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
        // --- IMPRIME ESTO EN CONSOLA ---
        String claveMD5Ingresada = convertirMD5(claveUsuario);
        System.out.println("Usuario buscado: " + nombreUsuario);
        System.out.println("Clave en BD:     " + (usuario != null ? usuario.getClave() : "null"));
        System.out.println("Clave generada:  " + claveMD5Ingresada);
    // ---------------------------------
        // Verificar si el usuario existe y si la clave coincide
        // adicionalmente verificar que el ID del usuario no sea cero y que el nombre y la clave no sean nulos
        if (usuario != null && usuario.getIdUsuario() != 0) {
            // Comparar la clave ingresada (convertida a MD5) con la clave almacenada en la base de datos
            String claveMD5 = convertirMD5(claveUsuario);
            
            return usuario.getClave().equalsIgnoreCase(claveMD5);
        }
        // Si el usuario no existe o la clave no coincide, se retorna FALSE, indicando que el usuario no es válido
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