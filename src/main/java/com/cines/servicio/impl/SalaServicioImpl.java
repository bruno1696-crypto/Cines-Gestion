// Bruno Mercado

package com.cines.servicio.impl;

import java.util.ArrayList;

import com.cines.dao.SalaDAO;
import com.cines.modelo.Sala;
import com.cines.servicio.SalaServicio;

public class SalaServicioImpl implements SalaServicio {

    private final SalaDAO salaDAO = new SalaDAO();

    @Override
    public boolean agregarSala(Sala sala) {
        return salaDAO.insertarSala(sala);
    }

    @Override
    public ArrayList<Sala> listarSalas() {
        return salaDAO.listarSalas();
    }

    @Override
    public ArrayList<Sala> listarSalasPorCine(int cineId) {
        return salaDAO.listarSalasPorCine(cineId);
    }

    @Override
    public Sala buscarSala(int id) {
        return salaDAO.obtenerSalaPorId(id);
    }

    @Override
    public boolean actualizarSala(Sala sala) {
        return salaDAO.actualizarSala(sala);
    }

    @Override
    public boolean eliminarSala(int id) {
        return salaDAO.eliminarSala(id);
    }

    @Override
    public boolean crearSalasParaCine(int cineId, int cantidad, int capacidad) {
        return salaDAO.crearSalasParaCine(cineId, cantidad, capacidad);
    }

    @Override
    public int generarSalasFaltantes() {
        return salaDAO.generarSalasFaltantes();
    }
}
