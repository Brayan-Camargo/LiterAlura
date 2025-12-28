package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Al extender de JpaRepository, Java ya sabe cómo GUARDAR,
    // BUSCAR y ELIMINAR libros sin que yo escriba el código.
}