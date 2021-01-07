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
import javax.persistence.Lob;
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
@Table(name = "archivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Archivo.findAll", query = "SELECT a FROM Archivo a")
    , @NamedQuery(name = "Archivo.findByIdFile", query = "SELECT a FROM Archivo a WHERE a.idFile = :idFile")
    , @NamedQuery(name = "Archivo.findByDescripcion", query = "SELECT a FROM Archivo a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Archivo.findByCarnet", query = "SELECT a FROM Archivo a WHERE a.carnet = :carnet")
    , @NamedQuery(name = "Archivo.findByFecha", query = "SELECT a FROM Archivo a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "Archivo.findByUsrCarga", query = "SELECT a FROM Archivo a WHERE a.usrCarga = :usrCarga")
    , @NamedQuery(name = "Archivo.findByLocked", query = "SELECT a FROM Archivo a WHERE a.locked = :locked")})
public class Archivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_file")
    private Integer idFile;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "carnet")
    private String carnet;
    @Size(max = 100)
    @Column(name = "fecha")
    private String fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "usr_carga")
    private String usrCarga;
    @Basic(optional = false)
    @NotNull
    @Column(name = "locked")
    private int locked;

    public Archivo() {
    }

    public Archivo(Integer idFile) {
        this.idFile = idFile;
    }

    public Archivo(Integer idFile, String url, String descripcion, String carnet, String usrCarga, int locked) {
        this.idFile = idFile;
        this.url = url;
        this.descripcion = descripcion;
        this.carnet = carnet;
        this.usrCarga = usrCarga;
        this.locked = locked;
    }

    public Integer getIdFile() {
        return idFile;
    }

    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsrCarga() {
        return usrCarga;
    }

    public void setUsrCarga(String usrCarga) {
        this.usrCarga = usrCarga;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFile != null ? idFile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Archivo)) {
            return false;
        }
        Archivo other = (Archivo) object;
        if ((this.idFile == null && other.idFile != null) || (this.idFile != null && !this.idFile.equals(other.idFile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.Archivo[ idFile=" + idFile + " ]";
    }
    
}
