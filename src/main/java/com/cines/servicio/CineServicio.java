// Bruno Mercado

package com.cines.servicio;

import java.util.ArrayList;

import com.cines.modelo.Cine;

public interface CineServicio {

    void agregarCine(Cine cine, ArrayList<Cine> listaCines);

    ArrayList<Cine> listarCines(ArrayList<Cine> listaCines);

    Cine buscarPorNombre(String nombre, ArrayList<Cine> listaCines);

}
