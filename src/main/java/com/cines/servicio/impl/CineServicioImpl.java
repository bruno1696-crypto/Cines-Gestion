// Bruno Mercado

package com.cines.servicio.impl;

import java.util.ArrayList;

import com.cines.dao.CineDAO;
import com.cines.modelo.Cine;
import com.cines.servicio.CineServicio;

public class CineServicioImpl implements CineServicio {

    private CineDAO cineDAO = new CineDAO();

    @Override
    public void agregarCine(Cine cine, ArrayList<Cine> listaCines) {
        listaCines.add(cine);

        // ademas de la lista en memoria, lo mando a la base. Si la conexion Mysql falla el DAO captura el error
        cineDAO.insertarCine(cine);
    }

    @Override
    public ArrayList<Cine> listarCines(ArrayList<Cine> listaCines) {
        return listaCines;
    }

    @Override
    public Cine buscarPorNombre(String nombre, ArrayList<Cine> listaCines) {
        // recorrido simple usando for con equalsIgnoreCase para ignorar mayusculas/minusculas exactas
        for (Cine cine : listaCines) {
            if (cine.getNombre().equalsIgnoreCase(nombre)) {
                return cine;
            }
        }
        return null;
    }

}
