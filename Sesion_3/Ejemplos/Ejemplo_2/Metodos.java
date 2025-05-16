package Ejemplo_02;

public class Metodos {

    // 1. Sin parámetros y sin retorno
    public void mostrarBienvenida() {
        System.out.println("👋 Bienvenido al sistema");
    }

    // 2. Con parámetros y sin retorno
    public void mostrarSuma(int a, int b) {
        int resultado = a + b;
        System.out.println("➕ La suma es: " + resultado);
    }

    // 3. Con parámetros y con retorno
    public int obtenerSuma(int a, int b) {
        return a + b;
    }

    // 4. Paso por valor
    public void duplicar(int numero) {
        numero = numero * 2;
        System.out.println("🔁 Dentro del método: " + numero);
    }

    // 5. Paso por referencia
    public void cambiarNombre(Persona p) {
        p.nombre = "Nuevo nombre";
    }

    // 6. Sobrecarga
    public void saludar() {
        System.out.println("👋 Hola");
    }

    public void saludar(String nombre) {
        System.out.println("👋 Hola, " + nombre);
    }
}

