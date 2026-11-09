// Bruno Mercado

package com.cines.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBase {

    // Carga el driver de MySQL una sola vez al iniciar la clase.
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("No se encontró el driver de MySQL: " + e.getMessage());
        }
    }

    // Constructor vacío: la conexión se abre en conectar().
    public ConectarBase() {
    }

    // Abre una conexión nueva con los datos de Configuracion.
    public Connection conectar() throws SQLException {
        String url = Configuracion.BBDD_DRIVER + "://" + Configuracion.BBDD_IP + ":"
                + Configuracion.BBDD_PUERTO + "/" + Configuracion.BBDD_NOMBRE;

        return DriverManager.getConnection(url, Configuracion.BBDD_USUARIO, Configuracion.BBDD_PASSWORD);
    }
}
