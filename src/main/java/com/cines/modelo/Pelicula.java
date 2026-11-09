// Bruno Mercado

package com.cines.modelo;

import java.time.LocalDate;

public class Pelicula {

    private int id;
    private String titulo;
    private int duracion;
    private boolean disponible;
    private LocalDate fechaEstreno;

    // Constructor vacío para que el DAO arme la película.
    public Pelicula() {
    }

    // Constructor para dar de alta una película.
    public Pelicula(String titulo, int duracion, boolean disponible, LocalDate fechaEstreno) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.disponible = disponible;
        this.fechaEstreno = fechaEstreno;
    }

    // Constructor con todos los datos de la película.
    public Pelicula(int id, String titulo, int duracion, boolean disponible, LocalDate fechaEstreno) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.disponible = disponible;
        this.fechaEstreno = fechaEstreno;
    }

    // Devuelve el id de la película.
    public int getId() {
        return id;
    }

    // Asigna el id de la base.
    public void setId(int id) {
        this.id = id;
    }

    // Devuelve el título.
    public String getTitulo() {
        return titulo;
    }

    // Cambia el título.
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Devuelve la duración en minutos.
    public int getDuracion() {
        return duracion;
    }

    // Asigna la duración.
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    // Indica si la película está disponible.
    public boolean isDisponible() {
        return disponible;
    }

    // Actualiza la disponibilidad.
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Devuelve la fecha de estreno.
    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    // Asigna la fecha de estreno.
    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    // Texto que se muestra en los listados y combos.
    @Override
    public String toString() {
        return titulo + " (" + duracion + " min, " + (disponible ? "disponible" : "no disponible") + ")";
    }

}
