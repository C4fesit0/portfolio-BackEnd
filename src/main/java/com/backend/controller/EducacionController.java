package com.backend.controller;

import com.backend.dto.EducacionDto;
import com.backend.model.Educacion;
import com.backend.model.NivelEstudio;
import com.backend.model.Persona;
import com.backend.service.IEducacionService;
import com.backend.service.INivelEstudioService;
import com.backend.service.IPersonaService;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "http://localhost:4200/")
public class EducacionController {

    @Autowired
    IEducacionService educacionService;

    @Autowired
    IPersonaService personaService;

    @Autowired
    INivelEstudioService nivelEstudioService;

    @GetMapping("/")
    public ResponseEntity<List<Educacion>> listarEducacion(){
        List<Educacion> list = educacionService.listEducacion();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Educacion> crearEducacion(@RequestBody EducacionDto data){
        Long id_persona = data.getId_persona();
        Persona persona = personaService.getPersona(id_persona);

        NivelEstudio ne = nivelEstudioService.getNivelEstudio(data.getId_nivel_estudio());

        String fecha1 = data.getFecha_final();
        Date fechaFinal= Date.valueOf(fecha1);

        String fecha2 = data.getFecha_inicio();
        Date fechaInicio= Date.valueOf(fecha2);
        
        Educacion educacion = new Educacion(data.getTitulo(),fechaFinal,fechaInicio,
                                data.getActualidad(),data.getInstitucion(),data.getImagen());

        educacion.setNivel(ne);
        educacion.setPersona(persona);
        educacionService.createEducacion(educacion);
        persona.addEducacion(educacion);
        personaService.updatePersona(persona);

        persona.getEducacion().forEach(x -> {
            System.out.println(x.getTitulo());
         });

         System.out.println(persona.getId());

         return new ResponseEntity<>(educacion,HttpStatus.OK);
    }

    @PutMapping("actuailizar/{id}")
    public void actualizarEducacion(@PathVariable("id")Long id,@RequestBody EducacionDto data){
        Educacion educacion = educacionService.getEducacion(id);
        educacion.setTitulo(data.getTitulo());
        educacion.setActualidad(data.getActualidad());
        educacion.setImagen(data.getImagen());

        String fecha1 = data.getFecha_final();
        Date fechaFinal= Date.valueOf(fecha1);

        String fecha2 = data.getFecha_inicio();
        Date fechaInicio= Date.valueOf(fecha2);

        educacion.setFecha_final(fechaFinal);
        educacion.setFecha_inicio(fechaInicio);
        educacion.setInstitucion(data.getInstitucion());
        educacion.setImagen(data.getImagen());
        educacionService.updateEducacion(educacion);
    }


    @DeleteMapping("/eliminar/{id}")
    public void eliminarEducacion(@PathVariable("id")Long id){
        Educacion educacion = educacionService.getEducacion(id);
        educacionService.deleteEducacion(educacion);
    }

}
