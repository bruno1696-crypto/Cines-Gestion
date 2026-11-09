// Bruno Mercado

package com.cines.modelo;

import java.time.LocalDate;

public class Cine {

    private int id;
    private String nombre;
    private int salas;
    private boolean abierto;
    private LocalDate fechaInauguracion;

    // Constructor vacío: lo usa el DAO al mapear la fila.
    public Cine() {
    }

    // Constructor para dar de alta un cine nuevo.
    public Cine(String nombre, int salas, boolean abierto, LocalDate fechaInauguracion) {
        this.nombre = nombre;
        this.salas = salas;
        this.abierto = abierto;
        this.fechaInauguracion = fechaInauguracion;
    }

    // Constructor con todos los datos, usado al leer de la base.
    public Cine(int id, String nombre, int salas, boolean abierto, LocalDate fechaInauguracion) {
        this.id = id;
        this.nombre = nombre;
        this.salas = salas;
        this.abierto = abierto;
        this.fechaInauguracion = fechaInauguracion;
    }

    // Devuelve el id del cine.
    public int getId() {
        return id;
    }

    // Asigna el id que viene de la base.
    public void setId(int id) {
        this.id = id;
    }

    // Devuelve el nombre del cine.
    public String getNombre() {
        return nombre;
    }

    // Cambia el nombre del cine.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve la cantidad de salas declaradas.
    public int getSalas() {
        return salas;
    }

    // Asigna la cantidad de salas.
    public void setSalas(int salas) {
        this.salas = salas;
    }

    // Indica si el cine está abierto.
    public boolean isAbierto() {
        return abierto;
    }

    // Actualiza el estado de apertura.
    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    // Devuelve la fecha de inauguración.
    public LocalDate getFechaInauguracion() {
        return fechaInauguracion;
    }

    // Asigna la fecha de inauguración.
    public void setFechaInauguracion(LocalDate fechaInauguracion) {
        this.fechaInauguracion = fechaInauguracion;
    }

    // Texto que se muestra en los listados y combos.
    @Override
    public String toString() {
        return nombre + " (" + salas + " salas, " + (abierto ? "abierto" : "cerrado") + ")";
    }

}
