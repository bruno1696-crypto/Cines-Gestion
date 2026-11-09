// Bruno Mercado

package com.cines.servicio;

import com.cines.modelo.Sala;
import java.util.ArrayList;

public interface SalaServicio {

    // Agrega una sala.
    boolean agregarSala(Sala sala);

    // Lista todas las salas.
    ArrayList<Sala> listarSalas();

    // Lista las salas de un cine.
    ArrayList<Sala> listarSalasPorCine(int cineId);

    // Busca una sala por id.
    Sala buscarSala(int id);

    // Actualiza una sala.
    boolean actualizarSala(Sala sala);

    // Elimina una sala.
    boolean eliminarSala(int id);

    // Crea las salas de un cine.
    boolean crearSalasParaCine(int cineId, int cantidad, int capacidad);

    // Genera las salas que faltan.
    int generarSalasFaltantes();
}
