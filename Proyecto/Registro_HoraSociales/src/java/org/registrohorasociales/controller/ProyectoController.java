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
import org.registrohorasociales.entity.Proyecto;
import org.registrohorasociales.repository.ProyectoRepository;

/**
 *
 * @author balmore
 */
@ManagedBean
@ViewScoped
public class ProyectoController implements Serializable{
    private String formNomProyecto, formIdInstitucion;
    private ProyectoRepository proyectoRepo;            //instancia de repositorio de proyecto.
    private List<Proyecto> proyectos;                   //lista de elementos de tipo "proyecto" (al hacer el select * from proyecto)
    private List<SelectItem> listaProyectos;            //es cuando se da clic sobre un elemento en la tabla de los elementos
    private Proyecto proyectoSelector;                  //elemento singular cuando se elige un proyecto.
    
    @PostConstruct
    public void initialize(){
        proyectoRepo = ApplicationContextProvider.getApplicationContext().getBean(ProyectoRepository.class);
        loadProyectos();
    }
    public void loadProyectos(){
        proyectos = new ArrayList<>();
        proyectos = proyectoRepo.proyectoList();
    }
    //CREATE
    //UDPATE
    //RETRIEVE
    //DELETE
}
