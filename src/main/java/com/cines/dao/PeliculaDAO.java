// Bruno Mercado

package com.cines.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cines.control.ConectarBase;
import com.cines.modelo.Pelicula;

public class PeliculaDAO {

    // Inserta una película y recupera su id.
    public boolean insertarPelicula(Pelicula pelicula) {
        String sql = "INSERT INTO pelicula (titulo, duracion, disponible, fechaEstreno) VALUES (?, ?, ?, ?)";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, pelicula.getTitulo());
            preparedStatement.setInt(2, pelicula.getDuracion());
            preparedStatement.setBoolean(3, pelicula.isDisponible());
            preparedStatement.setDate(4, java.sql.Date.valueOf(pelicula.getFechaEstreno()));

            if (preparedStatement.executeUpdate() == 0) {
                return false;
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pelicula.setId(generatedKeys.getInt(1));
                }
            }
            return true;

        } catch (SQLException e) {
            System.err.println("No se pudo guardar la película en la base de datos: " + e.getMessage());
            return false;
        }
    }

    // Devuelve todas las películas.
    public ArrayList<Pelicula> obtenerPeliculas() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        String sql = "SELECT id, titulo, duracion, disponible, fechaEstreno FROM pelicula ORDER BY id";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                listaPeliculas.add(mapearPelicula(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron leer las películas de la base de datos: " + e.getMessage());
        }
        return listaPeliculas;
    }

    // Busca una película por id.
    public Pelicula obtenerPeliculaPorId(int id) {
        String sql = "SELECT id, titulo, duracion, disponible, fechaEstreno FROM pelicula WHERE id = ?";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearPelicula(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("No se pudo buscar la película por id: " + e.getMessage());
        }
        return null;
    }

    // Actualiza una película existente.
    public boolean actualizarPelicula(Pelicula pelicula) {
        String sql = "UPDATE pelicula SET titulo = ?, duracion = ?, disponible = ?, fechaEstreno = ? WHERE id = ?";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setString(1, pelicula.getTitulo());
            preparedStatement.setInt(2, pelicula.getDuracion());
            preparedStatement.setBoolean(3, pelicula.isDisponible());
            preparedStatement.setDate(4, java.sql.Date.valueOf(pelicula.getFechaEstreno()));
            preparedStatement.setInt(5, pelicula.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("No se pudo actualizar la película: " + e.getMessage());
            return false;
        }
    }

    // Elimina una película por id.
    public boolean eliminarPelicula(int id) {
        String sql = "DELETE FROM pelicula WHERE id = ?";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("No se pudo eliminar la película: " + e.getMessage());
            return false;
        }
    }

    // Arma una Pelicula a partir de la fila actual.
    private Pelicula mapearPelicula(ResultSet resultSet) throws SQLException {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(resultSet.getInt("id"));
        pelicula.setTitulo(resultSet.getString("titulo"));
        pelicula.setDuracion(resultSet.getInt("duracion"));
        pelicula.setDisponible(resultSet.getBoolean("disponible"));
        pelicula.setFechaEstreno(resultSet.getDate("fechaEstreno").toLocalDate());
        return pelicula;
    }
}
