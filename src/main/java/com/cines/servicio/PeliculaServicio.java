// Bruno Mercado

package com.cines.servicio;

import java.util.ArrayList;

import com.cines.modelo.Pelicula;

public interface PeliculaServicio {

    boolean agregarPelicula(Pelicula pelicula);

    ArrayList<Pelicula> listarPeliculas();

    ArrayList<Pelicula> buscarPorTitulo(String titulo);

    boolean actualizarPelicula(Pelicula pelicula);

    boolean eliminarPelicula(int id);

}
