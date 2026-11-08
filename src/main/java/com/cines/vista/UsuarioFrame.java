package com.cines.vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.cines.servicio.UsuarioServicio;
import com.cines.servicio.impl.UsuarioServicioImpl;

public class UsuarioFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnIngresar;

    public UsuarioFrame() {
        setTitle("Acceso al Sistema - Cines Gestión");
        setSize(320, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Inicialización de componentes
        JLabel lblUsuario = new JLabel("  Usuario:");
        txtUsuario = new JTextField();

        JLabel lblClave = new JLabel("  Clave:");
        txtClave = new JPasswordField();

        btnIngresar = new JButton("Ingresar");

        // Agregar elementos al panel principal
        add(lblUsuario);
        add(txtUsuario);
        add(lblClave);
        add(txtClave);
        add(new JLabel("")); // Espacio vacío para alineación del botón
        add(btnIngresar);

        // Lógica del evento del botón Ingresar
        btnIngresar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText().trim();
                String clave = new String(txtClave.getPassword()).trim();

                System.out.println(">>> GUI - Usuario leído: '" + usuario + "'");
                System.out.println(">>> GUI - Clave leída: '" + clave + "' (Largo: " + clave.length() + ")");
                
                // Instanciamos el servicio correspondiente
                UsuarioServicio usuarioServicio = new UsuarioServicioImpl();
                
                boolean ingresoPermitido = usuarioServicio.validarUsuario(usuario, clave);

                if (ingresoPermitido) {
                    JOptionPane.showMessageDialog(UsuarioFrame.this, "¡Ingreso exitoso!");
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                    dispose(); // Cierra la ventana de login
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