// Bruno Mercado

package com.cines.servicio.impl;

import java.util.ArrayList;

import com.cines.dao.SalaDAO;
import com.cines.modelo.Sala;
import com.cines.servicio.SalaServicio;

public class SalaServicioImpl implements SalaServicio {

    private final SalaDAO salaDAO = new SalaDAO();

    // Agrega una sala nueva.
    @Override
    public boolean agregarSala(Sala sala) {
        return salaDAO.insertarSala(sala);
    }

    // Lista todas las salas.
    @Override
    public ArrayList<Sala> listarSalas() {
        return salaDAO.listarSalas();
    }

    // Lista las salas de un cine.
    @Override
    public ArrayList<Sala> listarSalasPorCine(int cineId) {
        return salaDAO.listarSalasPorCine(cineId);
    }

    // Busca una sala por id.
    @Override
    public Sala buscarSala(int id) {
        return salaDAO.obtenerSalaPorId(id);
    }

    // Actualiza la sala.
    @Override
    public boolean actualizarSala(Sala sala) {
        return salaDAO.actualizarSala(sala);
    }

    // Elimina la sala.
    @Override
    public boolean eliminarSala(int id) {
        return salaDAO.eliminarSala(id);
    }

    // Crea las salas iniciales.
    @Override
    public boolean crearSalasParaCine(int cineId, int cantidad, int capacidad) {
        return salaDAO.crearSalasParaCine(cineId, cantidad, capacidad);
    }

    // Completa las salas faltantes.
    @Override
    public int generarSalasFaltantes() {
        return salaDAO.generarSalasFaltantes();
    }
}
