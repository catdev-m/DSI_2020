/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.dto.EstudianteInfoDto;
import org.registrohorasociales.repository.EstudianteRepository;

/**
 *
 * @author denisse_mejia
 */
@ViewScoped
@ManagedBean
public class notasAController implements Serializable{
   private EstudianteRepository estudiantesRepository;
   private List<EstudianteInfoDto> estudianteList;
   private EstudianteInfoDto estudianteSelect;
   
@PostConstruct
    public void init() {
        estudiantesRepository = ApplicationContextProvider.getApplicationContext().getBean(EstudianteRepository.class);
        loadEstudiantes();
    }

    public void loadEstudiantes() {
        estudianteList = new ArrayList<>();

        estudiantesRepository.estudiantesInfoList().forEach(o -> {
            EstudianteInfoDto i = new EstudianteInfoDto();
            i.setDue(o[0].toString());
            i.setNombres(o[1].toString());
            i.setApellidos(o[2].toString());
            i.setCorreo(o[5].toString());
            i.setNombrecarrera(o[9].toString());
            i.setNom_institucion(o[11].toString());
            estudianteList.add(i);
        });

    }

    public EstudianteRepository getEstudiantesRepository() {
        return estudiantesRepository;
    }

    public void setEstudiantesRepository(EstudianteRepository estudiantesRepository) {
        this.estudiantesRepository = estudiantesRepository;
    }

    public List<EstudianteInfoDto> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<EstudianteInfoDto> estudianteList) {
        this.estudianteList = estudianteList;
    }

    public EstudianteInfoDto getEstudianteSelect() {
        return estudianteSelect;
    }

    public void setEstudianteSelect(EstudianteInfoDto estudianteSelect) {
        this.estudianteSelect = estudianteSelect;
    }
    
    
}
