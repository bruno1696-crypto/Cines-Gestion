// Bruno Mercado
package com.cines.servicio.impl;

import com.cines.dao.SalaDAO;
import com.cines.modelo.Sala;
import com.cines.servicio.SalaServicio;
import java.util.ArrayList;

// Implementacion del servicio de salas, delega la persistencia en SalaDAO
public class SalaServicioImpl implements SalaServicio {

    private final SalaDAO salaDAO = new SalaDAO();

    // Registra una nueva sala
    @Override
    public boolean agregarSala(Sala sala) {
        return salaDAO.insertarSala(sala);
    }

    // Devuelve todas las salas registradas
    @Override
    public ArrayList<Sala> listarSalas() {
        return salaDAO.listarSalas();
    }

    // Devuelve las salas de un cine determinado
    @Override
    public ArrayList<Sala> listarSalasPorCine(int cineId) {
        return salaDAO.listarSalasPorCine(cineId);
    }

    // Busca una sala por su identificador
    @Override
    public Sala buscarSala(int id) {
        return salaDAO.obtenerSalaPorId(id);
    }

    // Actualiza una sala existente
    @Override
    public boolean actualizarSala(Sala sala) {
        return salaDAO.actualizarSala(sala);
    }

    // Elimina una sala por su identificador
    @Override
    public boolean eliminarSala(int id) {
        return salaDAO.eliminarSala(id);
    }

    // Crea varias salas numeradas para un cine
    @Override
    public boolean crearSalasParaCine(int cineId, int cantidad, int capacidad) {
        return salaDAO.crearSalasParaCine(cineId, cantidad, capacidad);
    }

    // Completa las salas faltantes de los cines ya registrados
    @Override
    public int generarSalasFaltantes() {
        return salaDAO.generarSalasFaltantes();
    }
}
