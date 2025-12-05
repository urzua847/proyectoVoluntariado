package com.voluntariado.modelo;

public class Administrador extends Usuario {
    private static final long serialVersionUID = 1L;

    public Administrador(String id, String nombre, String password) {
        super(id, nombre, password, Rol.ADMINISTRADOR);
    }
}
