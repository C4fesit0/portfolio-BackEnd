package com.backend.service;

import com.backend.model.Tecnologia;

import java.util.List;
import java.util.Set;

public interface ITecnologiaService {

    void createTecnologia(Tecnologia tecnologia);
    void deleteTecnologia(Long id);
    List<Tecnologia> listarTecnologia();
    void updateTecnologia(Tecnologia tecnologia);
    Tecnologia getTecnologia(Long id);

}
