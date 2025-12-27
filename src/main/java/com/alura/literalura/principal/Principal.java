package com.alura.literalura.principal;

import ch.qos.logback.classic.pattern.CallerDataConverter;

import java.lang.classfile.instruction.SwitchCase;
import java.util.Scanner;

public class Principal {
    private static final String = "https://gutendex.com/books/";
    private APIConsumer consumer = new APIConsumer();
    private DataConverter converter = new DataConverter();
    private Scanner teclado = new Scanner(System.in);

    public void muestraMenu(){
        var opcion = -1;
        while (opcion != 0){
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
}
