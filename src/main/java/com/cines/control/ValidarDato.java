// Bruno Mercado

package com.cines.control;

// Agrupa las validaciones con expresiones regulares que usan los formularios.
// Se centralizan aqui para no repetir el mismo patron en cada ventana.
public class ValidarDato {

    // Verifica que el nombre de un cine tenga entre 2 y 100 caracteres validos
    public static boolean validarNombreCine(String nombre) {
        return nombre.matches("^[\\p{L}0-9 .,'-]{2,100}$");
    }

    // Verifica que el titulo de una pelicula tenga caracteres permitidos
    public static boolean validarTituloPelicula(String titulo) {
        return titulo.matches("^[\\p{L}0-9 .,:;!?'\"()\\[\\]-]{1,150}$");
    }

    // Verifica que el texto sea un numero entero positivo (salas, capacidad, duracion)
    public static boolean validarEnteroPositivo(String numero) {
        return numero.matches("^[1-9]\\d{0,4}$");
    }

    // Verifica que la fecha tenga el formato dd/MM/yyyy
    public static boolean validarFecha(String fecha) {
        return fecha.matches("^\\d{2}/\\d{2}/\\d{4}$");
    }

    // Verifica que el horario tenga el formato DD-MM-AAAA HH:MM
    public static boolean validarHorario(String horario) {
        return horario.matches("^\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}$");
    }

    // Verifica que el precio sea un numero positivo con hasta dos decimales
    public static boolean validarPrecio(String precio) {
        return precio.matches("^\\d+(\\.\\d{1,2})?$");
    }

    // Verifica el nombre de usuario del login
    public static boolean validarUsuario(String usuario) {
        return usuario.matches("^[A-Za-z0-9._-]{3,50}$");
    }

    // Verifica la clave del login (entre 4 y 50 caracteres sin espacios)
    public static boolean validarClave(String clave) {
        return clave.matches("^\\S{4,50}$");
    }
}
