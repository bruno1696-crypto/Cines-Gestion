// Bruno Mercado
package com.cines.modelo;

public class Sala {
    private int id;
    private int cineId;
    private String nombre;
    private int capacidad;

    // Constructor vacio requerido para mapear filas de la base de datos
    public Sala() {}

    // Constructor con parametros incluyendo el id
    public Sala(int id, int cineId, String nombre, int capacidad) {
        this.id = id;
        this.cineId = cineId;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    // Devuelve el identificador de la sala
    public int getId() { return id; }

    // Asigna el identificador de la sala
    public void setId(int id) { this.id = id; }

    // Devuelve el identificador del cine al que pertenece la sala
    public int getCineId() { return cineId; }

    // Asigna el identificador del cine al que pertenece la sala
    public void setCineId(int cineId) { this.cineId = cineId; }

    // Devuelve el nombre de la sala
    public String getNombre() { return nombre; }

    // Asigna el nombre de la sala
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Devuelve la capacidad de la sala
    public int getCapacidad() { return capacidad; }

    // Asigna la capacidad de la sala
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    // Devuelve una representacion en texto con el nombre y la capacidad de la sala
    @Override
    public String toString() {
        return nombre + " (Capacidad: " + capacidad + ")";
    }
}
