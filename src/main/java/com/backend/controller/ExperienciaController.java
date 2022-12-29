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
    public void crearExperiencia(@RequestBody ExperienciaDto data){
        System.out.println(data.getPuesto());
        /*
        String fecha1 = data.getFecha_final();
        Date fechaFinal= Date.valueOf(fecha1);

        String fecha2 = data.getFecha_inicio();
        Date fechaInicio= Date.valueOf(fecha2);

        Experiencia e = new Experiencia(data.getPuesto(),data.getEmpresa(),
                fechaInicio,fechaFinal,data.getActualidad(), data.getDescripcion(),
                data.getImagen());*/
        Experiencia e = new Experiencia();
        e.setExperienciaInfo(data);
        Persona p = personaService.getPersona(data.getId_persona());
        e.setPersona(p);
        p.addExperiencia(e);
        experienciaService.crearExpereriencia(e);
        System.out.println("Experiencia Creada");
    }

    @PutMapping("/actualizar/{id}")
    public void actualizarExperiencia(@PathVariable("id") Long id, @RequestBody ExperienciaDto data){
        Experiencia e = experienciaService.getExperiencia(id);
        e.setExperienciaInfo(data);
        experienciaService.actualizarExperiencia(e);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarExperiencia(@PathVariable("id")Long id){
        experienciaService.eliminarExperiencia(id);
    }
}
