package org.registrohorasociales.controller;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.entity.Estudiante;
import org.registrohorasociales.entity.Proyecto;
import org.registrohorasociales.entity.RelacionEstudianteProyecto;
import org.registrohorasociales.repository.EstudianteRepository;
import org.registrohorasociales.repository.InstitucionRepository;
import org.registrohorasociales.repository.ProyectoRepository;
import org.registrohorasociales.repository.RelacionEstudianteProyectoRepository;
import org.springframework.security.core.context.SecurityContextHolder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author balmore
 */
@ManagedBean
@SessionScoped
public class RelacionEstudianteProyectoController implements Serializable{
    /*variables de formulario que capturan los datos*/
    private String formIdEstudiante, formIdProyecto;
    private String formNombrePoryecto, formNombreInstitucion, fecha_inicio, fecha_final;
    private int formCuposPoryecto;
    private RelacionEstudianteProyecto relacionEstudianteProyectoSelector;
    private RelacionEstudianteProyectoRepository relacionesRepo;
    private List<RelacionEstudianteProyecto> relaciones;
    private List<SelectItem> listaRelaciones;
    /*variables de estudiantes*/
    private EstudianteRepository estudianteRepo;
    private List<Estudiante> estudiantes;
    private List<SelectItem> listaEstudiantes;
    /*variables de proyectos*/
    private ProyectoRepository proyectoRepo;
    private List<Proyecto> proyectos;
    private List<SelectItem> listaProyectos;
    private Proyecto proyectoSelector2;
    /*variables de instituciones*/
    private InstitucionRepository InstitucionRepo;
    
    private String color;
    
    @PostConstruct
    public void initialize(){
        proyectoRepo = ApplicationContextProvider.getApplicationContext().getBean(ProyectoRepository.class);
        relacionesRepo = ApplicationContextProvider.getApplicationContext().getBean(RelacionEstudianteProyectoRepository.class);
    }
    
    public RelacionEstudianteProyecto findRelacionByDue(String due) {
        relacionesRepo = ApplicationContextProvider.getApplicationContext().getBean(RelacionEstudianteProyectoRepository.class);
        relaciones = new ArrayList<>();
        relaciones = relacionesRepo.relacionEstudianteProyectoList();
        RelacionEstudianteProyecto rep = new RelacionEstudianteProyecto();
        for(RelacionEstudianteProyecto r: relaciones){
            if(r.getCarnetEstudiante().equals(due)){
                rep=r;
            }
        }
        return rep;
    }
    /*C R E A T E*/
    /*La asignacion de proyecto tiene como condicion en su respectiva tabla de base de datos que el carnet sea único, por lo que no acpeta más de una inscripción por sesión*/
    public void crearRelacionEstudianteProyecto(){
        try{
            RelacionEstudianteProyecto rep = new RelacionEstudianteProyecto();
            
            String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
            rep.setCarnetEstudiante(currentUserId);
            
            rep.setIdProyecto(proyectoSelector2.getIdInstitucion());
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            rep.setFechaInicio(now.toString());
            
            rep.setFechaFinal(null);
            
            relacionesRepo.save(rep);
            
            clearFormRelacionEstudianteProyecto();
            //loadProyectos();
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCES!", ""));
            
            
            Proyecto proyecto = proyectoRepo.findOne(proyectoSelector2.getIdProyecto());
            proyecto.setCuposProyecto(proyecto.getCuposProyecto() - 1);
            
            FacesContext context10 = FacesContext.getCurrentInstance();
            context10.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "(Remaining Spots for Project: "+proyecto.getCuposProyecto() + ")", ""));
            
            proyectoRepo.save(proyecto);
            
            
        }catch (Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puede ser asignado a más de un proyecto", ""));
        }
    }
    
    public void obtenerDatos(){
        setFormNombrePoryecto(proyectoSelector2.getNomProyecto());
        
        InstitucionRepo = ApplicationContextProvider.getApplicationContext().getBean(InstitucionRepository.class);
        String NombreIns = InstitucionRepo.findOne(proyectoSelector2.getIdInstitucion()).getNomInstitucion();
        setFormNombreInstitucion(NombreIns);
        
        setFormCuposPoryecto(proyectoSelector2.getCuposProyecto());
        
        Proyecto proyecto = proyectoRepo.findOne(proyectoSelector2.getIdProyecto());
        if(proyecto.getCuposProyecto() < 2) setColor("#ff6952");
        if(proyecto.getCuposProyecto() >= 2 && proyecto.getCuposProyecto() < 10) setColor("#ffff69");
        if(proyecto.getCuposProyecto() > 10) setColor("#73ff77");
    }
    public void clearFormRelacionEstudianteProyecto(){
        setFormNombrePoryecto(null);
        setFormNombreInstitucion(null);
        setFormCuposPoryecto(0);
    }
    
    /*G E T T E R S   A N D   S E T T E R S*/
    public String getFormIdEstudiante() {
        return formIdEstudiante;
    }

    public void setFormIdEstudiante(String formIdEstudiante) {
        this.formIdEstudiante = formIdEstudiante;
    }

    public String getFormIdProyecto() {
        return formIdProyecto;
    }

    public void setFormIdProyecto(String formIdProyecto) {
        this.formIdProyecto = formIdProyecto;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public EstudianteRepository getEstudianteRepo() {
        return estudianteRepo;
    }

    public void setEstudianteRepo(EstudianteRepository estudianteRepo) {
        this.estudianteRepo = estudianteRepo;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<SelectItem> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<SelectItem> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
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
    public RelacionEstudianteProyectoRepository getRelacionesRepo() {
        return relacionesRepo;
    }

    public void setRelacionesRepo(RelacionEstudianteProyectoRepository relacionesRepo) {
        this.relacionesRepo = relacionesRepo;
    }

    public List<RelacionEstudianteProyecto> getRelaciones() {
        return relaciones;
    }

    public void setRelaciones(List<RelacionEstudianteProyecto> relaciones) {
        this.relaciones = relaciones;
    }

    public List<SelectItem> getListaRelaciones() {
        return listaRelaciones;
    }

    public void setListaRelaciones(List<SelectItem> listaRelaciones) {
        this.listaRelaciones = listaRelaciones;
    }
    public String getFormNombrePoryecto() {
        return formNombrePoryecto;
    }

    public void setFormNombrePoryecto(String formNombrePoryecto) {
        this.formNombrePoryecto = formNombrePoryecto;
    }

    public String getFormNombreInstitucion() {
        return formNombreInstitucion;
    }

    public void setFormNombreInstitucion(String formNombreInstitucion) {
        this.formNombreInstitucion = formNombreInstitucion;
    }

    public RelacionEstudianteProyecto getRelacionEstudianteProyectoSelector() {
        return relacionEstudianteProyectoSelector;
    }

    public void setRelacionEstudianteProyectoSelector(RelacionEstudianteProyecto relacionEstudianteProyectoSelector) {
        this.relacionEstudianteProyectoSelector = relacionEstudianteProyectoSelector;
    }
    public Proyecto getProyectoSelector2() {
        return proyectoSelector2;
    }

    public void setProyectoSelector2(Proyecto proyectoSelector2) {
        this.proyectoSelector2 = proyectoSelector2;
    }
    public int getFormCuposPoryecto() {
        return formCuposPoryecto;
    }

    public void setFormCuposPoryecto(int formCuposPoryecto) {
        this.formCuposPoryecto = formCuposPoryecto;
    }
    public InstitucionRepository getInstitucionRepo() {
        return InstitucionRepo;
    }

    public void setInstitucionRepo(InstitucionRepository InstitucionRepo) {
        this.InstitucionRepo = InstitucionRepo;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
