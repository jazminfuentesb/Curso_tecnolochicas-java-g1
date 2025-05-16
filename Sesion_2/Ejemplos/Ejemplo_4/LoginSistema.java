package Ejemplo_04;

import java.util.Scanner;

public class LoginSistema {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String contraseñaCorrecta = "BEDU_JSE1";  // Contraseña predefinida
        String input;
        int intentos = 0;
        int maxIntentos = 3;

        // Ciclo que permite al usuario intentar hasta 3 veces
        while (intentos < maxIntentos) {
            System.out.print("Ingresa tu contraseña: ");
            input = scanner.nextLine();

            // Verifica si la contraseña ingresada es correcta
            if (input.equals(contraseñaCorrecta)) {
                System.out.println("✅ Acceso concedido.");
                break;
            } else {
                System.out.println("❌ Contraseña incorrecta.");
                intentos++;
            }
        }

        // Si se exceden los intentos, se bloquea el acceso
        if (intentos == maxIntentos) {
            System.out.println("🔒 Cuenta bloqueada por demasiados intentos.");
        }

        // Buenas prácticas: cerrar el Scanner al finalizar
        scanner.close();
    }
}
