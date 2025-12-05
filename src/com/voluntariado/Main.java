package com.voluntariado;

import com.voluntariado.controlador.LoginController;
import com.voluntariado.vista.LoginView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Intentar poner un LookAndFeel más bonito si es posible
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                // Si falla, usar el por defecto
            }
            new LoginController(new LoginView());
        });
    }
}
