package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDTO(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechadeNacimiento,
        @JsonAlias("death_year") Integer fechadeFallecimiento
) {
}
