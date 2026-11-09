// Bruno Mercado

package com.cines.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

    // Constructor del menu principal del sistema
    public MainFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    // Configura el titulo, tamano y comportamiento de cierre de la ventana
    private void configurarVentana() {
        setTitle("Sistema de Gestion de Cines");
        setSize(420, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Crea los botones de acceso a cada pantalla del sistema
    private void inicializarComponentes() {

        JPanel panel = new JPanel();
        // 7 filas para 6 botones de cines/peliculas + funciones + salir
        panel.setLayout(new GridLayout(8, 1, 5, 5));

        // -------------------------- BOTONES DE CINES -------------------------
        JButton btnAltaCine = new JButton("Gestionar Cines (Alta)");
        panel.add(btnAltaCine);
        btnAltaCine.addActionListener(e -> {
            CineFrame ventana = new CineFrame();
            ventana.setVisible(true);
            this.dispose();
        });

        JButton btnListarCines = new JButton("Listar Cines");
        panel.add(btnListarCines);
        btnListarCines.addActionListener(e -> {
            ListarCinesFrame ventana = new ListarCinesFrame();
            ventana.setVisible(true);
        });

        JButton btnBuscarCine = new JButton("Buscar Cine");
        panel.add(btnBuscarCine);
        btnBuscarCine.addActionListener(e -> {
            BuscarCineFrame ventana = new BuscarCineFrame();
            ventana.setVisible(true);
        });

        // -------------------------- BOTONES DE PELICULAS -------------------------
        JButton btnAltaPelicula = new JButton("Gestionar Peliculas (Alta)");
        panel.add(btnAltaPelicula);
        btnAltaPelicula.addActionListener(e -> {
            PeliculaFrame ventana = new PeliculaFrame();
            ventana.setVisible(true);
            this.dispose();
        });

        JButton btnListarPeliculas = new JButton("Listar Peliculas");
        panel.add(btnListarPeliculas);
        btnListarPeliculas.addActionListener(e -> {
            ListarPeliculasFrame ventana = new ListarPeliculasFrame();
            ventana.setVisible(true);
        });

        JButton btnBuscarPelicula = new JButton("Buscar Pelicula");
        panel.add(btnBuscarPelicula);
        btnBuscarPelicula.addActionListener(e -> {
            BuscarPeliculaFrame ventana = new BuscarPeliculaFrame();
            ventana.setVisible(true);
        });

        // -------------------------- BOTON FUNCIONES -------------------------
        JButton btnGestionFunciones = new JButton("Gestion de Funciones");
        panel.add(btnGestionFunciones);
        btnGestionFunciones.addActionListener(e -> {
            FuncionFrame funcionFrame = new FuncionFrame();
            funcionFrame.setVisible(true);
        });

        // ------------------------- BOTON SALIR -------------------------
        JButton btnSalir = new JButton("Salir");
        panel.add(btnSalir);
        btnSalir.addActionListener(e -> {
            System.out.println("Saliendo del programa.");
            System.exit(0);
        });

        add(panel, BorderLayout.CENTER);

        JLabel lblAlumno = new JLabel("Bruno Mercado", SwingConstants.RIGHT);
        add(lblAlumno, BorderLayout.SOUTH);
    }

}
