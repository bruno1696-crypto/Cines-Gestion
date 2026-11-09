// Bruno Mercado

package com.cines.servicio.impl;

import java.util.ArrayList;

import com.cines.dao.FuncionDAO;
import com.cines.modelo.Funcion;
import com.cines.servicio.FuncionServicio;

public class FuncionServicioImpl implements FuncionServicio {

    private final FuncionDAO funcionDAO = new FuncionDAO();

    @Override
    public boolean crearFuncion(Funcion funcion) {
        if (funcion.getPrecio() <= 0) {
            return false;
        }
        return funcionDAO.registrarFuncion(funcion);
    }

    @Override
    public ArrayList<Funcion> obtenerFunciones() {
        return funcionDAO.listarFunciones();
    }

    @Override
    public Funcion buscarFuncion(int id) {
        return funcionDAO.obtenerFuncionPorId(id);
    }

    @Override
    public boolean actualizarFuncion(Funcion funcion) {
        if (funcion.getPrecio() <= 0) {
            return false;
        }
        return funcionDAO.actualizarFuncion(funcion);
    }

    @Override
    public boolean eliminarFuncion(int id) {
        return funcionDAO.eliminarFuncion(id);
    }
}
