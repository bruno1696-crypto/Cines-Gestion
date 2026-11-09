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
import com.cines.modelo.Cine;
import com.cines.servicio.impl.CineServicioImpl;

public class BuscarCineFrame extends JFrame {

    private JTextField txtNombre;
    private JTextArea areaResultado;

    // Constructor de la ventana de busqueda de cines
    public BuscarCineFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    // Configura el titulo, tamano y comportamiento de cierre de la ventana
    private void configurarVentana() {
        setTitle("Buscar Cine");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Crea y ubica los componentes graficos de la ventana
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

        JLabel lblAlumno = new JLabel("Bruno Mercado", SwingConstants.RIGHT);
        add(lblAlumno, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarCine());
    }

    // Valida el nombre con una expresion regular y busca el cine por nombre
    private void buscarCine() {
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            areaResultado.setText("Ingrese un nombre para buscar.");
            return;
        }

        if (!ValidarDato.validarNombreCine(nombre)) {
            areaResultado.setText("El nombre solo puede contener letras, numeros y espacios.");
            return;
        }

        CineServicioImpl servicio = new CineServicioImpl();
        Cine cine = servicio.buscarPorNombre(nombre);

        if (cine == null) {
            areaResultado.setText("No se encontro ningun cine con ese nombre.");
        } else {
            areaResultado.setText("Cine encontrado:\n" + cine.toString());
        }
    }

}
