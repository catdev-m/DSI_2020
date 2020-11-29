/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author balmore
 */
@Entity
@Table(name = "relacion_estudiante-proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelacionEstudianteProyecto.findAll", query = "SELECT r FROM RelacionEstudianteProyecto r")
    , @NamedQuery(name = "RelacionEstudianteProyecto.findByIdrelacionEP", query = "SELECT r FROM RelacionEstudianteProyecto r WHERE r.idrelacionEP = :idrelacionEP")
    , @NamedQuery(name = "RelacionEstudianteProyecto.findByCarnetEstudiante", query = "SELECT r FROM RelacionEstudianteProyecto r WHERE r.carnetEstudiante = :carnetEstudiante")
    , @NamedQuery(name = "RelacionEstudianteProyecto.findByIdProyecto", query = "SELECT r FROM RelacionEstudianteProyecto r WHERE r.idProyecto = :idProyecto")
    , @NamedQuery(name = "RelacionEstudianteProyecto.findByFechaInicio", query = "SELECT r FROM RelacionEstudianteProyecto r WHERE r.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "RelacionEstudianteProyecto.findByFechaFinal", query = "SELECT r FROM RelacionEstudianteProyecto r WHERE r.fechaFinal = :fechaFinal")})
public class RelacionEstudianteProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_relacionEP")
    private Integer idrelacionEP;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "carnet_estudiante")
    private String carnetEstudiante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proyecto")
    private int idProyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    public RelacionEstudianteProyecto() {
    }

    public RelacionEstudianteProyecto(Integer idrelacionEP) {
        this.idrelacionEP = idrelacionEP;
    }

    public RelacionEstudianteProyecto(Integer idrelacionEP, String carnetEstudiante, int idProyecto, Date fechaInicio, Date fechaFinal) {
        this.idrelacionEP = idrelacionEP;
        this.carnetEstudiante = carnetEstudiante;
        this.idProyecto = idProyecto;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

    public Integer getIdrelacionEP() {
        return idrelacionEP;
    }

    public void setIdrelacionEP(Integer idrelacionEP) {
        this.idrelacionEP = idrelacionEP;
    }

    public String getCarnetEstudiante() {
        return carnetEstudiante;
    }

    public void setCarnetEstudiante(String carnetEstudiante) {
        this.carnetEstudiante = carnetEstudiante;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrelacionEP != null ? idrelacionEP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelacionEstudianteProyecto)) {
            return false;
        }
        RelacionEstudianteProyecto other = (RelacionEstudianteProyecto) object;
        if ((this.idrelacionEP == null && other.idrelacionEP != null) || (this.idrelacionEP != null && !this.idrelacionEP.equals(other.idrelacionEP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.registrohorasociales.entity.RelacionEstudianteProyecto[ idrelacionEP=" + idrelacionEP + " ]";
    }
    
}
