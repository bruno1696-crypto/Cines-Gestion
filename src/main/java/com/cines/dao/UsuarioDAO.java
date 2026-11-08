// Bruno Mercado

package com.cines.dao;

import com.cines.control.ConectarBase;
import com.cines.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean validarUsuario(String nombreUsuario, String claveMd5) {
        boolean esValido = false;
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ?";
        ConectarBase conexionDB = new ConectarBase();

        try (Connection con = conexionDB.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, nombreUsuario);
            pstmt.setString(2, claveMd5);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    esValido = true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al validar el usuario: " + e.getMessage());
        }

        return esValido;
    }


    // Método para registrar un nuevo usuario
    public boolean registrarUsuario(Usuario usuario) {
        boolean registrado = false;
        String sql = "INSERT INTO usuarios (usuario, clave) VALUES (?, ?)";

        ConectarBase conexionDB = new ConectarBase();

        try (Connection con = conexionDB.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getUsuario());
            pstmt.setString(2, usuario.getClave());

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                registrado = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar el usuario: " + e.getMessage());
        }

        return registrado;
    }
}