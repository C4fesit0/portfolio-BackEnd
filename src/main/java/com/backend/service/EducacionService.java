package com.backend.service;

import com.backend.model.Educacion;
import com.backend.repository.EducacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EducacionService implements IEducacionService {

    @Autowired
    EducacionRepository educacionRepository;

    @Override
    public List<Educacion> listEducacion() {
        return educacionRepository.findAll();
    }

    @Override
    public Educacion getEducacion(Long id) {
        return educacionRepository.findById(id).orElse(null);
    }

    @Override
    public void createEducacion(Educacion educacion) {
        educacionRepository.save(educacion);
    }

    @Override
    public void updateEducacion(Educacion educacion) {
        educacionRepository.save(educacion);
    }

    @Override
    public void deleteEducacion(Educacion educacion) {
        educacionRepository.delete(educacion);
    }
}
