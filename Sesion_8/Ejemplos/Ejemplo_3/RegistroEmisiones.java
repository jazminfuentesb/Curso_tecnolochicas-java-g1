package Ejemplo_03;

import java.util.ArrayList;
import java.util.List;

public class RegistroEmisiones {
    public static void main(String[] args) {
        List<Double> emisiones = new ArrayList<>();
        emisiones.add(123.5);
        emisiones.add(200.0);
        emisiones.add(123.5); // Duplicado
        emisiones.add(300.0);
        emisiones.add(200.0); // Duplicado

        for (int i = 0; i < emisiones.size(); i++) {
            for (int j = i + 1; j < emisiones.size(); j++) {
                if (emisiones.get(i).equals(emisiones.get(j))) {
                    System.out.println("⚠️ Registro duplicado de emisión: " + emisiones.get(i));
                }
            }
        }
    }
}
