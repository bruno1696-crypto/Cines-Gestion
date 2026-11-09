// Bruno Mercado

package com.cines.modelo;

import java.time.LocalDate;

public class Pelicula {

    private int id;
    private String titulo;
    private int duracion;
    private boolean disponible;
    private LocalDate fechaEstreno;

    // Constructor vacio requerido para mapear filas de la base de datos
    public Pelicula() {
    }

    // Constructor con parametros sin id (para altas nuevas)
    public Pelicula(String titulo, int duracion, boolean disponible, LocalDate fechaEstreno) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.disponible = disponible;
        this.fechaEstreno = fechaEstreno;
    }

    // Constructor con parametros incluyendo el id
    public Pelicula(int id, String titulo, int duracion, boolean disponible, LocalDate fechaEstreno) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.disponible = disponible;
        this.fechaEstreno = fechaEstreno;
    }

    // Devuelve el identificador de la pelicula
    public int getId() {
        return id;
    }

    // Asigna el identificador de la pelicula
    public void setId(int id) {
        this.id = id;
    }

    // Devuelve el titulo de la pelicula
    public String getTitulo() {
        return titulo;
    }

    // Asigna el titulo de la pelicula
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Devuelve la duracion de la pelicula en minutos
    public int getDuracion() {
        return duracion;
    }

    // Asigna la duracion de la pelicula en minutos
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    // Indica si la pelicula esta disponible
    public boolean isDisponible() {
        return disponible;
    }

    // Asigna si la pelicula esta disponible
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Devuelve la fecha de estreno de la pelicula
    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    // Asigna la fecha de estreno de la pelicula
    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    // Devuelve una representacion en texto con todos los atributos de la pelicula
    @Override
    public String toString() {
        return "Pelicula [id=" + id + ", titulo=" + titulo + ", duracion=" + duracion + ", disponible=" + disponible
                + ", fechaEstreno=" + fechaEstreno + "]";
    }

}
