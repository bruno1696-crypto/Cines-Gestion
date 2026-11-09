// Bruno Mercado

package com.cines.servicio;

import com.cines.modelo.Funcion;
import java.util.ArrayList;

public interface FuncionServicio {

    boolean crearFuncion(Funcion funcion);

    ArrayList<Funcion> obtenerFunciones();

    Funcion buscarFuncion(int id);

    boolean actualizarFuncion(Funcion funcion);

    boolean eliminarFuncion(int id);
}
