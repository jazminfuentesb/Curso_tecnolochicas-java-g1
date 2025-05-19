package Ejemplo_05;

import java.util.Scanner;

public class CXManagerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingrese la calificación del cliente (1 a 5): ");
            int input = scanner.nextInt();

            EncuestaSatisfaccion.registrarCalificacion(input);

        } catch (CalificacionInvalidaException e) {
            System.out.println("❌ Error de validación: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());

        } finally {
            scanner.close();
            System.out.println("📦 Recurso liberado correctamente.");
        }
    }
}
