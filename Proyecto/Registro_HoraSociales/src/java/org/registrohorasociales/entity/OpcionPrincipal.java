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
@Table(name = "opcion_principal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpcionPrincipal.findAll", query = "SELECT o FROM OpcionPrincipal o")
    , @NamedQuery(name = "OpcionPrincipal.findById", query = "SELECT o FROM OpcionPrincipal o WHERE o.id = :id")
    , @NamedQuery(name = "OpcionPrincipal.findByDescripcion", query = "SELECT o FROM OpcionPrincipal o WHERE o.descripcion = :descripcion")
    , @NamedQuery(name = "OpcionPrincipal.findByMenuIcon", query = "SELECT o FROM OpcionPrincipal o WHERE o.menuIcon = :menuIcon")
    , @NamedQuery(name = "OpcionPrincipal.findByStatus", query = "SELECT o FROM OpcionPrincipal o WHERE o.status = :status")})
public class OpcionPrincipal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "menu_icon")
    private String menuIcon;
    @Size(max = 255)
    @Column(name = "status")
    private String status;

    public OpcionPrincipal() {
    }

    public OpcionPrincipal(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpcionPrincipal)) {
            return false;
        }
        OpcionPrincipal other = (OpcionPrincipal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.OpcionPrincipal[ id=" + id + " ]";
    }
    
}
