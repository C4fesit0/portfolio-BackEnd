package com.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class NivelEstudio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    @OneToOne(mappedBy = "nivel", cascade = CascadeType.ALL)
    private Educacion educacion;


    public NivelEstudio() {
    }


    public NivelEstudio(String nombre) {
        this.nombre = nombre;
    }


}
