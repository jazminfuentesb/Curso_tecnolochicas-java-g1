package Ejemplo_01;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class ExploradorArchivos {
    public static void main(String[] args) {
        // 1. Crear un objeto Path que apunte a un archivo
        Path rutaArchivo = Paths.get("src/Ejemplo_01/ejemplo.txt");

        // 2. Imprimir la ruta absoluta del archivo
        System.out.println("📁 Ruta absoluta: " + rutaArchivo.toAbsolutePath());

        // 3. Verificar si el archivo existe
        if (Files.exists(rutaArchivo)) {
            System.out.println("✅ El archivo existe.");
        } else {
            System.out.println("❌ El archivo NO existe.");
        }

        // 4. Verificar si es un archivo o directorio
        if (Files.isDirectory(rutaArchivo)) {
            System.out.println("📂 Es un directorio.");
        } else {
            System.out.println("📄 Es un archivo.");
        }

        // 5. Verificar permisos
        System.out.println("🔒 ¿Se puede leer?: " + Files.isReadable(rutaArchivo));
        System.out.println("✏️ ¿Se puede escribir?: " + Files.isWritable(rutaArchivo));
    }
}
