package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.NivelEstudio;
import com.backend.service.INivelEstudioService;

@RestController
@RequestMapping("/nivelEstudio")
public class NivelEstudioController {
    
    @Autowired
    INivelEstudioService nivelEstudioService;

    @PostMapping("/crear")
    public void crearNivelEstudio(@RequestBody NivelEstudio data){
        nivelEstudioService.addNivelEstudio(data);
    }

}
