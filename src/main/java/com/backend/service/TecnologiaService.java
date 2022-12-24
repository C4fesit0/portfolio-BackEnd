package com.backend.service;

import com.backend.model.Tecnologia;
import com.backend.repository.TecnologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnologiaService implements ITecnologiaService{

    @Autowired
    TecnologiaRepository tecnologiaRepository;

    @Override
    public void createTecnologia(Tecnologia tecnologia) {
        tecnologiaRepository.save(tecnologia);
    }

    @Override
    public void deleteTecnologia(Long id) {
        tecnologiaRepository.deleteById(id);
    }

    @Override
    public List<Tecnologia> listarTecnologia() {
        return tecnologiaRepository.findAll();
    }

    @Override
    public void updateTecnologia(Tecnologia tecnologia) {
        if(tecnologia.getId() == null){
            System.err.println("No se puede actualizar tecnologia con ID: NULL");
        }else {
            tecnologiaRepository.save(tecnologia);
            System.out.println("Se actualizo tecnologia ID:"+tecnologia.getId());
        }
    }

}
