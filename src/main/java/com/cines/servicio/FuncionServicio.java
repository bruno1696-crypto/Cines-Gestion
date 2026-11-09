// Bruno Mercado
package com.cines.servicio;

import com.cines.modelo.Funcion;
import java.util.ArrayList;

public interface FuncionServicio {

    // Registra una nueva funcion
    boolean crearFuncion(Funcion funcion);

    // Devuelve el listado completo de funciones
    ArrayList<Funcion> obtenerFunciones();

    // Busca una funcion por su identificador
    Funcion buscarFuncion(int id);

    // Actualiza los datos de una funcion existente
    boolean actualizarFuncion(Funcion funcion);

    // Elimina una funcion por su identificador
    boolean eliminarFuncion(int id);
}
