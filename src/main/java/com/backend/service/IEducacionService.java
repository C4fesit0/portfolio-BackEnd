package com.backend.service;
import com.backend.model.Educacion;


import java.util.List;
import java.util.Optional;

public interface IEducacionService{

    public List<Educacion> listEducacion();
    public Optional<Educacion> getEducacion(Long id);
    public void createEducacion(Educacion educacion);
    public void updateEducacion(Educacion educacion);
    public void deleteEducacion(Educacion educacion);
}
