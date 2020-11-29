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
@Table(name = "datoscertificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datoscertificacion.findAll", query = "SELECT d FROM Datoscertificacion d")
    , @NamedQuery(name = "Datoscertificacion.findById", query = "SELECT d FROM Datoscertificacion d WHERE d.id = :id")
    , @NamedQuery(name = "Datoscertificacion.findByTitulo", query = "SELECT d FROM Datoscertificacion d WHERE d.titulo = :titulo")
    , @NamedQuery(name = "Datoscertificacion.findByNombre", query = "SELECT d FROM Datoscertificacion d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "Datoscertificacion.findByCargo", query = "SELECT d FROM Datoscertificacion d WHERE d.cargo = :cargo")})
public class Datoscertificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Cargo")
    private String cargo;

    public Datoscertificacion() {
    }

    public Datoscertificacion(Integer id) {
        this.id = id;
    }

    public Datoscertificacion(Integer id, String titulo, String nombre, String cargo) {
        this.id = id;
        this.titulo = titulo;
        this.nombre = nombre;
        this.cargo = cargo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datoscertificacion)) {
            return false;
        }
        Datoscertificacion other = (Datoscertificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.Datoscertificacion[ id=" + id + " ]";
    }
    
}
