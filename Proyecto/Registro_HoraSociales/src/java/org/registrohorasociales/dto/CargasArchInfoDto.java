/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.dto;

/**
 *
 * @author denisse_mejia
 */
public class CargasArchInfoDto {
    public String id_archivo;
    public String ruta;
    public String descripcionContent;
    public String usuario;
    public String nombreUsr;
    public String fechaCarga;

    public String getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(String fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    
    public String getId_archivo() {
        return id_archivo;
    }

    public void setId_archivo(String id_archivo) {
        this.id_archivo = id_archivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDescripcionContent() {
        return descripcionContent;
    }

    public void setDescripcionContent(String descripcionContent) {
        this.descripcionContent = descripcionContent;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsr() {
        return nombreUsr;
    }

    public void setNombreUsr(String nombreUsr) {
        this.nombreUsr = nombreUsr;
    }
    
    
}
