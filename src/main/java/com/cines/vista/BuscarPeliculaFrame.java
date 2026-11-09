// Bruno Mercado

package com.cines.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

    // Constructor de la ventana de busqueda de peliculas
    public BuscarPeliculaFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    // Configura el titulo, tamano y comportamiento de cierre de la ventana
    private void configurarVentana() {
        setTitle("Buscar Pelicula");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Crea y ubica los componentes graficos de la ventana
    private void inicializarComponentes() {
        JPanel panelBusqueda = new JPanel(new FlowLayout());
        panelBusqueda.add(new JLabel("Titulo de la pelicula:"));
        txtTitulo = new JTextField(15);
        panelBusqueda.add(txtTitulo);

        JButton btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        JLabel lblAlumno = new JLabel("Bruno Mercado", SwingConstants.RIGHT);
        add(lblAlumno, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarPelicula());
    }

    // Valida el titulo con una expresion regular y busca la pelicula por titulo
    private void buscarPelicula() {
        String titulo = txtTitulo.getText().trim();

        if (titulo.isEmpty()) {
            areaResultado.setText("Ingrese un titulo para buscar.");
            return;
        }

        if (!ValidarDato.validarTituloPelicula(titulo)) {
            areaResultado.setText("El titulo contiene caracteres no permitidos.");
            return;
        }

        PeliculaServicioImpl servicio = new PeliculaServicioImpl();
        Pelicula pelicula = servicio.buscarPorTitulo(titulo);

        if (pelicula == null) {
            areaResultado.setText("No se encontro ninguna pelicula con ese titulo.");
        } else {
            areaResultado.setText("Pelicula encontrada:\n" + pelicula.toString());
        }
    }

}
