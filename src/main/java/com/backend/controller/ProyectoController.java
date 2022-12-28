package com.backend.controller;


import com.backend.dto.ProyectoDto;
import com.backend.model.Persona;
import com.backend.model.Proyecto;
import com.backend.model.Tecnologia;
import com.backend.repository.ProyectoRepository;
import com.backend.repository.TecnologiaRepository;
import com.backend.service.IPersonaService;
import com.backend.service.IProyectoService;
import com.backend.service.ITecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {

    @Autowired
    IProyectoService proyectoService;

    @Autowired
    IPersonaService personaService;
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    @Autowired
    private ITecnologiaService tecnologiaService;

    @PostMapping("/crear")
    public void crearProyecto(@RequestBody ProyectoDto proyectoData){
        Persona autor = personaService.getPersona(1L);
        Set<Proyecto> proyectoList =  autor.getProyectos();

        Proyecto proyecto = new Proyecto(proyectoData.getNombre(), proyectoData.getDescripcion(),
                proyectoData.getRepositorio(), proyectoData.getDemo(), proyectoData.getImage());
        proyecto.setAutor(autor);
        List<Tecnologia> t = tecnologiaRepository.findAllById(proyectoData.getTecnologias());
        proyecto.getTecnologias().addAll(t);
        proyectoService.createProyecto(proyecto);
        System.out.println("Proyecto agregado - Persona ID: "+autor.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Proyecto>> listarProyectos(){
        return new ResponseEntity<>(proyectoService.getProyectos(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public void actualizarProyecto(@PathVariable("id") Long id, @RequestBody ProyectoDto body){
        System.out.println("ID: "+id+" NOMBRE: "+body.getNombre());
        if(id != null){
            Proyecto proyecto  = proyectoService.getProyecto(id);
            if(proyecto == null){
                System.out.println("No existe el proyecto");
            }else {
                proyecto.setDemo(body.getDemo());
                proyecto.setDescripcion(body.getDescripcion());
                proyecto.setImage(body.getImage());
                proyecto.setNombre(body.getNombre());
                proyecto.setRepositorio(body.getRepositorio());
                List<Tecnologia> list = tecnologiaRepository.findAllById(body.getTecnologias());
                for ( Tecnologia t: list){
                    System.out.println(t.getNombre());
                }
                Set<Tecnologia> tecnologias = new HashSet<>();
                tecnologias.addAll(list);
                proyecto.setTecnologias(tecnologias);
                proyectoService.updateProyecto(proyecto);
            }
        }else {
            System.err.println("ID de proyecto NULL");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarProyecto(@PathVariable ("id") Long id){
        proyectoService.deleteProyecto(id);
    }

}
