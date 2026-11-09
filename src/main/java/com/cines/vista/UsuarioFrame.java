// Bruno Mercado
package com.cines.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.cines.control.ValidarDato;
import com.cines.servicio.UsuarioServicio;
import com.cines.servicio.impl.UsuarioServicioImpl;

public class UsuarioFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnIngresar;

    // Constructor de la ventana de acceso (login)
    public UsuarioFrame() {
        setTitle("Acceso al Sistema - Cines Gestion");
        setSize(320, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));

        // Inicializacion de componentes
        JLabel lblUsuario = new JLabel("  Usuario:");
        txtUsuario = new JTextField();

        JLabel lblClave = new JLabel("  Clave:");
        txtClave = new JPasswordField();

        btnIngresar = new JButton("Ingresar");

        // Agregar elementos al panel principal
        panelFormulario.add(lblUsuario);
        panelFormulario.add(txtUsuario);
        panelFormulario.add(lblClave);
        panelFormulario.add(txtClave);
        panelFormulario.add(new JLabel("")); // Espacio vacio para alineacion del boton
        panelFormulario.add(btnIngresar);

        add(panelFormulario, BorderLayout.CENTER);

        JLabel lblAlumno = new JLabel("Bruno Mercado", SwingConstants.RIGHT);
        add(lblAlumno, BorderLayout.SOUTH);

        // Logica del evento del boton Ingresar
        btnIngresar.addActionListener(new ActionListener() {

            // Valida el formato de los datos y controla el acceso del usuario
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText().trim();
                String clave = new String(txtClave.getPassword()).trim();

                if (!ValidarDato.validarUsuario(usuario)) {
                    JOptionPane.showMessageDialog(UsuarioFrame.this,
                            "El usuario debe tener entre 3 y 50 caracteres (letras, numeros, punto, guion o guion bajo).",
                            "Error de validacion", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!ValidarDato.validarClave(clave)) {
                    JOptionPane.showMessageDialog(UsuarioFrame.this,
                            "La clave debe tener entre 4 y 50 caracteres sin espacios.",
                            "Error de validacion", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                UsuarioServicio usuarioServicio = new UsuarioServicioImpl();

                boolean ingresoPermitido = usuarioServicio.validarUsuario(usuario, clave);

                if (ingresoPermitido) {
                    JOptionPane.showMessageDialog(UsuarioFrame.this, "Ingreso exitoso.");
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                    dispose(); // Cierra la ventana de login
                } else {
                    JOptionPane.showMessageDialog(UsuarioFrame.this,
                            "Usuario o clave incorrectos",
                            "Error de Autenticacion",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
