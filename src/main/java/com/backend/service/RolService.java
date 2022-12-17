package com.backend.service;

import com.backend.model.Rol;
import com.backend.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RolService implements IRolService{

    @Autowired
    RolRepository rolRepository;

    @Override
    public List<Rol> getRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol getRol(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    public void createRol(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public void updateRol(Rol rol) {
        if(rol.getId()==null)
        {
            System.err.println("No se puede actualizar rol con id NULL");
        }else {
            rolRepository.save(rol);
        }
    }

    @Override
    public void deleteRol(Long id) {
        rolRepository.deleteById(id);
    }
}
