# 📄 README: Reto 02 - Gestión de Materiales de Curso en una Plataforma Educativa  
Este repositorio contiene la solución al Reto 02 del Módulo 2, enfocado en el uso avanzado de genéricos, wildcards (?, extends, super) y restricciones de tipo para la gestión de diversos materiales en una plataforma educativa.

## 🎯 Objetivo del Reto  
El objetivo principal de este desafío fue implementar un sistema que permitiera:

- Gestionar y mostrar diferentes tipos de materiales de un curso (videos, artículos, ejercicios).
- Aplicar filtros específicos y realizar acciones según el tipo de material.
- Demostrar la flexibilidad y seguridad de tipo que ofrecen los genéricos y wildcards de Java.

## 🧠 Contexto del Problema  
En una plataforma educativa online, los cursos se componen de varios tipos de materiales:

- **Videos (🎥):** Lecciones audiovisuales con una duración específica.  
- **Artículos (📄):** Contenido textual con un conteo de palabras.  
- **Ejercicios (📝):** Tareas que pueden ser marcadas como revisadas.  

El sistema debe ser capaz de listar todos los materiales, calcular la duración total de los videos y actualizar el estado de los ejercicios.

## 📝 Implementación  
La solución se organiza en las siguientes clases y métodos principales:

### 1. Estructura de Clases  

**MaterialCurso (Clase Abstracta):**  
- Define los atributos comunes: titulo (String) y autor (String).  
- Incluye el método abstracto mostrarDetalle(), que cada subclase debe implementar para describir sus particularidades.  

**Video (Subclase):**  
- Extiende MaterialCurso.  
- Añade el atributo duracion (en minutos).  
- Implementa mostrarDetalle() para videos.  

**Articulo (Subclase):**  
- Extiende MaterialCurso.  
- Añade el atributo palabras (conteo de palabras).  
- Implementa mostrarDetalle() para artículos.  

**Ejercicio (Subclase):**  
- Extiende MaterialCurso.  
- Añade el atributo booleano revisado.  
- Incluye un método setRevisado() para modificar su estado.  
- Implementa mostrarDetalle() para ejercicios.  

### 2. Métodos de Utilidad para la Gestión de Materiales  
La clase GestionMaterialesCurso contiene métodos estáticos que ejemplifican el uso de genéricos y wildcards:

**mostrarMateriales(List<? extends MaterialCurso> lista):**  
- Utiliza el wildcard de límite superior (? extends MaterialCurso).  
- Este método es perfecto para leer y mostrar los detalles de cualquier tipo de MaterialCurso o sus subclases (ej. List<Video>, List<Articulo>).  
- Garantiza que se puede acceder a la información común sin permitir la adición de nuevos elementos a la lista dentro del método.  

**contarDuracionVideos(List<? extends Video> lista):**  
- También usa un wildcard de límite superior (? extends Video), pero más específico.  
- Acepta solo listas de Video o sus subclases.  
- Esto asegura que todos los elementos en la lista tendrán el método getDuracion(), permitiendo sumar sus duraciones de forma segura y directa.  

**marcarEjerciciosRevisados(List<? super Ejercicio> lista):**  
- Emplea el wildcard de límite inferior (? super Ejercicio).  
- Ideal para métodos que necesitan modificar elementos o añadir elementos de un tipo específico (en este caso, Ejercicio).  
- Acepta una lista que puede ser de Ejercicio o de cualquier superclase de Ejercicio (como MaterialCurso).  
- Permite iterar y realizar operaciones de escritura (como llamar a setRevisado()) de forma segura.  

### 3. Desafío Adicional: Filtrado por Autor  

**filtrarMaterialesPorAutor(List<? extends MaterialCurso> lista, String autorFiltro):**  
- Este método utiliza ? extends MaterialCurso para la lista de entrada, ya que solo necesita leer los datos para el filtro.  
- Demuestra el uso de la interfaz funcional Predicate<MaterialCurso> para definir una condición de filtrado (`material -> material.getAutor().equals(autorFiltro)`) de manera concisa y expresiva.  
