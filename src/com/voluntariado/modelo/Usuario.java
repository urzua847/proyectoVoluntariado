package com.voluntariado.modelo;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String id;
    protected String nombre;
    protected String password;
    protected Rol rol;

    public Usuario(String id, String nombre, String password, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public Rol getRol() {
        return rol;
    }

    @Override
    public String toString() {
        return nombre + " (" + rol + ")";
    }
}
