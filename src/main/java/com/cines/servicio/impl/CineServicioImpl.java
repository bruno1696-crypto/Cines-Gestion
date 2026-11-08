// Bruno Mercado

package com.cines.servicio.impl;

import java.util.ArrayList;

import com.cines.dao.CineDAO;
import com.cines.modelo.Cine;
import com.cines.servicio.CineServicio;

// El servicio ya no mantiene listas en memoria: todas las operaciones
// se delegan al DAO, que trabaja directamente contra MySQL.
public class CineServicioImpl implements CineServicio {

    private final CineDAO cineDAO = new CineDAO();

    @Override
    public boolean agregarCine(Cine cine) {
        return cineDAO.insertarCine(cine);
    }

    @Override
    public ArrayList<Cine> listarCines() {
        return cineDAO.obtenerCines();
    }

    @Override
    public Cine buscarPorNombre(String nombre) {
        return cineDAO.buscarPorNombre(nombre);
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
