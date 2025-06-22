package com.example.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank; // Importa para validaciones

@Entity // Declara que esta clase es una entidad JPA y se mapeará a una tabla de BD
public class Marca {

    @Id // Marca 'id' como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura el ID para que se autoincremente en la BD
    private Long id;

    @NotBlank(message = "El nombre de la marca no puede estar vacío") // Validación: el nombre no puede ser nulo o solo espacios en blanco
    private String nombre;

    // Constructor vacío (JPA lo requiere)
    public Marca() {
    }

    // Constructor para crear objetos Marca con un nombre
    public Marca(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters (necesarios para que JPA acceda a las propiedades)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Marca[id=" + id + ", nombre='" + nombre + "']";
    }
}