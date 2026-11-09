// Bruno Mercado

package com.cines.modelo;

public class Usuario {
    private int idUsuario;
    private String usuario;
    private String clave;

    // Constructor vacio requerido para mapear filas de la base de datos
    public Usuario() {
    }

    // Constructor con parametros incluyendo el id
    public Usuario(int idUsuario, String usuario, String clave) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.clave = clave;
    }

    // Constructor sin id (ideal para nuevos registros)
    public Usuario(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    // Devuelve el identificador del usuario
    public int getIdUsuario() {
        return idUsuario;
    }

    // Asigna el identificador del usuario
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Devuelve el nombre de usuario
    public String getUsuario() {
        return usuario;
    }

    // Asigna el nombre de usuario
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    // Devuelve la clave almacenada del usuario
    public String getClave() {
        return clave;
    }

    // Asigna la clave del usuario
    public void setClave(String clave) {
        this.clave = clave;
    }

    // Devuelve una representacion en texto con todos los atributos del usuario
    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", usuario=" + usuario + ", clave=" + clave + "]";
    }
}
