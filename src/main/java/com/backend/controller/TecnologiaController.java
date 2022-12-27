package com.backend.controller;

import com.backend.dto.TecnologiaDto;
import com.backend.model.Persona;
import com.backend.model.Rol;
import com.backend.model.Tecnologia;
import com.backend.service.IPersonaService;
import com.backend.service.IRolService;
import com.backend.service.ITecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tecnologia")
public class TecnologiaController {

    @Autowired
    IPersonaService personaService;

    @Autowired
    ITecnologiaService tecnologiaService;
    @Autowired
    IRolService rolService;

    @GetMapping("/roles/all")
    public ResponseEntity<List<Rol>> getRoles(){
        return new ResponseEntity<>(rolService.getRoles(), HttpStatus.OK);
    }

    @PostMapping("/roles/crear")
    public void crearRoles(){
        Rol frontend= new Rol("FrontEnd");
        rolService.createRol(frontend);
        Rol backend= new Rol("BackEnd");
        rolService.createRol(backend);
    }


    @PostMapping("/crear")
    public void createTecnologia(@RequestBody TecnologiaDto data){

        Persona persona =personaService.getPersona(1L);
        if(persona == null){
            System.out.println("No existe la persona");
        }else{
            Rol rol = rolService.getRol(data.getRol());
            Tecnologia tecnologia = new Tecnologia(data.getNombre(),data.getLogo(),rol);
            tecnologiaService.createTecnologia(tecnologia);
            Set<Tecnologia> tecnologias = persona.getTecnologias();
            tecnologias.add(tecnologia);
            persona.setTecnologias(tecnologias);
            personaService.updatePersona(persona);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarTecnologias(){

        List<Tecnologia> list = tecnologiaService.listarTecnologia();
        list.forEach(tecnologia -> {
            System.out.println(tecnologia.getNombre());
        });


        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarTecnologia(@PathVariable("id") Long id){
        Persona p = personaService.getPersona(1L);
        Tecnologia t = tecnologiaService.getTecnologia(id);
        p.getTecnologias().remove(t);
        personaService.updatePersona(p);
        tecnologiaService.deleteTecnologia(id);
    }

}
