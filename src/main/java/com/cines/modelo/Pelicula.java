// Bruno Mercado

package com.cines.modelo;

import java.io.Serializable;
import java.time.LocalDate;


public class Pelicula implements Serializable {

    private String titulo;
    private int duracion;
    private boolean disponible;
    private LocalDate fechaEstreno;

    public Pelicula() {
    }

    public Pelicula(String titulo, int duracion, boolean disponible, LocalDate fechaEstreno) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.disponible = disponible;
        this.fechaEstreno = fechaEstreno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }


    @Override
    public String toString() {
        return "Pelicula [titulo=" + titulo + ", duracion=" + duracion + ", disponible=" + disponible
                + ", fechaEstreno=" + fechaEstreno + "]";
    }

}
