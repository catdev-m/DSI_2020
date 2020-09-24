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
    , @NamedQuery(name = "Estudiante.findByCorreo", query = "SELECT e FROM Estudiante e WHERE e.correo = :correo")
    , @NamedQuery(name = "Estudiante.findByClave", query = "SELECT e FROM Estudiante e WHERE e.clave = :clave")
    , @NamedQuery(name = "Estudiante.findByCicloInicio", query = "SELECT e FROM Estudiante e WHERE e.cicloInicio = :cicloInicio")
    , @NamedQuery(name = "Estudiante.findByIdcarrera", query = "SELECT e FROM Estudiante e WHERE e.idcarrera = :idcarrera")
    , @NamedQuery(name = "Estudiante.findByIdinstitucion", query = "SELECT e FROM Estudiante e WHERE e.idinstitucion = :idinstitucion")
    , @NamedQuery(name = "Estudiante.findByIdInstructor", query = "SELECT e FROM Estudiante e WHERE e.idInstructor = :idInstructor")})
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "DUE")
    private String due;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "CORREO")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "CLAVE")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CICLO_INICIO")
    private int cicloInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCARRERA")
    private int idcarrera;
    @Column(name = "IDINSTITUCION")
    private Integer idinstitucion;
    @Column(name = "idInstructor")
    private Integer idInstructor;

    public Estudiante() {
    }

    public Estudiante(String due) {
        this.due = due;
    }

    public Estudiante(String due, String nombres, String apellidos, String correo, String clave, int cicloInicio, int idcarrera) {
        this.due = due;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.clave = clave;
        this.cicloInicio = cicloInicio;
        this.idcarrera = idcarrera;
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

    public Integer getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Integer idInstructor) {
        this.idInstructor = idInstructor;
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
