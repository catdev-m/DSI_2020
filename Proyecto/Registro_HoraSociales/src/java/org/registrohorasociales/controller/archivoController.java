/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.dto.CargasArchInfoDto;
import org.registrohorasociales.entity.Archivo;
import org.registrohorasociales.repository.IArchivoRepository;

/**
 *
 * @author denisse_mejia
 */
@ViewScoped
@ManagedBean
public class archivoController implements Serializable{
    
    private IArchivoRepository archivoRepository;
    private List<CargasArchInfoDto> listadoArchivos;
    private Archivo archivoSelecter;
    
    private String formDescripcion;
    private String formFecha;
    private int formId;
    private String formNombre;
    
    @PostConstruct
     public void init() {
        archivoRepository = ApplicationContextProvider.getApplicationContext().getBean(IArchivoRepository.class);
        loadArchivo();
     }
     
    public void loadArchivo() {
        listadoArchivos = new ArrayList<>();
        
        System.out.print(archivoRepository.ArchivoList().size());
        try {
          archivoRepository.ArchivoList().forEach(o -> {
            CargasArchInfoDto i = new CargasArchInfoDto();
            i.id_archivo = o[0].toString();
            i.ruta = o[1].toString();
            i.descripcionContent = o[2].toString();
            i.usuario = o[3].toString();
            i.fechaCarga = o[4].toString();
            i.nombreUsr = o[5].toString() + o[6].toString();
            
            listadoArchivos.add(i);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public List<CargasArchInfoDto> getListadoArchivos() {
        return listadoArchivos;
    }

    public void setListadoArchivos(List<CargasArchInfoDto> listadoArchivos) {
        this.listadoArchivos = listadoArchivos;
    }
    
    
    public void ObtenerArchivos(){
        setFormDescripcion(archivoSelecter.getDescripcion());
        setFormFecha(archivoSelecter.getFecha().toString());
        setFormId(archivoSelecter.getIdFile());
        
        
    }

    public String getFormNombre() {
        return formNombre;
    }

    public void setFormNombre(String formNombre) {
        this.formNombre = formNombre;
    }
    
 
    public IArchivoRepository getArchivoRepository() {
        return archivoRepository;
    }

    public void setArchivoRepository(IArchivoRepository archivoRepository) {
        this.archivoRepository = archivoRepository;
    }

    public Archivo getArchivoSelecter() {
        return archivoSelecter;
    }

    public void setArchivoSelecter(Archivo archivoSelecter) {
        this.archivoSelecter = archivoSelecter;
    }

    public String getFormDescripcion() {
        return formDescripcion;
    }

    public void setFormDescripcion(String formDescripcion) {
        this.formDescripcion = formDescripcion;
    }

    public String getFormFecha() {
        return formFecha;
    }

    public void setFormFecha(String formFecha) {
        this.formFecha = formFecha;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }


}