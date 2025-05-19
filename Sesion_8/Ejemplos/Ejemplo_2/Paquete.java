package Ejemplo_02;

public class Paquete {
    private String destinatario;
    private double peso;

    public Paquete(String destinatario, double peso) {
        this.destinatario = destinatario;
        this.peso = peso;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public double getPeso() {
        return peso;
    }
}
