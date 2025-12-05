package com.voluntariado.modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Solicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String titulo;
    private String descripcion;
    private LocalDate fecha;
    private EstadoSolicitud estado;
    private String solicitanteId;
    private String voluntarioId; // Puede ser null si nadie la ha aceptado

    public Solicitud(String id, String titulo, String descripcion, String solicitanteId) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
        this.estado = EstadoSolicitud.PENDIENTE;
        this.solicitanteId = solicitanteId;
        this.voluntarioId = null;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public String getSolicitanteId() {
        return solicitanteId;
    }

    public String getVoluntarioId() {
        return voluntarioId;
    }

    public void setVoluntarioId(String voluntarioId) {
        this.voluntarioId = voluntarioId;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%s)", estado, titulo, fecha, solicitanteId);
    }
}
