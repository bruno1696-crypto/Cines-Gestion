// Bruno Mercado

package com.cines.vista;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.cines.modelo.Pelicula;
import com.cines.servicio.impl.PeliculaServicioImpl;

public class ListarPeliculasFrame extends JFrame {

    private JTextArea areaResultado;

    // Prepara la ventana y carga las películas.
    public ListarPeliculasFrame() {
        configurarVentana();
        inicializarComponentes();
        listarPeliculas();
    }

    // Título y tamaño.
    private void configurarVentana() {
        setTitle("Listado de Películas");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Área de texto y botón volver.
    private void inicializarComponentes() {
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultado);
        add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> volver());
        panelInferior.add(btnVolver, BorderLayout.WEST);

        JLabel lblAlumno = new JLabel("Bruno Mercado", SwingConstants.RIGHT);
        panelInferior.add(lblAlumno, BorderLayout.EAST);

        add(panelInferior, BorderLayout.SOUTH);
    }

    // Vuelve al menú.
    private void volver() {
        this.dispose();
        MainFrame ventanaPrincipal = new MainFrame();
        ventanaPrincipal.setVisible(true);
    }

    // Pide las películas y las muestra.
    private void listarPeliculas() {
        PeliculaServicioImpl servicio = new PeliculaServicioImpl();
        ArrayList<Pelicula> listaPeliculas = servicio.listarPeliculas();

        if (listaPeliculas.isEmpty()) {
            areaResultado.setText("No hay películas registradas.");
        } else {
            areaResultado.setText("Lista de Películas:\n");
            for (Pelicula pelicula : listaPeliculas) {
                areaResultado.append(pelicula.toString() + "\n");
            }
        }
    }

}
