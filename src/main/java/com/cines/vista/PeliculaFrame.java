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
import com.cines.modelo.Pelicula;
import com.cines.servicio.impl.PeliculaServicioImpl;

public class PeliculaFrame extends JFrame {

    private JTextField txtTitulo;
    private JTextField txtDuracion;
    private JCheckBox chkDisponible;
    private JTextField txtFechaEstreno;

    private JButton btnGuardar;
    private JButton btnVolver;

    // Constructor del formulario de alta de peliculas
    public PeliculaFrame() {
        configurarVentana();
        inicializarComponentes();
    }

    // Configura el titulo, tamano y comportamiento de cierre de la ventana
    private void configurarVentana() {
        setTitle("Alta de Pelicula");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Crea y ubica los componentes graficos del formulario
    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 20, 15));

        panelFormulario.add(new JLabel("Titulo:", SwingConstants.CENTER));
        txtTitulo = new JTextField();
        panelFormulario.add(txtTitulo);

        panelFormulario.add(new JLabel("Duracion (minutos):", SwingConstants.CENTER));
        txtDuracion = new JTextField();
        panelFormulario.add(txtDuracion);

        panelFormulario.add(new JLabel("Disponible:", SwingConstants.CENTER));
        chkDisponible = new JCheckBox();
        panelFormulario.add(chkDisponible);

        panelFormulario.add(new JLabel("Fecha de estreno (dd/MM/yyyy):", SwingConstants.CENTER));
        txtFechaEstreno = new JTextField();
        panelFormulario.add(txtFechaEstreno);

        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnVolver = new JButton("Volver");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.CENTER);

        // Etiqueta con el nombre del alumno
        JLabel lblAlumno = new JLabel("Bruno Mercado", SwingConstants.RIGHT);
        add(lblAlumno, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardarPelicula());
        btnVolver.addActionListener(e -> volver());
    }

    // Cierra el formulario y regresa al menu principal
    private void volver() {
        this.dispose();
        MainFrame ventanaPrincipal = new MainFrame();
        ventanaPrincipal.setVisible(true);
    }

    // Valida los datos con expresiones regulares y guarda la pelicula en la base
    private void guardarPelicula() {
        try {
            String titulo = txtTitulo.getText().trim();
            String duracionTxt = txtDuracion.getText().trim();
            String fechaTxt = txtFechaEstreno.getText().trim();

            if (titulo.isEmpty() || duracionTxt.isEmpty() || fechaTxt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!ValidarDato.validarTituloPelicula(titulo)) {
                JOptionPane.showMessageDialog(this, "El titulo contiene caracteres no permitidos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!ValidarDato.validarEnteroPositivo(duracionTxt)) {
                JOptionPane.showMessageDialog(this, "La duracion debe ser un numero entero positivo.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!ValidarDato.validarFecha(fechaTxt)) {
                JOptionPane.showMessageDialog(this, "La fecha debe tener el formato dd/MM/yyyy.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int duracion = Integer.parseInt(duracionTxt);
            boolean disponible = chkDisponible.isSelected();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaEstreno = LocalDate.parse(fechaTxt, formatter);

            Pelicula pelicula = new Pelicula(titulo, duracion, disponible, fechaEstreno);

            PeliculaServicioImpl servicio = new PeliculaServicioImpl();

            if (servicio.agregarPelicula(pelicula)) {
                JOptionPane.showMessageDialog(this, "Pelicula guardada correctamente.");
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar la pelicula en la base de datos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "La fecha ingresada no es valida.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtFechaEstreno.setText("");
            txtFechaEstreno.requestFocus();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Limpia todos los campos del formulario
    private void limpiarFormulario() {
        txtTitulo.setText("");
        txtDuracion.setText("");
        chkDisponible.setSelected(false);
        txtFechaEstreno.setText("");
        txtTitulo.requestFocus();
    }

}
