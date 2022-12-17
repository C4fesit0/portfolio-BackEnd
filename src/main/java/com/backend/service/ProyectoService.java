package com.backend.service;

import com.backend.model.Proyecto;
import com.backend.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoService implements IProyectoService {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Override
    public List<Proyecto> getProyectos() {
        return proyectoRepository.findAll();
    }

    @Override
    public Proyecto getProyecto(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    @Override
    public void createProyecto(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    @Override
    public void updateProyecto(Proyecto proyecto) {
        if (proyecto.getId() == null) {
            System.err.println("No se puede actualizar sin id de proyecto");
        } else{
            proyectoRepository.save(proyecto);
        }
    }

    @Override
    public void deleteProyecto(Long id) {
        proyectoRepository.deleteById(id);
    }
}
