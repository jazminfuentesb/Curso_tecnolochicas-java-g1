package Ejemplo_02;

public class ServicioEnvio {
    private EstrategiaCostoEnvio estrategia;

    public ServicioEnvio(EstrategiaCostoEnvio estrategia) {
        this.estrategia = estrategia;
    }

    public double obtenerCostoEnvio(Paquete paquete) {
        return estrategia.calcularCosto(paquete);
    }
}
