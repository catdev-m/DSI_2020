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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.entity.Estudiante;
import org.registrohorasociales.repository.EstudianteRepository;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class EstudianteController implements Serializable{
    private EstudianteRepository repoEstudiante= ApplicationContextProvider.getApplicationContext().getBean(EstudianteRepository.class);
    private List<Estudiante> estudiantes;
    private Estudiante estudianteSelector;
    
    @PostConstruct
    public void init(){
        repoEstudiante = ApplicationContextProvider.getApplicationContext().getBean(EstudianteRepository.class);
        loadEstudiantes();
    }

    public EstudianteController() {
    }
    
    public void loadEstudiantes(){
        estudiantes = new ArrayList<>();
        estudiantes=repoEstudiante.estudiantesList();
    }

    public EstudianteRepository getRepoEstudiante() {
        return repoEstudiante;
    }

    public void setRepoEstudiante(EstudianteRepository repoEstudiante) {
        this.repoEstudiante = repoEstudiante;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Estudiante getEstudianteSelector() {
        return estudianteSelector;
    }

    public void setEstudianteSelector(Estudiante estudianteSelector) {
        this.estudianteSelector = estudianteSelector;
    }
    
    
    
    
}
