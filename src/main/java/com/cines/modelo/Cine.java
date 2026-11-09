// Bruno Mercado

package com.cines.modelo;

import java.time.LocalDate;

public class Cine {

    private int id;
    private String nombre;
    private int salas;
    private boolean abierto;
    private LocalDate fechaInauguracion;

    // Constructor vacio requerido para mapear filas de la base de datos
    public Cine() {
    }

    // Constructor con parametros sin id (para altas nuevas)
    public Cine(String nombre, int salas, boolean abierto, LocalDate fechaInauguracion) {
        this.nombre = nombre;
        this.salas = salas;
        this.abierto = abierto;
        this.fechaInauguracion = fechaInauguracion;
    }

    // Constructor con parametros incluyendo el id
    public Cine(int id, String nombre, int salas, boolean abierto, LocalDate fechaInauguracion) {
        this.id = id;
        this.nombre = nombre;
        this.salas = salas;
        this.abierto = abierto;
        this.fechaInauguracion = fechaInauguracion;
    }

    // Devuelve el identificador del cine
    public int getId() {
        return id;
    }

    // Asigna el identificador del cine
    public void setId(int id) {
        this.id = id;
    }

    // Devuelve el nombre del cine
    public String getNombre() {
        return nombre;
    }

    // Asigna el nombre del cine
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve la cantidad de salas del cine
    public int getSalas() {
        return salas;
    }

    // Asigna la cantidad de salas del cine
    public void setSalas(int salas) {
        this.salas = salas;
    }

    // Indica si el cine esta abierto
    public boolean isAbierto() {
        return abierto;
    }

    // Asigna si el cine esta abierto
    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    // Devuelve la fecha de inauguracion del cine
    public LocalDate getFechaInauguracion() {
        return fechaInauguracion;
    }

    // Asigna la fecha de inauguracion del cine
    public void setFechaInauguracion(LocalDate fechaInauguracion) {
        this.fechaInauguracion = fechaInauguracion;
    }

    // Devuelve una representacion en texto con todos los atributos del cine
    @Override
    public String toString() {
        return "Cine [id=" + id + ", nombre=" + nombre + ", salas=" + salas + ", abierto=" + abierto
                + ", fechaInauguracion=" + fechaInauguracion + "]";
    }

}
