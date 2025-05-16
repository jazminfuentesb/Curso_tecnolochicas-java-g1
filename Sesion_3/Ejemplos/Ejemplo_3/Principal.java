package Ejemplo_03;

public class Principal {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Mario", 500.0);
        Empleado empleado = new Empleado();

        // Accedemos al nombre (método público)
        System.out.println("👤 Usuario: " + usuario.getNombre());

        // Accedemos al saldo usando método getter
        System.out.println("💰 Saldo inicial: $" + usuario.getSaldo());

        // Usamos un método público para modificar saldo
        usuario.depositar(250.0);
        System.out.println("💰 Saldo después del depósito: $" + usuario.getSaldo());

        // ✅ Acceso permitido desde método de la subclase
        System.out.println("📌 Nacionalidad antes del cambio:");
        empleado.mostrarNacionalidad();

        // ⚠️ Modificamos directamente el atributo 'nacionalidad' desde una clase del mismo paquete
        empleado.nacionalidad = "Reino Unido";

        System.out.println("📌 Nacionalidad después del cambio:");
        empleado.mostrarNacionalidad();
    }
}