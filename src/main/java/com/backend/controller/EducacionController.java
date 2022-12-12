package com.backend.controller;

import com.backend.model.Educacion;
import com.backend.service.IEducacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/educacion")
public class EducacionController {

    @Autowired
    IEducacionService educacionService;

    @GetMapping("/")
    public List<Educacion> listarEducacion(){
        return educacionService.listEducacion();
    }

    @PostMapping("/crear")
    public void crearEducacion(@RequestBody Educacion educacion, @RequestBody Long id){
        System.out.println(educacion.getTitulo());
        System.out.println(id);
    }
}
