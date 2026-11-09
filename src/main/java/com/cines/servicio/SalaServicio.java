// Bruno Mercado

package com.cines.servicio;

import com.cines.modelo.Sala;
import java.util.ArrayList;

public interface SalaServicio {

    boolean agregarSala(Sala sala);

    ArrayList<Sala> listarSalas();

    ArrayList<Sala> listarSalasPorCine(int cineId);

    Sala buscarSala(int id);

    boolean actualizarSala(Sala sala);

    boolean eliminarSala(int id);

    boolean crearSalasParaCine(int cineId, int cantidad, int capacidad);

    int generarSalasFaltantes();
}
