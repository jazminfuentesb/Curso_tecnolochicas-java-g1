# 📄 README: Reto 2 - Gestión Asíncrona de Aterrizajes en un Aeropuerto

Este repositorio contiene la solución al **Reto 2 de la Sesión del Módulo 2**, el cual simula un flujo asincrónico y no bloqueante para la gestión de aterrizajes en un aeropuerto internacional. Integra múltiples consultas que se ejecutan en paralelo usando `CompletableFuture`, combina sus resultados y maneja errores potenciales para tomar una decisión final sobre la autorización del aterrizaje.

---

## 🎯 Objetivo del Reto

El objetivo principal de este desafío fue:

- Ejecutar múltiples operaciones asincrónicas y concurrentes utilizando `CompletableFuture`.
- Utilizar `CompletableFuture.allOf()` para esperar a que un conjunto de tareas paralelas completen su ejecución.
- Combinar los resultados individuales de las tareas asincrónicas para tomar una decisión lógica basada en múltiples condiciones (**todas deben ser verdaderas**).
- Implementar fallas aleatorias en los servicios para simular escenarios del mundo real y probar la robustez del manejo de errores.
- Gestionar y recuperar errores utilizando el método `exceptionally` de `CompletableFuture`.

---

## 🧠 Contexto del Problema

En un aeropuerto internacional, la autorización para que un avión aterrice es un proceso crítico que depende de la verificación en tiempo real de varias condiciones:

- La disponibilidad de la **pista**.
- El estado **meteorológico**.
- El **tráfico aéreo** cercano.
- La disponibilidad del **personal en tierra**.

Si alguna de estas condiciones no es óptima (`false`) o si el servicio que la verifica experimenta una falla, el aterrizaje debe ser **denegado**. Este sistema debe operar de forma eficiente y **no bloquearse** mientras espera las respuestas de las diferentes verificaciones.

---

## 📝 Implementación

La solución se centraliza en la clase `AeropuertoControl`, la cual encapsula las operaciones asincrónicas de verificación y la lógica para la toma de decisiones.

---

### 1. Clase `AeropuertoControl`

Esta clase define los métodos que simulan cada una de las verificaciones de condiciones para el aterrizaje:

- `CompletableFuture<Boolean> verificarPista()`  
  Simula la verificación de la disponibilidad de la pista.

- `CompletableFuture<Boolean> verificarClima()`  
  Simula la verificación de las condiciones meteorológicas.

- `CompletableFuture<Boolean> verificarTraficoAereo()`  
  Simula la verificación del tráfico aéreo en las cercanías.

- `CompletableFuture<Boolean> verificarPersonalTierra()`  
  Simula la verificación de la disponibilidad de personal en tierra.

#### Características comunes:

- Utilizan `CompletableFuture.supplyAsync()` para ejecutar la simulación en un hilo separado (**no bloqueante**).
- Introducen una **latencia simulada** (entre 2 y 3 segundos) con `Thread.sleep()`.
- Cada verificación tiene una **probabilidad de éxito aleatoria**:
  - Pista: 80%
  - Clima: 85%
  - Tráfico: 90%
  - Personal: 95%
- Devuelven un `CompletableFuture<Boolean>` indicando si la condición es favorable (`true`) o no (`false`).

---

### 2. Orquestación y Toma de Decisión (`main`)

El método `main` de la clase `AeropuertoControl` es el encargado de coordinar todas las verificaciones y emitir la autorización final:

#### 🔁 Lanzamiento en Paralelo

Se inician todas las verificaciones simultáneamente, obteniendo un `CompletableFuture<Boolean>` por cada una de ellas.

#### 🧩 `CompletableFuture.allOf(...)`

- Método clave que crea un `CompletableFuture<Void>` que se completa **cuando todas** las tareas pasadas han terminado.
- Se encadena con `.thenApply(v -> { ... })` para procesar los resultados una vez completadas.

#### ✅ Procesamiento de Resultados

Dentro del bloque `thenApply`:

- Se usa `.join()` en cada uno de los `CompletableFuture` individuales para obtener sus valores booleanos.
- Se imprime el estado de cada verificación (pista, clima, tráfico, personal).
- Se toma la decisión final:
  - El aterrizaje **se autoriza** solo si **todas las condiciones son `true`**.
  - De lo contrario, el aterrizaje **es denegado**.

#### 🛠️ Manejo de Errores con `.exceptionally(ex -> { ... })`

- Captura cualquier excepción ocurrida en las operaciones previas.
- Devuelve un mensaje alternativo: **denegación por fallo del sistema**.

#### ⏳ `.join()`

- Se llama sobre el `CompletableFuture` final para **bloquear el hilo principal** hasta que todo se haya completado (éxito o error).
- Devuelve el mensaje final: autorización o denegación.

---
