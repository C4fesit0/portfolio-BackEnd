package com.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @JoinColumn(name = "autor")
    Persona autor;

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
