package com.voluntariado.vista;

import com.voluntariado.modelo.Rol;
import com.voluntariado.modelo.Solicitud;
import com.voluntariado.modelo.Usuario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class MainView extends JFrame {
    private JTable tablaSolicitudes;
    private DefaultTableModel modeloTabla;
    private JButton btnNuevaSolicitud;
    private JButton btnAceptarSolicitud;
    private JButton btnSalir;
    private JLabel lblUsuario;
    private Usuario usuarioActual;

    public MainView() {
        setTitle("Mural de Favores - Voluntariado");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top Panel
        JPanel panelTop = new JPanel(new BorderLayout());
        lblUsuario = new JLabel("Usuario: ");
        panelTop.add(lblUsuario, BorderLayout.WEST);
        btnSalir = new JButton("Cerrar Sesión");
        panelTop.add(btnSalir, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);

        // Center Panel (Table)
        String[] columnas = { "ID", "Título", "Descripción", "Fecha", "Estado", "Solicitante", "Voluntario" };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaSolicitudes = new JTable(modeloTabla);
        add(new JScrollPane(tablaSolicitudes), BorderLayout.CENTER);

        // Bottom Panel (Buttons)
        JPanel panelBot = new JPanel();
        btnNuevaSolicitud = new JButton("Nueva Solicitud");
        btnAceptarSolicitud = new JButton("Aceptar Solicitud");

        panelBot.add(btnNuevaSolicitud);
        panelBot.add(btnAceptarSolicitud);
        add(panelBot, BorderLayout.SOUTH);
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
        lblUsuario.setText("Usuario: " + usuario.getNombre() + " [" + usuario.getRol() + "]");
        configurarBotonesPorRol();
    }

    private void configurarBotonesPorRol() {
        btnNuevaSolicitud.setVisible(false);
        btnAceptarSolicitud.setVisible(false);

        if (usuarioActual.getRol() == Rol.SOLICITANTE) {
            btnNuevaSolicitud.setVisible(true);
        } else if (usuarioActual.getRol() == Rol.VOLUNTARIO) {
            btnAceptarSolicitud.setVisible(true);
        } else if (usuarioActual.getRol() == Rol.ADMINISTRADOR) {
            // Admin ve todo, podría tener botones de moderación
            btnNuevaSolicitud.setVisible(true); // Admin también puede crear por prueba
            btnAceptarSolicitud.setVisible(true); // Admin puede forzar aceptación
        }
    }

    public void mostrarSolicitudes(List<Solicitud> solicitudes) {
        modeloTabla.setRowCount(0);
        for (Solicitud s : solicitudes) {
            Object[] fila = {
                    s.getId(),
                    s.getTitulo(),
                    s.getDescripcion(),
                    s.getFecha(),
                    s.getEstado(),
                    s.getSolicitanteId(),
                    s.getVoluntarioId() == null ? "---" : s.getVoluntarioId()
            };
            modeloTabla.addRow(fila);
        }
    }

    public String getSelectedSolicitudId() {
        int row = tablaSolicitudes.getSelectedRow();
        if (row != -1) {
            return (String) modeloTabla.getValueAt(row, 0);
        }
        return null;
    }

    public void addNuevaSolicitudListener(ActionListener listener) {
        btnNuevaSolicitud.addActionListener(listener);
    }

    public void addAceptarSolicitudListener(ActionListener listener) {
        btnAceptarSolicitud.addActionListener(listener);
    }

    public void addSalirListener(ActionListener listener) {
        btnSalir.addActionListener(listener);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
