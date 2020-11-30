/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.entity.RelacionEstudianteProyecto;
import org.registrohorasociales.repository.RelacionEstudianteProyectoRepository;

/**
 *
 * @author balmore
 */
@ManagedBean
@ViewScoped
public class RelacionEstudianteProyectoController {

    private String formCarnetEstudiante, formIdInstitucion, formFechaInicial, formFechaFinal;
    private RelacionEstudianteProyectoRepository relacionesRepo;        //instacia de repositorio
    private List<RelacionEstudianteProyecto> relaciones;
    private List<SelectItem> listaRelacioens;
    private RelacionEstudianteProyecto relacionSelector;

    @PostConstruct
    public void initialize() {
        relacionesRepo = ApplicationContextProvider.getApplicationContext().getBean(RelacionEstudianteProyectoRepository.class);
        loadRelaciones();
    }

    public void loadRelaciones() {
        relaciones = new ArrayList<>();
        relaciones = relacionesRepo.relacionesList();
    }

    public RelacionEstudianteProyecto findRelacionByDue(String due) {
        relacionesRepo = ApplicationContextProvider.getApplicationContext().getBean(RelacionEstudianteProyectoRepository.class);
        relaciones = new ArrayList<>();
        relaciones = relacionesRepo.relacionesList();
        RelacionEstudianteProyecto rep = new RelacionEstudianteProyecto();
        for(RelacionEstudianteProyecto r: relaciones){
            if(r.getCarnetEstudiante().equals(due)){
                rep=r;
            }
        }
        return rep;
    }
    //CREATE
    //RETRIEVE
    //UPDATE
    //DELETE
}
