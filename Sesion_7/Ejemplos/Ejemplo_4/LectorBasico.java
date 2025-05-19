package Ejemplo_04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LectorBasico {
    public static void main(String[] args) {
        Path ruta = Paths.get("src/Ejemplo_04/datos.txt");

        try {
            String contenido = Files.readString(ruta);
            System.out.println("📄 Contenido del archivo:");
            System.out.println(contenido);
        } catch (IOException e) {
            System.out.println("❌ No se pudo leer el archivo: " + e.getMessage());
        }
    }
}