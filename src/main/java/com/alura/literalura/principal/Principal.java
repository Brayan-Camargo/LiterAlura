package com.alura.literalura.principal;

import com.alura.literalura.dto.LibroDTO;
import com.alura.literalura.dto.RespuestaLibrosDTO;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.DataConverter;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumer = new ConsumoAPI();
    private DataConverter converter = new DataConverter();
    private Scanner teclado = new Scanner(System.in);

    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;

    public Principal(LibroRepository repository, AutorRepository authorRepository) {
        this.repositorioLibro = repository;
        this.repositorioAutor = authorRepository;
    }

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Enlistar libros registrados
                    3 - Enlistar autores registrados
                    4 - Enlistar Autores vivos en un año determinado
                    5 - Enlistar libros por Idioma
                                                     
                    0 - Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    buscarLibrosRegistrados();
                    break;
                case 3:
                    buscarAutoresRegistrados();
                    break;
                case 4:
                    buscarAutoresVivosPorAno();
                    break;
                case 5:
                    buscarLibroPorIdioma();
                    break;
                case 0:
                    System.out.println("Gracias por usar LiterAlura. BYE.");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    // --- AQUÍ EMPIEZAN LOS MÉTODOS ---

    private void buscarLibroPorTitulo() {
        LibroDTO datos = getDatosLibro();

        if (datos != null) {
            var datosAutor = datos.autores().get(0);
            Autor autor = new Autor(datosAutor);
            Libro libro = new Libro(datos, autor);
            repositorioLibro.save(libro);
            System.out.println(libro);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    // Este metodo ayuda a buscar en la API
    private LibroDTO getDatosLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var nombreLibro = teclado.nextLine();
        var json = consumer.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));

        // Gutendex devuelve una lista llamada "results". Necesitamos este DTO para leerla.
        var datosBusqueda = converter.getData(json, RespuestaLibrosDTO.class);

        if (datosBusqueda != null && datosBusqueda.resultados() != null && !datosBusqueda.resultados().isEmpty()) {
            return datosBusqueda.resultados().get(0);
        }
        return null;
    }

    private void buscarLibrosRegistrados() {
        // Lógica para el paso 2
    }

    private void buscarAutoresRegistrados() {
        // Lógica para el paso 3
    }

    private void buscarAutoresVivosPorAno() {
        // Lógica para el paso 4
    }

    private void buscarLibroPorIdioma() {
        // Lógica para el paso 5
    }
}