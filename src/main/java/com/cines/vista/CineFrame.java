// Bruno Mercado

package com.cines.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.cines.control.ValidarDato;
import com.cines.modelo.Cine;
import com.cines.servicio.impl.CineServicioImpl;

public class CineFrame extends JFrame {

    private JTextField txtNombre;
    private JTextField txtSalas;
    private JTextField txtCapacidad;
    private JCheckBox chkAbierto;
    private JTextField txtFechaInauguracion;

    private JButton btnGuardar;
    private JButton btnVolver;

    public CineFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Alta de Cine");
        setSize(420, 340);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 20, 15));

        panelFormulario.add(new JLabel("Nombre:", SwingConstants.CENTER));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Cantidad de salas:", SwingConstants.CENTER));
        txtSalas = new JTextField();
        panelFormulario.add(txtSalas);

        panelFormulario.add(new JLabel("Capacidad por sala:", SwingConstants.CENTER));
        txtCapacidad = new JTextField("100");
        panelFormulario.add(txtCapacidad);

        panelFormulario.add(new JLabel("Abierto:", SwingConstants.CENTER));
        chkAbierto = new JCheckBox();
        panelFormulario.add(chkAbierto);

        panelFormulario.add(new JLabel("Fecha de inauguración (dd/MM/yyyy):", SwingConstants.CENTER));
        txtFechaInauguracion = new JTextField();
        panelFormulario.add(txtFechaInauguracion);

        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnVolver = new JButton("Volver");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.CENTER);

        JLabel lblAlumno = new JLabel("Bruno Mercado", SwingConstants.RIGHT);
        add(lblAlumno, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardarCine());
        btnVolver.addActionListener(e -> volver());
    }

    private void volver() {
        this.dispose();
        MainFrame ventanaPrincipal = new MainFrame();
        ventanaPrincipal.setVisible(true);
    }

    private void guardarCine() {
        try {
            String nombre = txtNombre.getText().trim();
            String salasTxt = txtSalas.getText().trim();
            String capacidadTxt = txtCapacidad.getText().trim();
            String fechaTxt = txtFechaInauguracion.getText().trim();

            if (nombre.isEmpty() || salasTxt.isEmpty() || capacidadTxt.isEmpty() || fechaTxt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!ValidarDato.validarNombreCine(nombre)) {
                JOptionPane.showMessageDialog(this, "El nombre solo puede contener letras, números y espacios.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!ValidarDato.validarEnteroPositivo(salasTxt)) {
                JOptionPane.showMessageDialog(this, "La cantidad de salas debe ser un número entero positivo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!ValidarDato.validarEnteroPositivo(capacidadTxt)) {
                JOptionPane.showMessageDialog(this, "La capacidad debe ser un número entero positivo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!ValidarDato.validarFecha(fechaTxt)) {
                JOptionPane.showMessageDialog(this, "La fecha debe tener el formato dd/MM/yyyy.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int salas = Integer.parseInt(salasTxt);
            int capacidad = Integer.parseInt(capacidadTxt);
            boolean abierto = chkAbierto.isSelected();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaInauguracion = LocalDate.parse(fechaTxt, formatter);

            Cine cine = new Cine(nombre, salas, abierto, fechaInauguracion);

            CineServicioImpl servicio = new CineServicioImpl();

            if (servicio.agregarCine(cine, capacidad)) {
                JOptionPane.showMessageDialog(this, "Cine guardado correctamente.");
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar el cine en la base de datos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "La fecha ingresada no es válida.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtFechaInauguracion.setText("");
            txtFechaInauguracion.requestFocus();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtSalas.setText("");
        txtCapacidad.setText("100");
        chkAbierto.setSelected(false);
        txtFechaInauguracion.setText("");
        txtNombre.requestFocus();
    }

}
