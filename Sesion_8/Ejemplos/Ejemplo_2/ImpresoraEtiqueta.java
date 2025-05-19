package Ejemplo_02;

public class ImpresoraEtiqueta implements Imprimible {
    public void imprimirEtiqueta(Paquete paquete) {
        System.out.println("📦 Envío a: " + paquete.getDestinatario());
    }
}
