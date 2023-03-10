package com.backend.controller;


import com.backend.dto.ExperienciaDto;
import com.backend.model.Experiencia;
import com.backend.model.Persona;
import com.backend.service.IExperienciaService;
import com.backend.service.IPersonaService;
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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/experiencia")
//@CrossOrigin(origins = "http://localhost:4200/")
@CrossOrigin(origins = "https://port-front.firebaseapp.com/")
public class ExperienciaController {

    @Autowired
    IExperienciaService experienciaService;
    @Autowired
    IPersonaService personaService;

    @GetMapping("/")
    public ResponseEntity<List<Experiencia>> getExperiencias(){
        List<Experiencia> list = experienciaService.listarExperiencia();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Experiencia> crearExperiencia(@RequestBody ExperienciaDto data){
        System.out.println(data.getPuesto());
        System.out.println(data.getFecha_final());
        System.out.println(data.getActualidad());
        System.out.println(data.getFecha_inicio());
        Experiencia e = new Experiencia();
        e.setExperienciaInfo(data);
        Persona p = personaService.getPersona(data.getId_persona());
        e.setPersona(p);
        p.addExperiencia(e);
        experienciaService.crearExpereriencia(e);
        System.out.println("Experiencia Creada");
        return new ResponseEntity<>(e,HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Experiencia> actualizarExperiencia(@PathVariable("id") Long id, @RequestBody ExperienciaDto data){
        Experiencia response = experienciaService.getExperiencia(id);
        if(response != null){
            try {
                response.setExperienciaInfo(data);
                experienciaService.actualizarExperiencia(response);
            }catch (Exception e){
                System.err.println(e);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            System.err.println("Problema al obtener la experiencia");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarExperiencia(@PathVariable("id")Long id){
        experienciaService.eliminarExperiencia(id);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<Experiencia> upload(@RequestParam("file")MultipartFile archivo, @PathVariable("id")Long id){
        Experiencia response = experienciaService.getExperiencia(id);

        if (!archivo.isEmpty()){
            String nombre_archivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ","");
            Path ruta_archivo = Paths.get("images").resolve(nombre_archivo).toAbsolutePath();
            try {
                Files.copy(archivo.getInputStream(),ruta_archivo);
            } catch (IOException e) {
                System.err.println("ERROR al subir la imagen");
                return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setImagen(nombre_archivo);
            experienciaService.actualizarExperiencia(response);
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImage(@PathVariable("id")Long id){
        Experiencia experiencia = experienciaService.getExperiencia(id);

        if(experiencia == null){
            return new ResponseEntity<>("Experiencia null" , HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            Path ruta = Paths.get("images").resolve(experiencia.getImagen()).toAbsolutePath();
            System.out.println(ruta.toString());
            byte[] data;
            try {
                data = Files.readAllBytes(new File(ruta.toString()).toPath());
            } catch (IOException e) {
                System.err.println("ERROR al buscar la imagen de experiencia ID:"+experiencia.getId());
                System.err.println(experiencia.getImagen());
                throw new RuntimeException(e);
            }
            return new ResponseEntity<>(data,HttpStatus.OK);
        }


    }
}
