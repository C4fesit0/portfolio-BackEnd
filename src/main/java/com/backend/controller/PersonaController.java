package com.backend.controller;


import com.backend.dto.PersonaDto;
import com.backend.model.Persona;
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
@RequestMapping("/persona")
@CrossOrigin(origins = "http://localhost:4200/")
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/")
    public ResponseEntity<Persona> getPersonaData(){
        return new ResponseEntity<>(personaService.getPersona(1L) , HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Persona>> getPersonas(){
        return new ResponseEntity<>(personaService.listPersonas(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public void crearPersona(@RequestBody Persona persona){
        System.out.println(persona.getEmail());
        personaService.createPersona(persona);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> patchPersona(@RequestBody PersonaDto persona){

        try {
            Persona response = personaService.getPersona(1L);
            response.setPerfil(persona);
            personaService.updatePersona(response);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            String error = "ERROR";
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/eliminar/{id}")
    public void deletePersona(@PathVariable Long id){
        personaService.deletePersona(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<Persona> upload(@RequestParam("file")MultipartFile archivo, @RequestParam("id")Long id){
        Persona response = personaService.getPersona(id);

        if (!archivo.isEmpty()){
            String nombre_archivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ","");
            Path ruta_archivo = Paths.get("images").resolve(nombre_archivo).toAbsolutePath();
            try {
                Files.copy(archivo.getInputStream(),ruta_archivo);
            } catch (IOException e) {
                System.err.println("ERROR al subir la imagen");
                return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setFoto_perfil(nombre_archivo);
            personaService.updatePersona(response);
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImagen(@PathVariable("id")Long id){
        Persona persona = personaService.getPersona(id);

        if(persona == null){
            return new ResponseEntity<>("Persona null" , HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            Path ruta = Paths.get("images").resolve(persona.getFoto_perfil()).toAbsolutePath();
            System.out.println(ruta.toString());
            byte[] data;
            try {
                data = Files.readAllBytes(new File(ruta.toString()).toPath());
            } catch (IOException e) {
                System.err.println("ERROR al buscar el archivo");
                System.err.println(persona.getFoto_perfil());
                throw new RuntimeException(e);
            }
            return new ResponseEntity<>(data,HttpStatus.OK);
        }


    }

}
