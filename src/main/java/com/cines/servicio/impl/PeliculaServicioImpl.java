// Bruno Mercado

package com.cines.servicio.impl;

import java.util.ArrayList;

import com.cines.dao.PeliculaDAO;
import com.cines.modelo.Pelicula;
import com.cines.servicio.PeliculaServicio;

public class PeliculaServicioImpl implements PeliculaServicio {

    private final PeliculaDAO peliculaDAO = new PeliculaDAO();

    // Guarda la película a través del DAO.
    @Override
    public boolean agregarPelicula(Pelicula pelicula) {
        return peliculaDAO.insertarPelicula(pelicula);
    }

    // Devuelve el listado del DAO.
    @Override
    public ArrayList<Pelicula> listarPeliculas() {
        return peliculaDAO.obtenerPeliculas();
    }

    // Busca coincidencias en el título.
    @Override
    public ArrayList<Pelicula> buscarPorTitulo(String titulo) {
        ArrayList<Pelicula> coincidencias = new ArrayList<>();
        String textoBuscado = titulo.toLowerCase();

        for (Pelicula pelicula : peliculaDAO.obtenerPeliculas()) {
            if (pelicula.getTitulo().toLowerCase().contains(textoBuscado)) {
                coincidencias.add(pelicula);
            }
        }
        return coincidencias;
    }

    // Actualiza la película.
    @Override
    public boolean actualizarPelicula(Pelicula pelicula) {
        return peliculaDAO.actualizarPelicula(pelicula);
    }

    // Elimina la película.
    @Override
    public boolean eliminarPelicula(int id) {
        return peliculaDAO.eliminarPelicula(id);
    }

}
