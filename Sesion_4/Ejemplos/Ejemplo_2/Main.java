package Ejemplo_02;

public class Main {
    public static void main(String[] args) {

        // Crear dos tickets con los mismos valores
        TicketSoporte t1 = new TicketSoporte("Error de login", "Accesos", 1);
        TicketSoporte t2 = new TicketSoporte("Error de login", "Accesos", 1);

        // Comparar si los objetos son equivalentes usando equals()
        System.out.println("¿Son iguales? " + t1.equals(t2));  // true

        // Mostrar los códigos hash de ambos objetos
        System.out.println("Hash de t1: " + t1.hashCode());
        System.out.println("Hash de t2: " + t2.hashCode());

        // Mostrar la representación legible del ticket
        System.out.println("Ticket 1:");
        System.out.println(t1);
    }
}
