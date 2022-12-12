package com.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String telefono;
    private String email;
    @Column(columnDefinition = "TEXT")
    private String sobre_mi;
    private String titulo;
    private String foto_perfil;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persona", cascade = CascadeType.ALL)
    List<Educacion> educacion;

    public Persona() {

    }

    public Persona(Long id, String nombre, String telefono, String sobre_mi, String titulo, String foto_perfil) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.sobre_mi = sobre_mi;
        this.titulo = titulo;
        this.foto_perfil = foto_perfil;
    }



}
