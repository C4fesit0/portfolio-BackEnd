package com.backend.service;

import com.backend.model.Proyecto;

import java.util.List;

public interface IProyectoService {

    public List<Proyecto> getProyectos();
    public Proyecto getProyecto(Long id);

    void createProyecto(Proyecto proyecto);

    public void updateProyecto(Proyecto proyecto);
    public void deleteProyecto(Long id);
}
