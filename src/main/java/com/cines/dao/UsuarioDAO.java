// Bruno Mercado

package com.cines.dao;

import com.cines.control.ConectarBase;
import com.cines.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario leerUsuario(String nombreUsuario) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";
        
        ConectarBase conectarBase = new ConectarBase();

        try (Connection conexion = conectarBase.conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            
            preparedStatement.setString(1, nombreUsuario);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(resultSet.getInt("idUsuario"));
                    usuario.setUsuario(resultSet.getString("usuario"));
                    usuario.setClave(resultSet.getString("clave"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error de SQL al leer el usuario: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error general al leer el usuario: " + e.getMessage());
            e.printStackTrace();
        }            
        
        return usuario; 
    }
}