# 📄 README: Reto 01 - Gestión de Órdenes de Producción en Planta Industrial

Este repositorio contiene la solución al **Reto 01** del **Módulo 2**, centrado en el uso de **genéricos y wildcards en Java** para manejar diferentes tipos de órdenes de producción en un entorno industrial.

---

## 🎯 Objetivo del Reto

El objetivo principal de este desafío fue construir un sistema que pudiera:

- Clasificar y gestionar **órdenes de producción en masa**, **personalizadas** y **prototipos**.
- Aprovechar los **genéricos y wildcards** de Java para procesar colecciones de estos distintos tipos de órdenes de manera **flexible y segura**.
- Desarrollar métodos capaces de leer y manipular órdenes con restricciones de tipo adecuadas, garantizando la **robustez del código**.

---

## 🧠 Contexto del Problema

Imaginamos una planta industrial con tres tipos de producción:

- **Órdenes de producción en masa** (🔧): Productos estándar, fabricados en grandes volúmenes.
- **Órdenes personalizadas** (🛠️): Productos adaptados a las especificaciones únicas de un cliente, lo que implica un costo adicional por ajuste.
- **Prototipos** (🧪): Productos en fase de desarrollo o prueba, a menudo en pequeñas cantidades.

---

## 📝 Implementación

### 1. Estructura de Clases

#### `OrdenProduccion` (Clase Abstracta)
- Define los atributos comunes a todas las órdenes: `codigo` (String) y `cantidad` (int).
- Incluye un método abstracto `mostrarResumen()`, que cada subclase debe implementar.

#### `OrdenMasa` (Subclase)
- Extiende `OrdenProduccion`.
- Proporciona la implementación de `mostrarResumen()` específica para órdenes de producción en masa.

#### `OrdenPersonalizada` (Subclase)
- Extiende `OrdenProduccion`.
- Añade un atributo exclusivo: `cliente` (String).
- Implementa `mostrarResumen()` con detalles para órdenes personalizadas, incluyendo el nombre del cliente.

#### `OrdenPrototipo` (Subclase)
- Extiende `OrdenProduccion`.
- Incorpora un atributo único: `faseDesarrollo` (String).
- Implementa `mostrarResumen()` para prototipos, indicando su fase actual.

---

### 2. Métodos de Utilidad para Gestión de Órdenes

La clase `GestionOrdenesProduccion` contiene métodos estáticos esenciales que demuestran la aplicación de **genéricos y wildcards**:

#### `mostrarOrdenes(List<? extends OrdenProduccion> lista)`
- Utiliza el wildcard de límite superior `? extends OrdenProduccion`.
- Permite leer y mostrar información de listas como `List<OrdenMasa>`, `List<OrdenPersonalizada>`, `List<OrdenPrototipo>`, etc.
- **Nota:** No permite agregar nuevos elementos a la lista dentro del método.

#### `procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional)`
- Emplea el wildcard de límite inferior `? super OrdenPersonalizada`.
- Diseñado para modificar/procesar órdenes personalizadas.
- Acepta listas de `OrdenPersonalizada` o cualquier superclase (como `OrdenProduccion` o `Object`), permitiendo la modificación y/o adición de objetos.

---

### 3. Desafío Adicional: Resumen de Órdenes

#### `contarTiposDeOrdenes(List<? extends OrdenProduccion> todasLasOrdenes)`
- Calcula y muestra el número total de órdenes de cada tipo.
- Utiliza el operador `instanceof` para identificar el tipo específico de cada orden.

---



