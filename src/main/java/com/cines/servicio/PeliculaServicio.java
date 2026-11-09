// Bruno Mercado

package com.cines.servicio;

import java.util.ArrayList;

import com.cines.modelo.Pelicula;

public interface PeliculaServicio {

    // Da de alta una película.
    boolean agregarPelicula(Pelicula pelicula);

    // Devuelve todas las películas.
    ArrayList<Pelicula> listarPeliculas();

    // Busca películas por título.
    ArrayList<Pelicula> buscarPorTitulo(String titulo);

    // Actualiza una película.
    boolean actualizarPelicula(Pelicula pelicula);

    // Elimina una película.
    boolean eliminarPelicula(int id);

}
