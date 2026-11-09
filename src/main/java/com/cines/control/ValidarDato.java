// Bruno Mercado

package com.cines.control;

public class ValidarDato {

    // Valida que el nombre del cine tenga solo caracteres permitidos.
    public static boolean validarNombreCine(String nombre) {
        return nombre.matches("^[\\p{L}0-9 .,'-]{2,100}$");
    }

    // Valida el título de una película.
    public static boolean validarTituloPelicula(String titulo) {
        return titulo.matches("^[\\p{L}0-9 .,:;!?'\"()\\[\\]-]{1,150}$");
    }

    // Acepta enteros positivos de hasta cinco dígitos.
    public static boolean validarEnteroPositivo(String numero) {
        return numero.matches("^[1-9]\\d{0,4}$");
    }

    // Controla el formato dd/MM/yyyy.
    public static boolean validarFecha(String fecha) {
        return fecha.matches("^\\d{2}/\\d{2}/\\d{4}$");
    }

    // Controla el formato dd-MM-yyyy HH:MM.
    public static boolean validarHorario(String horario) {
        return horario.matches("^\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}$");
    }

    // Acepta importes con hasta dos decimales.
    public static boolean validarPrecio(String precio) {
        return precio.matches("^\\d+(\\.\\d{1,2})?$");
    }

    // Restringe el usuario a letras, números, punto, guion y guion bajo.
    public static boolean validarUsuario(String usuario) {
        return usuario.matches("^[A-Za-z0-9._-]{3,50}$");
    }

    // La clave no puede tener espacios y debe tener entre 4 y 50 caracteres.
    public static boolean validarClave(String clave) {
        return clave.matches("^\\S{4,50}$");
    }
}
