package com.example.inventario.repository;

import com.example.inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Métodos de consulta personalizados (opcionales para este reto, pero buenos para tener)
    List<Producto> findByPrecioGreaterThan(double precio);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByPrecioBetween(double min, double max);
    List<Producto> findByNombreStartingWithIgnoreCase(String prefijo);

    // ¡BONUS! Un método para encontrar productos por una marca específica
    List<Producto> findByMarca(Marca marca);
}