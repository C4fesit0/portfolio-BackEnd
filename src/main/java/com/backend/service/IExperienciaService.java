package com.backend.service;

import com.backend.model.Experiencia;

import java.util.List;

public interface IExperienciaService {
    public List<Experiencia> listarExperiencia();
    public Experiencia getExperiencia(Long id);
    public void crearExpereriencia(Experiencia experiencia);
    public void actualizarExperiencia(Experiencia experiencia);
    public void eliminarExperiencia(Long id);

}
