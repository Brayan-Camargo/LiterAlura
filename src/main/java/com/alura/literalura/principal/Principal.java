package com.alura.literalura.principal;

import com.alura.literalura.dto.LibroDTO;
import com.alura.literalura.dto.RespuestaLibrosDTO;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.DataConverter;

import java.util.List;
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

            Libro libro = new Libro(datos);
            libro.setAutor(autor);

            try {
                repositorioLibro.save(libro);
                System.out.println(libro);
            } catch (Exception e) {
                System.out.println("Error al guardar: El libro ya existe o hay un problema con los datos.");
            }
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private LibroDTO getDatosLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var nombreLibro = teclado.nextLine();
        var json = consumer.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));

        var datosBusqueda = converter.getData(json, RespuestaLibrosDTO.class);

        if (datosBusqueda != null && datosBusqueda.resultados() != null && !datosBusqueda.resultados().isEmpty()) {
            return datosBusqueda.resultados().get(0);
        }
        return null;
    }

    private void buscarLibrosRegistrados() {
        List<Libro> libros = repositorioLibro.findAll();

        if (libros.isEmpty()){
            System.out.println("No se encontraron libros registrados. :(");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void buscarAutoresRegistrados() {
        List<Autor> autores = repositorioAutor.findAll();

        if (autores.isEmpty()){
            System.out.println("No existen autores registrados");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void buscarAutoresVivosPorAno() {
        System.out.println("Ingresa el año que deseas consultar: ");
        try{
            var ano = teclado.nextInt();
            teclado.nextLine();

            List<Autor> autoresVivos = repositorioAutor.findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoGreaterThanEqual(ano, ano);

            if (autoresVivos.isEmpty()){
                System.out.println("No se encontraron autores vivos en el año: " + ano);
            } else {
                autoresVivos.forEach(System.out::println);
            }
        } catch (Exception e){
            System.out.println("Año no valido");
            teclado.nextLine();
        }
    }

    private void buscarLibroPorIdioma() {
        // Lógica para el paso 5
    }
}