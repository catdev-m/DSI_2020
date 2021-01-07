/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.dto.CargasArchInfoDto;
import org.registrohorasociales.entity.Archivo;
import org.registrohorasociales.repository.IArchivoRepository;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author denisse_mejia
 */
@Named(value = "AdjuntarDocumentosController")
@SessionScoped
public class AdjuntarDocumentosController implements Serializable {

    private IArchivoRepository archivoRepository;
    private List<CargasArchInfoDto> listadoArchivos;
    private Archivo archivoSelecter;
    
    private String formDescripcion;
    private String formFecha;
    private int formId;
    private String formNombre;
    private String archivoLoad;
    
    
    final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
    
    public AdjuntarDocumentosController() {
    }
   
    @PostConstruct
     public void init() {
        archivoRepository = ApplicationContextProvider.getApplicationContext().getBean(IArchivoRepository.class);
        mostrarArchivos();
     }
     
    public void mostrarArchivos() {
    listadoArchivos = new ArrayList<>();
        
        try {

          //System.out.println("Cantidad de elementos File "+archivoRepository.ArchivoList(currentUserName).size());
          archivoRepository.ArchivoList(currentUserName).forEach(o -> {
            CargasArchInfoDto i = new CargasArchInfoDto();
            //i.id_archivo = o[0].toString();
            i.ruta = o[1].toString();
            i.descripcionContent = o[2].toString();
            i.usuario = o[3].toString();
            i.fechaCarga = o[4].toString();
            i.nombreUsr = o[5].toString() +" "+ o[6].toString();
            //i.habilitar = o[7].toString();
            listadoArchivos.add(i);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void guardarFile(){
        
        SimpleDateFormat a = new SimpleDateFormat("dd/MM/YYYY");
        String fecha = a.format(new Date());
    if (formDescripcion== null){
        String msj = "Debe agregar una breve descripción de la entrega";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, "") );
    }else{
    try{    
        Archivo arc = new Archivo();

        arc.setUrl(archivoLoad);
        arc.setUsrCarga("");
        arc.setCarnet(SecurityContextHolder.getContext().getAuthentication().getName());
        arc.setDescripcion(formDescripcion);
        arc.setFecha(fecha);
        arc.setLocked(0);
        arc.setUsrCarga(SecurityContextHolder.getContext().getAuthentication().getName());
        archivoRepository.save(arc);
        
        String msj = "Se adjuntó el archivo de manera exitosa";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msj, "") );
    }catch(Exception e){
         String msj = "Se encontró un error al guardar";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, "") );
    }
    }
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        try{
        
        InputStream inpS = event.getFile().getInputStream();
        FacesContext context = FacesContext.getCurrentInstance();
        int file = event.getFile().getContent().length;
        setArchivoLoad(base64metodo(inpS)); 
        String user_id = context.getExternalContext().getRequestParameterMap().get("descFile2");
        String msj = "Se adjuntó el archivo de manera exitosa, pude guardar los datos "+user_id;
        //FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msj, "") );
        }catch (Exception e){
        
        }
    }
    
    public String base64metodo(InputStream is) throws IOException{
      byte[] bytes = IOUtils.toByteArray(is);
        String encoded = Base64.getEncoder().encodeToString(bytes);
    return encoded;
    }
     
    public void eliminarArchivo(){
    }

    public String getArchivoLoad() {
        return archivoLoad;
    }

    public void setArchivoLoad(String archivoLoad) {
        this.archivoLoad = archivoLoad;
    }

    public IArchivoRepository getArchivoRepository() {
        return archivoRepository;
    }

    public void setArchivoRepository(IArchivoRepository archivoRepository) {
        this.archivoRepository = archivoRepository;
    }

    public List<CargasArchInfoDto> getListadoArchivos() {
        return listadoArchivos;
    }

    public void setListadoArchivos(List<CargasArchInfoDto> listadoArchivos) {
        this.listadoArchivos = listadoArchivos;
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

    public String getFormNombre() {
        return formNombre;
    }

    public void setFormNombre(String formNombre) {
        this.formNombre = formNombre;
    }
    
    
}
