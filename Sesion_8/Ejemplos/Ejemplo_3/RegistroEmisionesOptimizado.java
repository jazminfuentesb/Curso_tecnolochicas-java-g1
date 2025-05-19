package Ejemplo_03;

import java.util.HashSet;
import java.util.Set;

public class RegistroEmisionesOptimizado {
    public static void main(String[] args) {
        double[] emisiones = {123.5, 200.0, 123.5, 300.0, 200.0};
        Set<Double> emisionesRegistradas = new HashSet<>();

        for (double valor : emisiones) {
            if (!emisionesRegistradas.add(valor)) {
                System.out.println("⚠️ Duplicado detectado: " + valor + " kg de CO₂");
            }
        }
    }
}
