package com.voluntariado.vista;

import com.voluntariado.modelo.Rol;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegistroView extends JFrame {
    private JTextField txtId;
    private JTextField txtNombre;
    private JPasswordField txtPass;
    private JComboBox<Rol> cmbRol;
    private JButton btnRegistrar;
    private JButton btnCancelar;

    public RegistroView() {
        setTitle("Registro - Voluntariado");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("ID:"));
        txtId = new JTextField();
        add(txtId);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Contraseña:"));
        txtPass = new JPasswordField();
        add(txtPass);

        add(new JLabel("Rol:"));
        cmbRol = new JComboBox<>(Rol.values());
        add(cmbRol);

        btnRegistrar = new JButton("Registrar");
        add(btnRegistrar);

        btnCancelar = new JButton("Cancelar");
        add(btnCancelar);
    }

    public String getId() {
        return txtId.getText();
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getPassword() {
        return new String(txtPass.getPassword());
    }

    public Rol getRol() {
        return (Rol) cmbRol.getSelectedItem();
    }

    public void addRegistrarListener(ActionListener listener) {
        btnRegistrar.addActionListener(listener);
    }

    public void addCancelarListener(ActionListener listener) {
        btnCancelar.addActionListener(listener);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
