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
import org.registrohorasociales.entity.Archivo;
import org.registrohorasociales.repository.IArchivoRepository;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class ArchivoTutorController implements Serializable {
    static String due;
    private List<Archivo> archivos;
    private Archivo archivoSelect;
    private IArchivoRepository repo;
    
    
    @PostConstruct
    public void initialize() {
        repo = ApplicationContextProvider.getApplicationContext().getBean(IArchivoRepository.class);
        getArchivosByDue();
    }
    
    public void getArchivosByDue() {
        
        archivos = new ArrayList<>();
        try {
            archivos = repo.listaByCarnet(due);
        } catch (Exception e) {
        }
    }
    
    public void datosComentarios(String user, int idArc){
        ComentarioController.idArchivo=idArc;
        ComentarioController.user1=user;
        ComentarioController.user2=SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Archivo> archivos) {
        this.archivos = archivos;
    }

    public Archivo getArchivoSelect() {
        return archivoSelect;
    }

    public void setArchivoSelect(Archivo archivoSelect) {
        this.archivoSelect = archivoSelect;
    }    

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }
    
    
    
}
