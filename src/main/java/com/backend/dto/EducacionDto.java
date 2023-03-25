package com.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EducacionDto {
    Long id_persona;
    String titulo;
    String fecha_inicio;
    String fecha_final;
    Boolean actualidad;
    String institucion;
    String imagen;
    Long id_nivel_estudio;


    public EducacionDto(Long id_persona, String titulo, String fecha_inicio, String fecha_final, Boolean actualidad, String institucion, String imagen,Long id_nivel_estudio) {
        this.id_persona = id_persona;
        this.titulo = titulo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.actualidad = actualidad;
        this.institucion = institucion;
        this.imagen = imagen;
        this.id_nivel_estudio = id_nivel_estudio;
    }


}
