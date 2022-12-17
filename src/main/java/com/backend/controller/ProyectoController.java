package com.backend.controller;


import com.backend.dto.ProyectoDto;
import com.backend.model.Persona;
import com.backend.model.Proyecto;
import com.backend.service.IPersonaService;
import com.backend.service.IProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {

    @Autowired
    IProyectoService proyectoService;

    @Autowired
    IPersonaService personaService;

    @PostMapping("/crear")
    public void crearProyecto(@RequestBody ProyectoDto proyectoData){
        Persona autor = personaService.getPersona(proyectoData.getId_autor());
        System.out.println(autor.getId());
        List<Proyecto> proyectoList =  autor.getProyectos();

        Proyecto proyecto = new Proyecto(proyectoData.getNombre(), proyectoData.getDescripcion(),
                proyectoData.getRepositorio(), proyectoData.getDemo(), proyectoData.getImage());
        proyecto.setAutor(autor);
        proyectoList.add(proyecto);
        autor.setProyectos(proyectoList);
        personaService.updatePersona(autor);
        System.out.println("Proyecto agregado - Persona ID: "+autor.getId());
    }

}
