// Bruno Mercado

package com.cines.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// conexion con la base de datos y driver de Mysql
public class ConectarBase {
    Connection conexion;

    public ConectarBase() {
        this.conexion = null;
    }

    public Connection conectar() throws InstantiationException, IllegalAccessException {
        try {
            // carga driver de Mysql sin framework de pro medio
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = Configuracion.BBDD_DRIVER + "://" + Configuracion.BBDD_IP + ":"
                    + Configuracion.BBDD_PUERTO + "/" + Configuracion.BBDD_NOMBRE;
            conexion = DriverManager.getConnection(url, Configuracion.BBDD_USUARIO, Configuracion.BBDD_PASSWORD);
            // no imprimo "Conexion establecida" aca, se repetiria una vez por
            // cada DAO que se conecta. El aviso unico se hace en Main
        } catch (ClassNotFoundException | SQLException e) {
            // no imprimi mensaje aca para que no se repita dos veces en la consola, ya que el error se maneja en el DAO
        }

        return conexion;
    }
}
