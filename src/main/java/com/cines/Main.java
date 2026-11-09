// Bruno Mercado

package com.cines;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.cines.control.ConectarBase;
import com.cines.servicio.SalaServicio;
import com.cines.servicio.impl.SalaServicioImpl;
import com.cines.vista.UsuarioFrame;

public class Main {

    // Punto de entrada: verifica la base y abre el login.
    public static void main(String[] args) {

        System.out.println("Iniciando programa.");

        if (!hayConexionBD()) {
            String mensaje = "No se pudo conectar con la base de datos MySQL.\n"
                    + "Verifique que el servidor esté levantado y que el usuario/contraseña sean correctos.";
            System.out.println(mensaje);
            JOptionPane.showMessageDialog(null, mensaje, "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Conexión establecida.");

        SalaServicio salaServicio = new SalaServicioImpl();
        int salasGeneradas = salaServicio.generarSalasFaltantes();
        if (salasGeneradas > 0) {
            System.out.println("Se generaron " + salasGeneradas + " salas faltantes para cines existentes.");
        }

        SwingUtilities.invokeLater(() -> {
            UsuarioFrame login = new UsuarioFrame();
            login.setVisible(true);
        });
    }

    // Prueba la conexión antes de arrancar la interfaz.
    private static boolean hayConexionBD() {
        try (Connection conexion = new ConectarBase().conectar()) {
            return conexion != null;
        } catch (SQLException e) {
            return false;
        }
    }

}
