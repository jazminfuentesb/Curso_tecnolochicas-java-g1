package Ejemplo_04;

import java.util.Scanner;

public class ValidadorHorasConexion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Total de horas conectadas: ");
        int horas = scanner.nextInt();

        System.out.print("Número de días laborales: ");
        int dias = scanner.nextInt();

        int promedio = horas / dias;
        System.out.println("Promedio de horas por día: " + promedio);
        scanner.close();
    }
}
