package Ejemplo_02;

public class Main {
    public static void main(String[] args) {
        Paquete paquete = new Paquete("Mario Gonzalez", 2.5);

        Imprimible impresora = new ImpresoraEtiqueta();
        impresora.imprimirEtiqueta(paquete);

        EstrategiaCostoEnvio estrategia = new CostoEnvioEstandar();
        ServicioEnvio servicio = new ServicioEnvio(estrategia);

        double costo = servicio.obtenerCostoEnvio(paquete);
        System.out.println("💰 Costo de envío: $" + costo);
    }
}
