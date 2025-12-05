package com.voluntariado.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SolicitudFormView extends JDialog {
    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JButton btnGuardar;
    private JButton btnCancelar;

    public SolicitudFormView(Frame parent) {
        super(parent, "Nueva Solicitud", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridLayout(2, 1));

        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(new JLabel("Título:"), BorderLayout.NORTH);
        txtTitulo = new JTextField();
        p1.add(txtTitulo, BorderLayout.CENTER);

        JPanel p2 = new JPanel(new BorderLayout());
        p2.add(new JLabel("Descripción:"), BorderLayout.NORTH);
        txtDescripcion = new JTextArea();
        p2.add(new JScrollPane(txtDescripcion), BorderLayout.CENTER);

        panelForm.add(p1);
        panelForm.add(p2);
        add(panelForm, BorderLayout.CENTER);

        JPanel panelBot = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        panelBot.add(btnGuardar);
        panelBot.add(btnCancelar);
        add(panelBot, BorderLayout.SOUTH);
    }

    public String getTitulo() {
        return txtTitulo.getText();
    }

    public String getDescripcion() {
        return txtDescripcion.getText();
    }

    public void addGuardarListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public void addCancelarListener(ActionListener listener) {
        btnCancelar.addActionListener(listener);
    }
}
