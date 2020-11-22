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
public class EstudianteListDto {
    
    private String due;
    private String nombres;
    private String apellidos;
    private String correo;
    private String clave;
    private int cicloInicio;
    private int idcarrera;
    private Integer idinstitucion;
    private String idInstructor;
    private String descCarrera;

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getCicloInicio() {
        return cicloInicio;
    }

    public void setCicloInicio(int cicloInicio) {
        this.cicloInicio = cicloInicio;
    }

    public int getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(int idcarrera) {
        this.idcarrera = idcarrera;
    }

    public Integer getIdinstitucion() {
        return idinstitucion;
    }

    public void setIdinstitucion(Integer idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    public String getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(String idInstructor) {
        this.idInstructor = idInstructor;
    }

    public String getDescCarrera() {
        return descCarrera;
    }

    public void setDescCarrera(String descCarrera) {
        this.descCarrera = descCarrera;
    }
    
    
}
