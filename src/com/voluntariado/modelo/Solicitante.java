package com.voluntariado.modelo;

public class Solicitante extends Usuario {
    private static final long serialVersionUID = 1L;

    public Solicitante(String id, String nombre, String password) {
        super(id, nombre, password, Rol.SOLICITANTE);
    }
}
