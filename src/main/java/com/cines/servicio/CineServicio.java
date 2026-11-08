// Bruno Mercado

package com.cines.servicio;

import java.util.ArrayList;

import com.cines.modelo.Cine;

public interface CineServicio {

    boolean agregarCine(Cine cine);

    ArrayList<Cine> listarCines();

    Cine buscarPorNombre(String nombre);

    boolean actualizarCine(Cine cine);

    boolean eliminarCine(int id);

}
