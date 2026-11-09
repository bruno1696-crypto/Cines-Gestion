// Bruno Mercado

package com.cines.servicio.impl;

import java.util.ArrayList;

import com.cines.dao.CineDAO;
import com.cines.dao.SalaDAO;
import com.cines.modelo.Cine;
import com.cines.servicio.CineServicio;

public class CineServicioImpl implements CineServicio {

    private final CineDAO cineDAO = new CineDAO();
    private final SalaDAO salaDAO = new SalaDAO();

    @Override
    public boolean agregarCine(Cine cine, int capacidadPorSala) {
        if (!cineDAO.insertarCine(cine)) {
            return false;
        }

        if (cine.getSalas() > 0) {
            int capacidad = capacidadPorSala > 0 ? capacidadPorSala : SalaDAO.CAPACIDAD_POR_DEFECTO;
            salaDAO.crearSalasParaCine(cine.getId(), cine.getSalas(), capacidad);
        }
        return true;
    }

    @Override
    public ArrayList<Cine> listarCines() {
        return cineDAO.obtenerCines();
    }

    @Override
    public ArrayList<Cine> buscarPorNombre(String nombre) {
        ArrayList<Cine> coincidencias = new ArrayList<>();
        String textoBuscado = nombre.toLowerCase();

        for (Cine cine : cineDAO.obtenerCines()) {
            if (cine.getNombre().toLowerCase().contains(textoBuscado)) {
                coincidencias.add(cine);
            }
        }
        return coincidencias;
    }

    @Override
    public boolean actualizarCine(Cine cine) {
        return cineDAO.actualizarCine(cine);
    }

    @Override
    public boolean eliminarCine(int id) {
        return cineDAO.eliminarCine(id);
    }

}
