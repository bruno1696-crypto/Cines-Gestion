// Bruno Mercado
package com.cines.servicio.impl;

import com.cines.dao.FuncionDAO;
import com.cines.modelo.Funcion;
import com.cines.servicio.FuncionServicio;
import java.util.ArrayList;

// Implementacion del servicio de funciones, delega la persistencia en FuncionDAO
public class FuncionServicioImpl implements FuncionServicio {

    private final FuncionDAO funcionDAO = new FuncionDAO();

    // Valida el precio y registra la funcion a traves del DAO
    @Override
    public boolean crearFuncion(Funcion funcion) {
        if (funcion.getPrecio() <= 0) {
            return false;
        }
        return funcionDAO.registrarFuncion(funcion);
    }

    // Devuelve todas las funciones registradas
    @Override
    public ArrayList<Funcion> obtenerFunciones() {
        return funcionDAO.listarFunciones();
    }

    // Busca una funcion por su identificador
    @Override
    public Funcion buscarFuncion(int id) {
        return funcionDAO.obtenerFuncionPorId(id);
    }

    // Actualiza una funcion existente
    @Override
    public boolean actualizarFuncion(Funcion funcion) {
        if (funcion.getPrecio() <= 0) {
            return false;
        }
        return funcionDAO.actualizarFuncion(funcion);
    }

    // Elimina una funcion por su identificador
    @Override
    public boolean eliminarFuncion(int id) {
        return funcionDAO.eliminarFuncion(id);
    }
}
