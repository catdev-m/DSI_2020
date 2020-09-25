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
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class InstitucionController implements Serializable{

    private String formName, formHead, formTel, formMail, formRs, formRes;  //inputText elements
    private InstitucionRepository institucionRepo;
    private List<Institucion> instituciones;
    private List<SelectItem> listaInstituciones;
    private Institucion institucionSelector;
    
    @PostConstruct
    public void init(){
        institucionRepo = ApplicationContextProvider.getApplicationContext().getBean(InstitucionRepository.class);
        loadInstituciones();
    }
    public void loadInstituciones(){
        instituciones = new ArrayList<>();
        instituciones = institucionRepo.institucionList();     //method to select * from institucion
    }
    //CREATE
    public void crearInstitucion(){
        try {
            Institucion ins = new Institucion();
            ins.setNomInstitucion(formName);
            ins.setEncInstitucion(formHead);
            ins.setTelInstitucion(Integer.parseInt(formTel));
            ins.setCorreoInstitucion(formMail);
            ins.setRsInstitucion(formRs);
            ins.setRes(formRes);
            ins.setStatus("A");
            institucionRepo = ApplicationContextProvider.getApplicationContext().getBean(InstitucionRepository.class);
            institucionRepo.save(ins);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating", ""));
        }
    }
    //RETRIEVE
    public void obtenerDatos(){
        setFormName(institucionSelector.getNomInstitucion());
        setFormHead(institucionSelector.getEncInstitucion());
        int i = institucionSelector.getTelInstitucion();
        setFormTel(Integer.toString(i));
        setFormMail(institucionSelector.getCorreoInstitucion());
        setFormRs(institucionSelector.getRsInstitucion());
        setFormRes(institucionSelector.getRes());
    }
    //UPDATE
    public void actualizarInstitucion(int id){
        try {
            institucionRepo = ApplicationContextProvider.getApplicationContext().getBean(InstitucionRepository.class);
        } catch (Exception e) {
        }
    }
    //DELETE
    public void eliminarInstitucion(int idInstitucion){
        try {
            //institucionRepo = ApplicationContextProvider.getApplicationContext().getBean(InstitucionRepository.class);
            institucionRepo.delete(idInstitucion);
        } catch (Exception e) {
        }
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
