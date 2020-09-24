/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e")
    , @NamedQuery(name = "Estudiante.findByDue", query = "SELECT e FROM Estudiante e WHERE e.due = :due")
    , @NamedQuery(name = "Estudiante.findByNombres", query = "SELECT e FROM Estudiante e WHERE e.nombres = :nombres")
    , @NamedQuery(name = "Estudiante.findByApellidos", query = "SELECT e FROM Estudiante e WHERE e.apellidos = :apellidos")
    , @NamedQuery(name = "Estudiante.findByCicloInicio", query = "SELECT e FROM Estudiante e WHERE e.cicloInicio = :cicloInicio")
    , @NamedQuery(name = "Estudiante.findByClave", query = "SELECT e FROM Estudiante e WHERE e.clave = :clave")
    , @NamedQuery(name = "Estudiante.findByCorreo", query = "SELECT e FROM Estudiante e WHERE e.correo = :correo")
    , @NamedQuery(name = "Estudiante.findByIdcarrera", query = "SELECT e FROM Estudiante e WHERE e.idcarrera = :idcarrera")
    , @NamedQuery(name = "Estudiante.findByIdInstructor", query = "SELECT e FROM Estudiante e WHERE e.idInstructor = :idInstructor")
    , @NamedQuery(name = "Estudiante.findByIdinstitucion", query = "SELECT e FROM Estudiante e WHERE e.idinstitucion = :idinstitucion")})
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DUE")
    private String due;
    @Size(max = 255)
    @Column(name = "NOMBRES")
    private String nombres;
    @Size(max = 255)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "CICLO_INICIO")
    private Integer cicloInicio;
    @Size(max = 255)
    @Column(name = "CLAVE")
    private String clave;
    @Size(max = 255)
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "IDCARRERA")
    private Integer idcarrera;
    @Size(max = 100)
    @Column(name = "idInstructor")
    private String idInstructor;
    @Column(name = "IDINSTITUCION")
    private Integer idinstitucion;

    public Estudiante() {
    }

    public Estudiante(String due) {
        this.due = due;
    }

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

    public Integer getCicloInicio() {
        return cicloInicio;
    }

    public void setCicloInicio(Integer cicloInicio) {
        this.cicloInicio = cicloInicio;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(Integer idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(String idInstructor) {
        this.idInstructor = idInstructor;
    }

    public Integer getIdinstitucion() {
        return idinstitucion;
    }

    public void setIdinstitucion(Integer idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (due != null ? due.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.due == null && other.due != null) || (this.due != null && !this.due.equals(other.due))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.Estudiante[ due=" + due + " ]";
    }
    
}
