// Bruno Mercado
package com.cines.dao;

import com.cines.control.ConectarBase;
import com.cines.modelo.Sala;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {

    public List<Sala> listarSalasPorCine(int cineId) {
        List<Sala> lista = new ArrayList<>();
        String sql = "SELECT * FROM sala WHERE cine_id = ?";
        ConectarBase conectarBase = new ConectarBase();

        try (Connection conexion = conectarBase.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setInt(1, cineId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sala sala = new Sala();
                    sala.setId(rs.getInt("id"));
                    sala.setCineId(rs.getInt("cine_id"));
                    sala.setNombre(rs.getString("nombre"));
                    sala.setCapacidad(rs.getInt("capacidad"));
                    lista.add(sala);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar salas por cine: " + e.getMessage());
        }
        return lista;
    }
}