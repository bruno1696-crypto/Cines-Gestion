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

import com.cines.modelo.Cine;
import com.cines.servicio.impl.CineServicioImpl;

public class CineFrame extends JFrame {

    private JTextField txtNombre;
    private JTextField txtSalas;
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
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 20, 15));

        panelFormulario.add(new JLabel("Nombre:", SwingConstants.CENTER));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Cantidad de salas:", SwingConstants.CENTER));
        txtSalas = new JTextField();
        panelFormulario.add(txtSalas);

        panelFormulario.add(new JLabel("Abierto:", SwingConstants.CENTER));
        chkAbierto = new JCheckBox();
        panelFormulario.add(chkAbierto);

        panelFormulario.add(new JLabel("Fecha de inauguracion (dd/MM/yyyy):", SwingConstants.CENTER));
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

        // etiqueta con mi nombre
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

            // chequeo de campos vacios 
            if (nombre.isEmpty() || txtSalas.getText().trim().isEmpty()
                    || txtFechaInauguracion.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int salas = Integer.parseInt(txtSalas.getText().trim());
            boolean abierto = chkAbierto.isSelected();

            // pasar fecha de String a LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaInauguracion = LocalDate.parse(txtFechaInauguracion.getText().trim(), formatter);

            Cine cine = new Cine(nombre, salas, abierto, fechaInauguracion);

            CineServicioImpl servicio = new CineServicioImpl();

            if (servicio.agregarCine(cine)) {
                JOptionPane.showMessageDialog(this, "Cine guardado correctamente.");
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar el cine en la base de datos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            // cheqeo si salas es numerico
            JOptionPane.showMessageDialog(this, "La cantidad de salas debe ser numerica.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtSalas.setText("");
            txtSalas.requestFocus();

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "La fecha debe tener el formato dd/MM/yyyy.", "Error",
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
        chkAbierto.setSelected(false);
        txtFechaInauguracion.setText("");
        txtNombre.requestFocus();
    }

}
