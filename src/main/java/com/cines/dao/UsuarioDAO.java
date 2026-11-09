// Bruno Mercado

package com.cines.dao;

import com.cines.control.ConectarBase;
import com.cines.modelo.Usuario;

public class UsuarioDAO {

    // Busca un usuario por nombre para el login.
    public Usuario leerUsuario(String nombreUsuario) {

        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";

        try (var conexion = new ConectarBase().conectar();
             var preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setString(1, nombreUsuario);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    usuario.setIdUsuario(resultSet.getInt("idUsuario"));
                    usuario.setUsuario(nombreUsuario);
                    usuario.setClave(resultSet.getString("clave"));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer el usuario: " + e.getMessage());
        }
        return usuario;
    }
}
