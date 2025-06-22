# 📄 README: Reto 1 - Simulación Asíncrona de App de Movilidad con CompletableFuture

Este repositorio contiene la solución al **Reto 1 de la Sesión 4 del Módulo 2**, el cual simula procesos asincrónicos en una aplicación de movilidad (similar a Uber o DiDi) utilizando `CompletableFuture` para realizar tareas en paralelo como el cálculo de rutas y la estimación de tarifas, y finalmente, notificar al usuario con la información combinada.

---

## 🎯 Objetivo del Reto

El objetivo principal de este desafío fue:

- Aplicar `CompletableFuture` para ejecutar tareas de manera asíncrona y no bloqueante.
- Simular operaciones de larga duración que se ejecutan en segundo plano.
- Utilizar el método `thenCombine` para fusionar los resultados de dos operaciones asíncronas independientes.
- Implementar el manejo de errores en la cadena de procesamiento asíncrono con `exceptionally`.

---

## 🧠 Contexto del Problema

En una aplicación de movilidad, cuando un usuario solicita un viaje, se requiere que el sistema realice varias operaciones críticas simultáneamente:

- Calcular la **ruta óptima** desde el origen al destino.
- Estimar la **tarifa del viaje**.

Es vital que estas operaciones no bloqueen la interfaz de usuario ni el procesamiento de otras solicitudes.  
Una vez que ambas tareas han completado, la aplicación debe presentar una confirmación al usuario con la ruta y la tarifa estimada.

---

## 📝 Implementación

La solución se centra en la clase `MovilidadApp`, que encapsula la lógica asíncrona y la combinación de sus resultados.

---

### 1. Clase `MovilidadApp`

Esta clase contiene los métodos que simulan las operaciones asíncronas:

#### `CompletableFuture<String> calcularRuta()`

- Simula el proceso de calcular la ruta óptima.
- Utiliza `CompletableFuture.supplyAsync()` para ejecutar esta tarea en un hilo separado, haciendo que la llamada sea **no bloqueante**.
- Incluye una latencia simulada (entre 2 y 3 segundos) usando `Thread.sleep()` para imitar un procesamiento real.
- Devuelve un `CompletableFuture` que eventualmente contendrá una cadena con la ruta (ej., `"Centro -> Norte"`).

#### `CompletableFuture<Double> estimarTarifa()`

- Simula el proceso de estimar la tarifa del viaje.
- También usa `CompletableFuture.supplyAsync()` para su ejecución asíncrona.
- Introduce una latencia simulada (entre 1 y 2 segundos).
- Devuelve un `CompletableFuture` que contendrá un valor numérico (`Double`) de la tarifa.

---

### 2. Combinación de Operaciones y Manejo de Errores

La lógica principal para combinar y gestionar los resultados se encuentra en el método `main`:

#### `rutaFuture.thenCombine(tarifaFuture, (ruta, tarifa) -> { ... })`

- Este es el método central para la **composición asíncrona**.
- `thenCombine` toma otro `CompletableFuture` (`tarifaFuture`) y una función `BiFunction` (la expresión lambda).
- La función `BiFunction` se ejecuta **solo cuando ambos** `CompletableFuture` (la ruta y la tarifa) han completado sus respectivas operaciones exitosamente.
- Los resultados de ambos (`ruta` como `String` y `tarifa` como `Double`) son pasados como argumentos a esta función, donde se construye el **mensaje final de confirmación** para el usuario.

#### `.exceptionally(ex -> { ... })`

- Este método se encadena después de `thenCombine` y proporciona un mecanismo robusto para el **manejo de errores**.
- Si **cualquier** `CompletableFuture` en la cadena (ya sea `calcularRuta`, `estimarTarifa` o la operación de combinación `thenCombine`) falla y lanza una excepción, `exceptionally` intercepta esa excepción.
- La función lambda dentro de `exceptionally` recibe la excepción y puede retornar un valor de respaldo o un **mensaje de error amigable**, asegurando que el flujo del programa no se detenga abruptamente.

#### `.join()`

- Finalmente, se llama a `.join()` en el `CompletableFuture` resultante.
- Este método es **bloqueante** y espera a que la cadena completa de operaciones asíncronas finalice.
- Una vez completado, devuelve el resultado final (el **mensaje de éxito** o el **mensaje de error** si se activó `exceptionally`).
