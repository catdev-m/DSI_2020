/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.entity.Datoscertificacion;
import org.registrohorasociales.repository.IDatosCertificacionRespository;
import org.springframework.beans.BeansException;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
public class certificacionContrller {
    private List<Datoscertificacion> datos;
    private Datoscertificacion datosSelector;
    private IDatosCertificacionRespository repo;
    private String formId, formTitulo, formNombre, formCargo;
    @PostConstruct
    public void initialize(){
        repo = ApplicationContextProvider.getApplicationContext().getBean(IDatosCertificacionRespository.class);
        cargarDatos();
    }
    
    public void obtenerDatos(){
        setFormId(String.valueOf(datosSelector.getId()));
        setFormTitulo(datosSelector.getTitulo());
        setFormNombre(datosSelector.getNombre());
        setFormCargo(datosSelector.getCargo());
    }
    
    public Datoscertificacion findDatosById(int code){
        try{
        repo = ApplicationContextProvider.getApplicationContext().getBean(IDatosCertificacionRespository.class);
        Datoscertificacion datos = new Datoscertificacion();
        datos =  repo.findOne(code);
        return datos;
        }catch(BeansException ex){
            throw new RuntimeException("User not exist");
        }
    }   
    
    
    public void cargarDatos(){
        datos = new ArrayList<>();
        datos = repo.datosCertificacionList(); 
    }

    public List<Datoscertificacion> getDatos() {
        return datos;
    }

    public void setDatos(List<Datoscertificacion> datos) {
        this.datos = datos;
    }

    public Datoscertificacion getDatosSelector() {
        return datosSelector;
    }

    public void setDatosSelector(Datoscertificacion datosSelector) {
        this.datosSelector = datosSelector;
    } 

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFormTitulo() {
        return formTitulo;
    }

    public void setFormTitulo(String formTitulo) {
        this.formTitulo = formTitulo;
    }

    public String getFormNombre() {
        return formNombre;
    }

    public void setFormNombre(String formNombre) {
        this.formNombre = formNombre;
    }

    public String getFormCargo() {
        return formCargo;
    }

    public void setFormCargo(String formCargo) {
        this.formCargo = formCargo;
    }

}

