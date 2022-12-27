package com.backend.model;

import com.backend.dto.PersonaDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Educacion> educacion =  new HashSet<>();

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Set<Proyecto> proyectos =  new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE })
    @JoinTable(
            name = "tecnologia_persona",
            joinColumns = @JoinColumn(name = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "tecnologia_id")
    )
    private Set<Tecnologia> tecnologias = new HashSet<>();

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

    public Persona setPerfil(PersonaDto data){
        this.nombre = data.getNombre();
        this.email = data.getEmail();
        this.titulo = data.getTitulo();
        this.telefono= data.getTelefono();
        this.sobre_mi = data.getSobre_mi();
        this.foto_perfil= data.getFoto_perfil();
        return this;
    }

    public Persona addEducacion(Educacion educacion){
        this.educacion.add(educacion);
        return this;
    }

    public Persona addTecnologia(Tecnologia tecnologia){
        this.tecnologias.add(tecnologia);
        return this;
    }

    public Persona addProyecto(Proyecto proyecto){
        this.proyectos.add(proyecto);
        return this;
    }

}
