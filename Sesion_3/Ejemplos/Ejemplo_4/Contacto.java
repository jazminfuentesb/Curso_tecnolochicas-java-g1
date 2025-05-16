package Ejemplo_04;

import java.util.Optional;

public class Contacto {

    // Utilizamos Optional para representar un valor que puede estar presente o no
    private Optional<String> telefono;

    // El constructor acepta un valor que puede ser null y lo envuelve con Optional.ofNullable
    public Contacto(String telefono) {
        this.telefono = Optional.ofNullable(telefono);
    }

    // Getter que devuelve el Optional, permitiendo al consumidor manejar la ausencia de valor de forma segura
    public Optional<String> getTelefono() {
        return telefono;
    }
}
