package Ejemplo_05;

public class EncuestaSatisfaccion {
    public static void registrarCalificacion(int calificacion) throws CalificacionInvalidaException {
        if (calificacion < 1 || calificacion > 5) {
            throw new CalificacionInvalidaException("La calificación debe estar entre 1 y 5.");
        }

        System.out.println("✅ Calificación registrada: " + calificacion);
    }
}
