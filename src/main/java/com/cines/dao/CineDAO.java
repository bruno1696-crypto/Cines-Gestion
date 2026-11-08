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

// PreparedStatement en vez de armar el SQL con concatenacion de Strings, evita quilombos 
// si el nombre del cine tiene algun caracter raro
public class CineDAO {

    public void insertarCine(Cine cine) {
        Connection conexion = obtenerConexion();
        
        if (conexion == null) { 
            System.out.println("No se guardo el cine en la base de datos (sin conexion a MySQL).");
            return;
        }

        String sql = "INSERT INTO cine (nombre, salas, abierto, fechaInauguracion) VALUES (?, ?, ?, ?)";

        try (conexion; PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setString(1, cine.getNombre());
            preparedStatement.setInt(2, cine.getSalas());
            preparedStatement.setBoolean(3, cine.isAbierto());
            // LocalDate no lo entiende JDBC directo, hay que pasarlo a java.sql.Date
            preparedStatement.setDate(4, java.sql.Date.valueOf(cine.getFechaInauguracion()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("No se pudo guardar el cine en la base de datos: " + e.getMessage());
        }
    }

    public ArrayList<Cine> obtenerCines() {
        ArrayList<Cine> listaCines = new ArrayList<>();
        Connection conexion = obtenerConexion();
        
        if (conexion == null) {
            return listaCines;
        }

        String sql = "SELECT * FROM cine";

         try (conexion;
             Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Cine cine = new Cine();
                cine.setNombre(resultSet.getString("nombre"));
                cine.setSalas(resultSet.getInt("salas"));
                cine.setAbierto(resultSet.getBoolean("abierto"));
                cine.setFechaInauguracion(resultSet.getDate("fechaInauguracion").toLocalDate());

                listaCines.add(cine);
            }
        } catch (SQLException e) {
            System.out.println("No se pudieron leer los cines de la base de datos: " + e.getMessage());
        }
        return listaCines;
    }

    // metodo privado para no repetir codigo de conexion en cada metodo
    private Connection obtenerConexion() {
        try {
            return new ConectarBase().conectar();
        } catch (Exception e) {
            return null;
        }
    }
}
