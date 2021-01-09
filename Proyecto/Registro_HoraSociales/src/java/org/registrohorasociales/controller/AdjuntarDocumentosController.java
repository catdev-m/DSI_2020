/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

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
import org.registrohorasociales.entity.Anuncio;
import org.registrohorasociales.entity.Archivo;
import org.registrohorasociales.repository.IAnuncioRepository;
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
    private CargasArchInfoDto archivoSelecter;
    private List<Anuncio> listAnuncio;
    private IAnuncioRepository anuncioRepository;
    
    private String formDescripcion;
    private String formFecha;
    private int formId;
    private String formNombre;
    private String archivoLoad;
    private String imagenAnuncio;
    private String formAnuncioDesc;
    private Date formfechaAnuncio;
    private String formAnuncioDialog;
   
    
    
    final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
    
    public AdjuntarDocumentosController() {
    }
   
    @PostConstruct
     public void init() {
        archivoRepository = ApplicationContextProvider.getApplicationContext().getBean(IArchivoRepository.class);
        anuncioRepository = ApplicationContextProvider.getApplicationContext().getBean(IAnuncioRepository.class);
        mostrarArchivos();
        ObtenerAnuncio();
     }
     
     public void ObtenerAnuncio(){
         listAnuncio = anuncioRepository.findAll();
     }
     
     public String guardarAnuncio(){
         
    if (validNullString(imagenAnuncio) && validNullString(formAnuncioDesc)){
        String msj = "Debe adjuntar una imagen o ingresar el texto del anuncio";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, "") );
        return null;
    }
    if (formfechaAnuncio == null){
        String msj = "Debe ingresar una fecha de fin del anuncio";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, "") );
        return null;
    }
    
         Anuncio anuncio = new Anuncio();
         //anuncio.setAnuncioText(formAnuncioDesc);
         anuncio.setImagen(imagenAnuncio);
         anuncio.setFecha(formfechaAnuncio);
         anuncioRepository.save(anuncio);
         String msj = "Se ha almacenado el anuncio de manera correcta";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msj, "") );
         
         formAnuncioDesc= null;
         imagenAnuncio = null;
         formfechaAnuncio = null;
         ObtenerAnuncio();
         
         return null;
         
     }
     
     public void verAnuncio(String a){
         formAnuncioDialog= a;
     }
     
         public void eliminarAnuncio(Anuncio a){
             anuncioRepository.delete(a);
             String msj = "Se ha eliminado el anuncio seleccionado";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msj, "") );
        ObtenerAnuncio();
    }
     
    public void mostrarArchivos() {
    listadoArchivos = new ArrayList<>();
        
        try {
           
       for(Object[] o : archivoRepository.ArchivoList(currentUserName)){
           CargasArchInfoDto i = new CargasArchInfoDto();
           i.ruta = o[1].toString();
            i.descripcionContent = o[2].toString();
            i.usuario = o[3].toString();
            i.fechaCarga = o[4].toString();
            i.nombreUsr = o[5].toString() +" "+ o[6].toString();
            i.setId_archivo(o[0].toString());
           
           listadoArchivos.add(i);
           }        
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String guardarFile(){
            
        SimpleDateFormat a = new SimpleDateFormat("dd/MM/YYYY");
        String fecha = a.format(new Date());
    if (validNullString (formDescripcion)){
        String msj = "Debe agregar una breve descripción de la entrega";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, "") );
        return null;
    }
    if (validNullString(archivoLoad)){
        String msj = "Debe adjuntar un archivo para la entrega";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, "") );
        return null;
    }
    else{
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
        
        String msj = "Se guardaron los datos correctamente";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msj, "") );
        mostrarArchivos();
        
        formDescripcion ="";
    }catch(Exception e){
         String msj = "Se encontró un error al guardar";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, "") );
    }
    }
    return  null;
    }
        public boolean validNullString(String field) {
        boolean nul = false;
        if (field == null || field.equals("")) {
            nul = true;
        }
        return nul;
    }
    
        public  void eliminarArchivo(String id_file){
         Archivo ar  =  archivoRepository.finByIdFile(id_file);
         archivoRepository.delete(ar);
        String msj = "Se elimino Archivo";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msj, "") );
        mostrarArchivos();
        }
        
    public void handleFileUpload(FileUploadEvent event) {
        try{
        
        InputStream inpS = event.getFile().getInputStream();
        int file = event.getFile().getContent().length;
        setArchivoLoad(base64metodo(inpS)); 
        
        String msj = "Se adjuntó el archivo de manera exitosa, pude guardar los datos";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msj, "") );
        }catch (Exception e){
        
        }
    }
    
    public String base64metodo(InputStream is) throws IOException{
      byte[] bytes = IOUtils.toByteArray(is);
        String encoded = Base64.getEncoder().encodeToString(bytes);
    return encoded;
    }
    
        public void saveImage(FileUploadEvent event) {
        try{
        
        InputStream inpS = event.getFile().getInputStream();
        int file = event.getFile().getContent().length;
        setImagenAnuncio(base64metodo(inpS));
        String msj = "Se adjuntó la imagen de manera exitosa, pude guardar los datos";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msj, "") );
        }catch (Exception e){
        
        }
    }

    public String getFormAnuncioDialog() {
        return formAnuncioDialog;
    }

    public void setFormAnuncioDialog(String formAnuncioDialog) {
        this.formAnuncioDialog = formAnuncioDialog;
    }

    public Date getFormfechaAnuncio() {
        return formfechaAnuncio;
    }

    public void setFormfechaAnuncio(Date formfechaAnuncio) {
        this.formfechaAnuncio = formfechaAnuncio;
    }

    public List<Anuncio> getListAnuncio() {
        return listAnuncio;
    }

    public void setListAnuncio(List<Anuncio> listAnuncio) {
        this.listAnuncio = listAnuncio;
    }

    public String getFormAnuncioDesc() {
        return formAnuncioDesc;
    }

    public void setFormAnuncioDesc(String formAnuncioDesc) {
        this.formAnuncioDesc = formAnuncioDesc;
    }

    public String getImagenAnuncio() {
        return imagenAnuncio;
    }

    public void setImagenAnuncio(String imagenAnuncio) {
        this.imagenAnuncio = imagenAnuncio;
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

    public CargasArchInfoDto getArchivoSelecter() {
        return archivoSelecter;
    }

    public void setArchivoSelecter(CargasArchInfoDto archivoSelecter) {
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
