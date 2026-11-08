// Bruno Mercado

package com.cines.vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.cines.Main;
import com.cines.modelo.Pelicula;
import com.cines.servicio.impl.PeliculaServicioImpl;

public class ListarPeliculasFrame extends JFrame {

    private JTextArea areaResultado;

    public ListarPeliculasFrame() {
        configurarVentana();
        inicializarComponentes();
        listarPeliculas();
    }

    private void configurarVentana() {
        setTitle("Listado de Peliculas");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultado);
        add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> this.dispose());
        panelInferior.add(btnCerrar, BorderLayout.WEST);

        JLabel lblAlumno = new JLabel("Bruno Mercado", SwingConstants.RIGHT);
        panelInferior.add(lblAlumno, BorderLayout.EAST);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private void listarPeliculas() {
        // igual que en ListarCinesFrame
        PeliculaServicioImpl servicio = new PeliculaServicioImpl();
        var listaPeliculas = servicio.listarPeliculas(Main.listaPeliculas);

        if (listaPeliculas.isEmpty()) {
            areaResultado.setText("No hay peliculas registradas.");
        } else {
            areaResultado.setText("Lista de Peliculas:\n");
            for (Pelicula pelicula : listaPeliculas) {
                areaResultado.append(pelicula.toString() + "\n");
            }
        }
    }

}
