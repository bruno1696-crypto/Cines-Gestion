// Bruno Mercado
package com.cines.dao;

import com.cines.control.ConectarBase;
import com.cines.modelo.Funcion;
import java.sql.*;
import java.util.ArrayList;

// Persistencia de la entidad Funcion contra la tabla 'funcion' de MySQL
public class FuncionDAO {

    // Inserta una nueva funcion en la base de datos
    public boolean registrarFuncion(Funcion funcion) {
        String sql = "INSERT INTO funcion (pelicula_id, sala_id, horario, precio) VALUES (?, ?, ?, ?)";
        ConectarBase conectarBase = new ConectarBase();

        try (Connection conexion = conectarBase.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, funcion.getPeliculaId());
            ps.setInt(2, funcion.getSalaId());
            ps.setTimestamp(3, Timestamp.valueOf(funcion.getHorario()));
            ps.setDouble(4, funcion.getPrecio());

            if (ps.executeUpdate() == 0) {
                return false;
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    funcion.setId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error al registrar funcion: " + e.getMessage());
            return false;
        }
    }

    // Devuelve el listado completo de funciones
    public ArrayList<Funcion> listarFunciones() {
        ArrayList<Funcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcion ORDER BY id";
        ConectarBase conectarBase = new ConectarBase();

        try (Connection conexion = conectarBase.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearFuncion(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar funciones: " + e.getMessage());
        }
        return lista;
    }

    // Busca una funcion por su identificador y la devuelve, o null si no existe
    public Funcion obtenerFuncionPorId(int id) {
        String sql = "SELECT * FROM funcion WHERE id = ?";
        ConectarBase conectarBase = new ConectarBase();

        try (Connection conexion = conectarBase.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearFuncion(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar funcion por id: " + e.getMessage());
        }
        return null;
    }

    // Actualiza los datos de una funcion existente
    public boolean actualizarFuncion(Funcion funcion) {
        String sql = "UPDATE funcion SET pelicula_id = ?, sala_id = ?, horario = ?, precio = ? WHERE id = ?";
        ConectarBase conectarBase = new ConectarBase();

        try (Connection conexion = conectarBase.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, funcion.getPeliculaId());
            ps.setInt(2, funcion.getSalaId());
            ps.setTimestamp(3, Timestamp.valueOf(funcion.getHorario()));
            ps.setDouble(4, funcion.getPrecio());
            ps.setInt(5, funcion.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar funcion: " + e.getMessage());
            return false;
        }
    }

    // Elimina una funcion por su identificador
    public boolean eliminarFuncion(int id) {
        String sql = "DELETE FROM funcion WHERE id = ?";
        ConectarBase conectarBase = new ConectarBase();

        try (Connection conexion = conectarBase.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar funcion: " + e.getMessage());
            return false;
        }
    }

    // Arma un objeto Funcion a partir de una fila del ResultSet
    private Funcion mapearFuncion(ResultSet rs) throws SQLException {
        Funcion f = new Funcion();
        f.setId(rs.getInt("id"));
        f.setPeliculaId(rs.getInt("pelicula_id"));
        f.setSalaId(rs.getInt("sala_id"));
        f.setHorario(rs.getTimestamp("horario").toLocalDateTime());
        f.setPrecio(rs.getDouble("precio"));
        return f;
    }
}
