import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit; // For TimeUnit.SECONDS

public class AeropuertoControl {

    /**
     * Simulates checking runway availability.
     * Latency: 2-3 seconds. Success probability: 80%.
     *
     * @return CompletableFuture<Boolean> indicating if the runway is available.
     */
    public CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                long sleepTime = ThreadLocalRandom.current().nextLong(2, 4); // 2 to 3 seconds
                TimeUnit.SECONDS.sleep(sleepTime);
                boolean isAvailable = ThreadLocalRandom.current().nextDouble() < 0.80; // 80% success chance
                System.out.println("🛣️ Pista disponible: " + isAvailable);
                // Optional: Simulate an error for testing exceptionally
                // if (!isAvailable && ThreadLocalRandom.current().nextDouble() < 0.5) {
                //     throw new RuntimeException("Error en el sensor de pista!");
                // }
                return isAvailable;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Verificación de pista interrumpida", e);
            }
        });
    }

    /**
     * Simulates checking weather conditions.
     * Latency: 2-3 seconds. Success probability: 85%.
     *
     * @return CompletableFuture<Boolean> indicating if the weather is favorable.
     */
    public CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                long sleepTime = ThreadLocalRandom.current().nextLong(2, 4);
                TimeUnit.SECONDS.sleep(sleepTime);
                boolean isFavorable = ThreadLocalRandom.current().nextDouble() < 0.85; // 85% success chance
                System.out.println("🌦️ Clima favorable: " + isFavorable);
                return isFavorable;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Verificación de clima interrumpida", e);
            }
        });
    }

    /**
     * Simulates checking nearby air traffic.
     * Latency: 2-3 seconds. Success probability: 90%.
     *
     * @return CompletableFuture<Boolean> indicating if air traffic is clear.
     */
    public CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                long sleepTime = ThreadLocalRandom.current().nextLong(2, 4);
                TimeUnit.SECONDS.sleep(sleepTime);
                boolean isClear = ThreadLocalRandom.current().nextDouble() < 0.90; // 90% success chance
                System.out.println("🚦 Tráfico aéreo despejado: " + isClear);
                return isClear;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Verificación de tráfico aéreo interrumpida", e);
            }
        });
    }

    /**
     * Simulates checking ground staff availability.
     * Latency: 2-3 seconds. Success probability: 95%.
     *
     * @return CompletableFuture<Boolean> indicating if ground staff is available.
     */
    public CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                long sleepTime = ThreadLocalRandom.current().nextLong(2, 4);
                TimeUnit.SECONDS.sleep(sleepTime);
                boolean isAvailable = ThreadLocalRandom.current().nextDouble() < 0.95; // 95% success chance
                System.out.println("👷‍♂️ Personal disponible: " + isAvailable);
                return isAvailable;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Verificación de personal en tierra interrumpida", e);
            }
        });
    }

    public static void main(String[] args) {
        System.out.println("🛫 Verificando condiciones para aterrizaje...");
        AeropuertoControl control = new AeropuertoControl();

        // Start all verifications in parallel
        CompletableFuture<Boolean> pistaFuture = control.verificarPista();
        CompletableFuture<Boolean> climaFuture = control.verificarClima();
        CompletableFuture<Boolean> traficoFuture = control.verificarTraficoAereo();
        CompletableFuture<Boolean> personalFuture = control.verificarPersonalTierra();

        // Use allOf to wait for all futures to complete, then combine their results
        String finalDecision = CompletableFuture.allOf(pistaFuture, climaFuture, traficoFuture, personalFuture)
            .thenApply(v -> {
                // This block executes ONLY after all individual futures are done.
                // We use .join() here because we know they're complete.
                boolean pistaOk = pistaFuture.join();
                boolean climaOk = climaFuture.join();
                boolean traficoOk = traficoFuture.join();
                boolean personalOk = personalFuture.join();

                // Make the final decision
                if (pistaOk && climaOk && traficoOk && personalOk) {
                    return "🛬 Aterrizaje autorizado: todas las condiciones óptimas.";
                } else {
                    return "🚫 Aterrizaje denegado: condiciones no óptimas.";
                }
            })
            .exceptionally(ex -> {
                // Handle any exceptions that occurred in any of the preceding CompletableFutures
                System.err.println("❌ Error crítico en la verificación de aterrizaje: " + ex.getMessage());
                return "🚫 Aterrizaje denegado: Fallo en el sistema de verificación.";
            })
            .join(); // Block and get the final result

        System.out.println("\n" + finalDecision);
    }
}