// Bruno Mercado

package com.cines.servicio.impl;

import java.util.ArrayList;

import com.cines.dao.CineDAO;
import com.cines.dao.SalaDAO;
import com.cines.modelo.Cine;
import com.cines.servicio.CineServicio;

// El servicio no mantiene listas en memoria: todas las operaciones
// se delegan al DAO, que trabaja directamente contra MySQL.
public class CineServicioImpl implements CineServicio {

    private final CineDAO cineDAO = new CineDAO();
    private final SalaDAO salaDAO = new SalaDAO();

    // Registra un cine y crea automaticamente sus salas
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

    // Devuelve el listado completo de cines
    @Override
    public ArrayList<Cine> listarCines() {
        return cineDAO.obtenerCines();
    }

    // Busca un cine por su nombre
    @Override
    public Cine buscarPorNombre(String nombre) {
        return cineDAO.buscarPorNombre(nombre);
    }

    // Actualiza los datos de un cine existente
    @Override
    public boolean actualizarCine(Cine cine) {
        return cineDAO.actualizarCine(cine);
    }

    // Elimina un cine por su identificador
    @Override
    public boolean eliminarCine(int id) {
        return cineDAO.eliminarCine(id);
    }

}
