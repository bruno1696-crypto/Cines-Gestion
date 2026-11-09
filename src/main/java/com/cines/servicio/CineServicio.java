// Bruno Mercado

package com.cines.servicio;

import java.util.ArrayList;

import com.cines.modelo.Cine;

public interface CineServicio {

    // Da de alta un cine y sus salas.
    boolean agregarCine(Cine cine, int capacidadPorSala);

    // Devuelve todos los cines.
    ArrayList<Cine> listarCines();

    // Busca cines por coincidencia de nombre.
    ArrayList<Cine> buscarPorNombre(String nombre);

    // Actualiza un cine.
    boolean actualizarCine(Cine cine);

    // Elimina un cine.
    boolean eliminarCine(int id);

}
