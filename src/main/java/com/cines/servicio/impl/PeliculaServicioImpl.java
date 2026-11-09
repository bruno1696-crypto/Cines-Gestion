// Bruno Mercado

package com.cines.servicio.impl;

import java.util.ArrayList;

import com.cines.dao.PeliculaDAO;
import com.cines.modelo.Pelicula;
import com.cines.servicio.PeliculaServicio;

// Implementacion del servicio de peliculas, delega la persistencia en PeliculaDAO
public class PeliculaServicioImpl implements PeliculaServicio {

    private final PeliculaDAO peliculaDAO = new PeliculaDAO();

    // Registra una nueva pelicula
    @Override
    public boolean agregarPelicula(Pelicula pelicula) {
        return peliculaDAO.insertarPelicula(pelicula);
    }

    // Devuelve el listado completo de peliculas
    @Override
    public ArrayList<Pelicula> listarPeliculas() {
        return peliculaDAO.obtenerPeliculas();
    }

    // Busca una pelicula por su titulo
    @Override
    public Pelicula buscarPorTitulo(String titulo) {
        return peliculaDAO.buscarPorTitulo(titulo);
    }

    // Actualiza los datos de una pelicula existente
    @Override
    public boolean actualizarPelicula(Pelicula pelicula) {
        return peliculaDAO.actualizarPelicula(pelicula);
    }

    // Elimina una pelicula por su identificador
    @Override
    public boolean eliminarPelicula(int id) {
        return peliculaDAO.eliminarPelicula(id);
    }

}
