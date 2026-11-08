// Bruno Mercado

package com.cines.modelo;

public class Usuario {
    private int idUsuario;
    private String usuario;
    private String clave;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(int idUsuario, String usuario, String clave) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.clave = clave;
    }

    // Constructor sin ID (ideal para nuevos registros)
    public Usuario(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
