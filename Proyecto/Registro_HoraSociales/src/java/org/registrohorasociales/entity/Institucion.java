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
 * @author balmore
 */
@Entity
@Table(name = "institucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Institucion.findAll", query = "SELECT i FROM Institucion i")
    , @NamedQuery(name = "Institucion.findByIdInstitucion", query = "SELECT i FROM Institucion i WHERE i.idInstitucion = :idInstitucion")
    , @NamedQuery(name = "Institucion.findByNomInstitucion", query = "SELECT i FROM Institucion i WHERE i.nomInstitucion = :nomInstitucion")
    , @NamedQuery(name = "Institucion.findByEncInstitucion", query = "SELECT i FROM Institucion i WHERE i.encInstitucion = :encInstitucion")
    , @NamedQuery(name = "Institucion.findByTelInstitucion", query = "SELECT i FROM Institucion i WHERE i.telInstitucion = :telInstitucion")
    , @NamedQuery(name = "Institucion.findByCorreoInstitucion", query = "SELECT i FROM Institucion i WHERE i.correoInstitucion = :correoInstitucion")
    , @NamedQuery(name = "Institucion.findByRsInstitucion", query = "SELECT i FROM Institucion i WHERE i.rsInstitucion = :rsInstitucion")
    , @NamedQuery(name = "Institucion.findByStatus", query = "SELECT i FROM Institucion i WHERE i.status = :status")})
public class Institucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_institucion")
    private Integer idInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "nom_institucion")
    private String nomInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "enc_institucion")
    private String encInstitucion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tel_institucion")
    private int telInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "correo_institucion")
    private String correoInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rs_institucion")
    private String rsInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "res")
    private String res;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "status")
    private String status;

    public Institucion() {
    }

    public Institucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public Institucion(Integer idInstitucion, String nomInstitucion, String encInstitucion, int telInstitucion, String correoInstitucion, String rsInstitucion, String res, String status) {
        this.idInstitucion = idInstitucion;
        this.nomInstitucion = nomInstitucion;
        this.encInstitucion = encInstitucion;
        this.telInstitucion = telInstitucion;
        this.correoInstitucion = correoInstitucion;
        this.rsInstitucion = rsInstitucion;
        this.res = res;
        this.status = status;
    }

    public Integer getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNomInstitucion() {
        return nomInstitucion;
    }

    public void setNomInstitucion(String nomInstitucion) {
        this.nomInstitucion = nomInstitucion;
    }

    public String getEncInstitucion() {
        return encInstitucion;
    }

    public void setEncInstitucion(String encInstitucion) {
        this.encInstitucion = encInstitucion;
    }

    public int getTelInstitucion() {
        return telInstitucion;
    }

    public void setTelInstitucion(int telInstitucion) {
        this.telInstitucion = telInstitucion;
    }

    public String getCorreoInstitucion() {
        return correoInstitucion;
    }

    public void setCorreoInstitucion(String correoInstitucion) {
        this.correoInstitucion = correoInstitucion;
    }

    public String getRsInstitucion() {
        return rsInstitucion;
    }

    public void setRsInstitucion(String rsInstitucion) {
        this.rsInstitucion = rsInstitucion;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstitucion != null ? idInstitucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institucion)) {
            return false;
        }
        Institucion other = (Institucion) object;
        if ((this.idInstitucion == null && other.idInstitucion != null) || (this.idInstitucion != null && !this.idInstitucion.equals(other.idInstitucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.Institucion[ idInstitucion=" + idInstitucion + " ]";
    }
    
}
