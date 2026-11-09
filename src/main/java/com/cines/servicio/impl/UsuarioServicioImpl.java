// Bruno Mercado

package com.cines.servicio.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import com.cines.dao.UsuarioDAO;
import com.cines.modelo.Usuario;
import com.cines.servicio.UsuarioServicio;

// Implementacion del servicio de usuarios, valida las credenciales contra el DAO
public class UsuarioServicioImpl implements UsuarioServicio {

    // Verifica que el usuario exista y que la clave coincida con el hash MD5 almacenado
    @Override
    public boolean validarUsuario(String nombreUsuario, String claveUsuario) {
        UsuarioDAO usuariosDAO = new UsuarioDAO();
        Usuario usuario = usuariosDAO.leerUsuario(nombreUsuario);

        if (usuario.getIdUsuario() != 0 && usuario.getUsuario() != null && usuario.getClave() != null) {
            String claveMD5 = convertirMD5(claveUsuario);
            return usuario.getClave().equalsIgnoreCase(claveMD5);
        }

        return false;
    }

    // Convierte un texto plano a su hash MD5 en formato hexadecimal
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
