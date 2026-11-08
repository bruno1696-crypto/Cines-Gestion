// Bruno Mercado

package com.cines.servicio.impl;

import com.cines.dao.UsuarioDAO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.cines.modelo.Usuario;
import com.cines.servicio.UsuarioServicio;

public class UsuarioServicioImpl implements UsuarioServicio {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public boolean validarUsuario(String usuario, String clavePlana) {
        String claveMd5 = convertirA_MD5(clavePlana);
        return usuarioDAO.validarUsuario(usuario, claveMd5);
    }

    // Método auxiliar para encriptar texto a MD5
    private String convertirA_MD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al cifrar la contraseña en MD5", e);
        }
    }
}