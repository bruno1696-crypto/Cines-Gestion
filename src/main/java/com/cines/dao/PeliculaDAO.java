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

// mismo esquema que CineDAO pero apuntando a la tabla 'pelicula'
public class PeliculaDAO {

    public void insertarPelicula(Pelicula pelicula) {
        Connection conexion = obtenerConexion();

        if (conexion == null) {
            System.out.println("No se guardo la pelicula en la base de datos (sin conexion a MySQL).");
            return;
        }

        String sql = "INSERT INTO pelicula (titulo, duracion, disponible, fechaEstreno) VALUES (?, ?, ?, ?)";

        try (conexion; PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setString(1, pelicula.getTitulo());
            preparedStatement.setInt(2, pelicula.getDuracion());
            preparedStatement.setBoolean(3, pelicula.isDisponible());
            preparedStatement.setDate(4, java.sql.Date.valueOf(pelicula.getFechaEstreno()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("No se pudo guardar la pelicula en la base de datos: " + e.getMessage());
        }
    }

    public ArrayList<Pelicula> obtenerPeliculas() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        Connection conexion = obtenerConexion();

        if (conexion == null) {
            return listaPeliculas;
        }
        
        String sql = "SELECT * FROM pelicula";

        try (conexion;
             Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setTitulo(resultSet.getString("titulo"));
                pelicula.setDuracion(resultSet.getInt("duracion"));
                pelicula.setDisponible(resultSet.getBoolean("disponible"));
                pelicula.setFechaEstreno(resultSet.getDate("fechaEstreno").toLocalDate());

                listaPeliculas.add(pelicula);
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron leer las peliculas de la base de datos: " + e.getMessage());
        }
        return listaPeliculas;
    }

    private Connection obtenerConexion() {
        try {
            return new ConectarBase().conectar();
        } catch (Exception e) {
            return null;
        }
    }
}
