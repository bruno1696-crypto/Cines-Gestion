// Bruno Mercado
package com.cines.servicio;

import com.cines.modelo.Sala;
import java.util.ArrayList;

public interface SalaServicio {

    // Registra una nueva sala
    boolean agregarSala(Sala sala);

    // Devuelve el listado completo de salas
    ArrayList<Sala> listarSalas();

    // Devuelve las salas que pertenecen a un cine
    ArrayList<Sala> listarSalasPorCine(int cineId);

    // Busca una sala por su identificador
    Sala buscarSala(int id);

    // Actualiza los datos de una sala existente
    boolean actualizarSala(Sala sala);

    // Elimina una sala por su identificador
    boolean eliminarSala(int id);

    // Crea varias salas numeradas para un cine
    boolean crearSalasParaCine(int cineId, int cantidad, int capacidad);

    // Completa las salas faltantes de los cines ya registrados
    int generarSalasFaltantes();
}
