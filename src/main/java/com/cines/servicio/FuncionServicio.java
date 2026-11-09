// Bruno Mercado

package com.cines.servicio;

import com.cines.modelo.Funcion;
import java.util.ArrayList;

public interface FuncionServicio {

    // Registra una función.
    boolean crearFuncion(Funcion funcion);

    // Devuelve todas las funciones.
    ArrayList<Funcion> obtenerFunciones();

    // Busca una función por id.
    Funcion buscarFuncion(int id);

    // Actualiza una función.
    boolean actualizarFuncion(Funcion funcion);

    // Elimina una función.
    boolean eliminarFuncion(int id);
}
