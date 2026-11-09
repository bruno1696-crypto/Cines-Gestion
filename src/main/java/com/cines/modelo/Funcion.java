// Bruno Mercado
package com.cines.modelo;

import java.time.LocalDateTime;

public class Funcion {
    private int id;
    private int peliculaId;
    private int salaId;
    private LocalDateTime horario;
    private double precio;

    // Constructor vacio requerido para mapear filas de la base de datos
    public Funcion() {}

    // Constructor con parametros incluyendo el id
    public Funcion(int id, int peliculaId, int salaId, LocalDateTime horario, double precio) {
        this.id = id;
        this.peliculaId = peliculaId;
        this.salaId = salaId;
        this.horario = horario;
        this.precio = precio;
    }

    // Devuelve el identificador de la funcion
    public int getId() { return id; }

    // Asigna el identificador de la funcion
    public void setId(int id) { this.id = id; }

    // Devuelve el identificador de la pelicula asociada
    public int getPeliculaId() { return peliculaId; }

    // Asigna el identificador de la pelicula asociada
    public void setPeliculaId(int peliculaId) { this.peliculaId = peliculaId; }

    // Devuelve el identificador de la sala asociada
    public int getSalaId() { return salaId; }

    // Asigna el identificador de la sala asociada
    public void setSalaId(int salaId) { this.salaId = salaId; }

    // Devuelve el horario de la funcion
    public LocalDateTime getHorario() { return horario; }

    // Asigna el horario de la funcion
    public void setHorario(LocalDateTime horario) { this.horario = horario; }

    // Devuelve el precio de la funcion
    public double getPrecio() { return precio; }

    // Asigna el precio de la funcion
    public void setPrecio(double precio) { this.precio = precio; }

    // Devuelve una representacion en texto con todos los atributos de la funcion
    @Override
    public String toString() {
        return "Funcion [id=" + id + ", peliculaId=" + peliculaId + ", salaId=" + salaId
                + ", horario=" + horario + ", precio=" + precio + "]";
    }
}
