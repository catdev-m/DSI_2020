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
import org.registrohorasociales.entity.Institucion;
import org.registrohorasociales.entity.Proyecto;
import org.registrohorasociales.repository.InstitucionRepository;
import org.registrohorasociales.repository.ProyectoRepository;

/**
 *
 * @author balmore
 */
@ManagedBean
@ViewScoped
public class ProyectoController implements Serializable{
    /*variables de campos de formulario*/
    private String formNomProyecto, formNombreInstitucion;
    private int formIdInstitucion, formCupoProyecto;
    
    /*variables de proyecto*/
    private ProyectoRepository proyectoRepo;            //instancia de repositorio de proyecto.
    private List<Proyecto> proyectos;
    private List<Proyecto> proyectosMayorQueCero;
    private List<SelectItem> listaProyectos;            //es cuando se da clic sobre un elemento en la tabla de los elementos
    protected Proyecto proyectoSelector;                //elemento singular cuando se elige un proyecto.
    
    /*variables de instituciones para relacionarlas con un proyecto*/
    private List<Institucion> instituciones;
    private InstitucionRepository InstitucionRepo;
    private List<SelectItem> listaInstituciones;
    
    @PostConstruct
    public void initialize(){
        proyectoRepo = ApplicationContextProvider.getApplicationContext().getBean(ProyectoRepository.class);
        InstitucionRepo = ApplicationContextProvider.getApplicationContext().getBean(InstitucionRepository.class);
        loadProyectos();
        loadProyectosMayorQueCero();
        loadInstituciones();
    }
    public void loadProyectos(){
        proyectos = new ArrayList<>();
        proyectos = proyectoRepo.proyectoList();
    }
    public void loadProyectosMayorQueCero(){
        proyectosMayorQueCero = new ArrayList<>();
        proyectosMayorQueCero = proyectoRepo.proyectoConCupo();
    }
    
    //Se utiliza en Certificacion Controller: line 65
    public Proyecto findProyectoById(int id){
        proyectoRepo = ApplicationContextProvider.getApplicationContext().getBean(ProyectoRepository.class);
        Proyecto p = proyectoRepo.proyectoById(id);
        return p;
    }
    
    public void loadInstituciones(){
        //Lista de elementos institucion que obtenemos mediante el repositorio
        instituciones = new ArrayList<>();
        instituciones = InstitucionRepo.institucionList();
        //lista de elementos de selectores para instituciones
        listaInstituciones = new ArrayList<>();
        listaInstituciones.clear();
        //las propiedades de la instituciones que queremos en la lista que los elementos selectores deben acceder, se especifican a continuacion
        instituciones
                .stream()
                .map( (institu) -> new SelectItem(institu.getIdInstitucion(), institu.getNomInstitucion() ) )
                .forEachOrdered( (i) -> { this.listaInstituciones.add(i); });
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
            loadProyectos();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!", ""));
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating", ""));
        }
    }
    //RETRIEVE
    public void obtenerDatos(){
        setFormNomProyecto(proyectoSelector.getNomProyecto());
        setFormIdInstitucion(proyectoSelector.getIdInstitucion());
        setFormCupoProyecto(proyectoSelector.getCuposProyecto());
    }
    //UPDATE
    public void actualizarProyecto(){
        try {
            Proyecto pr = new Proyecto();
            //Obtener Id Proyecto
            pr.setIdProyecto(proyectoSelector.getIdProyecto());
            
            //Obtener Nombre Proyecto
            pr.setNomProyecto(formNomProyecto);
            
            //Obtener Institucion Id
            pr.setIdInstitucion(formIdInstitucion);
            
            //Obtener Cupos Proyecto
            pr.setCuposProyecto(formCupoProyecto);
            
            //Guardar
            proyectoRepo.save(pr);
            
            //Cargar Proyectos
            loadProyectos();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha ACTUALIZADO el proyecto: "+formNomProyecto, "") );
            
            //Limpiar Formulario
            clearFormProyecto();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se generó un error al actualizar", ""));
        }
    }
    //DELETE
    public void eliminarProyecto(){
        try {
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha ELIMINADO el proyecto : "+proyectoSelector.getNomProyecto(), "") );
            proyectoRepo.delete(proyectoSelector.getIdProyecto());
            clearFormProyecto();
            loadProyectos();
        
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha ELIMINADO correctamente", "") );
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error eliminando: " + e, "") );
        }
    }
    
    public void clearFormProyecto(){
        formNomProyecto = null;
        formIdInstitucion = 0;
        formCupoProyecto = 0;
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
    public String getFormNombreInstitucion() {
        return formNombreInstitucion;
    }

    public void setFormNombreInstitucion(String formNombreInstitucion) {
        this.formNombreInstitucion = formNombreInstitucion;
    }
    
    public List<SelectItem> getListaInstituciones() {
        return listaInstituciones;
    }

    public void setListaInstituciones(List<SelectItem> listaInstituciones) {
        this.listaInstituciones = listaInstituciones;
    }
    
    public InstitucionRepository getInstitucionRepo() {
        return InstitucionRepo;
    }

    public void setInstitucionRepo(InstitucionRepository InstitucionRepo) {
        this.InstitucionRepo = InstitucionRepo;
    }
    
    public List<Institucion> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(List<Institucion> instituciones) {
        this.instituciones = instituciones;
    }
    public List<Proyecto> getProyectosMayorQueCero() {
        return proyectosMayorQueCero;
    }

    public void setProyectosMayorQueCero(List<Proyecto> proyectosMayorQueCero) {
        this.proyectosMayorQueCero = proyectosMayorQueCero;
    }
}
