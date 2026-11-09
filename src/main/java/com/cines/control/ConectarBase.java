// Bruno Mercado

package com.cines.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBase {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("No se encontró el driver de MySQL: " + e.getMessage());
        }
    }

    public ConectarBase() {
    }

    public Connection conectar() throws SQLException {
        String url = Configuracion.BBDD_DRIVER + "://" + Configuracion.BBDD_IP + ":"
                + Configuracion.BBDD_PUERTO + "/" + Configuracion.BBDD_NOMBRE;

        return DriverManager.getConnection(url, Configuracion.BBDD_USUARIO, Configuracion.BBDD_PASSWORD);
    }
}
