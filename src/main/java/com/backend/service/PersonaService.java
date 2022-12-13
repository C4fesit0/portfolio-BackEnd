package com.backend.service;

import com.backend.model.Persona;
import com.backend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService implements IPersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> listPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public void createPersona(Persona persona) {
        personaRepository.save(persona);
    }


    @Override
    public Persona getPersona(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    @Override
    public void updatePersona(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }

}
