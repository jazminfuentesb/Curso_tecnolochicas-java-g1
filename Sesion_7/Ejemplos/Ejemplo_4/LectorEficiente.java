package Ejemplo_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LectorEficiente {
    public static void main(String[] args) {
        Path ruta = Paths.get("src/Ejemplo_04/log_operaciones.txt");

        try (BufferedReader lector = Files.newBufferedReader(ruta)) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                System.out.println("➡️ " + linea);
            }
        } catch (IOException e) {
            System.out.println("❌ Error al procesar el archivo: " + e.getMessage());
        }
    }
}
