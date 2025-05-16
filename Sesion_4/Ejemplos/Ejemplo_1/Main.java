package Ejemplo_01;

public class Main {
    public static void main(String[] args) {

        // Crear un ticket con el constructor por defecto
        TicketSoporte ticket1 = new TicketSoporte();
        System.out.println("\n✅ Ticket creado con constructor por defecto:");
        ticket1.mostrarInfo();

        // Crear un ticket con parámetros personalizados
        TicketSoporte ticket2 = new TicketSoporte("Error al iniciar sesión", "Accesos", 1);
        System.out.println("\n✅ Ticket creado con parámetros:");
        ticket2.mostrarInfo();

        // Cerrar el ticket personalizado
        ticket2.cerrar();
        ticket2.mostrarInfo();

        // Crear un ticket como copia del anterior
        TicketSoporte ticket3 = new TicketSoporte(ticket2);
        System.out.println("\n✅ Ticket copiado desde otro:");
        ticket3.mostrarInfo();

        // Crear un ticket usando solo el título
        TicketSoporte ticket4 = new TicketSoporte("Consulta sobre facturación");
        System.out.println("\n✅ Ticket con constructor sobrecargado (solo título):");
        ticket4.mostrarInfo();
    }
}
