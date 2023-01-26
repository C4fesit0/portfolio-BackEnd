package com.backend.model;

import com.backend.dto.EducacionDto;
import com.backend.dto.ExperienciaDto;
import com.backend.util.date.Fecha;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Entity
@Getter @Setter
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private Date fecha_inicio;
    private Date fecha_final;
    private boolean actualidad;
    private String institucion;
    private String imagen;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "id_nivel_estudio")
    NivelEstudio nivel;


    public Educacion() {

    }

    public Educacion(String titulo, Date fecha_inicio, Date fecha_final, boolean actualidad, String institucion, String imagen) {
        this.titulo = titulo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.actualidad = actualidad;
        this.institucion = institucion;
        this.imagen = imagen;
    }

    public Educacion setEducacionInfo(EducacionDto data){
        this.titulo = data.getTitulo();
        this.institucion = data.getInstitucion();
        Date fecha_inicio = Fecha.convertir(data.getFecha_inicio());
        this.fecha_inicio = fecha_inicio;
        this.actualidad = data.getActualidad();
        if(data.getActualidad()){
            this.fecha_final = null;
        }else if(!data.getActualidad()){
            Date fecha_final = Fecha.convertir(data.getFecha_final());
            this.fecha_final = fecha_final;
        }
        this.imagen = data.getImagen();
        return this;
    }

    
}
