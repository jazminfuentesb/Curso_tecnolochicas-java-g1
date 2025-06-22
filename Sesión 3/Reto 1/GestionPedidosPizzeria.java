import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// 1️⃣ Crear la clase Pedido
class Pedido {
    private String cliente;
    private String tipoEntrega; // "domicilio" o "local"
    private String telefono;    // Puede ser null

    public Pedido(String cliente, String tipoEntrega, String telefono) {
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    public String getCliente() {
        return cliente;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    // Implementar el método getTelefono() que devuelva un Optional<String>.
    public Optional<String> getTelefono() {
        return Optional.ofNullable(telefono); // Crea un Optional que es vacío si telefono es null, o contiene el valor si no es null
    }

    @Override
    public String toString() {
        return "Pedido [cliente=" + cliente + ", tipoEntrega=" + tipoEntrega + ", telefono=" + telefono + "]";
    }
}

public class GestionPedidosPizzeria {

    public static void main(String[] args) {

        // Crear una lista de pedidos de ejemplo
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("Ana García", "domicilio", "555-1234"));
        pedidos.add(new Pedido("Luis Pérez", "local", null)); // Recogida en local, sin teléfono
        pedidos.add(new Pedido("María López", "domicilio", "555-5678"));
        pedidos.add(new Pedido("Juan Ruiz", "domicilio", null)); // Domicilio, pero sin teléfono
        pedidos.add(new Pedido("Elena Castro", "local", "555-9999"));

        System.out.println("Procesando pedidos de pizzería...");

        // 2️⃣ Procesar la lista de pedidos usando Stream API
        List<String> mensajesConfirmacion = pedidos.stream()
            // 1. Filtrar solo los pedidos con tipo de entrega "domicilio".
            .filter(pedido -> "domicilio".equals(pedido.getTipoEntrega()))
            // 2. Recuperar los teléfonos disponibles usando Optional.
            // flatMap aquí es clave: transforma un Stream<Optional<String>> a un Stream<String>
            // desechando los Optional vacíos.
            .flatMap(pedido -> pedido.getTelefono().stream()) // Convierte Optional<String> a Stream<String> (0 o 1 elemento)
            // 3. Transformar cada teléfono en un mensaje de confirmación.
            .map(telefono -> "📞 Confirmación enviada al número: " + telefono)
            // Recolectar los resultados en una nueva lista de Strings
            .collect(Collectors.toList());

        // Mostrar todos los mensajes en consola.
        mensajesConfirmacion.forEach(System.out::println);

        System.out.println("\n✅ Procesamiento de pedidos finalizado.");
    }
}