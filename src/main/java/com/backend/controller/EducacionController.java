package com.backend.controller;

import com.backend.dto.EducacionDto;
import com.backend.model.Educacion;
import com.backend.model.Persona;
import com.backend.service.IEducacionService;
import com.backend.service.IPersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/educacion")
public class EducacionController {

    @Autowired
    IEducacionService educacionService;

    @Autowired
    IPersonaService personaService;

    @GetMapping("/")
    public List<Educacion> listarEducacion(){
        return educacionService.listEducacion();
    }

    @PostMapping("/crear")
    public void crearEducacion(@RequestBody EducacionDto data){
        Long id_persona = data.getId_persona();
        Persona persona = personaService.getPersona(id_persona);


        System.out.println(data.getId_persona());
        System.out.println(data.getTitulo());
        System.out.println(data.getInstitucion());
        System.out.println(data.getFecha_final());
        System.out.println(data.getFecha_inicio());

        

        String fecha1 = data.getFecha_final();
        Date fechaFinal= Date.valueOf(fecha1);

        String fecha2 = data.getFecha_inicio();
        Date fechaInicio= Date.valueOf(fecha2);
        
        Educacion educacion = new Educacion(0L,data.getTitulo(),fechaFinal,fechaInicio,
                                data.getActualidad(),data.getInstitucion(),data.getImagen());

        educacion.setPersona(persona);
        
        
        List<Educacion> educacionLista = persona.getEducacion();
        educacionLista.add(educacion);
        
        persona.setEducacion(educacionLista);
        
        persona.getEducacion().forEach(x -> {
            System.out.println(x.getTitulo());
         });

         System.out.println(persona.getId());

        personaService.updatePersona(persona);
    }
}
