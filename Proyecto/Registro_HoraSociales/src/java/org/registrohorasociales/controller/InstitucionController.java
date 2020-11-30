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
import org.registrohorasociales.repository.InstitucionRepository;

/**
 *
 * @author balmore
 */
@ManagedBean
@ViewScoped
//@SessionScoped
public class InstitucionController implements Serializable{

    private String formName, formHead, formTel, formMail, formRs, formRes;  //inputText elements
    private InstitucionRepository institucionRepo;
    private List<Institucion> instituciones;
    private List<SelectItem> listaInstituciones;
    private Institucion institucionSelector;
    
    @PostConstruct
    public void initialize(){
        institucionRepo = ApplicationContextProvider.getApplicationContext().getBean(InstitucionRepository.class);
        loadInstituciones();
    }
    public void loadInstituciones(){
        instituciones = new ArrayList<>();
        instituciones = institucionRepo.institucionList();
    }
    //CREATE
    public void crearInstitucion(){
        try {
            Institucion ins = new Institucion();
            ins.setNomInstitucion(formName);
            ins.setEncInstitucion(formHead);
            ins.setTelInstitucion(formTel);
            ins.setCorreoInstitucion(formMail);
            ins.setRsInstitucion(formRs);
            ins.setResInstitucion(formRes);
            ins.setStatusInstitucion('A');
            institucionRepo.save(ins);
            clearFormInstitucion();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating", ""));
        }
    }
    
    public Institucion obtenerInstitucionById(int id){
        institucionRepo = ApplicationContextProvider.getApplicationContext().getBean(InstitucionRepository.class);
        Institucion ins = institucionRepo.getInstitucionById(id);
        return ins;
    }
    //RETRIEVE
    public void obtenerDatos(){
        setFormName(institucionSelector.getNomInstitucion());
        setFormHead(institucionSelector.getEncInstitucion());
        setFormTel(institucionSelector.getTelInstitucion());
        setFormMail(institucionSelector.getCorreoInstitucion());
        setFormRs(institucionSelector.getRsInstitucion());
        setFormRes(institucionSelector.getResInstitucion());
    }
    //UPDATE
    public void actualizarInstitucion(){
        try {
            Institucion ins = new Institucion();
            ins.setIdInstitucion(institucionSelector.getIdInstitucion());
            ins.setNomInstitucion(formName);
            ins.setEncInstitucion(formHead);
            ins.setTelInstitucion(formTel);
            ins.setCorreoInstitucion(formMail);
            ins.setRsInstitucion(formRs);
            ins.setResInstitucion(formRes);
            ins.setStatusInstitucion('A');
            institucionRepo.save(ins);
            loadInstituciones();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha ACTUALIZADO la institucion: "+formName, "") );
            clearFormInstitucion();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se generó un error al actualizar", ""));
        }
    }
    //DELETE
    public void eliminarInstitucion(){
        try {
            institucionRepo.delete(institucionSelector.getIdInstitucion());
            loadInstituciones();
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha ELIMINADO la institucion: "+formName, "") );
            clearFormInstitucion();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se generó un error al eliminar", ""));
            e.printStackTrace();
        }
    }
    public void clearFormInstitucion(){
        formName = null;
        formHead = null;
        formTel = null;
        formMail = null;
        formRs = null;
        formRes = null;
    }
    
        public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormHead() {
        return formHead;
    }

    public void setFormHead(String formHead) {
        this.formHead = formHead;
    }

    public String getFormTel() {
        return formTel;
    }

    public void setFormTel(String formTel) {
        this.formTel = formTel;
    }

    public String getFormMail() {
        return formMail;
    }

    public void setFormMail(String formMail) {
        this.formMail = formMail;
    }

    public String getFormRs() {
        return formRs;
    }

    public void setFormRs(String formRs) {
        this.formRs = formRs;
    }

    public String getFormRes() {
        return formRes;
    }

    public void setFormRes(String formRes) {
        this.formRes = formRes;
    }

    public InstitucionRepository getInstitucionRepo() {
        return institucionRepo;
    }

    public void setInstitucionRepo(InstitucionRepository institucionRepo) {
        this.institucionRepo = institucionRepo;
    }

    public List<Institucion> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(List<Institucion> instituciones) {
        this.instituciones = instituciones;
    }

    public List<SelectItem> getListaInstituciones() {
        return listaInstituciones;
    }

    public void setListaInstituciones(List<SelectItem> listaInstituciones) {
        this.listaInstituciones = listaInstituciones;
    }

    public Institucion getInstitucionSelector() {
        return institucionSelector;
    }

    public void setInstitucionSelector(Institucion institucionSelector) {
        this.institucionSelector = institucionSelector;
    }
}
