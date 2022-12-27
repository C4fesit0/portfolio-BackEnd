package com.backend.controller;


import com.backend.dto.PersonaDto;
import com.backend.model.Persona;
import com.backend.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "http://localhost:4200/")
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/")
    public ResponseEntity<Persona> getPersonaData(){
        return new ResponseEntity<>(personaService.getPersona(1L) , HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Persona>> getPersonas(){
        return new ResponseEntity<>(personaService.listPersonas(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public void crearPersona(@RequestBody Persona persona){
        System.out.println(persona.getEmail());
        personaService.createPersona(persona);
    }

    @PutMapping("/actualizar")
    public void patchPersona(@RequestBody PersonaDto persona){
        Persona persona1 = personaService.getPersona(1L);
        if(persona1 == null){
            System.err.println("No existe la persona");
        }else{
            persona1.setPerfil(persona);
            personaService.updatePersona(persona1);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public void deletePersona(@PathVariable Long id){
        personaService.deletePersona(id);
    }
}
