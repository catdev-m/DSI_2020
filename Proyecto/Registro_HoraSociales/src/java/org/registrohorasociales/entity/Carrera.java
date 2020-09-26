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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "carrera")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrera.findAll", query = "SELECT c FROM Carrera c")
    , @NamedQuery(name = "Carrera.findByIdcarrera", query = "SELECT c FROM Carrera c WHERE c.idcarrera = :idcarrera")
    , @NamedQuery(name = "Carrera.findByCodigocarrera", query = "SELECT c FROM Carrera c WHERE c.codigocarrera = :codigocarrera")
    , @NamedQuery(name = "Carrera.findByNombrecarrera", query = "SELECT c FROM Carrera c WHERE c.nombrecarrera = :nombrecarrera")
    , @NamedQuery(name = "Carrera.findByCodigofacultad", query = "SELECT c FROM Carrera c WHERE c.codigofacultad = :codigofacultad")
    , @NamedQuery(name = "Carrera.findByNombrefacultad", query = "SELECT c FROM Carrera c WHERE c.nombrefacultad = :nombrefacultad")})
public class Carrera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcarrera")
    private Integer idcarrera;
    @Size(max = 255)
    @Column(name = "codigocarrera")
    private String codigocarrera;
    @Size(max = 255)
    @Column(name = "nombrecarrera")
    private String nombrecarrera;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "codigofacultad")
    private String codigofacultad;
    @Basic(optional = false)
    @Size(min = 1, max = 250)
    @Column(name = "nombrefacultad")
    private String nombrefacultad;

    public Carrera() {
    }

    public Carrera(Integer idcarrera) {
        this.idcarrera = idcarrera;
    }

    public Carrera(Integer idcarrera, String codigofacultad, String nombrefacultad) {
        this.idcarrera = idcarrera;
        this.codigofacultad = codigofacultad;
        this.nombrefacultad = nombrefacultad;
    }

    public Integer getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(Integer idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getCodigocarrera() {
        return codigocarrera;
    }

    public void setCodigocarrera(String codigocarrera) {
        this.codigocarrera = codigocarrera;
    }

    public String getNombrecarrera() {
        return nombrecarrera;
    }

    public void setNombrecarrera(String nombrecarrera) {
        this.nombrecarrera = nombrecarrera;
    }

    public String getCodigofacultad() {
        return codigofacultad;
    }

    public void setCodigofacultad(String codigofacultad) {
        this.codigofacultad = codigofacultad;
    }

    public String getNombrefacultad() {
        return nombrefacultad;
    }

    public void setNombrefacultad(String nombrefacultad) {
        this.nombrefacultad = nombrefacultad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcarrera != null ? idcarrera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrera)) {
            return false;
        }
        Carrera other = (Carrera) object;
        if ((this.idcarrera == null && other.idcarrera != null) || (this.idcarrera != null && !this.idcarrera.equals(other.idcarrera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.Carrera[ idcarrera=" + idcarrera + " ]";
    }
    
}
