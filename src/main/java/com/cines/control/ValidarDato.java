// Bruno Mercado

package com.cines.control;

public class ValidarDato {

    public static boolean validarNombreCine(String nombre) {
        return nombre.matches("^[\\p{L}0-9 .,'-]{2,100}$");
    }

    public static boolean validarTituloPelicula(String titulo) {
        return titulo.matches("^[\\p{L}0-9 .,:;!?'\"()\\[\\]-]{1,150}$");
    }

    public static boolean validarEnteroPositivo(String numero) {
        return numero.matches("^[1-9]\\d{0,4}$");
    }

    public static boolean validarFecha(String fecha) {
        return fecha.matches("^\\d{2}/\\d{2}/\\d{4}$");
    }

    public static boolean validarHorario(String horario) {
        return horario.matches("^\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}$");
    }

    public static boolean validarPrecio(String precio) {
        return precio.matches("^\\d+(\\.\\d{1,2})?$");
    }

    public static boolean validarUsuario(String usuario) {
        return usuario.matches("^[A-Za-z0-9._-]{3,50}$");
    }

    public static boolean validarClave(String clave) {
        return clave.matches("^\\S{4,50}$");
    }
}
