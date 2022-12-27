package com.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter @Getter
public class Tecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String logo;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @ManyToMany(mappedBy = "tecnologias")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Persona> personas = new HashSet<>();

    @ManyToMany(mappedBy = "tecnologias")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Proyecto> proyectos = new HashSet<>();


    public Tecnologia(String nombre, String logo, Rol rol) {
        this.nombre = nombre;
        this.logo = logo;
        this.rol = rol;
    }
    public Tecnologia() {}
}
