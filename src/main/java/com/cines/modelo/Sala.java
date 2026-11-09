// Bruno Mercado
package com.cines.modelo;

public class Sala {
    private int id;
    private int cineId;
    private String nombre;
    private int capacidad;

    public Sala() {}

    public Sala(int id, int cineId, String nombre, int capacidad) {
        this.id = id;
        this.cineId = cineId;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCineId() { return cineId; }
    public void setCineId(int cineId) { this.cineId = cineId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
}