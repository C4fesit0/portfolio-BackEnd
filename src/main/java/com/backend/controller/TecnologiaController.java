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

    @PostMapping("/crear")
    public void createTecnologia(@RequestBody TecnologiaDto data){

        Persona persona =personaService.getPersona(data.getId_persona());
        if(persona == null){
            System.out.println("No existe la persona");
        }else{
            Rol rol = rolService.getRol(data.getRol());
            Tecnologia tecnologia = new Tecnologia(data.getNombre(),data.getLogo(),rol);
            tecnologiaService.createTecnologia(tecnologia);
            List<Tecnologia> tecnologias = persona.getTecnologias();
            tecnologias.add(tecnologia);
            persona.setTecnologias(tecnologias);
            personaService.updatePersona(persona);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Tecnologia>> listarTecnologias(){

        List<Tecnologia> data = tecnologiaService.listarTecnologia();

        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarTecnologia(@PathVariable("id") Long id){
        tecnologiaService.deleteTecnologia(id);
    }

}
