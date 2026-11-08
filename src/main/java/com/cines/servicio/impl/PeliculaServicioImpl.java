// Bruno Mercado

package com.cines.servicio.impl;

import java.util.ArrayList;

import com.cines.dao.PeliculaDAO;
import com.cines.modelo.Pelicula;
import com.cines.servicio.PeliculaServicio;

// igual que CineServicioImpl: sin listas en memoria, todo contra MySQL
public class PeliculaServicioImpl implements PeliculaServicio {

    private final PeliculaDAO peliculaDAO = new PeliculaDAO();

    @Override
    public boolean agregarPelicula(Pelicula pelicula) {
        return peliculaDAO.insertarPelicula(pelicula);
    }

    @Override
    public ArrayList<Pelicula> listarPeliculas() {
        return peliculaDAO.obtenerPeliculas();
    }

    @Override
    public Pelicula buscarPorTitulo(String titulo) {
        return peliculaDAO.buscarPorTitulo(titulo);
    }

    @Override
    public boolean actualizarPelicula(Pelicula pelicula) {
        return peliculaDAO.actualizarPelicula(pelicula);
    }

    @Override
    public boolean eliminarPelicula(int id) {
        return peliculaDAO.eliminarPelicula(id);
    }

}
