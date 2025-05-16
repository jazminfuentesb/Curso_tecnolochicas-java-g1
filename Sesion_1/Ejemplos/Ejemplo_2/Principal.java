package Ejemplo_02;

public class Principal {
    public static void main(String[] args) {
        // Crear un nuevo objeto de tipo Estudiante
        Estudiante estudiante1 = new Estudiante();

        // Asignar valores a los atributos
        estudiante1.nombre = "Lena Marie";
        estudiante1.edad = 20;

        // Llamar al método saludar() del objeto
        estudiante1.saludar();
    }
}
