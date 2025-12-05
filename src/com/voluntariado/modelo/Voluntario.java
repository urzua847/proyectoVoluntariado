package com.voluntariado.modelo;

public class Voluntario extends Usuario {
    private static final long serialVersionUID = 1L;

    public Voluntario(String id, String nombre, String password) {
        super(id, nombre, password, Rol.VOLUNTARIO);
    }
}
