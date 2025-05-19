package Ejemplo_02;

public class CostoEnvioEstandar implements EstrategiaCostoEnvio {
    public double calcularCosto(Paquete paquete) {
        return paquete.getPeso() * 10.5;
    }
}
