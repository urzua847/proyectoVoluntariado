package com.voluntariado.controlador;

import com.voluntariado.modelo.*;
import com.voluntariado.vista.MainView;
import com.voluntariado.vista.SolicitudFormView;
import com.voluntariado.vista.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

public class MainController {
    private MainView mainView;
    private Usuario usuarioActual;
    private List<Solicitud> solicitudes;

    public MainController(MainView mainView, Usuario usuarioActual) {
        this.mainView = mainView;
        this.usuarioActual = usuarioActual;
        this.solicitudes = Persistencia.cargarSolicitudes();

        this.mainView.setUsuarioActual(usuarioActual);
        this.mainView.mostrarSolicitudes(solicitudes);

        this.mainView.addNuevaSolicitudListener(new NuevaSolicitudListener());
        this.mainView.addAceptarSolicitudListener(new AceptarSolicitudListener());
        this.mainView.addSalirListener(new SalirListener());

        this.mainView.setVisible(true);
    }

    class NuevaSolicitudListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SolicitudFormView form = new SolicitudFormView(mainView);
            form.addGuardarListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String titulo = form.getTitulo();
                    String desc = form.getDescripcion();

                    if (titulo.isEmpty() || desc.isEmpty()) {
                        JOptionPane.showMessageDialog(form, "Todos los campos son obligatorios");
                        return;
                    }

                    Solicitud nueva = new Solicitud(
                            UUID.randomUUID().toString().substring(0, 8),
                            titulo,
                            desc,
                            usuarioActual.getId());
                    solicitudes.add(nueva);
                    Persistencia.guardarSolicitudes(solicitudes);
                    mainView.mostrarSolicitudes(solicitudes);
                    form.dispose();
                }
            });
            form.addCancelarListener(ev -> form.dispose());
            form.setVisible(true);
        }
    }

    class AceptarSolicitudListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String idSolicitud = mainView.getSelectedSolicitudId();
            if (idSolicitud == null) {
                mainView.showMessage("Seleccione una solicitud");
                return;
            }

            Solicitud solicitudSeleccionada = null;
            for (Solicitud s : solicitudes) {
                if (s.getId().equals(idSolicitud)) {
                    solicitudSeleccionada = s;
                    break;
                }
            }

            if (solicitudSeleccionada != null) {
                if (solicitudSeleccionada.getEstado() != EstadoSolicitud.PENDIENTE) {
                    mainView.showMessage("Solo se pueden aceptar solicitudes pendientes");
                    return;
                }

                // Validar que no sea el mismo solicitante (opcional, pero lógico)
                if (solicitudSeleccionada.getSolicitanteId().equals(usuarioActual.getId())) {
                    mainView.showMessage("No puedes aceptar tu propia solicitud");
                    return;
                }

                solicitudSeleccionada.setEstado(EstadoSolicitud.ACEPTADA);
                solicitudSeleccionada.setVoluntarioId(usuarioActual.getId());
                Persistencia.guardarSolicitudes(solicitudes);
                mainView.mostrarSolicitudes(solicitudes);
                mainView.showMessage("Solicitud aceptada con éxito");
            }
        }
    }

    class SalirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainView.dispose();
            new LoginController(new LoginView());
        }
    }
}
