package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.NivelEstudio;
import com.backend.repository.NivelEstudioRepository;

@Service
public class NivelEstudioService implements INivelEstudioService{
    
    @Autowired
    NivelEstudioRepository nivelEstudioRepository;

    public List<NivelEstudio> getNiveles(){
        return nivelEstudioRepository.findAll();
    }

    @Override
    public NivelEstudio getNivelEstudio(Long id) {
        return nivelEstudioRepository.findById(id).orElse(null);
    }

    @Override
    public void addNivelEstudio(NivelEstudio estudio) {
        nivelEstudioRepository.save(estudio);
    }

    @Override
    public void deleteNivelEstudio(NivelEstudio estudio) {
        nivelEstudioRepository.delete(estudio);
    }

    @Override
    public void updateNivelEstudio(NivelEstudio estudio) {
        if(estudio.getId() == null) 
        {
            System.out.println("No se puede actualizar por falta de ID");
        }else {
            nivelEstudioRepository.save(estudio);
        }
    }

    

}
