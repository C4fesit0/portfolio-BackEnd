package com.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToOne(fetch = FetchType.LAZY)
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
}
