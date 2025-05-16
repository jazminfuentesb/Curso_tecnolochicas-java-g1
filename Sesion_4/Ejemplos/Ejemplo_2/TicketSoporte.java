package Ejemplo_02;

import java.util.Objects;

// Clase TicketSoporte con métodos especiales para comparación y visualización
public class TicketSoporte {

    private String titulo;
    private String categoria;
    private int prioridad;
    private boolean estaActivo;

    public TicketSoporte(String titulo, String categoria, int prioridad) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.estaActivo = true;
    }

    // Sobrescribe equals() para comparar el contenido de los objetos
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Verifica si es el mismo objeto
        if (obj == null || getClass() != obj.getClass()) return false;

        TicketSoporte otro = (TicketSoporte) obj;
        return this.titulo.equals(otro.titulo) &&
               this.categoria.equals(otro.categoria) &&
               this.prioridad == otro.prioridad &&
               this.estaActivo == otro.estaActivo;
    }

    // hashCode() debe ser consistente con equals()
    @Override
    public int hashCode() {
        return Objects.hash(titulo, categoria, prioridad, estaActivo);
    }

    // toString() proporciona una representación legible del objeto
    @Override
    public String toString() {
        return "🎫 TicketSoporte {" +
                "titulo='" + titulo + "'" +
                ", categoria='" + categoria + "'" +
                ", prioridad=" + prioridad +
                ", estaActivo=" + estaActivo +
                '}';
    }
}
