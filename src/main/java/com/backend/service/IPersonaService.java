package com.backend.service;

import com.backend.model.Persona;

import java.util.List;

public interface IPersonaService {

    public List<Persona> listPersonas();
    public Persona getPersona(Long id);
    public void createPersona(Persona persona);
    public void updatePersona(Persona persona);
    public void deletePersona(Long id);

}
