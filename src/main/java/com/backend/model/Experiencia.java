package com.backend.model;

import com.backend.dto.ExperienciaDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Setter @Getter
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String puesto;
    private String empresa;
    private Date fecha_inicio;
    private Date fecha_final;
    private boolean actualidad;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private String imagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "persona_id")
    private Persona persona;

    public Experiencia(String puesto, String empresa, Date fecha_inicio, Date fecha_final, boolean actualidad, String descripcion, String imagen) {
        this.puesto = puesto;
        this.empresa = empresa;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.actualidad = actualidad;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Experiencia(){

    }

    public Experiencia setExperienciaInfo(ExperienciaDto data){
        this.puesto = data.getPuesto();
        this.empresa = data.getEmpresa();
        this.actualidad = data.getActualidad();
        String fecha1 = data.getFecha_final();
        Date fechaFinal= Date.valueOf(fecha1);
        String fecha2 = data.getFecha_inicio();
        Date fechaInicio= Date.valueOf(fecha2);
        this.fecha_final = fechaFinal;
        this.fecha_inicio = fechaInicio;
        this.descripcion = data.getDescripcion();
        this.imagen = data.getImagen();

        return this;
    }

}
