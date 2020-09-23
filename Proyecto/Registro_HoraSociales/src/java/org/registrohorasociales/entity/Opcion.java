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
@Table(name = "opcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o")
    , @NamedQuery(name = "Opcion.findById", query = "SELECT o FROM Opcion o WHERE o.id = :id")
    , @NamedQuery(name = "Opcion.findByDesc", query = "SELECT o FROM Opcion o WHERE o.desc = :desc")
    , @NamedQuery(name = "Opcion.findByStatus", query = "SELECT o FROM Opcion o WHERE o.status = :status")
    , @NamedQuery(name = "Opcion.findByMenuIcon", query = "SELECT o FROM Opcion o WHERE o.menuIcon = :menuIcon")
    , @NamedQuery(name = "Opcion.findByUrl", query = "SELECT o FROM Opcion o WHERE o.url = :url")
    , @NamedQuery(name = "Opcion.findByIdOpcPpal", query = "SELECT o FROM Opcion o WHERE o.idOpcPpal = :idOpcPpal")})
public class Opcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "desc")
    private String desc;
    @Size(max = 1)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "menu_icon")
    private String menuIcon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_opc_ppal")
    private int idOpcPpal;

    public Opcion() {
    }

    public Opcion(Integer id) {
        this.id = id;
    }

    public Opcion(Integer id, String menuIcon, String url, int idOpcPpal) {
        this.id = id;
        this.menuIcon = menuIcon;
        this.url = url;
        this.idOpcPpal = idOpcPpal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdOpcPpal() {
        return idOpcPpal;
    }

    public void setIdOpcPpal(int idOpcPpal) {
        this.idOpcPpal = idOpcPpal;
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
        if (!(object instanceof Opcion)) {
            return false;
        }
        Opcion other = (Opcion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.Opcion[ id=" + id + " ]";
    }
    
}
