// Bruno Mercado

package com.cines.modelo;

import java.time.LocalDate;


public class Pelicula {

    private int id;
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

    public Pelicula(int id, String titulo, int duracion, boolean disponible, LocalDate fechaEstreno) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.disponible = disponible;
        this.fechaEstreno = fechaEstreno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Pelicula [id=" + id + ", titulo=" + titulo + ", duracion=" + duracion + ", disponible=" + disponible
                + ", fechaEstreno=" + fechaEstreno + "]";
    }

}
