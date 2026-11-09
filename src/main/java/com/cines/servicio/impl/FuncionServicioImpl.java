// Bruno Mercado
package com.cines.servicio.impl;

import com.cines.dao.FuncionDAO;
import com.cines.modelo.Funcion;
import com.cines.servicio.FuncionServicio;
import java.util.List;

public class FuncionServicioImpl implements FuncionServicio {
    private FuncionDAO funcionDAO = new FuncionDAO();

    @Override
    public boolean crearFuncion(Funcion funcion) {
        if (funcion.getPrecio() <= 0) {
            return false;
        }
        return funcionDAO.registrarFuncion(funcion);
    }

    @Override
    public List<Funcion> obtenerFunciones() {
        return funcionDAO.listarFunciones();
    }
}