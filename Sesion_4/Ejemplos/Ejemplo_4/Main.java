package Ejemplo_04;

public class Main {
    public static void main(String[] args) {

        // Empleado con encapsulación
        Empleado empleado = new Empleado();
        empleado.setNombre("Valeria");
        empleado.setEdad(29);
        empleado.mostrarInfo();

        // Contrato inmutable
        Contrato contrato = new Contrato("Tiempo completo", 25000.00);
        contrato.mostrarInfo();
    }
}
