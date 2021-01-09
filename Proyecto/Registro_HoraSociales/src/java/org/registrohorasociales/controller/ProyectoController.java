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
    private String formNomProyecto, formularioIdentificador, formularioCuposHabiles, formNombreInstitucion;
    private int formIdInstitucion, formCupoProyecto;
    
    /*variables de proyecto*/
    private ProyectoRepository proyectoRepo;            //instancia de repositorio de proyecto.
    private List<Proyecto> proyectos;
    private List<SelectItem> listaProyectos;            //es cuando se da clic sobre un elemento en la tabla de los elementos
    protected Proyecto proyectoSelector;                  //elemento singular cuando se elige un proyecto.
    
    /*variables de instituciones para relacionarlas con un proyecto*/
    private List<Institucion> instituciones;
    private InstitucionRepository InstitucionRepo;
    private List<SelectItem> listaInstituciones;
    
    @PostConstruct
    public void initialize(){
        proyectoRepo = ApplicationContextProvider.getApplicationContext().getBean(ProyectoRepository.class);
        InstitucionRepo = ApplicationContextProvider.getApplicationContext().getBean(InstitucionRepository.class);
        loadProyectos();
        loadInstituciones();
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
    public String metodoParaEncontrarInstitucionPorId(int id){
        Institucion ins = InstitucionRepo.findOne(id);
        String NombreInstitucion = ins.getNomInstitucion();
        return NombreInstitucion;
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
            //Getting ProjectId
            pr.setIdProyecto(proyectoSelector.getIdProyecto());
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Get ProjectId for Update: "+proyectoSelector.getIdProyecto(), "") );
            //Getting ProjectName
            pr.setNomProyecto(formNomProyecto);
            FacesContext context3 = FacesContext.getCurrentInstance();
            context3.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Set New Name or preserverd name: "+formNomProyecto, "") );
            //Getting FacilityId
            pr.setIdInstitucion(formIdInstitucion);
            FacesContext context4 = FacesContext.getCurrentInstance();
            context4.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Setting New InstitucionId: "+formIdInstitucion, "") );
            pr.setCuposProyecto(formCupoProyecto);
            proyectoRepo.save(pr);
            loadProyectos();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha ACTUALIZADO el proyecto: "+formNomProyecto, "") );
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
            context2.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha ELIMINADO el proyecto con id: "+proyectoSelector.getIdProyecto(), "") );
            proyectoRepo.delete(proyectoSelector.getIdProyecto());
            clearFormProyecto();
            loadProyectos();
        
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha ELIMINADO el proyecto: "+formNomProyecto, "") );
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error eliminando: "+formNomProyecto + e, "") );
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
}
