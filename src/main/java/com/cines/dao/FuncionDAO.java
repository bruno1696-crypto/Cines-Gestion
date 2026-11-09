// Bruno Mercado
package com.cines.dao;

import com.cines.control.ConectarBase;
import com.cines.modelo.Funcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionDAO {

    public boolean registrarFuncion(Funcion funcion) {
        String sql = "INSERT INTO funcion (pelicula_id, sala_id, horario, precio) VALUES (?, ?, ?, ?)";
        ConectarBase conectarBase = new ConectarBase();

        try (Connection conexion = conectarBase.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setInt(1, funcion.getPeliculaId());
            ps.setInt(2, funcion.getSalaId());
            ps.setTimestamp(3, Timestamp.valueOf(funcion.getHorario()));
            ps.setDouble(4, funcion.getPrecio());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al registrar función: " + e.getMessage());
            return false;
        }
    }

    public List<Funcion> listarFunciones() {
        List<Funcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcion";
        ConectarBase conectarBase = new ConectarBase();

        try (Connection conexion = conectarBase.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Funcion f = new Funcion();
                f.setId(rs.getInt("id"));
                f.setPeliculaId(rs.getInt("pelicula_id"));
                f.setSalaId(rs.getInt("sala_id"));
                f.setHorario(rs.getTimestamp("horario").toLocalDateTime());
                f.setPrecio(rs.getDouble("precio"));
                lista.add(f);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar funciones: " + e.getMessage());
        }
        return lista;
    }
}