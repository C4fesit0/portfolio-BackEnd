package com.backend.controller;


import com.backend.model.Persona;
import com.backend.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<Persona> getPersonaData(){
        return new ResponseEntity<>(personaService.getPersona(1L) , HttpStatus.OK);
    }

    @PostMapping("/crear")
    public void postPersona(@RequestBody Persona persona){
        System.out.println(persona.getTelefono());
        personaService.createPersona(persona);
    }

    @PutMapping("/actualizar")
    public void patchPersona(@RequestBody Persona persona){
        personaService.updatePersona(persona);
    }

    @DeleteMapping("/{id}/eliminar")
    public void deletePersona(@RequestParam Long id){
        personaService.deletePersona(id);
    }
}
