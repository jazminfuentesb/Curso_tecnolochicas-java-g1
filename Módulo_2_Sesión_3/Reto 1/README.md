# 📄 README: Reto 1 - Gestión de Pedidos de Pizzería con Optional y Stream API

Este repositorio contiene la solución al **Reto 1 d ela Sesión 3 del Módulo 2**, enfocado en el uso de `Optional` y la **Stream API** para procesar eficientemente una lista de pedidos de una pizzería, garantizando el manejo seguro de datos que podrían estar incompletos.

---

## 🎯 Objetivo del Reto

El objetivo principal de este desafío fue:

- Implementar la clase `Optional` para representar y manejar de forma segura la posible ausencia de valores (como números de teléfono `null`).
- Utilizar las operaciones de la **Stream API** para filtrar, transformar y procesar colecciones de datos de manera declarativa y eficiente.
- Generar mensajes personalizados basados en datos filtrados y seguros.

---

## 🧠 Contexto del Problema

En una pizzería, el sistema de pedidos necesita gestionar entregas a domicilio. Sin embargo, no todos los clientes proporcionan un número de teléfono para la confirmación.  
El desafío consiste en procesar estos pedidos para:

- Identificar solo aquellos que son para **entrega a domicilio**.
- Extraer de forma segura los **números de teléfono disponibles**, ignorando los que son `null`.
- Crear un **mensaje de confirmación** por cada número de teléfono válido encontrado.

---

## 📝 Implementación

La solución se compone de una clase `Pedido` y una clase principal `GestionPedidosPizzeria` que utiliza la **Stream API**.

---

### 1. Clase `Pedido`

Esta clase modela un pedido individual y es clave para el uso de `Optional`:

**Atributos:**
- `cliente` (`String`)
- `tipoEntrega` (`String`) - puede ser `"domicilio"` o `"local"`
- `telefono` (`String`) - Este atributo puede ser `null`.

**Método `getTelefono()`:**
- Devuelve un `Optional<String>`.
- Utiliza `Optional.ofNullable(telefono)` para crear una instancia de `Optional`.  
  Si `telefono` es `null`, `Optional.ofNullable()` devuelve un `Optional.empty()`; de lo contrario, devuelve un `Optional` que contiene el valor de `telefono`.
- Esto fuerza al código que llama a considerar explícitamente la posibilidad de que el número de teléfono no exista, **promoviendo un manejo más robusto de los `null`**.

---

### 2. Procesamiento con Stream API

La clase `GestionPedidosPizzeria` contiene el método `main` donde se demuestra el procesamiento de la lista de pedidos:

- Se crea una `List<Pedido>` con una mezcla de pedidos a domicilio y para recoger en local, incluyendo algunos con y sin número de teléfono.

- Se utiliza una cadena de operaciones de la **Stream API** para procesar la lista:

```java
.filter(pedido -> "domicilio".equals(pedido.getTipoEntrega()))
