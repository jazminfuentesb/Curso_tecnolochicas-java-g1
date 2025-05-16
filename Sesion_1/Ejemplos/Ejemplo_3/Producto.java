package Ejemplo_03;

public class Producto {
    // Atributos del producto
    String nombre;
    double precio;

    // Constructor que inicializa el producto con nombre y precio
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Método que muestra la información del producto en consola
    public void mostrarInformacion() {
        System.out.println("Producto: " + nombre + " - Precio: $" + precio);
        System.out.println("Producto: " + nombre + " - Precio: $" + String.format("%,.2f", precio));

    }
}
