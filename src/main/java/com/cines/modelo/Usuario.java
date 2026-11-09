// Bruno Mercado

package com.cines.modelo;

public class Usuario {
    private int idUsuario;
    private String usuario;
    private String clave;

    // Constructor vacío para el usuario que devuelve el DAO.
    public Usuario() {
    }

    // Constructor completo del usuario.
    public Usuario(int idUsuario, String usuario, String clave) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.clave = clave;
    }

    // Constructor para probar credenciales.
    public Usuario(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    // Devuelve el id del usuario.
    public int getIdUsuario() {
        return idUsuario;
    }

    // Asigna el id de la base.
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Devuelve el nombre de usuario.
    public String getUsuario() {
        return usuario;
    }

    // Asigna el nombre de usuario.
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    // Devuelve el hash de la clave.
    public String getClave() {
        return clave;
    }

    // Asigna el hash de la clave.
    public void setClave(String clave) {
        this.clave = clave;
    }

    // Representación del usuario; la clave no se muestra en la UI.
    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", usuario=" + usuario + ", clave=" + clave + "]";
    }
}
