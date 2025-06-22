import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate; // <--- Asegúrate de que esta línea esté presente

// 1. Clase abstracta MaterialCurso
abstract class MaterialCurso {
    protected String titulo;
    protected String autor;

    public MaterialCurso(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    // Método abstracto para mostrar los detalles del material
    public abstract void mostrarDetalle();
}

// 2. Subclase Video
class Video extends MaterialCurso {
    private int duracion; // en minutos

    public Video(String titulo, String autor, int duracion) {
        super(titulo, autor);
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("🎥 Video: " + titulo + " - Autor: " + autor + " - Duración: " + duracion + " min");
    }
}

// 3. Subclase Articulo
class Articulo extends MaterialCurso {
    private int palabras; // conteo de palabras

    public Articulo(String titulo, String autor, int palabras) {
        super(titulo, autor);
        this.palabras = palabras;
    }

    public int getPalabras() {
        return palabras;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("📄 Artículo: " + titulo + " - Autor: " + autor + " - Palabras: " + palabras);
    }
}

// 4. Subclase Ejercicio
class Ejercicio extends MaterialCurso {
    private boolean revisado;

    public Ejercicio(String titulo, String autor, boolean revisado) {
        super(titulo, autor);
        this.revisado = revisado;
    }

    public boolean isRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("📝 Ejercicio: " + titulo + " - Autor: " + autor + " - Revisado: " + revisado);
    }
}

public class GestionMaterialesCurso {

    // Método genérico: mostrarMateriales
    // Usa <? extends MaterialCurso> para leer cualquier tipo de MaterialCurso o sus subclases.
    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        System.out.println("📚 Materiales del curso:");
        for (MaterialCurso material : lista) {
            material.mostrarDetalle();
        }
    }

    // Método para contar la duración total de los videos
    // Usa <? extends Video> para garantizar que solo se procesen objetos de tipo Video o sus subclases.
    public static void contarDuracionVideos(List<? extends Video> lista) {
        int duracionTotal = 0;
        for (Video video : lista) {
            duracionTotal += video.getDuracion();
        }
        System.out.println("\n🎥 Duración total de videos: " + duracionTotal + " minutos");
    }

    // Método para marcar ejercicios como revisados
    // Usa <? super Ejercicio> para permitir la modificación de objetos de tipo Ejercicio
    // o de sus superclases (en este caso, directamente Ejercicio).
    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object obj : lista) {
            if (obj instanceof Ejercicio) {
                Ejercicio ejercicio = (Ejercicio) obj;
                if (!ejercicio.isRevisado()) { // Solo marcar si no está revisado
                    ejercicio.setRevisado(true);
                    System.out.println("✅ Ejercicio '" + ejercicio.getTitulo() + "' marcado como revisado.");
                }
            }
        }
    }

    // Desafío adicional: Filtrar materiales por autor usando Predicate
    public static void filtrarMaterialesPorAutor(List<? extends MaterialCurso> lista, String autorFiltro) {
        System.out.println("\n🔍 Filtrando materiales por autor (" + autorFiltro + "):");
        // Definimos el Predicate para filtrar por autor
        Predicate<MaterialCurso> porAutor = material -> material.getAutor().equals(autorFiltro);

        for (MaterialCurso material : lista) {
            if (porAutor.test(material)) {
                material.mostrarDetalle();
            }
        }
    }

    public static void main(String[] args) {
        // Crear listas de diferentes tipos de materiales
        List<Video> videos = new ArrayList<>();
        videos.add(new Video("Introducción a Java", "Mario", 15));
        videos.add(new Video("POO en Java", "Carlos", 20));

        List<Articulo> articulos = new ArrayList<>();
        articulos.add(new Articulo("Historia de Java", "Ana", 1200));
        articulos.add(new Articulo("Tipos de datos", "Luis", 800));

        List<Ejercicio> ejercicios = new ArrayList<>();
        ejercicios.add(new Ejercicio("Variables y tipos", "Luis", false));
        ejercicios.add(new Ejercicio("Condicionales", "Mario", false));

        // Para mostrar todos los materiales y el filtro por autor, combinamos todas las listas
        // en una lista de tipo MaterialCurso.
        List<MaterialCurso> todosLosMateriales = new ArrayList<>();
        todosLosMateriales.addAll(videos);
        todosLosMateriales.addAll(articulos);
        todosLosMateriales.addAll(ejercicios);

        // 1. Mostrar todos los materiales
        mostrarMateriales(todosLosMateriales);

        // 2. Contar duración total de videos
        contarDuracionVideos(videos);

        // 3. Marcar ejercicios como revisados
        // Se pasa la lista de ejercicios directamente, ya que el wildcard <? super Ejercicio> lo permite.
        System.out.println(); // Salto de línea para la salida
        marcarEjerciciosRevisados(ejercicios);

        // 4. Desafío Adicional: Filtrar materiales por autor
        filtrarMaterialesPorAutor(todosLosMateriales, "Mario");
    }
}