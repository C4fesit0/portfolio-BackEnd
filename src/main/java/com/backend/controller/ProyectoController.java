package com.backend.controller;


import com.backend.dto.ProyectoDto;
import com.backend.model.Persona;
import com.backend.model.Proyecto;
import com.backend.model.Tecnologia;
import com.backend.repository.ProyectoRepository;
import com.backend.repository.TecnologiaRepository;
import com.backend.service.IPersonaService;
import com.backend.service.IProyectoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/crear")
    public void crearProyecto(@RequestBody ProyectoDto proyectoData){
        Persona autor = personaService.getPersona(proyectoData.getId_autor());
        System.out.println(autor.getId());
        List<Proyecto> proyectoList =  autor.getProyectos();

        Proyecto proyecto = new Proyecto(proyectoData.getNombre(), proyectoData.getDescripcion(),
                proyectoData.getRepositorio(), proyectoData.getDemo(), proyectoData.getImage());
        proyecto.setAutor(autor);
        List<Tecnologia> tecnologias = tecnologiaRepository.findAllById(proyectoData.getTecnologias());
        proyecto.setTecnologias(tecnologias);
        proyectoList.add(proyecto);
        autor.setProyectos(proyectoList);
        personaService.updatePersona(autor);
        System.out.println("Proyecto agregado - Persona ID: "+autor.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Proyecto>> listarProyectos(){
        return new ResponseEntity<>(proyectoService.getProyectos(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public void actualizarProyecto(@PathVariable("id") Long id, @RequestBody ProyectoDto body){
        System.out.println(id);
        System.out.println(body.getNombre());
        if(id != null){
            Proyecto proyecto  = proyectoService.getProyecto(id);
            if(proyecto ==null){
                System.out.println("No existe el proyecto");
            }else {
                proyecto.setDemo(body.getDemo());
                proyecto.setDescripcion(body.getDescripcion());
                proyecto.setImage(body.getImage());
                proyecto.setNombre(body.getNombre());
                proyecto.setRepositorio(body.getRepositorio());
                List<Tecnologia> tecnologias = tecnologiaRepository.findAllById(body.getTecnologias());
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
