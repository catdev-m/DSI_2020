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
    private String formIdEstudiante, formIdProyecto, fecha_inicio, fecha_final;
    private String formNombrePoryecto, formNombreInstitucion;
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
    
    @PostConstruct
    public void initialize(){
        //proyectoRepo = ApplicationContextProvider.getApplicationContext().getBean(ProyectoRepository.class);
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
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Passed Getting StudentID: " + currentUserId, ""));
            rep.setCarnetEstudiante(currentUserId);
            FacesContext context3 = FacesContext.getCurrentInstance();
            context3.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Passed Setting StudentID: " + currentUserId, ""));
            FacesContext context4 = FacesContext.getCurrentInstance();
            context4.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Passed Getting ProjectID: " + proyectoSelector2.getIdProyecto(), ""));
            rep.setIdProyecto(proyectoSelector2.getIdInstitucion());
            FacesContext context5 = FacesContext.getCurrentInstance();
            context5.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Passed Setting ProjectID: " + proyectoSelector2.getIdProyecto(), ""));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            FacesContext context6 = FacesContext.getCurrentInstance();
            context6.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Passed Getting Current Date: " + now.toString(), ""));
            rep.setFechaInicio(now.toString());
            FacesContext context7 = FacesContext.getCurrentInstance();
            context7.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Passed Setting Current Date: " + now.toString(), ""));
            /*rep.setFechaInicio(now.toString());*/
            //acáa tenog que hacer que el número de los cupos disminuya uan unidad
            FacesContext context8 = FacesContext.getCurrentInstance();
            context8.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Next Step is Saving: ", ""));
            //relacionesRepo = ApplicationContextProvider.getApplicationContext().getBean(RelacionEstudianteProyectoRepository.class);
            rep.setFechaFinal(null);
            relacionesRepo.save(rep);
            clearFormRelacionEstudianteProyecto();
            //loadProyectos();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success !", ""));
        }catch (Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puede ser asignado a más de un proyecto", ""));
        }
    }
    
    public void obtenerDatos3(){
        setFormNombrePoryecto(proyectoSelector2.getNomProyecto());
        setFormNombreInstitucion(ObtenerNombreDeInstitucionPorId(proyectoSelector2.getIdInstitucion()));
        setFormCuposPoryecto(proyectoSelector2.getCuposProyecto());
    }
    public String ObtenerNombreDeInstitucionPorId (int id){
        InstitucionRepo = ApplicationContextProvider.getApplicationContext().getBean(InstitucionRepository.class);
        String NombreInstitucionPorId = InstitucionRepo.findOne(id).getNomInstitucion();
        return NombreInstitucionPorId;
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
}
