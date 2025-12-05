package com.voluntariado.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    private static final String USUARIOS_FILE = "usuarios.dat";
    private static final String SOLICITUDES_FILE = "solicitudes.dat";

    public static void guardarUsuarios(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USUARIOS_FILE))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Usuario> cargarUsuarios() {
        File file = new File(USUARIOS_FILE);
        if (!file.exists())
            return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void guardarSolicitudes(List<Solicitud> solicitudes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SOLICITUDES_FILE))) {
            oos.writeObject(solicitudes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Solicitud> cargarSolicitudes() {
        File file = new File(SOLICITUDES_FILE);
        if (!file.exists())
            return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Solicitud>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
