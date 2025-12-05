package com.voluntariado.controlador;

import com.voluntariado.modelo.*;
import com.voluntariado.vista.LoginView;
import com.voluntariado.vista.RegistroView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginController {
    private LoginView loginView;
    private List<Usuario> usuarios;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.usuarios = Persistencia.cargarUsuarios();

        // Crear admin por defecto si no existe nadie
        if (usuarios.isEmpty()) {
            usuarios.add(new Administrador("admin", "Administrador", "admin123"));
            Persistencia.guardarUsuarios(usuarios);
        }

        this.loginView.addLoginListener(new LoginListener());
        this.loginView.addRegistroListener(new RegistroListener());
        this.loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = loginView.getUsername();
            String pass = loginView.getPassword();

            Usuario usuarioEncontrado = null;
            for (Usuario u : usuarios) {
                if (u.getId().equals(user) && u.getPassword().equals(pass)) {
                    usuarioEncontrado = u;
                    break;
                }
            }

            if (usuarioEncontrado != null) {
                loginView.dispose();
                new MainController(new com.voluntariado.vista.MainView(), usuarioEncontrado);
            } else {
                loginView.showMessage("Credenciales incorrectas");
            }
        }
    }

    class RegistroListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            RegistroView registroView = new RegistroView();
            registroView.addRegistrarListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String id = registroView.getId();
                    String nombre = registroView.getNombre();
                    String pass = registroView.getPassword();
                    Rol rol = registroView.getRol();

                    if (id.isEmpty() || nombre.isEmpty() || pass.isEmpty()) {
                        registroView.showMessage("Todos los campos son obligatorios");
                        return;
                    }

                    // Verificar si ya existe
                    for (Usuario u : usuarios) {
                        if (u.getId().equals(id)) {
                            registroView.showMessage("El ID de usuario ya existe");
                            return;
                        }
                    }

                    Usuario nuevoUsuario = null;
                    switch (rol) {
                        case SOLICITANTE:
                            nuevoUsuario = new Solicitante(id, nombre, pass);
                            break;
                        case VOLUNTARIO:
                            nuevoUsuario = new Voluntario(id, nombre, pass);
                            break;
                        case ADMINISTRADOR:
                            nuevoUsuario = new Administrador(id, nombre, pass);
                            break;
                    }

                    usuarios.add(nuevoUsuario);
                    Persistencia.guardarUsuarios(usuarios);
                    registroView.showMessage("Usuario registrado con éxito");
                    registroView.dispose();
                }
            });
            registroView.addCancelarListener(e1 -> registroView.dispose());
            registroView.setVisible(true);
        }
    }
}
