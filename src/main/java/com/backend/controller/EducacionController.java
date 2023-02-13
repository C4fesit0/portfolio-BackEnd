package com.backend.controller;

import com.backend.dto.EducacionDto;
import com.backend.model.Educacion;
import com.backend.model.Experiencia;
import com.backend.model.NivelEstudio;
import com.backend.model.Persona;
import com.backend.service.IEducacionService;
import com.backend.service.INivelEstudioService;
import com.backend.service.IPersonaService;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

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

        System.out.println(data.getActualidad());
        System.out.println(data.getFecha_final());

        Long id_persona = data.getId_persona();
        Persona persona = personaService.getPersona(id_persona);

        NivelEstudio nivelEstudio = nivelEstudioService.getNivelEstudio(data.getId_nivel_estudio());
        
        Educacion educacion = new Educacion();

        educacion = educacion.setEducacionInfo(data);

        educacion.setNivel(nivelEstudio);
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

    @PutMapping("actualizar/{id}")
    public ResponseEntity<Educacion> actualizarEducacion(@PathVariable("id")Long id,@RequestBody EducacionDto data){
        Educacion educacion = educacionService.getEducacion(id);
        educacion = educacion.setEducacionInfo(data);
        NivelEstudio nivelEstudio = nivelEstudioService.getNivelEstudio(data.getId_nivel_estudio());
        educacion.setNivel(nivelEstudio);
        educacionService.updateEducacion(educacion);
        return new ResponseEntity<>(educacion,HttpStatus.OK);
    }


    @DeleteMapping("/eliminar/{id}")
    public void eliminarEducacion(@PathVariable("id")Long id){
        Educacion educacion = educacionService.getEducacion(id);
        educacionService.deleteEducacion(educacion);
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<Educacion> upload(@RequestParam("file") MultipartFile archivo, @PathVariable("id")Long id){
        Educacion response = educacionService.getEducacion(id);

        if (!archivo.isEmpty()){
            String nombre_archivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ","");
            Path rutaArchivo = Paths.get("images").resolve(nombre_archivo).toAbsolutePath();
            try {
                Files.copy(archivo.getInputStream(),rutaArchivo);
            } catch (IOException e) {
                System.err.println("ERROR al subir la imagen");
                return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setImagen(nombre_archivo);
            educacionService.updateEducacion(response);
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImage(@PathVariable("id")Long id){
        Educacion educacion = educacionService.getEducacion(id);

        if(educacion == null){
            return new ResponseEntity<>("Experiencia null" , HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            Path ruta = Paths.get("images").resolve(educacion.getImagen()).toAbsolutePath();
            System.out.println(ruta.toString());
            byte[] data;
            try {
                data = Files.readAllBytes(new File(ruta.toString()).toPath());
            } catch (IOException e) {
                System.err.println("ERROR al buscar la imagen de experiencia ID:"+educacion.getId());
                System.err.println(educacion.getImagen());
                throw new RuntimeException(e);
            }
            return new ResponseEntity<>(data,HttpStatus.OK);
        }


    }

}
