/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pascalfares
 */
@Entity
@Table(name = "Demandeur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demandeur.findAll", query = "SELECT d FROM Demandeur d")})
public class Demandeur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idDemandeur")
    private Integer idDemandeur;
    @Size(max = 45)
    @Column(name = "informations")
    private String informations;
    @JoinTable(name = "CriteresDemandeur", joinColumns = {
        @JoinColumn(name = "idDemandeur", referencedColumnName = "idDemandeur")}, inverseJoinColumns = {
        @JoinColumn(name = "idValeurType", referencedColumnName = "idValeurType")})
    @ManyToMany
    private Set<ValeurType> valeurTypeSet;

    public Demandeur() {
    }

    public Demandeur(Integer idDemandeur) {
        this.idDemandeur = idDemandeur;
    }

    public Integer getIdDemandeur() {
        return idDemandeur;
    }

    public void setIdDemandeur(Integer idDemandeur) {
        this.idDemandeur = idDemandeur;
    }

    public String getInformations() {
        return informations;
    }

    public void setInformations(String informations) {
        this.informations = informations;
    }

    @XmlTransient
    public Set<ValeurType> getValeurTypeSet() {
        return valeurTypeSet;
    }

    public void setValeurTypeSet(Set<ValeurType> valeurTypeSet) {
        this.valeurTypeSet = valeurTypeSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDemandeur != null ? idDemandeur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Demandeur)) {
            return false;
        }
        Demandeur other = (Demandeur) object;
        if ((this.idDemandeur == null && other.idDemandeur != null) || (this.idDemandeur != null && !this.idDemandeur.equals(other.idDemandeur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.cofares.Demandeur[ idDemandeur=" + idDemandeur + " ]";
    }
    
}
