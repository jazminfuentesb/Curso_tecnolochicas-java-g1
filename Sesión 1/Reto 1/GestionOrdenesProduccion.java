import java.util.ArrayList;
import java.util.List;

// 1. Clase abstracta OrdenProduccion
abstract class OrdenProduccion {
    protected String codigo;
    protected int cantidad;

    public OrdenProduccion(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    // Método para imprimir información básica de la orden
    public abstract void mostrarResumen();
}

// 2. Subclase OrdenMasa
class OrdenMasa extends OrdenProduccion {
    public OrdenMasa(String codigo, int cantidad) {
        super(codigo, cantidad);
    }

    @Override
    public void mostrarResumen() {
        System.out.println("🔧 OrdenMasa - Código: " + codigo + " - Cantidad: " + cantidad);
    }
}

// 3. Subclase OrdenPersonalizada
class OrdenPersonalizada extends OrdenProduccion {
    private String cliente;

    public OrdenPersonalizada(String codigo, int cantidad, String cliente) {
        super(codigo, cantidad);
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("🛠️ OrdenPersonalizada - Código: " + codigo + " - Cantidad: " + cantidad + " - Cliente: " + cliente);
    }
}

// 4. Subclase OrdenPrototipo
class OrdenPrototipo extends OrdenProduccion {
    private String faseDesarrollo;

    public OrdenPrototipo(String codigo, int cantidad, String faseDesarrollo) {
        super(codigo, cantidad);
        this.faseDesarrollo = faseDesarrollo;
    }

    public String getFaseDesarrollo() {
        return faseDesarrollo;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("🧪 OrdenPrototipo - Código: " + codigo + " - Cantidad: " + cantidad + " - Fase: " + faseDesarrollo);
    }
}

public class GestionOrdenesProduccion {

    // Método genérico: mostrarOrdenes
    // Usa <? extends OrdenProduccion> para leer cualquier tipo de OrdenProduccion o sus subclases.
    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
    }

    // Método para procesar órdenes personalizadas
    // Usa <? super OrdenPersonalizada> para poder modificar OrdenPersonalizada o sus superclases (en este caso, solo OrdenPersonalizada).
    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("\n💰 Procesando órdenes personalizadas...");
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada) {
                OrdenPersonalizada orden = (OrdenPersonalizada) obj;
                // Aquí podrías agregar lógica para actualizar el costo en la orden, si tuviera un atributo de costo.
                // Para este ejemplo, simplemente mostramos el mensaje.
                System.out.println("✅ Orden " + orden.getCodigo() + " ajustada con costo adicional de $" + costoAdicional);
            }
        }
    }

    // Desafío adicional: Contar el total de órdenes de cada tipo
    public static void contarTiposDeOrdenes(List<? extends OrdenProduccion> todasLasOrdenes) {
        int countMasa = 0;
        int countPersonalizada = 0;
        int countPrototipo = 0;

        for (OrdenProduccion orden : todasLasOrdenes) {
            if (orden instanceof OrdenMasa) {
                countMasa++;
            } else if (orden instanceof OrdenPersonalizada) {
                countPersonalizada++;
            } else if (orden instanceof OrdenPrototipo) {
                countPrototipo++;
            }
        }

        System.out.println("\n📊 Resumen total de órdenes:");
        System.out.println("🔧 Producción en masa: " + countMasa);
        System.out.println("🛠️Personalizadas: " + countPersonalizada);
        System.out.println("🧪 Prototipos: " + countPrototipo);
    }

    public static void main(String[] args) {
        // Crear listas con varios tipos de órdenes
        List<OrdenMasa> ordenesMasa = new ArrayList<>();
        ordenesMasa.add(new OrdenMasa("A123", 500));
        ordenesMasa.add(new OrdenMasa("A124", 750));

        List<OrdenPersonalizada> ordenesPersonalizadas = new ArrayList<>();
        ordenesPersonalizadas.add(new OrdenPersonalizada("P456", 100, "ClienteX"));
        ordenesPersonalizadas.add(new OrdenPersonalizada("P789", 150, "ClienteY"));

        List<OrdenPrototipo> ordenesPrototipo = new ArrayList<>();
        ordenesPrototipo.add(new OrdenPrototipo("T789", 10, "Diseño"));
        ordenesPrototipo.add(new OrdenPrototipo("T790", 5, "Pruebas"));

        // Demostrar el método mostrarOrdenes
        System.out.println("📋 Órdenes registradas:");
        mostrarOrdenes(ordenesMasa);
        System.out.println("\n📋 Órdenes registradas:");
        mostrarOrdenes(ordenesPersonalizadas);
        System.out.println("\n📋 Órdenes registradas:");
        mostrarOrdenes(ordenesPrototipo);

        // Demostrar el método procesarPersonalizadas
        // Para este método, se requiere una lista que pueda contener OrdenPersonalizada o sus superclases.
        // Creamos una lista genérica que usaremos para procesar.
        List<OrdenPersonalizada> listaParaProcesarPersonalizadas = new ArrayList<>();
        listaParaProcesarPersonalizadas.add(new OrdenPersonalizada("P456", 100, "ClienteX"));
        listaParaProcesarPersonalizadas.add(new OrdenPersonalizada("P789", 150, "ClienteY"));
        procesarPersonalizadas(listaParaProcesarPersonalizadas, 200);

        // Para el desafío adicional, combinamos todas las órdenes en una sola lista para contarlas.
        List<OrdenProduccion> todasLasOrdenes = new ArrayList<>();
        todasLasOrdenes.addAll(ordenesMasa);
        todasLasOrdenes.addAll(ordenesPersonalizadas);
        todasLasOrdenes.addAll(ordenesPrototipo);

        contarTiposDeOrdenes(todasLasOrdenes);
    }
}