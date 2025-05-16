package Ejemplo_01;

public class Notificacion {
    String mensaje;

    public Notificacion(String mensaje) {
        this.mensaje = mensaje;
    }

    public void enviar() {
        System.out.println("📢 Enviando notificación: " + mensaje);
    }
}
