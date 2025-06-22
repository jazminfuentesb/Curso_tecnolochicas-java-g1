import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Clase Encuesta
class Encuesta {
    private String paciente;
    private String comentario; // Puede ser null
    private int calificacion;

    public Encuesta(String paciente, String comentario, int calificacion) {
        this.paciente = paciente;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public String getPaciente() {
        return paciente;
    }

    // Método que devuelve un Optional<String> para el comentario
    public Optional<String> getComentario() {
        return Optional.ofNullable(comentario);
    }

    public int getCalificacion() {
        return calificacion;
    }

    @Override
    public String toString() {
        return "Encuesta [paciente=" + paciente + ", comentario=" + comentario + ", calificacion=" + calificacion + "]";
    }
}

// Clase Sucursal
class Sucursal {
    private String nombre;
    private List<Encuesta> encuestas;

    public Sucursal(String nombre, List<Encuesta> encuestas) {
        this.nombre = nombre;
        this.encuestas = encuestas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Encuesta> getEncuestas() {
        return encuestas;
    }

    @Override
    public String toString() {
        return "Sucursal [nombre=" + nombre + ", encuestas=" + encuestas.size() + " encuestas]";
    }
}

public class AnalisisEncuestas {

    public static void main(String[] args) {
        System.out.println("Análisis de encuestas de satisfacción iniciado...");

        // Crear datos de ejemplo: Varias sucursales con sus encuestas
        List<Sucursal> sucursales = new ArrayList<>();

        // Sucursal Centro
        List<Encuesta> encuestasCentro = Arrays.asList(
            new Encuesta("Roberto", "El tiempo de espera fue largo", 2),
            new Encuesta("Sofía", "Todo excelente, muy buena atención", 5),
            new Encuesta("Pedro", null, 3) // Calificación baja, pero sin comentario
        );
        sucursales.add(new Sucursal("Centro", encuestasCentro));

        // Sucursal Norte
        List<Encuesta> encuestasNorte = Arrays.asList(
            new Encuesta("Gabriela", "La atención fue buena, pero la limpieza puede mejorar", 3),
            new Encuesta("Miguel", "Rápido y eficiente", 4)
        );
        sucursales.add(new Sucursal("Norte", encuestasNorte));

        // Sucursal Sur
        List<Encuesta> encuestasSur = Arrays.asList(
            new Encuesta("Laura", null, 5), // Calificación alta, sin comentario
            new Encuesta("Diego", "Personal amable y profesional", 4)
        );
        sucursales.add(new Sucursal("Sur", encuestasSur));


        // Procesar las encuestas usando Stream API y flatMap
        List<String> mensajesDeSeguimiento = sucursales.stream()
            // Desanidar todas las encuestas de las sucursales
            .flatMap(sucursal -> sucursal.getEncuestas().stream()
                // Filtrar solo las encuestas con calificación menor o igual a 3
                .filter(encuesta -> encuesta.getCalificacion() <= 3)
                // Recuperar los comentarios disponibles (sin null) y transformarlos en el mensaje
                // Usamos otro flatMap para aplanar el Optional<String> a Stream<String>
                .flatMap(encuesta -> encuesta.getComentario()
                                        .map(comentario -> "Sucursal " + sucursal.getNombre() + ": Seguimiento a paciente con comentario: \"" + comentario + "\"")
                                        .stream() // Convierte Optional<String> a Stream<String> (0 o 1 elemento)
                )
            )
            // Recolectar todos los mensajes finales
            .collect(Collectors.toList());

        // Mostrar todos los mensajes en consola
        mensajesDeSeguimiento.forEach(System.out::println);

        System.out.println("\n✅ Análisis de encuestas finalizado.");
    }
}