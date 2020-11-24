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
@Table(name = "finalizado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Finalizado.findAll", query = "SELECT f FROM Finalizado f")
    , @NamedQuery(name = "Finalizado.findByDue", query = "SELECT f FROM Finalizado f WHERE f.due = :due")
    , @NamedQuery(name = "Finalizado.findByNombres", query = "SELECT f FROM Finalizado f WHERE f.nombres = :nombres")
    , @NamedQuery(name = "Finalizado.findByApellidos", query = "SELECT f FROM Finalizado f WHERE f.apellidos = :apellidos")
    , @NamedQuery(name = "Finalizado.findByCarrera", query = "SELECT f FROM Finalizado f WHERE f.carrera = :carrera")})
public class Finalizado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "due")
    private String due;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "carrera")
    private int carrera;

    public Finalizado() {
    }

    public Finalizado(String due) {
        this.due = due;
    }

    public Finalizado(String due, String nombres, String apellidos, int carrera) {
        this.due = due;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.carrera = carrera;
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

    public int getCarrera() {
        return carrera;
    }

    public void setCarrera(int carrera) {
        this.carrera = carrera;
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
        if (!(object instanceof Finalizado)) {
            return false;
        }
        Finalizado other = (Finalizado) object;
        if ((this.due == null && other.due != null) || (this.due != null && !this.due.equals(other.due))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.Finalizado[ due=" + due + " ]";
    }
    
}
