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
import org.registrohorasociales.entity.Instructor;
import org.registrohorasociales.repository.InstructorRepository;

/**
 *
 * @author denisse_mejia
 */

@ViewScoped
@ManagedBean
public class instructorController implements Serializable{
    
    private InstructorRepository instructorRepository;
    private List<Instructor> instructoresList ;
    private Instructor instructorSelecter;
    
@PostConstruct    
    public void init(){
        instructorRepository = ApplicationContextProvider.getApplicationContext().getBean(InstructorRepository.class);
        loadInstructors();
    }
    
    public void loadInstructors(){
        instructoresList = new ArrayList<>();
        instructoresList = instructorRepository.InstructorList();
    }
    
    public InstructorRepository getInstructorRepository() {
        return instructorRepository;
    }

    public void setInstructorRepository(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> getInstructoresList() {
        return instructoresList;
    }

    public void setInstructoresList(List<Instructor> instructoresList) {
        this.instructoresList = instructoresList;
    }

    public Instructor getInstructorSelecter() {
        return instructorSelecter;
    }

    public void setInstructorSelecter(Instructor instructorSelecter) {
        this.instructorSelecter = instructorSelecter;
    }
    
    
}
