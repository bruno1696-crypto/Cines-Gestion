// Bruno Mercado
package com.cines.servicio;

import com.cines.modelo.Funcion;
import java.util.List;

public interface FuncionServicio {
    boolean crearFuncion(Funcion funcion);
    List<Funcion> obtenerFunciones();
}