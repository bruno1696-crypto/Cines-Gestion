// Bruno Mercado

package com.cines.servicio.impl;

import java.util.ArrayList;

import com.cines.dao.PeliculaDAO;
import com.cines.modelo.Pelicula;
import com.cines.servicio.PeliculaServicio;

public class PeliculaServicioImpl implements PeliculaServicio {

    private PeliculaDAO peliculaDAO = new PeliculaDAO();

    @Override
    public void agregarPelicula(Pelicula pelicula, ArrayList<Pelicula> listaPeliculas) {
        listaPeliculas.add(pelicula);
        
        // igual que en CineServicioImpl, ademas de la lista en memoria, lo mando a la base
        peliculaDAO.insertarPelicula(pelicula);
    }

    @Override
    public ArrayList<Pelicula> listarPeliculas(ArrayList<Pelicula> listaPeliculas) {
        return listaPeliculas;
    }

    @Override
    public Pelicula buscarPorTitulo(String titulo, ArrayList<Pelicula> listaPeliculas) {
        for (Pelicula pelicula : listaPeliculas) {
            if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
                return pelicula;
            }
        }
        return null;
    }

}
