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

    public MainFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión de Cines");
        setSize(420, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 5, 5));

        JButton btnAltaCine = new JButton("Gestionar Cines (Alta)");
        panel.add(btnAltaCine);
        btnAltaCine.addActionListener(e -> abrirVentana(new CineFrame()));

        JButton btnListarCines = new JButton("Listar Cines");
        panel.add(btnListarCines);
        btnListarCines.addActionListener(e -> abrirVentana(new ListarCinesFrame()));

        JButton btnBuscarCine = new JButton("Buscar Cine");
        panel.add(btnBuscarCine);
        btnBuscarCine.addActionListener(e -> abrirVentana(new BuscarCineFrame()));

        JButton btnAltaPelicula = new JButton("Gestionar Películas (Alta)");
        panel.add(btnAltaPelicula);
        btnAltaPelicula.addActionListener(e -> abrirVentana(new PeliculaFrame()));

        JButton btnListarPeliculas = new JButton("Listar Películas");
        panel.add(btnListarPeliculas);
        btnListarPeliculas.addActionListener(e -> abrirVentana(new ListarPeliculasFrame()));

        JButton btnBuscarPelicula = new JButton("Buscar Película");
        panel.add(btnBuscarPelicula);
        btnBuscarPelicula.addActionListener(e -> abrirVentana(new BuscarPeliculaFrame()));

        JButton btnGestionFunciones = new JButton("Gestión de Funciones");
        panel.add(btnGestionFunciones);
        btnGestionFunciones.addActionListener(e -> abrirVentana(new FuncionFrame()));

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

    private void abrirVentana(JFrame ventana) {
        ventana.setVisible(true);
        this.dispose();
    }

}
