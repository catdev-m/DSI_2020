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
@Table(name = "solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s")
    , @NamedQuery(name = "Solicitud.findByDue", query = "SELECT s FROM Solicitud s WHERE s.due = :due")
    , @NamedQuery(name = "Solicitud.findByApellido", query = "SELECT s FROM Solicitud s WHERE s.apellido = :apellido")
    , @NamedQuery(name = "Solicitud.findByCiclo", query = "SELECT s FROM Solicitud s WHERE s.ciclo = :ciclo")
    , @NamedQuery(name = "Solicitud.findByIdcarrera", query = "SELECT s FROM Solicitud s WHERE s.idcarrera = :idcarrera")
    , @NamedQuery(name = "Solicitud.findByNombre", query = "SELECT s FROM Solicitud s WHERE s.nombre = :nombre")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "due")
    private String due;
    @Size(max = 255)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "ciclo")
    private Short ciclo;
    @Column(name = "idcarrera")
    private Integer idcarrera;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;

    public Solicitud() {
    }

    public Solicitud(String due) {
        this.due = due;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Short getCiclo() {
        return ciclo;
    }

    public void setCiclo(Short ciclo) {
        this.ciclo = ciclo;
    }

    public Integer getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(Integer idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.due == null && other.due != null) || (this.due != null && !this.due.equals(other.due))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.Solicitud[ due=" + due + " ]";
    }
    
}
