package com.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProyectoDto {

    private Long id_autor;
    private String nombre;
    private String descripcion;
    private String repositorio;
    private String demo;
    private String image;

}
