package com.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(
            name = "tecnologia_persona",
            joinColumns = @JoinColumn(name = "tecnologia_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "persona_id", referencedColumnName = "id")
    )
    private List<Persona> personas;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(
            name = "tecnologia_proyecto",
            joinColumns = @JoinColumn(name = "tecnologia_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    )
    private List<Proyecto> proyectos;

    public Tecnologia(String nombre, String logo, Rol rol) {
        this.nombre = nombre;
        this.logo = logo;
        this.rol = rol;
    }
    public Tecnologia() {}
}
