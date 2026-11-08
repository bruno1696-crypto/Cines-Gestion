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

import com.cines.modelo.Cine;
import com.cines.servicio.impl.CineServicioImpl;

public class ListarCinesFrame extends JFrame {

    private JTextArea areaResultado;

    public ListarCinesFrame() {
        configurarVentana();
        inicializarComponentes();
        listarCines();
    }

    private void configurarVentana() {
        setTitle("Listado de Cines");
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

    private void listarCines() {
        // se llama una sola vez, apenas se abre la ventana 
        CineServicioImpl servicio = new CineServicioImpl();
        var listaCines = servicio.listarCines();

        if (listaCines.isEmpty()) {
            areaResultado.setText("No hay cines registrados.");
        } else {
            areaResultado.setText("Lista de Cines:\n");
            for (Cine cine : listaCines) {
                areaResultado.append(cine.toString() + "\n");
            }
        }
    }

}
