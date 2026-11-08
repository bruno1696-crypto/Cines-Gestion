// Bruno Mercado

package com.cines.modelo;

import java.io.Serializable;
import java.time.LocalDate;


public class Cine implements Serializable {

    private String nombre;
    private int salas;
    private boolean abierto;
    private LocalDate fechaInauguracion;

    public Cine() {
    }

    public Cine(String nombre, int salas, boolean abierto, LocalDate fechaInauguracion) {
        this.nombre = nombre;
        this.salas = salas;
        this.abierto = abierto;
        this.fechaInauguracion = fechaInauguracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalas() {
        return salas;
    }

    public void setSalas(int salas) {
        this.salas = salas;
    }

    public boolean isAbierto() {
        return abierto;
    }

    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    public LocalDate getFechaInauguracion() {
        return fechaInauguracion;
    }

    public void setFechaInauguracion(LocalDate fechaInauguracion) {
        this.fechaInauguracion = fechaInauguracion;
    }

    
    @Override
    public String toString() {
        return "Cine [nombre=" + nombre + ", salas=" + salas + ", abierto=" + abierto
                + ", fechaInauguracion=" + fechaInauguracion + "]";
    }

}
