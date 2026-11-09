// Bruno Mercado

package com.cines.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.cines.control.ValidarDato;
import com.cines.modelo.Pelicula;
import com.cines.servicio.impl.PeliculaServicioImpl;

public class BuscarPeliculaFrame extends JFrame {

    private JTextField txtTitulo;
    private JTextArea areaResultado;

    public BuscarPeliculaFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Buscar Película");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panelBusqueda = new JPanel(new FlowLayout());
        panelBusqueda.add(new JLabel("Título de la película:"));
        txtTitulo = new JTextField(15);
        panelBusqueda.add(txtTitulo);

        JButton btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> volver());
        panelInferior.add(btnVolver, BorderLayout.WEST);

        JLabel lblAlumno = new JLabel("Bruno Mercado", SwingConstants.RIGHT);
        panelInferior.add(lblAlumno, BorderLayout.EAST);

        add(panelInferior, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarPelicula());
    }

    private void volver() {
        this.dispose();
        MainFrame ventanaPrincipal = new MainFrame();
        ventanaPrincipal.setVisible(true);
    }

    private void buscarPelicula() {
        String titulo = txtTitulo.getText().trim();

        if (titulo.isEmpty()) {
            areaResultado.setText("Ingrese un título para buscar.");
            return;
        }

        if (!ValidarDato.validarTituloPelicula(titulo)) {
            areaResultado.setText("El título contiene caracteres no permitidos.");
            return;
        }

        PeliculaServicioImpl servicio = new PeliculaServicioImpl();
        ArrayList<Pelicula> coincidencias = servicio.buscarPorTitulo(titulo);

        if (coincidencias.isEmpty()) {
            areaResultado.setText("No se encontró ninguna película con ese título.");
        } else {
            areaResultado.setText("Películas encontradas: " + coincidencias.size() + "\n\n");
            for (Pelicula pelicula : coincidencias) {
                areaResultado.append(pelicula.toString() + "\n");
            }
        }
    }

}
