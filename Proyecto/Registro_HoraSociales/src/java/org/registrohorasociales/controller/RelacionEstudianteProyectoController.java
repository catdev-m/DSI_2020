package org.registrohorasociales.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.entity.Estudiante;
import org.registrohorasociales.entity.Proyecto;
import org.registrohorasociales.entity.RelacionEstudianteProyecto;
import org.registrohorasociales.repository.EstudianteRepository;
import org.registrohorasociales.repository.ProyectoRepository;
import org.registrohorasociales.repository.RelacionEstudianteProyectoRepository;

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
    private String formNombrePoryecto, formNombreInstitucion, formCuposPoryecto;
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
    
    @PostConstruct
    public void initialize(){
        proyectoRepo = ApplicationContextProvider.getApplicationContext().getBean(ProyectoRepository.class);
    }
    public void loadProyectos(){
        
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

    public String getFormCuposPoryecto() {
        return formCuposPoryecto;
    }

    public void setFormCuposPoryecto(String formCuposPoryecto) {
        this.formCuposPoryecto = formCuposPoryecto;
    }
}
