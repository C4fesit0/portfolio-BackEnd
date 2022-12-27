package com.backend.dto;

import com.backend.model.Tecnologia;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter @Setter
public class ProyectoDto {

    private Long id_autor;
    private String nombre;
    private String descripcion;
    private String repositorio;
    private String demo;
    private String image;
    private List<Long> tecnologias;

}
