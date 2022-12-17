package com.backend.service;

import com.backend.model.Rol;
import com.backend.repository.RolRepository;

import java.util.List;

public interface IRolService {
    public List<Rol> getRoles();
    public Rol getRol(Long id);
    public void createRol(Rol rol);
    public void updateRol(Rol rol);
    public void deleteRol(Long id);
}
