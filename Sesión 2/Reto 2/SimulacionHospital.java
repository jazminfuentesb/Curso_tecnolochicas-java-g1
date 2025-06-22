import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

// 1️⃣ Crear una clase RecursoMedico
class RecursoMedico {
    private String nombre;
    private final ReentrantLock lock = new ReentrantLock(); // El candado para el acceso exclusivo

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    // Método para simular el uso del recurso
    public void usar(String profesional) {
        // Intenta adquirir el candado. Si el recurso ya está en uso, el hilo esperará aquí.
        lock.lock();
        try {
            System.out.println(profesional + " ha ingresado a " + nombre);
            // Simula el tiempo de uso del recurso
            Thread.sleep(obtenerTiempoAleatorio()); // Tiempo de uso aleatorio entre 500ms y 2000ms
            System.out.println("✅ " + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            // Maneja la interrupción del hilo (por ejemplo, si se apaga el ExecutorService abruptamente)
            System.err.println(profesional + " fue interrumpido mientras usaba " + nombre);
            Thread.currentThread().interrupt(); // Restaura el estado de interrupción
        } finally {
            // Es CRÍTICO liberar el candado en un bloque finally para asegurar que siempre se libere
            lock.unlock();
        }
    }

    // Genera un tiempo aleatorio para simular el uso
    private long obtenerTiempoAleatorio() {
        return 500 + (long) (Math.random() * 1500); // Entre 500 y 2000 ms
    }
}

public class SimulacionHospital {

    public static void main(String[] args) {
        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...");

        // Instancia del recurso compartido
        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        // 2️⃣ Crear tareas que representen a profesionales médicos (usando Lambdas con Runnable)
        // Cada lambda representa un hilo intentando usar el recurso.
        Runnable medico1 = () -> salaCirugia.usar("👩‍⚕️ Dra. Sánchez");
        Runnable medico2 = () -> salaCirugia.usar("👨‍⚕️ Dr. Gómez");
        Runnable medico3 = () -> salaCirugia.usar("👩‍⚕️ Enf. Laura");
        Runnable medico4 = () -> salaCirugia.usar("👨‍⚕️ Dr. Pérez");
        Runnable medico5 = () -> salaCirugia.usar("👩‍⚕️ Dra. Ruiz");

        // 3️⃣ Ejecutar la simulación
        // Se utiliza un ExecutorService con un pool de 4 hilos para simular la concurrencia.
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Envía las tareas al executor. El orden de envío no garantiza el orden de ejecución.
        executor.submit(medico1);
        executor.submit(medico2);
        executor.submit(medico3);
        executor.submit(medico4);
        executor.submit(medico5); // Aquí hay más tareas que hilos, algunas esperarán en la cola

        // Apaga el executor de forma ordenada
        executor.shutdown();
        try {
            // Espera a que todas las tareas terminen o que pasen 10 segundos
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("\n🚫 Algunas tareas no terminaron a tiempo. Forzando el cierre...");
                executor.shutdownNow(); // Fuerza el cierre de tareas pendientes
            }
        } catch (InterruptedException ie) {
            System.err.println("\n🚫 Simulación interrumpida mientras esperaba la finalización.");
            executor.shutdownNow();
            Thread.currentThread().interrupt(); // Restaura el estado de interrupción
        }

        System.out.println("\n✅ Simulación de acceso a recursos hospitalarios finalizada.");
    }
}