package com.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private String repositorio;
    private String demo;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "autor")
    Persona autor;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tecnologia_proyecto",
            joinColumns = @JoinColumn(name = "proyecto_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tecnologia_id", referencedColumnName = "id")
    )
    private List<Tecnologia> tecnologias;

    public Proyecto() {

    }

    public Proyecto(String nombre, String descripcion, String repositorio, String demo, String image) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.repositorio = repositorio;
        this.demo = demo;
        this.image = image;
    }
}
