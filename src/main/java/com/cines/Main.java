// Bruno Mercado

package com.cines;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.cines.control.ConectarBase;
import com.cines.vista.MainFrame;

// El programa ya no lee ni escribe archivos .dat.
// Toda la informacion vive en la base de datos MySQL.
public class Main {

    public static void main(String[] args) {

        System.out.println("Iniciando programa.");

        // si no hay base de datos no tiene sentido abrir la aplicacion,
        // porque ya no existe un respaldo en archivos
        if (!hayConexionBD()) {
            String mensaje = "No se pudo conectar con la base de datos MySQL.\n"
                    + "Verifique que el servidor este levantado y que el usuario/contraseña sean correctos.";
            System.out.println(mensaje);
            JOptionPane.showMessageDialog(null, mensaje, "Error de conexion", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Conexion establecida.");

        SwingUtilities.invokeLater(() -> {
            MainFrame ventana = new MainFrame();
            ventana.setVisible(true);
        });
    }

    // prueba la conexion una unica vez al arrancar. Cada DAO abre y cierra
    // su propia conexion cuando la necesita
    private static boolean hayConexionBD() {
        try (Connection conexion = new ConectarBase().conectar()) {
            return conexion != null;
        } catch (SQLException e) {
            return false;
        }
    }

}
