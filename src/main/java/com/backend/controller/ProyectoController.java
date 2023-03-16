package com.backend.controller;


import com.backend.dto.ProyectoDto;
import com.backend.model.Persona;
import com.backend.model.Proyecto;
import com.backend.model.Tecnologia;
import com.backend.repository.ProyectoRepository;
import com.backend.repository.TecnologiaRepository;
import com.backend.service.IPersonaService;
import com.backend.service.IProyectoService;
import com.backend.service.ITecnologiaService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/proyecto")//@CrossOrigin(origins = "http://localhost:4200/")
@CrossOrigin(origins = "https://port-front.firebaseapp.com/")
public class ProyectoController {

    @Autowired
    IProyectoService proyectoService;

    @Autowired
    IPersonaService personaService;
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    @Autowired
    private ITecnologiaService tecnologiaService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearProyecto(@RequestBody ProyectoDto proyectoData){
        Persona autor = personaService.getPersona(1L);
        Set<Proyecto> proyectoList =  autor.getProyectos();

        Proyecto proyecto = new Proyecto(proyectoData.getNombre(), proyectoData.getDescripcion(),
                proyectoData.getRepositorio(), proyectoData.getDemo(), proyectoData.getImage());
        proyecto.setAutor(autor);
        List<Tecnologia> t = tecnologiaRepository.findAllById(proyectoData.getTecnologias());
        proyecto.getTecnologias().addAll(t);
        proyectoService.createProyecto(proyecto);
        System.out.println("Proyecto agregado - Persona ID: "+autor.getId());
        return new ResponseEntity<>(proyecto,HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Proyecto>> listarProyectos(){
        return new ResponseEntity<>(proyectoService.getProyectos(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public void actualizarProyecto(@PathVariable("id") Long id, @RequestBody ProyectoDto body){
        System.out.println("ID: "+id+" NOMBRE: "+body.getNombre());
        if(id != null){
            Proyecto proyecto  = proyectoService.getProyecto(id);
            if(proyecto == null){
                System.out.println("No existe el proyecto");
            }else {
                proyecto.setDemo(body.getDemo());
                proyecto.setDescripcion(body.getDescripcion());
                proyecto.setImage(body.getImage());
                proyecto.setNombre(body.getNombre());
                proyecto.setRepositorio(body.getRepositorio());
                List<Tecnologia> list = tecnologiaRepository.findAllById(body.getTecnologias());
                for ( Tecnologia t: list){
                    System.out.println(t.getNombre());
                }
                Set<Tecnologia> tecnologias = new HashSet<>();
                tecnologias.addAll(list);
                proyecto.setTecnologias(tecnologias);
                proyectoService.updateProyecto(proyecto);
            }
        }else {
            System.err.println("ID de proyecto NULL");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarProyecto(@PathVariable ("id") Long id){
        proyectoService.deleteProyecto(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<Proyecto> upload(@RequestParam("file") MultipartFile archivo, @RequestParam("id")Long id){
        Proyecto response = proyectoService.getProyecto(id);

        if (!archivo.isEmpty()){
            String nombre_archivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ","");
            Path ruta_archivo = Paths.get("images").resolve(nombre_archivo).toAbsolutePath();
            try {
                Files.copy(archivo.getInputStream(),ruta_archivo);
            } catch (IOException e) {
                System.err.println("ERROR al subir la imagen");
                return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setImage(nombre_archivo);
            proyectoService.updateProyecto(response);
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImagen(@PathVariable("id")Long id){
        Proyecto proyecto = proyectoService.getProyecto(id);

        if(proyecto == null){
            return new ResponseEntity<>("Persona null" , HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            Path ruta = Paths.get("images").resolve(proyecto.getImage()).toAbsolutePath();
            System.out.println(ruta.toString());
            byte[] data;
            try {
                data = Files.readAllBytes(new File(ruta.toString()).toPath());
            } catch (IOException e) {
                System.err.println("ERROR al buscar el archivo");
                System.err.println(proyecto.getImage());
                throw new RuntimeException(e);
            }
            return new ResponseEntity<>(data,HttpStatus.OK);
        }


    }
}
