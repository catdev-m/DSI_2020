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
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author denisse_mejia
 */
@Named(value = "cargaArchivos")
@SessionScoped
public class CargaArchivos implements Serializable {
private UploadedFile arcvhivo;
    /**
     * Creates a new instance of CargaArchivos
     */
    public CargaArchivos() {
    }
    
   public String upload() throws IOException {
     if (arcvhivo != null) {            
            InputStream inpS = arcvhivo.getInputStream();
     }
       return null;
   
   } 

    public UploadedFile getArcvhivo() {
        return arcvhivo;
    }

    public void setArcvhivo(UploadedFile arcvhivo) {
        this.arcvhivo = arcvhivo;
    }
   
}
