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
import javax.validation.ConstraintViolationException;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.entity.Carrera;
import org.registrohorasociales.entity.Solicitud;
import org.registrohorasociales.repository.SolicitudRepository;

/**
 *
 * @author Miguel
 */
@ViewScoped
@ManagedBean
public class solicitudController implements Serializable {

    private String formDue, formNombre, formApellido;
    private int formCarrera;
    private short formCiclo;
    private SolicitudRepository soliRepository = ApplicationContextProvider.getApplicationContext().getBean(SolicitudRepository.class);
    private List<Solicitud> solicitudes;
    private List<Carrera> carreras;
    private Solicitud solicitudSelector;
    private Carrera carreraSelector;
    

    @PostConstruct
    public void init() {
        soliRepository = ApplicationContextProvider.getApplicationContext().getBean(SolicitudRepository.class);
        loadSolicitudes();
    }

    public solicitudController() {
    }
    
    

    public void loadSolicitudes() {
        solicitudes = new ArrayList<>();
        solicitudes = soliRepository.SolicitudList();
    }

    public String crearSolicitud() {

        try {
            
            Solicitud soli = new Solicitud();
            soli.setDue(getFormDue());
            soli.setNombre(getFormNombre());
            soli.setApellido(getFormApellido());
            soli.setIdcarrera(getFormCarrera());
            soli.setCiclo(getFormCiclo());
            soliRepository.save(soli);
        } catch (ConstraintViolationException log) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se generó un error al crear el usuario", ""));
        }
        return null;
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

    public int getFormCarrera() {
        return formCarrera;
    }

    public void setFormCarrera(int formCarrera) {
        this.formCarrera = formCarrera;
    }

    public short getFormCiclo() {
        return formCiclo;
    }

    public void setFormCiclo(short formCiclo) {
        this.formCiclo = formCiclo;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public Solicitud getSolicitudSelector() {
        return solicitudSelector;
    }

    public void setSolicitudSelector(Solicitud solicitudSelector) {
        this.solicitudSelector = solicitudSelector;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    public Carrera getCarreraSelector() {
        return carreraSelector;
    }

    public void setCarreraSelector(Carrera carreraSelector) {
        this.carreraSelector = carreraSelector;
    }
    
    public void eliminarCarreraById(String i){
        soliRepository = ApplicationContextProvider.getApplicationContext().getBean(SolicitudRepository.class);
        soliRepository.delete(i);
    }
    
}
