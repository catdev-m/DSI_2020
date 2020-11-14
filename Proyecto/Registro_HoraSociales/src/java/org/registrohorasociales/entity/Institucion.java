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
    , @NamedQuery(name = "Institucion.findByResInstitucion", query = "SELECT i FROM Institucion i WHERE i.resInstitucion = :resInstitucion")
    , @NamedQuery(name = "Institucion.findByStatusInstitucion", query = "SELECT i FROM Institucion i WHERE i.statusInstitucion = :statusInstitucion")})
public class Institucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Size(min = 1, max = 70)
    @Column(name = "enc_institucion")
    private String encInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "tel_institucion")
    private String telInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "correo_institucion")
    private String correoInstitucion;
    @Size(max = 255)
    @Column(name = "rs_institucion")
    private String rsInstitucion;
    @Size(max = 255)
    @Column(name = "res_institucion")
    private String resInstitucion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status_institucion")
    private Character statusInstitucion;

    public Institucion() {
    }

    public Institucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public Institucion(Integer idInstitucion, String nomInstitucion, String encInstitucion, String telInstitucion, String correoInstitucion, Character statusInstitucion) {
        this.idInstitucion = idInstitucion;
        this.nomInstitucion = nomInstitucion;
        this.encInstitucion = encInstitucion;
        this.telInstitucion = telInstitucion;
        this.correoInstitucion = correoInstitucion;
        this.statusInstitucion = statusInstitucion;
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

    public String getTelInstitucion() {
        return telInstitucion;
    }

    public void setTelInstitucion(String telInstitucion) {
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

    public String getResInstitucion() {
        return resInstitucion;
    }

    public void setResInstitucion(String resInstitucion) {
        this.resInstitucion = resInstitucion;
    }

    public Character getStatusInstitucion() {
        return statusInstitucion;
    }

    public void setStatusInstitucion(Character statusInstitucion) {
        this.statusInstitucion = statusInstitucion;
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
        return !((this.idInstitucion == null && other.idInstitucion != null) || (this.idInstitucion != null && !this.idInstitucion.equals(other.idInstitucion)));
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.Institucion[ idInstitucion=" + idInstitucion + " ]";
    }
    
}
