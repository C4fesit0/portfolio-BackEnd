package com.backend.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NivelEstudio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String nombre;
}
