# 📄 README: Reto 2 - Análisis de Encuestas de Satisfacción con Stream API y flatMap

Este repositorio contiene la solución al **Reto 2 de la Sesión 3 del Módulo 2**, el cual demuestra cómo utilizar la **Stream API** y `flatMap` en Java para procesar colecciones anidadas (encuestas de satisfacción de pacientes de diversas sucursales de una clínica), filtrar respuestas específicas y transformar los datos en mensajes procesables para el área de calidad.

---

## 🎯 Objetivo del Reto

El objetivo principal de este desafío fue:

- Aplicar `flatMap` para desanidar y procesar datos de colecciones dentro de otras colecciones.
- Combinar operaciones de **Stream** como `filter` y `map` para extraer información basada en múltiples criterios.
- Continuar utilizando `Optional` para el manejo seguro de valores que pueden ser `null` (como los comentarios de las encuestas).
- Generar mensajes personalizados y útiles a partir de los datos procesados.

---

## 🧠 Contexto del Problema

Una clínica con varias sucursales recopila encuestas de satisfacción de pacientes.  
Cada encuesta incluye el nombre del paciente, un comentario (opcional) y una calificación del 1 al 5.  
El departamento de calidad necesita identificar rápidamente a los pacientes **insatisfechos** (aquellos con calificación de **3 o menos**) y, si dejaron un comentario, generar un mensaje de seguimiento que incluya el nombre de la sucursal.

---

## 📝 Implementación

La solución se estructura en tres clases: `Encuesta` y `Sucursal` como modelos de datos, y `AnalisisEncuestas` que contiene la lógica principal para procesar estos datos anidados.

---

### 1. Clases de Modelo: `Encuesta` y `Sucursal`

#### Encuesta

Representa una encuesta individual.

**Atributos:**
- `paciente` (`String`)
- `comentario` (`String`, que puede ser `null`)
- `calificacion` (`int`)

**Método `getComentario()`:**
- Devuelve un `Optional<String>`.
- Utiliza `Optional.ofNullable(comentario)` para encapsular el comentario.
- Si el comentario es `null`, devuelve un `Optional` vacío, lo que obliga al código cliente a manejar explícitamente la ausencia del valor, mejorando la robustez.

#### Sucursal

Representa una de las sucursales de la clínica.

**Atributos:**
- `nombre` (`String`)
- `List<Encuesta>` que contiene todas las encuestas de esa sucursal.

---

### 2. Procesamiento con Stream API y `flatMap`

La clase `AnalisisEncuestas` contiene la lógica principal en su método `main`, donde se crea y procesa una lista de objetos `Sucursal`:

- `sucursales.stream()`: Inicia un `Stream` de objetos `Sucursal`.

#### Primer `flatMap` (para desanidar encuestas):

```java
.flatMap(sucursal -> sucursal.getEncuestas().stream())
