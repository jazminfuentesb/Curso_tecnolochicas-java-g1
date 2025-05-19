package Ejemplo_01;

public class ComisionVentas {

    public static void main(String[] args) {
        String nombre = "Carlos";
        double ventas = 30000;
        double comision;

        if (ventas >= 20000 && ventas <= 40000) {
            comision = ventas * 0.10;
            System.out.println("Comisión de " + nombre + ": $" + comision);
        } else if (ventas > 40000) {
            comision = ventas * 0.12;
            System.out.println("Comisión de " + nombre + ": $" + comision);
        } else {
            comision = ventas * 0.05;
            System.out.println("Comisión de " + nombre + ": $" + comision);
        }

        if (ventas > 25000 && ventas < 30000) {
            System.out.println("¡Carlos está cerca del siguiente nivel!");
        }

    }
}
