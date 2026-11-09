// Bruno Mercado
package com.cines.dao;

import com.cines.control.ConectarBase;
import com.cines.modelo.Sala;
import java.sql.*;
import java.util.ArrayList;

// Persistencia de la entidad Sala contra la tabla 'sala' de MySQL
public class SalaDAO {

    // Capacidad usada cuando hay que generar salas sin un dato explicito
    public static final int CAPACIDAD_POR_DEFECTO = 100;

    // Inserta una nueva sala en la base de datos
    public boolean insertarSala(Sala sala) {
        String sql = "INSERT INTO sala (cine_id, nombre, capacidad) VALUES (?, ?, ?)";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, sala.getCineId());
            ps.setString(2, sala.getNombre());
            ps.setInt(3, sala.getCapacidad());

            if (ps.executeUpdate() == 0) {
                return false;
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    sala.setId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar sala: " + e.getMessage());
            return false;
        }
    }

    // Devuelve el listado completo de salas
    public ArrayList<Sala> listarSalas() {
        ArrayList<Sala> lista = new ArrayList<>();
        String sql = "SELECT * FROM sala ORDER BY id";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearSala(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar salas: " + e.getMessage());
        }
        return lista;
    }

    // Devuelve las salas que pertenecen a un cine determinado
    public ArrayList<Sala> listarSalasPorCine(int cineId) {
        ArrayList<Sala> lista = new ArrayList<>();
        String sql = "SELECT * FROM sala WHERE cine_id = ? ORDER BY id";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, cineId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearSala(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar salas por cine: " + e.getMessage());
        }
        return lista;
    }

    // Busca una sala por su identificador y la devuelve, o null si no existe
    public Sala obtenerSalaPorId(int id) {
        String sql = "SELECT * FROM sala WHERE id = ?";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearSala(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar sala por id: " + e.getMessage());
        }
        return null;
    }

    // Actualiza los datos de una sala existente
    public boolean actualizarSala(Sala sala) {
        String sql = "UPDATE sala SET cine_id = ?, nombre = ?, capacidad = ? WHERE id = ?";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, sala.getCineId());
            ps.setString(2, sala.getNombre());
            ps.setInt(3, sala.getCapacidad());
            ps.setInt(4, sala.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar sala: " + e.getMessage());
            return false;
        }
    }

    // Elimina una sala por su identificador
    public boolean eliminarSala(int id) {
        String sql = "DELETE FROM sala WHERE id = ?";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar sala: " + e.getMessage());
            return false;
        }
    }

    // Crea 'cantidad' salas para un cine, numeradas desde 1
    public boolean crearSalasParaCine(int cineId, int cantidad, int capacidad) {
        return crearSalas(cineId, 1, cantidad, capacidad) == cantidad;
    }

    // Crea salas numeradas a partir de 'desdeNumero' y devuelve cuantas se crearon
    private int crearSalas(int cineId, int desdeNumero, int cantidad, int capacidad) {
        if (cantidad <= 0) {
            return 0;
        }

        String sql = "INSERT INTO sala (cine_id, nombre, capacidad) VALUES (?, ?, ?)";

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            for (int i = 0; i < cantidad; i++) {
                ps.setInt(1, cineId);
                ps.setString(2, "Sala " + (desdeNumero + i));
                ps.setInt(3, capacidad);
                ps.addBatch();
            }

            int[] resultados = ps.executeBatch();
            int creadas = 0;
            for (int resultado : resultados) {
                if (resultado != Statement.EXECUTE_FAILED) {
                    creadas++;
                }
            }
            return creadas;

        } catch (SQLException e) {
            System.err.println("Error al crear salas del cine " + cineId + ": " + e.getMessage());
            return 0;
        }
    }

    // Completa las salas de los cines que tienen menos filas que la cantidad indicada en cine.salas
    public int generarSalasFaltantes() {
        String sql = "SELECT c.id, c.salas, COUNT(s.id) AS cargadas "
                + "FROM cine c LEFT JOIN sala s ON s.cine_id = c.id "
                + "GROUP BY c.id, c.salas HAVING COUNT(s.id) < c.salas";

        ArrayList<int[]> pendientes = new ArrayList<>();

        try (Connection conexion = new ConectarBase().conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                pendientes.add(new int[]{
                        rs.getInt("id"),
                        rs.getInt("salas"),
                        rs.getInt("cargadas")
                });
            }
        } catch (SQLException e) {
            System.err.println("Error al calcular las salas faltantes: " + e.getMessage());
            return 0;
        }

        int creadas = 0;
        for (int[] pendiente : pendientes) {
            int cineId = pendiente[0];
            int totalEsperadas = pendiente[1];
            int cargadas = pendiente[2];
            creadas += crearSalas(cineId, cargadas + 1, totalEsperadas - cargadas, CAPACIDAD_POR_DEFECTO);
        }
        return creadas;
    }

    // Arma un objeto Sala a partir de una fila del ResultSet
    private Sala mapearSala(ResultSet rs) throws SQLException {
        Sala sala = new Sala();
        sala.setId(rs.getInt("id"));
        sala.setCineId(rs.getInt("cine_id"));
        sala.setNombre(rs.getString("nombre"));
        sala.setCapacidad(rs.getInt("capacidad"));
        return sala;
    }
}
