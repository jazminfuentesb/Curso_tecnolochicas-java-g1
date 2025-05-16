package Ejemplo_04;

// ✅ Clase con atributos privados, validación y acceso controlado
public class Empleado {

    private String nombre;
    private int edad;

    public Empleado() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            this.nombre = nombre;
        } else {
            System.out.println("❌ El nombre no puede estar vacío.");
        }
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad > 0) {
            this.edad = edad;
        } else {
            System.out.println("❌ La edad debe ser mayor a 0.");
        }
    }

    public void mostrarInfo() {
        System.out.println("👨 Empleado: " + nombre + ", Edad: " + edad);
    }
}
