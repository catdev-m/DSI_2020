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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
    private String formNomProyecto, formularioIdentificador, formularioCuposHabiles, formNombreInstitucion;
    private int formIdInstitucion, formCupoProyecto;
    private ProyectoRepository proyectoRepo;            //instancia de repositorio de proyecto.
    private List<Proyecto> proyectos;
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
    
    public Proyecto findProyectoById(int id){
        proyectoRepo = ApplicationContextProvider.getApplicationContext().getBean(ProyectoRepository.class);
        Proyecto p = proyectoRepo.proyectoById(id);
        return p;
    }
    //CREATE
    public void crearProyecto(){
        try {
            Proyecto p = new Proyecto();
            p.setNomProyecto(formNomProyecto);
            p.setIdInstitucion(formIdInstitucion);
            p.setCuposProyecto(formCupoProyecto);
            proyectoRepo.save(p);
            clearFormProyecto();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating", ""));
        }
    }
    //RETRIEVE
    public void obtenerDatos(){
        setFormNomProyecto(proyectoSelector.getNomProyecto());
        //setFormIdInstitucion(proyectoSelector.getIdInstitucion());
        //setFormularioIdentificador(proyectoSelector.getIdInstitucion());
        setFormCupoProyecto(proyectoSelector.getIdProyecto());
    }
    //UPDATE
    public void actualizarProyecto(){
        try {
            Proyecto pr = new Proyecto();
            pr.setIdProyecto(proyectoSelector.getIdProyecto());
            pr.setNomProyecto(proyectoSelector.getNomProyecto());
            pr.setIdInstitucion(proyectoSelector.getIdInstitucion());
            proyectoRepo.save(pr);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha ACTUALIZADO el proyecto: "+formNomProyecto, "") );
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se generó un error al actualizar", ""));
        }
    }
    //DELETE
    public void eliminarProyecto(){
        try {
            proyectoRepo.delete(proyectoSelector.getIdProyecto());
            loadProyectos();
        
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha ELIMINADO la institucion: "+formNomProyecto, "") );
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error eliminando: "+formNomProyecto, "") );
        }
    }
    
    public void clearFormProyecto(){
        formNomProyecto = null;
        formIdInstitucion = 0;
    }
    
    public String getFormNomProyecto() {
        return formNomProyecto;
    }

    public void setFormNomProyecto(String formNomProyecto) {
        this.formNomProyecto = formNomProyecto;
    }

    public ProyectoRepository getProyectoRepo() {
        return proyectoRepo;
    }

    public void setProyectoRepo(ProyectoRepository proyectoRepo) {
        this.proyectoRepo = proyectoRepo;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public List<SelectItem> getListaProyectos() {
        return listaProyectos;
    }

    public void setListaProyectos(List<SelectItem> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    public Proyecto getProyectoSelector() {
        return proyectoSelector;
    }

    public void setProyectoSelector(Proyecto proyectoSelector) {
        this.proyectoSelector = proyectoSelector;
    }

    public int getFormIdInstitucion() {
        return formIdInstitucion;
    }

    public void setFormIdInstitucion(int formIdInstitucion) {
        this.formIdInstitucion = formIdInstitucion;
    }
    public int getFormCupoProyecto(){
        return formCupoProyecto;
    }
    public void setFormCupoProyecto(int formCupoProyecto){
        this.formCupoProyecto = formCupoProyecto;
    }
    public String getFormularioIdentificador() {
        return formularioIdentificador;
    }

    public void setFormularioIdentificador(String formularioIdentificador) {
        this.formularioIdentificador = formularioIdentificador;
    }

    public String getFormularioCuposHabiles() {
        return formularioCuposHabiles;
    }

    public void setFormularioCuposHabiles(String formularioCuposHabiles) {
        this.formularioCuposHabiles = formularioCuposHabiles;
    }
    
    public String getFormNombreInstitucion() {
        return formNombreInstitucion;
    }

    public void setFormNombreInstitucion(String formNombreInstitucion) {
        this.formNombreInstitucion = formNombreInstitucion;
    }
}
