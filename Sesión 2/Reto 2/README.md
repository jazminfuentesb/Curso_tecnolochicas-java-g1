# 📄 README: Reto 2 - Simulación Hospitalaria con Sincronización

Este repositorio contiene la solución al **Reto 2 de la Sesión 2 del Módulo 2**, el cual simula una situación hospitalaria donde múltiples profesionales médicos necesitan acceder a un recurso crítico (como una sala de cirugía) de manera exclusiva, aplicando sincronización con `ReentrantLock` para evitar condiciones de carrera y garantizar la integridad del sistema.

---

## 🎯 Objetivo del Reto

El objetivo principal de este desafío fue:

- Simular el acceso concurrente a un recurso compartido que solo puede ser utilizado por un hilo a la vez.
- Implementar mecanismos de sincronización utilizando la clase `ReentrantLock` de Java.
- Comprender y evitar condiciones de carrera y asegurar la exclusión mutua en un entorno concurrente.

---

## 🧠 Contexto del Problema

En un entorno hospitalario, recursos como quirófanos, equipos especializados (resonancia magnética) o camas de UCI son limitados y solo pueden ser utilizados por un único profesional o equipo a la vez.  
Este reto modela esta restricción, donde múltiples hilos (representando profesionales médicos) intentan acceder a un mismo recurso compartido (la **"Sala de cirugía"**).

---

## 📝 Implementación

La solución se estructura en dos clases principales: `RecursoMedico` que representa el recurso compartido y `SimulacionHospital` que orquesta la concurrencia.

---

### 1. Clase `RecursoMedico`

Esta clase modela el recurso crítico que requiere acceso exclusivo:

- `nombre` (`String`): Identifica el recurso (e.g., "Sala de cirugía").
- `ReentrantLock lock`: Una instancia de `ReentrantLock` es el corazón de la sincronización. Actúa como un **candado** que los hilos deben adquirir antes de usar el recurso y liberar después.

#### Método `usar(String profesional)`:

- `lock.lock()`: Este método es invocado por un hilo para intentar adquirir el candado. Si el candado ya está en posesión de otro hilo, el hilo actual se bloqueará y esperará hasta que el candado sea liberado.
- Simula el tiempo de uso del recurso con `Thread.sleep()`.
- `lock.unlock()`: Este método libera el candado. Es crucial que se llame dentro de un bloque `finally` para garantizar que el candado siempre sea liberado, incluso si ocurre una excepción durante el uso del recurso.  
  Esto previene **deadlocks**.

---

### 2. Tareas de Profesionales Médicos (Implementando `Runnable`)

Los profesionales médicos se representan como tareas concurrentes:

- Se definen varias instancias de `Runnable` (utilizando expresiones lambda para concisión).
- Cada `Runnable` encapsula la acción de un profesional intentando usar el `RecursoMedico` a través del método `usar()`.

---

### 3. Ejecución de la Simulación con `ExecutorService`

La clase `SimulacionHospital` coordina la ejecución concurrente:

- Se crea un `ExecutorService` con un **pool de hilos fijo** (`Executors.newFixedThreadPool(4)`).  
  Esto permite que hasta **4 profesionales** intenten acceder al recurso simultáneamente.
- Las tareas `Runnable` (los profesionales) se envían al `ExecutorService` mediante el método `submit()`.
- Se implementa un mecanismo de **apagado ordenado** del `ExecutorService` utilizando `shutdown()` y `awaitTermination()`, que espera a que todas las tareas en el pool finalicen antes de que el programa termine.
