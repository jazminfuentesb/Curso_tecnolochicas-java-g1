package com.example.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne; // Importa para la relación ManyToOne
import jakarta.persistence.JoinColumn; // Importa para la anotación JoinColumn
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; // Para validar que la marca no sea nula

@Entity // Declara que esta clase es una entidad JPA
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @Min(value = 1, message = "El precio debe ser al menos 1")
    private double precio;

    // --- Relación Many-to-One con Marca ---
    @ManyToOne // Indica que muchos Productos pueden estar asociados a una Marca
    @JoinColumn(name = "marca_id", nullable = false) // Define la columna de clave foránea en la tabla 'producto'
    @NotNull(message = "El producto debe tener una marca asociada") // Asegura que no se puede crear un Producto sin Marca
    private Marca marca;
    // --- Fin de relación ---

    // Constructor vacío (requerido por JPA)
    public Producto() {
    }

    // Constructor para crear objetos Producto sin especificar la marca inicialmente
    public Producto(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // --- Nuevo constructor que incluye la Marca ---
    public Producto(String nombre, String descripcion, double precio, Marca marca) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.marca = marca;
    }
    // --- Fin del nuevo constructor ---

    // Getters y Setters
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // --- Getter y Setter para la Marca ---
    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    // --- Fin Getter y Setter para Marca ---

    @Override
    public String toString() {
        // Incluimos el nombre de la marca en el toString para facilitar la depuración
        return "Producto[id=" + id + ", nombre='" + nombre + "', descripcion='" + descripcion + "', precio=" + String.format("%.2f", precio) + ", marca=" + (marca != null ? marca.getNombre() : "N/A") + "]";
    }
}