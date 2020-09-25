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
import org.registrohorasociales.entity.Carrera;
import org.registrohorasociales.repository.CarreraRepository;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class carreraController implements Serializable {

    private String formName,formCode, formCodeFacu, formNameFacu;
    private CarreraRepository carreraRepo = ApplicationContextProvider.getApplicationContext().getBean(CarreraRepository.class);
    private List<Carrera> carreras;
    private List<SelectItem> listaCarreras;
    private Carrera carreraSelector;
    
    
@PostConstruct
    public void init() {
        carreraRepo=ApplicationContextProvider.getApplicationContext().getBean(CarreraRepository.class);
        loadCarreras();
    }

    public String crearCarrera() {

        try {
            Carrera car = new Carrera();
            car.setCodigocarrera(formCode);
            car.setNombrecarrera(formName);
            car.setCodigofacultad(formCodeFacu);
            car.setNombrefacultad(formNameFacu);
            carreraRepo.save(car);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Carrera creada con éxito",""));
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo almacenar la carrera", ""));
        }

        return null;
    }

    public void loadCarreras(){
        carreras = new ArrayList<>();
        this.listaCarreras= new ArrayList<>();
        carreras = carreraRepo.carrerasList();
        listaCarreras.clear();
        
        carreras.stream().map((car) -> new SelectItem(car.getIdcarrera(),    car.getNombrecarrera())).forEachOrdered((c) -> {
            this.listaCarreras.add(c);
        });
    }
    
    public String findCarreraById(int code){
        try{
        carreraRepo = ApplicationContextProvider.getApplicationContext().getBean(CarreraRepository.class);
        Carrera ca = new Carrera();
        ca =  carreraRepo.findOne(code);
        return ca.getNombrecarrera();
        }catch(EmptyResultDataAccessException ex){
            throw new RuntimeException("User not exist");
        }
    }

    public String getFormName() {
        return formName;
    }
    
    public void deleteCarreraById(int id){
        try{
            carreraRepo.delete(id);
        }catch(Exception e){
            
        }
    }


    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
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

    public List<SelectItem> getListaCarreras() {
        return listaCarreras;
    }

    public void setListaCarreras(List<SelectItem> listaCarreras) {
        this.listaCarreras = listaCarreras;
    }

    public String getFormCodeFacu() {
        return formCodeFacu;
    }

    public void setFormCodeFacu(String formCodeFacu) {
        this.formCodeFacu = formCodeFacu;
    }

    public String getFormNameFacu() {
        return formNameFacu;
    }

    public void setFormNameFacu(String formNameFacu) {
        this.formNameFacu = formNameFacu;
    }

    

}
