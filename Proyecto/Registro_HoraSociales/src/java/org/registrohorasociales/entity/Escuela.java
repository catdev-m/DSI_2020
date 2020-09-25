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
 * @author denisse_mejia
 */
@Entity
@Table(name = "escuela")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Escuela.findAll", query = "SELECT e FROM Escuela e")
    , @NamedQuery(name = "Escuela.findByIdEscuela", query = "SELECT e FROM Escuela e WHERE e.idEscuela = :idEscuela")
    , @NamedQuery(name = "Escuela.findByEscuela", query = "SELECT e FROM Escuela e WHERE e.escuela = :escuela")})
public class Escuela implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "id_escuela")
    private String idEscuela;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "escuela")
    private String escuela;

    public Escuela() {
    }

    public Escuela(String idEscuela) {
        this.idEscuela = idEscuela;
    }

    public Escuela(String idEscuela, String escuela) {
        this.idEscuela = idEscuela;
        this.escuela = escuela;
    }

    public String getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(String idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEscuela != null ? idEscuela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Escuela)) {
            return false;
        }
        Escuela other = (Escuela) object;
        if ((this.idEscuela == null && other.idEscuela != null) || (this.idEscuela != null && !this.idEscuela.equals(other.idEscuela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.Escuela[ idEscuela=" + idEscuela + " ]";
    }
    
}
