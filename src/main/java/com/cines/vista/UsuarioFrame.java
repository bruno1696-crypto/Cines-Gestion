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

    // Construye el formulario de acceso.
    public UsuarioFrame() {
        setTitle("Acceso al Sistema - Cines Gestión");
        setSize(320, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel lblUsuario = new JLabel("  Usuario:");
        txtUsuario = new JTextField();

        JLabel lblClave = new JLabel("  Clave:");
        txtClave = new JPasswordField();

        btnIngresar = new JButton("Ingresar");

        panelFormulario.add(lblUsuario);
        panelFormulario.add(txtUsuario);
        panelFormulario.add(lblClave);
        panelFormulario.add(txtClave);
        panelFormulario.add(new JLabel(""));
        panelFormulario.add(btnIngresar);

        add(panelFormulario, BorderLayout.CENTER);

        JLabel lblAlumno = new JLabel("Bruno Mercado", SwingConstants.RIGHT);
        add(lblAlumno, BorderLayout.SOUTH);

        btnIngresar.addActionListener(new ActionListener() {

            // Valida los datos y abre el menú si el login es correcto.
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText().trim();
                String clave = new String(txtClave.getPassword()).trim();

                if (!ValidarDato.validarUsuario(usuario)) {
                    JOptionPane.showMessageDialog(UsuarioFrame.this,
                            "El usuario debe tener entre 3 y 50 caracteres (letras, números, punto, guion o guion bajo).",
                            "Error de validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!ValidarDato.validarClave(clave)) {
                    JOptionPane.showMessageDialog(UsuarioFrame.this,
                            "La clave debe tener entre 4 y 50 caracteres sin espacios.",
                            "Error de validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                UsuarioServicio usuarioServicio = new UsuarioServicioImpl();

                boolean ingresoPermitido = usuarioServicio.validarUsuario(usuario, clave);

                if (ingresoPermitido) {
                    JOptionPane.showMessageDialog(UsuarioFrame.this, "Ingreso exitoso.");
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(UsuarioFrame.this,
                            "Usuario o clave incorrectos",
                            "Error de Autenticación",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
