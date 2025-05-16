package Ejemplo_02;

public class Principal {
    public static void main(String[] args) {
        Metodos util = new Metodos();

        // 1. Método sin parámetros y sin retorno
        util.mostrarBienvenida();

        // 2. Método con parámetros y sin retorno
        util.mostrarSuma(10, 20);

        // 3. Método con parámetros y con retorno
        int resultado = util.obtenerSuma(5, 7);
        System.out.println("🧮 Resultado: " + resultado);

        // 4. Paso por valor
        int numeroOriginal = 15;
        util.duplicar(numeroOriginal);
        System.out.println("🔁 Fuera del método: " + numeroOriginal);  // No cambia

        // 5. Paso por referencia
        Persona persona = new Persona("Mario");
        util.cambiarNombre(persona);
        System.out.println("🧾 Nombre actualizado: " + persona.nombre);

        // 6. Sobrecarga de métodos
        util.saludar();
        util.saludar("Mario");
    }
}
