import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit; // Para usar TimeUnit.SECONDS o MILLISECONDS

// Parte 1️⃣: Crear clases que simulen subsistemas e implementen Callable<String>

/**
 * Simula el Sistema de Navegacion: calcula trayectoria y correcciones orbitales.
 */
class SistemaNavegacion implements Callable<String> {
    @Override
    public String call() throws Exception {
        // Simula un procesamiento de 1 segundo
        Thread.sleep(1000);
        return "🛰️ Navegación: trayectoria corregida con éxito.";
    }
}

/**
 * Simula el Sistema de Soporte Vital: monitorea presión, oxígeno y condiciones internas.
 */
class SistemaSoporteVital implements Callable<String> {
    @Override
    public String call() throws Exception {
        // Simula un procesamiento de 1.2 segundos
        Thread.sleep(1200);
        return "🧪 Soporte vital: presión y oxígeno dentro de parámetros normales.";
    }
}

/**
 * Simula el Sistema de Control Termico: supervisa temperaturas internas y externas.
 */
class SistemaControlTermico implements Callable<String> {
    @Override
    public String call() throws Exception {
        // Simula un procesamiento de 0.8 segundos
        Thread.sleep(800);
        return "🔥 Control térmico: temperatura estable (22°C).";
    }
}

/**
 * Simula el Sistema de Comunicaciones: establece contacto con la estación terrestre.
 */
class SistemaComunicaciones implements Callable<String> {
    @Override
    public String call() throws Exception {
        // Simula un procesamiento de 0.9 segundos
        Thread.sleep(900);
        return "📡 Comunicaciones: enlace con estación terrestre establecido.";
    }
}

public class MisionEspacial {

    public static void main(String[] args) {
        System.out.println("🚀 Simulación de misión espacial iniciada...");

        // Parte 2️⃣: Ejecutar tareas con ExecutorService
        // Usamos un pool de 4 hilos, uno por cada subsistema.
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Envía las tareas al ExecutorService usando submit()
        Future<String> navFuture = executor.submit(new SistemaNavegacion());
        Future<String> soporteVitalFuture = executor.submit(new SistemaSoporteVital());
        Future<String> controlTermicoFuture = executor.submit(new SistemaControlTermico());
        Future<String> comunicacionesFuture = executor.submit(new SistemaComunicaciones());

        // Parte 3️⃣: Mostrar los resultados al finalizar
        // Recupera los resultados con Future.get(). Esto bloqueará hasta que la tarea termine.
        try {
            // El orden de impresión aquí determinará el orden en que los resultados se muestran
            // en la consola, no el orden en que las tareas terminan.
            // Para garantizar que todos se impriman una vez que estén listos,
            // llamamos a .get() para cada Future.
            System.out.println(comunicacionesFuture.get());
            System.out.println(soporteVitalFuture.get());
            System.out.println(controlTermicoFuture.get());
            System.out.println(navFuture.get());

            System.out.println("✅ Todos los sistemas reportan estado operativo.");

        } catch (Exception e) {
            // Manejo básico de excepciones en caso de que alguna tarea falle
            System.err.println("❌ Error durante la simulación de subsistemas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cierra el ExecutorService de forma ordenada
            executor.shutdown(); // Inicia el apagado ordenado
            try {
                // Espera un tiempo prudencial para que todas las tareas pendientes terminen
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    // Si las tareas no terminan en el tiempo dado, las fuerza a cerrar
                    executor.shutdownNow();
                }
            } catch (InterruptedException ie) {
                // Si el hilo actual es interrumpido mientras espera, forza el cierre
                executor.shutdownNow();
                Thread.currentThread().interrupt(); // Restaura el estado de interrupción
            }
        } 
    } 
} 