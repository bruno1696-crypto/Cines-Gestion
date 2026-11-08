// Bruno Mercado

package com.cines;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.cines.control.ConectarBase;
import com.cines.dao.CineDAO;
import com.cines.dao.PeliculaDAO;
import com.cines.modelo.Cine;
import com.cines.modelo.Pelicula;
import com.cines.vista.MainFrame;

public class Main {

    public static final ArrayList<Cine> listaCines = new ArrayList<>();

    public static final ArrayList<Pelicula> listaPeliculas = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Iniciando programa.");

        // cargar primero los archivos .dat
        leerDatos(listaCines, listaPeliculas);

        // chequeo la conexion a la BD una sola vez aca. Si no hay, aviso una
        // sola vez y directamente no intento cargar ni de Cines ni de Peliculas
        if (hayConexionBD()) {
            System.out.println("Conexion establecida.");

            // sumo lo que hay en la base de datos, pero sin duplicar lo que ya vino del archivo 
            // una misma alta puede haberse quedado guardada dos veces
            // tanto en el .dat con "Guardar Datos" y en la BD
            // asi que reviso antes de agregar
            CineDAO cineDAO = new CineDAO();
            for (Cine cine : cineDAO.obtenerCines()) {
                if (!existeCinePorNombre(cine.getNombre(), listaCines)) {
                    listaCines.add(cine);
                }
            }

            PeliculaDAO peliculaDAO = new PeliculaDAO();
            for (Pelicula pelicula : peliculaDAO.obtenerPeliculas()) {
                if (!existePeliculaPorTitulo(pelicula.getTitulo(), listaPeliculas)) {
                    listaPeliculas.add(pelicula);
                }
            }
        } else {
            System.out.println("No se pudo conectar con la base de datos MySQL (revisar si esta levantada y/o usuario/contraseña).");
            System.out.println("Se continua solo con los datos de los archivos.");
        }

        // recien aca se abre la ventana
        SwingUtilities.invokeLater(() -> {
            MainFrame ventana = new MainFrame();
            ventana.setVisible(true);
        });
    }

    // prueba la conexion una unica vez al arrancar. Cierra la conexion de
    // prueba enseguida, cada DAO abre la suya propia cuando la necesita
    private static boolean hayConexionBD() {
        try {
            Connection conexion = new ConectarBase().conectar();
            if (conexion == null) {
                return false;
            }
            conexion.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // recorrido para chequear si un cine ya esta cargado, y no agregarlo dos veces (.dat y db)
    private static boolean existeCinePorNombre(String nombre, ArrayList<Cine> lista) {
        for (Cine cine : lista) {
            if (cine.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    private static boolean existePeliculaPorTitulo(String titulo, ArrayList<Pelicula> lista) {
        for (Pelicula pelicula : lista) {
            if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
                return true;
            }
        }
        return false;
    }

    // metodo se llama solo cuando se aprieta "Guardar Datos" en el menu, no se guarda automaticamente despues de cada alta
    public static void guardarDatos(ArrayList<Cine> listaCines, ArrayList<Pelicula> listaPeliculas) {
        String rutaDirectorio = "datos" + File.separator;
        File directorio = new File("datos");
        if (!directorio.exists()) {
            directorio.mkdirs(); // la primera vez que se corre el programa esta carpeta no existe
        }

        try (ObjectOutputStream oosCines = new ObjectOutputStream(new FileOutputStream(rutaDirectorio + "cines.dat"));
             ObjectOutputStream oosPeliculas = new ObjectOutputStream(new FileOutputStream(rutaDirectorio + "peliculas.dat"))) {

            oosCines.writeObject(listaCines);
            oosPeliculas.writeObject(listaPeliculas);
            System.out.println("Datos guardados correctamente en: " + rutaDirectorio);

        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void leerDatos(ArrayList<Cine> listaCines, ArrayList<Pelicula> listaPeliculas) {
        String rutaDirectorio = "datos" + File.separator;

        try (ObjectInputStream oisCines = new ObjectInputStream(new FileInputStream(rutaDirectorio + "cines.dat"));
             ObjectInputStream oisPeliculas = new ObjectInputStream(new FileInputStream(rutaDirectorio + "peliculas.dat"))) {

            ArrayList<Cine> cinesCargados = (ArrayList<Cine>) oisCines.readObject();
            ArrayList<Pelicula> peliculasCargadas = (ArrayList<Pelicula>) oisPeliculas.readObject();

            listaCines.clear();
            listaCines.addAll(cinesCargados);

            listaPeliculas.clear();
            listaPeliculas.addAll(peliculasCargadas);

            System.out.println("Datos cargados correctamente desde: " + rutaDirectorio);

        } catch (FileNotFoundException e) {
            // la primera vez que se corre el programa, todavia no se guardo nada
            System.out.println("Archivos de datos no encontrados en " + rutaDirectorio + ". Se inicia con listas vacias.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer los datos: " + e.getMessage());
        }
    }

}
