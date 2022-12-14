package com.backend.service;

import java.util.List;

import com.backend.model.NivelEstudio;

public interface INivelEstudioService {
    
    public List<NivelEstudio> getNiveles();
    public NivelEstudio getNivelEstudio(Long id);
    public void addNivelEstudio(NivelEstudio estudio);
    public void deleteNivelEstudio(NivelEstudio estudio);
    public void updateNivelEstudio(NivelEstudio estudio);
}
