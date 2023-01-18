package com.backend.controller;


import com.backend.dto.ExperienciaDto;
import com.backend.model.Experiencia;
import com.backend.model.Persona;
import com.backend.repository.ExperienciaRepository;
import com.backend.service.ExperienciaService;
import com.backend.service.IExperienciaService;
import com.backend.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/experiencia")
@CrossOrigin(origins = "http://localhost:4200/")
public class ExperienciaController {

    @Autowired
    IExperienciaService experienciaService;
    @Autowired
    IPersonaService personaService;

    @GetMapping("/")
    public ResponseEntity<List<Experiencia>> getExperiencias(){
        List<Experiencia> list = experienciaService.listarExperiencia();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Experiencia> crearExperiencia(@RequestBody ExperienciaDto data){
        System.out.println(data.getPuesto());
        System.out.println(data.getFecha_final());
        System.out.println(data.getActualidad());
        System.out.println(data.getFecha_inicio());
        Experiencia e = new Experiencia();
        e.setExperienciaInfo(data);
        Persona p = personaService.getPersona(data.getId_persona());
        e.setPersona(p);
        p.addExperiencia(e);
        experienciaService.crearExpereriencia(e);
        System.out.println("Experiencia Creada");
        return new ResponseEntity<>(e,HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public void actualizarExperiencia(@PathVariable("id") Long id, @RequestBody ExperienciaDto data){
        Experiencia e = experienciaService.getExperiencia(id);
        e.setExperienciaInfo(data);
        experienciaService.actualizarExperiencia(e);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarExperiencia(@PathVariable("id")Long id){
        experienciaService.eliminarExperiencia(id);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @PostMapping("/upload/{id}")
    public void subirLogo(@PathVariable("id")Long id, @RequestParam("file") MultipartFile logo){

    }
}
