// Bruno Mercado

package com.cines.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.cines.control.ValidarDato;
import com.cines.modelo.Cine;
import com.cines.modelo.Funcion;
import com.cines.modelo.Pelicula;
import com.cines.modelo.Sala;
import com.cines.servicio.impl.CineServicioImpl;
import com.cines.servicio.impl.FuncionServicioImpl;
import com.cines.servicio.impl.PeliculaServicioImpl;
import com.cines.servicio.impl.SalaServicioImpl;

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

    public FuncionFrame() {
        setTitle("Gestión de Funciones - Cines");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Seleccionar Cine:"));
        cmbCines = new JComboBox<>();
        panelForm.add(cmbCines);

        panelForm.add(new JLabel("Seleccionar Sala:"));
        cmbSalas = new JComboBox<>();
        panelForm.add(cmbSalas);

        panelForm.add(new JLabel("Seleccionar Película:"));
        cmbPeliculas = new JComboBox<>();
        panelForm.add(cmbPeliculas);

        panelForm.add(new JLabel("Horario (DD-MM-AAAA HH:MM):"));
        txtHorario = new JTextField("10-11-2026 18:00");
        panelForm.add(txtHorario);

        panelForm.add(new JLabel("Precio ($):"));
        txtPrecio = new JTextField();
        panelForm.add(txtPrecio);

        add(panelForm, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "ID Película", "ID Sala", "Horario", "Precio"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaFunciones = new JTable(modeloTabla);
        add(new JScrollPane(tablaFunciones), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Registrar Función");
        JButton btnActualizar = new JButton("Actualizar Lista");
        JButton btnEliminar = new JButton("Dar de Baja Función");
        JButton btnVolver = new JButton("Volver");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);

        JLabel lblAlumno = new JLabel("Bruno Mercado", SwingConstants.RIGHT);
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(panelBotones, BorderLayout.CENTER);
        panelInferior.add(lblAlumno, BorderLayout.EAST);
        add(panelInferior, BorderLayout.SOUTH);

        cargarCines();
        cargarPeliculas();

        cmbCines.addActionListener(e -> {
            Cine cineSeleccionado = (Cine) cmbCines.getSelectedItem();
            if (cineSeleccionado != null) {
                cargarSalasPorCine(cineSeleccionado.getId());
            }
        });

        // Al llenar el combo antes de registrar el listener, el primer cine queda
        // seleccionado pero no dispara el evento. Cargo sus salas explícitamente.
        Cine cineInicial = (Cine) cmbCines.getSelectedItem();
        if (cineInicial != null) {
            cargarSalasPorCine(cineInicial.getId());
        }

        btnGuardar.addActionListener(e -> registrarFuncion());
        btnActualizar.addActionListener(e -> cargarTablaFunciones());
        btnEliminar.addActionListener(e -> darDeBajaFuncion());
        btnVolver.addActionListener(e -> volver());

        cargarTablaFunciones();
    }

    private void volver() {
        this.dispose();
        MainFrame ventanaPrincipal = new MainFrame();
        ventanaPrincipal.setVisible(true);
    }

    private void cargarCines() {
        cmbCines.removeAllItems();
        ArrayList<Cine> cines = cineServicio.listarCines();
        for (Cine cine : cines) {
            cmbCines.addItem(cine);
        }
    }

    private void cargarSalasPorCine(int cineId) {
        cmbSalas.removeAllItems();
        ArrayList<Sala> salas = salaServicio.listarSalasPorCine(cineId);
        for (Sala sala : salas) {
            cmbSalas.addItem(sala);
        }
    }

    private void cargarPeliculas() {
        cmbPeliculas.removeAllItems();
        ArrayList<Pelicula> peliculas = peliculaServicio.listarPeliculas();
        for (Pelicula p : peliculas) {
            cmbPeliculas.addItem(p);
        }
    }

    private void registrarFuncion() {
        try {
            Sala salaSeleccionada = (Sala) cmbSalas.getSelectedItem();
            Pelicula peliculaSeleccionada = (Pelicula) cmbPeliculas.getSelectedItem();

            if (salaSeleccionada == null || peliculaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una sala y una película válidas.",
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
                JOptionPane.showMessageDialog(this, "El precio debe ser un número positivo con hasta dos decimales.",
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
                JOptionPane.showMessageDialog(this, "Función registrada con éxito.");
                cargarTablaFunciones();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la función en la base de datos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "El horario ingresado no es válido.", "Error",
                    JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el formato de los datos: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void darDeBajaFuncion() {
        int fila = tablaFunciones.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una función de la tabla para dar de baja.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "Confirma dar de baja la función ID " + id + "?",
                "Confirmar baja", JOptionPane.YES_NO_OPTION);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        if (funcionServicio.eliminarFuncion(id)) {
            JOptionPane.showMessageDialog(this, "Función dada de baja correctamente.");
            cargarTablaFunciones();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo dar de baja la función.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

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
