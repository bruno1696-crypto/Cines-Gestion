// Bruno Mercado
package com.cines.vista;

import com.cines.control.ValidarDato;
import com.cines.modelo.Cine;
import com.cines.modelo.Pelicula;
import com.cines.modelo.Funcion;
import com.cines.modelo.Sala;
import com.cines.servicio.impl.CineServicioImpl;
import com.cines.servicio.impl.FuncionServicioImpl;
import com.cines.servicio.impl.PeliculaServicioImpl;
import com.cines.servicio.impl.SalaServicioImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FuncionFrame extends JFrame {

    private static final DateTimeFormatter FORMATO_HORARIO = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private JComboBox<Cine> cmbCines;
    private JComboBox<Sala> cmbSalas;
    private JComboBox<Pelicula> cmbPeliculas;
    private JTextField txtHorario;
    private JTextField txtPrecio;
    private JTable tablaFunciones;
    private DefaultTableModel modeloTabla;

    private final CineServicioImpl cineServicio = new CineServicioImpl();
    private final SalaServicioImpl salaServicio = new SalaServicioImpl();
    private final PeliculaServicioImpl peliculaServicio = new PeliculaServicioImpl();
    private final FuncionServicioImpl funcionServicio = new FuncionServicioImpl();

    // Constructor del formulario de gestion de funciones
    public FuncionFrame() {
        setTitle("Gestion de Funciones - Cines");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL DE FORMULARIO (NORTE) ---
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Seleccionar Cine:"));
        cmbCines = new JComboBox<>();
        panelForm.add(cmbCines);

        panelForm.add(new JLabel("Seleccionar Sala:"));
        cmbSalas = new JComboBox<>();
        panelForm.add(cmbSalas);

        panelForm.add(new JLabel("Seleccionar Pelicula:"));
        cmbPeliculas = new JComboBox<>();
        panelForm.add(cmbPeliculas);

        panelForm.add(new JLabel("Horario (DD-MM-AAAA HH:MM):"));
        txtHorario = new JTextField("10-11-2026 18:00");
        panelForm.add(txtHorario);

        panelForm.add(new JLabel("Precio ($):"));
        txtPrecio = new JTextField();
        panelForm.add(txtPrecio);

        add(panelForm, BorderLayout.NORTH);

        // --- PANEL DE TABLA (CENTRO) ---
        modeloTabla = new DefaultTableModel(new String[]{"ID", "ID Pelicula", "ID Sala", "Horario", "Precio"}, 0) {
            // Impide que el usuario edite las celdas de la tabla
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaFunciones = new JTable(modeloTabla);
        add(new JScrollPane(tablaFunciones), BorderLayout.CENTER);

        // --- PANEL DE BOTONES (SUR) ---
        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Registrar Funcion");
        JButton btnActualizar = new JButton("Actualizar Lista");
        JButton btnEliminar = new JButton("Dar de Baja Funcion");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        JLabel lblAlumno = new JLabel("Bruno Mercado", SwingConstants.RIGHT);
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(panelBotones, BorderLayout.CENTER);
        panelInferior.add(lblAlumno, BorderLayout.EAST);
        add(panelInferior, BorderLayout.SOUTH);

        // --- CARGA INICIAL Y EVENTOS ---
        cargarCines();
        cargarPeliculas();

        // Evento en cascada para las salas del cine seleccionado
        cmbCines.addActionListener(e -> {
            Cine cineSeleccionado = (Cine) cmbCines.getSelectedItem();
            if (cineSeleccionado != null) {
                cargarSalasPorCine(cineSeleccionado.getId());
            }
        });

        // Al llenar el combo antes de registrar el listener, el primer cine queda
        // seleccionado pero no dispara el evento. Cargo sus salas explicitamente.
        Cine cineInicial = (Cine) cmbCines.getSelectedItem();
        if (cineInicial != null) {
            cargarSalasPorCine(cineInicial.getId());
        }

        btnGuardar.addActionListener(e -> registrarFuncion());
        btnActualizar.addActionListener(e -> cargarTablaFunciones());
        btnEliminar.addActionListener(e -> darDeBajaFuncion());

        cargarTablaFunciones();
    }

    // Carga el combo de cines a partir del servicio de cines
    private void cargarCines() {
        cmbCines.removeAllItems();
        ArrayList<Cine> cines = cineServicio.listarCines();
        for (Cine cine : cines) {
            cmbCines.addItem(cine);
        }
    }

    // Carga el combo de salas correspondientes al cine seleccionado
    private void cargarSalasPorCine(int cineId) {
        cmbSalas.removeAllItems();
        ArrayList<Sala> salas = salaServicio.listarSalasPorCine(cineId);
        for (Sala sala : salas) {
            cmbSalas.addItem(sala);
        }
    }

    // Carga el combo de peliculas a partir del servicio de peliculas
    private void cargarPeliculas() {
        cmbPeliculas.removeAllItems();
        ArrayList<Pelicula> peliculas = peliculaServicio.listarPeliculas();
        for (Pelicula p : peliculas) {
            cmbPeliculas.addItem(p);
        }
    }

    // Valida el horario y el precio con expresiones regulares y registra la funcion
    private void registrarFuncion() {
        try {
            Sala salaSeleccionada = (Sala) cmbSalas.getSelectedItem();
            Pelicula peliculaSeleccionada = (Pelicula) cmbPeliculas.getSelectedItem();

            if (salaSeleccionada == null || peliculaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una sala y una pelicula validas.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String horarioTxt = txtHorario.getText().trim();
            String precioTxt = txtPrecio.getText().trim().replace(',', '.');

            if (!ValidarDato.validarHorario(horarioTxt)) {
                JOptionPane.showMessageDialog(this, "El horario debe tener el formato DD-MM-AAAA HH:MM.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!ValidarDato.validarPrecio(precioTxt)) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un numero positivo con hasta dos decimales.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double precio = Double.parseDouble(precioTxt);

            if (precio <= 0) {
                JOptionPane.showMessageDialog(this, "El precio debe ser mayor a cero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDateTime horario = LocalDateTime.parse(horarioTxt, FORMATO_HORARIO);

            Funcion f = new Funcion();
            f.setPeliculaId(peliculaSeleccionada.getId());
            f.setSalaId(salaSeleccionada.getId());
            f.setHorario(horario);
            f.setPrecio(precio);

            boolean exito = funcionServicio.crearFuncion(f);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Funcion registrada con exito.");
                cargarTablaFunciones();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la funcion en la base de datos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "El horario ingresado no es valido.", "Error",
                    JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el formato de los datos: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Elimina la funcion seleccionada en la tabla
    private void darDeBajaFuncion() {
        int fila = tablaFunciones.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una funcion de la tabla para dar de baja.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "Confirma dar de baja la funcion ID " + id + "?",
                "Confirmar baja", JOptionPane.YES_NO_OPTION);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        if (funcionServicio.eliminarFuncion(id)) {
            JOptionPane.showMessageDialog(this, "Funcion dada de baja correctamente.");
            cargarTablaFunciones();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo dar de baja la funcion.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Carga en la tabla el listado de funciones obtenido del servicio
    private void cargarTablaFunciones() {
        modeloTabla.setRowCount(0);
        ArrayList<Funcion> lista = funcionServicio.obtenerFunciones();
        for (Funcion f : lista) {
            modeloTabla.addRow(new Object[]{
                f.getId(),
                f.getPeliculaId(),
                f.getSalaId(),
                f.getHorario() != null ? f.getHorario().format(FORMATO_HORARIO) : "",
                f.getPrecio()
            });
        }
    }
}
