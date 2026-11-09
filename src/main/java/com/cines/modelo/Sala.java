// Bruno Mercado

package com.cines.modelo;

public class Sala {
    private int id;
    private int cineId;
    private String nombre;
    private int capacidad;

    // Constructor vacío: lo usa el mapeo de la base.
    public Sala() {}

    // Constructor con todos los datos de la sala.
    public Sala(int id, int cineId, String nombre, int capacidad) {
        this.id = id;
        this.cineId = cineId;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    // Devuelve el id de la sala.
    public int getId() { return id; }

    // Asigna el id de la base.
    public void setId(int id) { this.id = id; }

    // Devuelve el cine al que pertenece.
    public int getCineId() { return cineId; }

    // Asigna el cine de la sala.
    public void setCineId(int cineId) { this.cineId = cineId; }

    // Devuelve el nombre de la sala.
    public String getNombre() { return nombre; }

    // Cambia el nombre de la sala.
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Devuelve la capacidad.
    public int getCapacidad() { return capacidad; }

    // Asigna la capacidad.
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    // Texto que se usa en los combos de funciones.
    @Override
    public String toString() {
        return nombre + " (Capacidad: " + capacidad + ")";
    }
}
