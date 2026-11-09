// Bruno Mercado

package com.cines.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cines.control.ConectarBase;
import com.cines.modelo.Cine;

public class CineDAO {

    public boolean insertarCine(Cine cine) {
        String sql = "INSERT INTO cine (nombre, salas, abierto, fechaInauguracion) VALUES (?, ?, ?, ?)";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, cine.getNombre());
            preparedStatement.setInt(2, cine.getSalas());
            preparedStatement.setBoolean(3, cine.isAbierto());
            // LocalDate no lo entiende JDBC directo, hay que pasarlo a java.sql.Date
            preparedStatement.setDate(4, java.sql.Date.valueOf(cine.getFechaInauguracion()));

            if (preparedStatement.executeUpdate() == 0) {
                return false;
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cine.setId(generatedKeys.getInt(1));
                }
            }
            return true;

        } catch (SQLException e) {
            System.err.println("No se pudo guardar el cine en la base de datos: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Cine> obtenerCines() {
        ArrayList<Cine> listaCines = new ArrayList<>();
        String sql = "SELECT id, nombre, salas, abierto, fechaInauguracion FROM cine ORDER BY id";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                listaCines.add(mapearCine(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron leer los cines de la base de datos: " + e.getMessage());
        }
        return listaCines;
    }

    public Cine obtenerCinePorId(int id) {
        String sql = "SELECT id, nombre, salas, abierto, fechaInauguracion FROM cine WHERE id = ?";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearCine(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("No se pudo buscar el cine por id: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarCine(Cine cine) {
        String sql = "UPDATE cine SET nombre = ?, salas = ?, abierto = ?, fechaInauguracion = ? WHERE id = ?";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setString(1, cine.getNombre());
            preparedStatement.setInt(2, cine.getSalas());
            preparedStatement.setBoolean(3, cine.isAbierto());
            preparedStatement.setDate(4, java.sql.Date.valueOf(cine.getFechaInauguracion()));
            preparedStatement.setInt(5, cine.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("No se pudo actualizar el cine: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCine(int id) {
        String sql = "DELETE FROM cine WHERE id = ?";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("No se pudo eliminar el cine: " + e.getMessage());
            return false;
        }
    }

    private Cine mapearCine(ResultSet resultSet) throws SQLException {
        Cine cine = new Cine();
        cine.setId(resultSet.getInt("id"));
        cine.setNombre(resultSet.getString("nombre"));
        cine.setSalas(resultSet.getInt("salas"));
        cine.setAbierto(resultSet.getBoolean("abierto"));
        cine.setFechaInauguracion(resultSet.getDate("fechaInauguracion").toLocalDate());
        return cine;
    }
}
