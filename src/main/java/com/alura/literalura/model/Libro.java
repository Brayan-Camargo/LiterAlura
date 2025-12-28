package com.alura.literalura.model;

import com.alura.literalura.dto.LibroDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    private Long id;
    @Column(length = 1000)
    private String titulo;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer numeroDeDescargas;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor autor;

    public Libro() {}

    public Libro(LibroDTO datosLibro){
        this.id = datosLibro.id();
        this.titulo = datosLibro.titulo();
        this.idioma = Idioma.fromString(datosLibro.idiomas().get(0));
        this.numeroDeDescargas = datosLibro.numeroDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
        // Si el autor no tiene este libro en su lista, lo agregamos
        if (autor != null && !autor.getLibros().contains(this)) {
            autor.getLibros().add(this);
        }
    }

    @Override
    public String toString() {
        return "---------------- LIBRO ----------------" + "\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + (autor != null ? autor.getNombre() : "Desconocido") + "\n" +
                "Idioma: " + idioma + "\n" +
                "Número de descargas: " + numeroDeDescargas + "\n" +
                "---------------------------------------" + "\n";
    }
}
