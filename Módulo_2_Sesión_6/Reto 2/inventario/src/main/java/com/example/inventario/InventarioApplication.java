package com.example.inventario;

import com.example.inventario.model.Marca;
import com.example.inventario.model.Producto;
import com.example.inventario.repository.MarcaRepository;
import com.example.inventario.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner; // Importar
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; // Importar

@SpringBootApplication
public class InventarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioApplication.class, args);
    }

    // Definimos un Bean de CommandLineRunner que se ejecutará al inicio de la aplicación
    @Bean
    public CommandLineRunner run(ProductoRepository productoRepository, MarcaRepository marcaRepository) {
        return args -> {
            System.out.println("--- Iniciando pruebas de Inventario con Relaciones JPA ---");

            // --- 1. Crear al menos 2 marcas ---
            System.out.println("\n--- Creando Marcas ---");
            Marca apple = new Marca("Apple");
            Marca samsung = new Marca("Samsung");
            Marca dell = new Marca("Dell");

            marcaRepository.save(apple);
            marcaRepository.save(samsung);
            marcaRepository.save(dell);
            System.out.println("Marcas guardadas: " + marcaRepository.findAll());


            // --- 2. Asociar al menos 2 productos a cada marca ---
            System.out.println("\n--- Guardando Productos Asociados a Marcas ---");
            productoRepository.save(new Producto("iPhone 15", "Smartphone de última generación", 999.99, apple));
            productoRepository.save(new Producto("iPad Pro", "Tablet potente para profesionales", 799.00, apple));

            productoRepository.save(new Producto("Galaxy S24", "Smartphone Android insignia", 899.99, samsung));
            productoRepository.save(new Producto("Smart TV QLED 4K", "Televisor de alta resolución", 1500.00, samsung));

            productoRepository.save(new Producto("Dell XPS 15", "Laptop premium para productividad", 1800.00, dell));
            productoRepository.save(new Producto("Dell UltraSharp Monitor", "Monitor profesional de alta calidad", 650.00, dell));

            System.out.println("Productos asociados y guardados.");


            // --- 3. Mostrar los productos agrupados por marca ---
            System.out.println("\n📚 Productos por marca:");
            // Mejorado: Usar findByMarca del ProductoRepository para obtener productos directamente asociados
            marcaRepository.findAll().forEach(marca -> {
                System.out.println("🏷️ " + marca.getNombre() + ":");
                List<Producto> productosDeEstaMarca = productoRepository.findByMarca(marca);
                if (productosDeEstaMarca.isEmpty()) {
                    System.out.println("   (No hay productos asociados a esta marca)");
                } else {
                    productosDeEstaMarca.forEach(p -> System.out.println("   - " + p.getNombre()));
                }
            });

            System.out.println("\n--- Pruebas de Relaciones JPA Finalizadas ---");
        };
    }
}