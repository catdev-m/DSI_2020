/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.entity.Estudiante;
import org.registrohorasociales.repository.EstudianteRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Miguel
 */
@ViewScoped
@ManagedBean
public class EstudianteInstructorController implements Serializable{
    EstudianteRepository repoEstudiante;
    private List<Estudiante> estudiantes;
    private Estudiante estudianteSelector;
    String usuario;
    
    
    
    public String obtenerUsuario(){
        String userName=null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userName = auth.getName();
        Object objeto = auth.getPrincipal();
        return userName;
    }
    
    
    @PostConstruct
    public void initialize(){
        repoEstudiante = ApplicationContextProvider.getApplicationContext().getBean(EstudianteRepository.class);
        usuario = obtenerUsuario();
        obtenerEstudiantes(usuario);
    }

    public void obtenerEstudiantes(String usuario){
        estudiantes = new ArrayList<>();
        estudiantes = repoEstudiante.estudiantesByInstructorList(usuario);
    }
    
    public void getArchivosByDue(String carnet) {
        ArchivoTutorController.due = carnet;
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

