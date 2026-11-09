// Bruno Mercado

package com.cines.modelo;

import java.time.LocalDateTime;

public class Funcion {
    private int id;
    private int peliculaId;
    private int salaId;
    private LocalDateTime horario;
    private double precio;

    // Constructor vacío: lo completa el DAO.
    public Funcion() {}

    // Constructor con todos los datos de la función.
    public Funcion(int id, int peliculaId, int salaId, LocalDateTime horario, double precio) {
        this.id = id;
        this.peliculaId = peliculaId;
        this.salaId = salaId;
        this.horario = horario;
        this.precio = precio;
    }

    // Devuelve el id de la función.
    public int getId() { return id; }

    // Asigna el id de la base.
    public void setId(int id) { this.id = id; }

    // Devuelve el id de la película asociada.
    public int getPeliculaId() { return peliculaId; }

    // Asigna la película de la función.
    public void setPeliculaId(int peliculaId) { this.peliculaId = peliculaId; }

    // Devuelve el id de la sala.
    public int getSalaId() { return salaId; }

    // Asigna la sala donde se proyecta.
    public void setSalaId(int salaId) { this.salaId = salaId; }

    // Devuelve la fecha y hora de la función.
    public LocalDateTime getHorario() { return horario; }

    // Asigna el horario.
    public void setHorario(LocalDateTime horario) { this.horario = horario; }

    // Devuelve el precio de la entrada.
    public double getPrecio() { return precio; }

    // Asigna el precio.
    public void setPrecio(double precio) { this.precio = precio; }

    // Texto descriptivo de la función.
    @Override
    public String toString() {
        return "Función [id=" + id + ", peliculaId=" + peliculaId + ", salaId=" + salaId
                + ", horario=" + horario + ", precio=" + precio + "]";
    }
}
