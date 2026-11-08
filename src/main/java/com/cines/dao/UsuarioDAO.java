// Bruno Mercado

package com.cines.dao;

import com.cines.control.ConectarBase;
import com.cines.modelo.Usuario;

public class UsuarioDAO {

    public Usuario leerUsuario(String nombreUsuario) {
        
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";
        
        try {
            var conexion = new ConectarBase().conectar();
            var preparedStatement = conexion.prepareStatement(sql);
            
            preparedStatement.setString(1, nombreUsuario);
            var resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                int idUsuario = resultSet.getInt("idUsuario");
                String claveUsuario = resultSet.getString("clave");
                usuario.setIdUsuario(idUsuario);
                usuario.setUsuario(nombreUsuario);
                usuario.setClave(claveUsuario);

            }
        } catch (Exception e) {
            e.printStackTrace();
            }             
        return usuario; 
        }
    }