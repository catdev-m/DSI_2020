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
import javax.faces.model.SelectItem;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.entity.Estudiante;
import org.registrohorasociales.entity.Instructor;
import org.registrohorasociales.repository.EstudianteRepository;
import org.registrohorasociales.repository.IInstructorRepository;


/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class EstudianteController implements Serializable {

    private EstudianteRepository repoEstudiante;
    private IInstructorRepository repoTutores;
    private List<Estudiante> estudiantes;
    private List<Estudiante> sintutor;
    private List<Instructor> tutores;
    private List<SelectItem> listaTutores;
    private Estudiante estudianteSelector;
    private String formDue, formNombre, formApellido, formEmail, formPass,formIdTutor;
    private int formIdInstitucion;

    @PostConstruct
    public void initialize() {
        repoEstudiante = ApplicationContextProvider.getApplicationContext().getBean(EstudianteRepository.class);
        repoTutores = ApplicationContextProvider.getApplicationContext().getBean(IInstructorRepository.class);
        loadEstudiantes();
        sinTutor();
        loadInstructores();
    }

    public EstudianteController() {
    }

    public void loadEstudiantes() {
        estudiantes = new ArrayList<>();
        estudiantes = repoEstudiante.estudiantesList();
    }


    public void sinTutor() {
        sintutor = new ArrayList<>();
        for (Estudiante e : estudiantes) {
            if (e.getIdInstructor() == null) {
                sintutor.add(e);
            }
        }
    }
    
    public void loadInstructores(){
        tutores = new ArrayList<>();
        tutores = repoTutores.listaTutores();
        listaTutores = new ArrayList<>();
        listaTutores.clear();
        tutores.stream().map((car) -> new SelectItem(car.getId(),    car.getFirstName())).forEachOrdered((c) -> {
            this.listaTutores.add(c);
        });
    }
    
    public void asignarTutor(String due){
        Estudiante est = new Estudiante();
        est = repoEstudiante.findByDue(due);
        est.setIdInstructor(formIdTutor);
        repoEstudiante.save(est);
        System.out.println("Nombre: "+est.getNombres());
    }

    public EstudianteRepository getRepoEstudiante() {
        return repoEstudiante;
    }

    public void setRepoEstudiante(EstudianteRepository repoEstudiante) {
        this.repoEstudiante = repoEstudiante;
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

    public List<Estudiante> getSintutor() {
        return sintutor;
    }

    public void setSintutor(List<Estudiante> sintutor) {
        this.sintutor = sintutor;
    }

    public List<Instructor> getTutores() {
        return tutores;
    }

    public void setTutores(List<Instructor> tutores) {
        this.tutores = tutores;
    }

    public String getFormDue() {
        return formDue;
    }

    public void setFormDue(String formDue) {
        this.formDue = formDue;
    }

    public String getFormNombre() {
        return formNombre;
    }

    public void setFormNombre(String formNombre) {
        this.formNombre = formNombre;
    }

    public String getFormApellido() {
        return formApellido;
    }

    public void setFormApellido(String formApellido) {
        this.formApellido = formApellido;
    }

    public String getFormEmail() {
        return formEmail;
    }

    public void setFormEmail(String formEmail) {
        this.formEmail = formEmail;
    }

    public String getFormPass() {
        return formPass;
    }

    public void setFormPass(String formPass) {
        this.formPass = formPass;
    }

    public int getFormIdInstitucion() {
        return formIdInstitucion;
    }

    public void setFormIdInstitucion(int formIdInstitucion) {
        this.formIdInstitucion = formIdInstitucion;
    }

    public String getFormIdTutor() {
        return formIdTutor;
    }

    public void setFormIdTutor(String formIdTutor) {
        this.formIdTutor = formIdTutor;
    }

    public List<SelectItem> getListaTutores() {
        return listaTutores;
    }

    public void setListaTutores(List<SelectItem> listaTutores) {
        this.listaTutores = listaTutores;
    }
    
}
