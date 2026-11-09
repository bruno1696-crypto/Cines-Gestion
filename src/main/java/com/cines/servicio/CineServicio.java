// Bruno Mercado

package com.cines.servicio;

import java.util.ArrayList;

import com.cines.modelo.Cine;

public interface CineServicio {

    boolean agregarCine(Cine cine, int capacidadPorSala);

    ArrayList<Cine> listarCines();

    ArrayList<Cine> buscarPorNombre(String nombre);

    boolean actualizarCine(Cine cine);

    boolean eliminarCine(int id);

}
