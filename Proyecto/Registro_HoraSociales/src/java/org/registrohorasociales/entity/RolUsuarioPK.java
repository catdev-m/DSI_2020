/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author denisse_mejia
 */
@Embeddable
public class RolUsuarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usr")
    private String usr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rol")
    private int idRol;

    public RolUsuarioPK() {
    }

    public RolUsuarioPK(String usr, int idRol) {
        this.usr = usr;
        this.idRol = idRol;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usr != null ? usr.hashCode() : 0);
        hash += (int) idRol;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolUsuarioPK)) {
            return false;
        }
        RolUsuarioPK other = (RolUsuarioPK) object;
        if ((this.usr == null && other.usr != null) || (this.usr != null && !this.usr.equals(other.usr))) {
            return false;
        }
        if (this.idRol != other.idRol) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.RolUsuarioPK[ usr=" + usr + ", idRol=" + idRol + " ]";
    }
    
}
