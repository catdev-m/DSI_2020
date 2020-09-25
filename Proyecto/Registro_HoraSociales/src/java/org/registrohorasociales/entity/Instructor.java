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
@Table(name = "instructor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instructor.findAll", query = "SELECT i FROM Instructor i")
    , @NamedQuery(name = "Instructor.findById", query = "SELECT i FROM Instructor i WHERE i.id = :id")
    , @NamedQuery(name = "Instructor.findByFirstName", query = "SELECT i FROM Instructor i WHERE i.firstName = :firstName")
    , @NamedQuery(name = "Instructor.findBySecondName", query = "SELECT i FROM Instructor i WHERE i.secondName = :secondName")
    , @NamedQuery(name = "Instructor.findByLastName", query = "SELECT i FROM Instructor i WHERE i.lastName = :lastName")
    , @NamedQuery(name = "Instructor.findBySecondLastName", query = "SELECT i FROM Instructor i WHERE i.secondLastName = :secondLastName")
    , @NamedQuery(name = "Instructor.findByStatus", query = "SELECT i FROM Instructor i WHERE i.status = :status")
    , @NamedQuery(name = "Instructor.findByIdEscuela", query = "SELECT i FROM Instructor i WHERE i.idEscuela = :idEscuela")})
public class Instructor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 100)
    @Column(name = "second_name")
    private String secondName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 100)
    @Column(name = "second_last_name")
    private String secondLastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "id_escuela")
    private String idEscuela;

    public Instructor() {
    }

    public Instructor(String id) {
        this.id = id;
    }

    public Instructor(String id, String firstName, String lastName, String status, String idEscuela) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.idEscuela = idEscuela;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(String idEscuela) {
        this.idEscuela = idEscuela;
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
        if (!(object instanceof Instructor)) {
            return false;
        }
        Instructor other = (Instructor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.Instructor[ id=" + id + " ]";
    }
    
}
