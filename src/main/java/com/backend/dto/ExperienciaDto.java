package com.backend.dto;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
public class ExperienciaDto {
    private Long id_persona;
    private String puesto;
    private String empresa;
    private String fecha_inicio;
    private String fecha_final;
    private Boolean actualidad;
    private String descripcion;
    private String imagen;
}
