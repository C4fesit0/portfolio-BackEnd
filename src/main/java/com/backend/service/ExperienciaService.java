package com.backend.service;

import com.backend.model.Experiencia;
import com.backend.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienciaService implements IExperienciaService{

    @Autowired
    ExperienciaRepository experienciaRepository;

    @Override
    public List<Experiencia> listarExperiencia() {
        return experienciaRepository.findAll();
    }

    @Override
    public Experiencia getExperiencia(Long id) {
        return experienciaRepository.findById(id).orElse(null);
    }

    @Override
    public void crearExpereriencia(Experiencia experiencia) {
        experienciaRepository.save(experiencia);
    }

    @Override
    public void actualizarExperiencia(Experiencia experiencia) {
        if(experiencia.getId()==null){
            System.out.println("No se puede actualizar experiencia por ID NULL");
        }else{
            experienciaRepository.save(experiencia);
        }
    }

    @Override
    public void eliminarExperiencia(Long id) {
        experienciaRepository.deleteById(id);
    }
}
