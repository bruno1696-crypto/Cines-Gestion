// Bruno Mercado

package com.cines.servicio;

import java.util.ArrayList;

import com.cines.modelo.Pelicula;

public interface PeliculaServicio {

    void agregarPelicula(Pelicula pelicula, ArrayList<Pelicula> listaPeliculas);

    ArrayList<Pelicula> listarPeliculas(ArrayList<Pelicula> listaPeliculas);

    Pelicula buscarPorTitulo(String titulo, ArrayList<Pelicula> listaPeliculas);

}
