import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit; // Para usar TimeUnit.SECONDS

public class MovilidadApp {

    /**
     * Simula el cálculo asincrónico de la ruta óptima.
     * La latencia simulada es de 2 a 3 segundos.
     *
     * @return CompletableFuture que contendrá la cadena de la ruta.
     */
    public CompletableFuture<String> calcularRuta() {
        System.out.println("🚦 Calculando ruta...");
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simula latencia de 2 a 3 segundos
                long sleepTime = ThreadLocalRandom.current().nextLong(2, 4); // entre 2 y 3 segundos
                TimeUnit.SECONDS.sleep(sleepTime);
                // Opcional: simular un error para probar 'exceptionally'
                // if (Math.random() < 0.2) { // 20% de probabilidad de error
                //     throw new RuntimeException("Error al calcular la ruta!");
                // }
                return "Centro -> Norte";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaura el estado de interrupción
                throw new IllegalStateException("Cálculo de ruta interrumpido", e);
            }
        });
    }

    /**
     * Simula la estimación asincrónica de la tarifa.
     * La latencia simulada es de 1 a 2 segundos.
     *
     * @return CompletableFuture que contendrá el valor de la tarifa.
     */
    public CompletableFuture<Double> estimarTarifa() {
        System.out.println("💰 Estimando tarifa...");
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simula latencia de 1 a 2 segundos
                long sleepTime = ThreadLocalRandom.current().nextLong(1, 3); // entre 1 y 2 segundos
                TimeUnit.SECONDS.sleep(sleepTime);
                // Opcional: simular un error para probar 'exceptionally'
                // if (Math.random() < 0.1) { // 10% de probabilidad de error
                //     throw new RuntimeException("Error al estimar la tarifa!");
                // }
                return 75.50; // Tarifa de ejemplo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaura el estado de interrupción
                throw new IllegalStateException("Estimación de tarifa interrumpida", e);
            }
        });
    }

    public static void main(String[] args) {
        MovilidadApp app = new MovilidadApp();

        // Obtener los CompletableFuture para las operaciones asincrónicas
        CompletableFuture<String> rutaFuture = app.calcularRuta();
        CompletableFuture<Double> tarifaFuture = app.estimarTarifa();

        // Combinar los resultados de ambas operaciones y manejar posibles errores
        String finalMessage = rutaFuture.thenCombine(tarifaFuture, (ruta, tarifa) -> {
            // Este bloque se ejecuta solo cuando ambos futuros (ruta y tarifa) han completado exitosamente
            return "✅ 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + String.format("%.2f", tarifa);
        }).exceptionally(ex -> {
            // Este bloque se ejecuta si alguna de las operaciones anteriores (ruta, tarifa o la combinación) falla
            System.err.println("❌ Ocurrió un error en la solicitud: " + ex.getMessage());
            return "❌ No se pudo completar la solicitud de viaje. Inténtalo de nuevo.";
        }).join(); // .join() bloquea hasta que el CompletableFuture finaliza y obtiene el resultado

        System.out.println(finalMessage);
    }
}