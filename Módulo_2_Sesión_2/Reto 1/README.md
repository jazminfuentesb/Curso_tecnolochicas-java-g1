# 📄 README: Reto 01 - Simulación de Subsistemas de Misión Espacial

Este repositorio contiene la solución al **Reto 01** de la **sesión 2 del Módulo 2**, enfocado en la **simulación del comportamiento paralelo de subsistemas críticos** durante una misión espacial utilizando los conceptos de **programación concurrente en Java**.

---

## 🎯 Objetivo del Reto

El objetivo principal de este desafío fue:

- Simular la ejecución simultánea de múltiples subsistemas.
- Utilizar las interfaces `Runnable` y `Callable`, junto con `ExecutorService` y `Future`, para gestionar tareas concurrentes.
- Comprender cómo recuperar los resultados de tareas ejecutadas en hilos separados.

---

## 🧠 Contexto del Problema

Durante una misión aeroespacial, diversos sistemas operan en paralelo para asegurar el éxito y la seguridad de la operación. Este reto representa cuatro **subsistemas clave**, cada uno ejecutándose como una tarea independiente:

- **Sistema de Navegación (🛰️):** Encargado de calcular la trayectoria y realizar correcciones orbitales.
- **Sistema de Soporte Vital (🧪):** Monitorea parámetros críticos como la presión y el oxígeno.
- **Sistema de Control Térmico (🔥):** Supervisa y mantiene las temperaturas internas y externas.
- **Sistema de Comunicaciones (📡):** Establece y mantiene el contacto con la estación terrestre.

---

## 📝 Implementación

La solución se estructura en clases que implementan `Callable` para cada subsistema y una clase principal que orquesta su ejecución concurrente.

### 1. Clases de Subsistemas (Implementando `Callable<String>`)

Cada subsistema está representado por una clase Java que implementa la interfaz `Callable<String>`:

- `SistemaNavegacion`: Simula el cálculo de trayectoria.
- `SistemaSoporteVital`: Simula el monitoreo de condiciones ambientales.
- `SistemaControlTermico`: Simula la supervisión de temperatura.
- `SistemaComunicaciones`: Simula el establecimiento de enlace.

Cada una de estas clases sobrescribe el método `call()`, donde se simula el tiempo de procesamiento mediante `Thread.sleep()` y se retorna un `String` que indica el estado operativo del subsistema.

---

### 2. Ejecución de Tareas con `ExecutorService`

La clase principal `MisionEspacial` es la encargada de coordinar la ejecución:

- Se crea un `ExecutorService` utilizando `Executors.newFixedThreadPool(4)`, que establece un pool de **4 hilos fijos**, permitiendo que los cuatro subsistemas se ejecuten concurrentemente.
- Las instancias de los subsistemas (`Callable`) se envían al `ExecutorService` mediante el método `submit()`.  
  Este método devuelve un objeto `Future<String>` para cada tarea, que representa el resultado futuro de la ejecución asíncrona.

---

### 3. Recuperación de Resultados y Finalización

- Los resultados de cada subsistema se recuperan utilizando el método `get()` del objeto `Future` correspondiente (ej., `navFuture.get()`).
- Es importante notar que `get()` es un **método bloqueante**, lo que significa que el programa principal **esperará hasta que la tarea asociada complete su ejecución** y el resultado esté disponible.
- Se incluye un bloque `try-catch-finally` para manejar posibles excepciones durante la ejecución de las tareas y asegurar que el `ExecutorService` se cierre de manera ordenada.
- El método `executor.shutdown()` inicia el apagado del pool de hilos, y `executor.awaitTermination()` espera un tiempo determinado para que todas las tareas finalicen antes de cerrar el pool completamente.
