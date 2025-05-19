package Ejemplo_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LecturaEscrituraArchivos {
    public static void main(String[] args) {
        // 1. Definir la ruta del archivo
        Path ruta = Paths.get("src/Ejemplo_02/notas.txt");

        // 2. Contenido a escribir en el archivo
        String contenido = "📘 Este es un archivo de prueba.\nAquí aprendemos a escribir y leer en Java.";

        try {
            // 3. Escribir contenido en el archivo (crea o sobrescribe)
            Files.write(ruta, contenido.getBytes());
            System.out.println("✅ Archivo escrito exitosamente.");

            // 4. Leer el contenido completo como String
            String textoLeido = Files.readString(ruta);
            System.out.println("📄 Contenido leído:");
            System.out.println(textoLeido);

        } catch (IOException e) {
            System.out.println("❌ Error al trabajar con el archivo: " + e.getMessage());
        }
    }
}
