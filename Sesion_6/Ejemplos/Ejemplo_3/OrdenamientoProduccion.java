package Ejemplo_03;

import java.util.*;

// Clase que representa una orden de producción
class OrdenProduccion implements Comparable<OrdenProduccion> {
    String codigo;
    String producto;
    int prioridad;

    // Constructor para inicializar cada orden
    public OrdenProduccion(String codigo, String producto, int prioridad) {
        this.codigo = codigo;
        this.producto = producto;
        this.prioridad = prioridad;
    }

    // Implementación de la interfaz Comparable para ordenar por producto (orden natural)
    @Override
    public int compareTo(OrdenProduccion otra) {
        return this.producto.compareTo(otra.producto);
    }

    // Método toString para mostrar el contenido de la orden
    @Override
    public String toString() {
        return "Código: " + codigo + " | Producto: " + producto + " | Prioridad: " + prioridad;
    }
}

public class OrdenamientoProduccion {
    public static void main(String[] args) {
        // Creamos una lista con varias órdenes de producción
        List<OrdenProduccion> ordenes = new ArrayList<>();
        ordenes.add(new OrdenProduccion("OP-003", "Tornillo M6", 2));
        ordenes.add(new OrdenProduccion("OP-001", "Tuerca M8", 1));
        ordenes.add(new OrdenProduccion("OP-002", "Arandela 10mm", 3));

        // Ordenamos por producto usando Comparable (orden natural)
        Collections.sort(ordenes);
        System.out.println("📦 Órdenes ordenadas por producto (orden natural):");
        for (OrdenProduccion op : ordenes) {
            System.out.println(op);
        }

        // Ordenamos por prioridad usando Comparator (orden personalizado)
        ordenes.sort(new Comparator<OrdenProduccion>() {
            @Override
            public int compare(OrdenProduccion a, OrdenProduccion b) {
                return Integer.compare(a.prioridad, b.prioridad); // Menor prioridad primero
            }
        });

        System.out.println("\n🔥 Órdenes ordenadas por prioridad (1 = alta):");
        for (OrdenProduccion op : ordenes) {
            System.out.println(op);
        }
    }
}