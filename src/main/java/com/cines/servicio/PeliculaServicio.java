// Bruno Mercado

package com.cines.servicio;

import java.util.ArrayList;

import com.cines.modelo.Pelicula;

public interface PeliculaServicio {

    // Registra una nueva pelicula
    boolean agregarPelicula(Pelicula pelicula);

    // Devuelve el listado completo de peliculas
    ArrayList<Pelicula> listarPeliculas();

    // Busca una pelicula por su titulo
    Pelicula buscarPorTitulo(String titulo);

    // Actualiza los datos de una pelicula existente
    boolean actualizarPelicula(Pelicula pelicula);

    // Elimina una pelicula por su identificador
    boolean eliminarPelicula(int id);

}
