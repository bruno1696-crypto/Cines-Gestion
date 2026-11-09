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
import com.cines.modelo.Cine;
import com.cines.servicio.impl.CineServicioImpl;

public class BuscarCineFrame extends JFrame {

    private JTextField txtNombre;
    private JTextArea areaResultado;

    // Prepara la búsqueda por nombre.
    public BuscarCineFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    // Configura la ventana.
    private void configurarVentana() {
        setTitle("Buscar Cine");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Campo de búsqueda y área de resultados.
    private void inicializarComponentes() {
        JPanel panelBusqueda = new JPanel(new FlowLayout());
        panelBusqueda.add(new JLabel("Nombre del cine:"));
        txtNombre = new JTextField(15);
        panelBusqueda.add(txtNombre);

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

        btnBuscar.addActionListener(e -> buscarCine());
    }

    // Vuelve al menú.
    private void volver() {
        this.dispose();
        MainFrame ventanaPrincipal = new MainFrame();
        ventanaPrincipal.setVisible(true);
    }

    // Valida y busca coincidencias.
    private void buscarCine() {
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            areaResultado.setText("Ingrese un nombre para buscar.");
            return;
        }

        if (!ValidarDato.validarNombreCine(nombre)) {
            areaResultado.setText("El nombre solo puede contener letras, números y espacios.");
            return;
        }

        CineServicioImpl servicio = new CineServicioImpl();
        ArrayList<Cine> coincidencias = servicio.buscarPorNombre(nombre);

        if (coincidencias.isEmpty()) {
            areaResultado.setText("No se encontró ningún cine con ese nombre.");
        } else {
            areaResultado.setText("Cines encontrados: " + coincidencias.size() + "\n\n");
            for (Cine cine : coincidencias) {
                areaResultado.append(cine.toString() + "\n");
            }
        }
    }

}
