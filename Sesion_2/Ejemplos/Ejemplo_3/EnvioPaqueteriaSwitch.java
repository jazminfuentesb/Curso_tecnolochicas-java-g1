package Ejemplo_03;

import java.util.Scanner;

public class EnvioPaqueteriaSwitch {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Solicita al usuario que escriba el tipo de paquete
        System.out.print("Introduce el tipo de paquete (pequeño, mediano, grande): ");
        String tipo = scanner.nextLine();

        // Evalúa el tipo de paquete usando switch mejorado y asigna el costo correspondiente
        double costo = switch (tipo.toLowerCase()) {
            case "pequeño" -> 49.99;
            case "mediano" -> 89.99;
            case "grande" -> 149.99;
            default -> {
                System.out.println("Tipo no válido.");
                yield 0.0;  // yield devuelve el valor en el caso default
            }
        };

        // Imprime el costo calculado del envío
        System.out.println("Costo del envío: $" + costo);
    }
}