// Bruno Mercado

package com.cines.servicio;

import java.util.ArrayList;

import com.cines.modelo.Cine;

public interface CineServicio {

    // Registra un nuevo cine
    boolean agregarCine(Cine cine, int capacidadPorSala);

    // Devuelve el listado completo de cines
    ArrayList<Cine> listarCines();

    // Busca un cine por su nombre
    Cine buscarPorNombre(String nombre);

    // Actualiza los datos de un cine existente
    boolean actualizarCine(Cine cine);

    // Elimina un cine por su identificador
    boolean eliminarCine(int id);

}
