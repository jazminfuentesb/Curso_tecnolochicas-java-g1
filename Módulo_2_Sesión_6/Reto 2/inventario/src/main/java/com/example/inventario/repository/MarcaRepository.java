package com.example.inventario.repository;

import com.example.inventario.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interfaz es un componente de repositorio de Spring
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    // Al extender JpaRepository, Spring Data JPA nos da métodos CRUD básicos automáticamente (save, findById, findAll, delete, etc.)
}