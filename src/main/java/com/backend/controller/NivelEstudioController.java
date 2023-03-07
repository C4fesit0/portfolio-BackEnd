package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.model.NivelEstudio;
import com.backend.service.INivelEstudioService;

import java.util.List;

@RestController
@RequestMapping("/nivelEstudio")
@CrossOrigin(origins ="https://port-front.firebaseapp.com/")
public class NivelEstudioController {
    
    @Autowired
    INivelEstudioService nivelEstudioService;

    @PostMapping("/crear")
    public void crearNivelEstudio(@RequestBody NivelEstudio data){
        nivelEstudioService.addNivelEstudio(data);
    }

    @GetMapping("/listar")
     public ResponseEntity<?> getNiveles(){
        return new ResponseEntity<>(nivelEstudioService.getNiveles(), HttpStatus.OK);
    }

}
