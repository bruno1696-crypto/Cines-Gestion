// Bruno Mercado
package com.cines.vista;

import com.cines.dao.CineDAO;
import com.cines.dao.PeliculaDAO;
import com.cines.dao.SalaDAO;
import com.cines.dao.FuncionDAO;
import com.cines.modelo.Cine;
import com.cines.modelo.Pelicula;
import com.cines.modelo.Funcion;
import com.cines.modelo.Sala;
import com.cines.servicio.impl.FuncionServicioImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FuncionFrame extends JFrame {

    private JComboBox<Cine> cmbCines;
    private JComboBox<Sala> cmbSalas;
    private JComboBox<Pelicula> cmbPeliculas;
    private JTextField txtHorario;
    private JTextField txtPrecio;
    private JTable tablaFunciones;
    private DefaultTableModel modeloTabla;
    
    private CineDAO cineDAO = new CineDAO();
    private SalaDAO salaDAO = new SalaDAO();
    private PeliculaDAO peliculaDAO = new PeliculaDAO();
    private FuncionServicioImpl funcionServicio = new FuncionServicioImpl();

    public FuncionFrame() {
        setTitle("Gestión de Funciones - Cines");
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

        panelForm.add(new JLabel("Seleccionar Película:"));
        cmbPeliculas = new JComboBox<>();
        panelForm.add(cmbPeliculas);

        panelForm.add(new JLabel("Horario (AAAA-MM-DD HH:MM):"));
        txtHorario = new JTextField("2026-11-10 18:00");
        panelForm.add(txtHorario);

        panelForm.add(new JLabel("Precio ($):"));
        txtPrecio = new JTextField();
        panelForm.add(txtPrecio);

        add(panelForm, BorderLayout.NORTH);

        // --- PANEL DE TABLA (CENTRO) ---
        modeloTabla = new DefaultTableModel(new String[]{"ID", "ID Película", "ID Sala", "Horario", "Precio"}, 0);
        tablaFunciones = new JTable(modeloTabla);
        add(new JScrollPane(tablaFunciones), BorderLayout.CENTER);

        // --- PANEL DE BOTONES (SUR) ---
        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Registrar Función");
        JButton btnActualizar = new JButton("Actualizar Lista");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        add(panelBotones, BorderLayout.SOUTH);

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

        btnGuardar.addActionListener(e -> registrarFuncion());
        btnActualizar.addActionListener(e -> cargarTablaFunciones());

        cargarTablaFunciones();
    }

    private void cargarCines() {
        cmbCines.removeAllItems();
        List<Cine> cines = cineDAO.obtenerCines();
        for (Cine cine : cines) {
            cmbCines.addItem(cine);
        }
    }

    private void cargarSalasPorCine(int cineId) {
        cmbSalas.removeAllItems();
        List<Sala> salas = salaDAO.listarSalasPorCine(cineId);
        for (Sala sala : salas) {
            cmbSalas.addItem(sala);
        }
    }

    private void cargarPeliculas() {
        cmbPeliculas.removeAllItems();
        List<Pelicula> peliculas = peliculaDAO.obtenerPeliculas();
        for (Pelicula p : peliculas) {
            cmbPeliculas.addItem(p);
        }
    }

    private void registrarFuncion() {
        try {
            Sala salaSeleccionada = (Sala) cmbSalas.getSelectedItem();
            Pelicula peliculaSeleccionada = (Pelicula) cmbPeliculas.getSelectedItem();

            if (salaSeleccionada == null || peliculaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una sala y una película válidas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double precio = Double.parseDouble(txtPrecio.getText().trim());
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime horario = LocalDateTime.parse(txtHorario.getText().trim(), formatter);

            Funcion f = new Funcion();
            f.setPeliculaId(peliculaSeleccionada.getId());
            f.setSalaId(salaSeleccionada.getId());
            f.setHorario(horario);
            f.setPrecio(precio);

            boolean exito = funcionServicio.crearFuncion(f);
            if (exito) {
                JOptionPane.showMessageDialog(this, "¡Función registrada con éxito!");
                cargarTablaFunciones();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la función en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el formato de los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarTablaFunciones() {
        modeloTabla.setRowCount(0);
        List<Funcion> lista = funcionServicio.obtenerFunciones();
        for (Funcion f : lista) {
            modeloTabla.addRow(new Object[]{
                f.getId(),
                f.getPeliculaId(),
                f.getSalaId(),
                f.getHorario(),
                f.getPrecio()
            });
        }
    }
}