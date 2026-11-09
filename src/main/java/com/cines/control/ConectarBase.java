// Bruno Mercado

package com.cines.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// conexion con la base de datos y driver de Mysql
public class ConectarBase {

    // el driver se carga una sola vez cuando se usa la clase por primera vez
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("No se encontro el driver de MySQL: " + e.getMessage());
        }
    }

    // Constructor vacio de la clase de conexion
    public ConectarBase() {
    }

    // devuelve una conexion nueva a MySQL. Si algo falla se propaga la excepcion
    // para que el DAO que llamo pueda informar el error (no se oculta el problema)
    public Connection conectar() throws SQLException {
        String url = Configuracion.BBDD_DRIVER + "://" + Configuracion.BBDD_IP + ":"
                + Configuracion.BBDD_PUERTO + "/" + Configuracion.BBDD_NOMBRE;

        return DriverManager.getConnection(url, Configuracion.BBDD_USUARIO, Configuracion.BBDD_PASSWORD);
    }
}
