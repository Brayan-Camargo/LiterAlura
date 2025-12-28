package com.alura.literalura.model;

public enum Idioma {
    ESPAÑOL("es"),
    INGLES("en"),
    FRANCES("fr"),
    PORTUGUES("pt");

    private String codigo;

    Idioma(String codigo){
        this.codigo = codigo;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.codigo.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun Idioma encontrado para: " + text);
    }
}
