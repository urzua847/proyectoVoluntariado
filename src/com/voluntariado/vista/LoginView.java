package com.voluntariado.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private JButton btnRegistro;

    public LoginView() {
        setTitle("Login - Voluntariado");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Usuario:"));
        txtUser = new JTextField();
        add(txtUser);

        add(new JLabel("Contraseña:"));
        txtPass = new JPasswordField();
        add(txtPass);

        btnLogin = new JButton("Ingresar");
        add(btnLogin);

        btnRegistro = new JButton("Registrarse");
        add(btnRegistro);
    }

    public String getUsername() {
        return txtUser.getText();
    }

    public String getPassword() {
        return new String(txtPass.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    public void addRegistroListener(ActionListener listener) {
        btnRegistro.addActionListener(listener);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
