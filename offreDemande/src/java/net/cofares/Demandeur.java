/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pfares
 */
@Entity
@Table(name = "Demandeur", catalog = "offreDemande", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demandeur.findAll", query = "SELECT d FROM Demandeur d"),
    @NamedQuery(name = "Demandeur.findByIdDemandeur", query = "SELECT d FROM Demandeur d WHERE d.idDemandeur = :idDemandeur"),
    @NamedQuery(name = "Demandeur.findByInformations", query = "SELECT d FROM Demandeur d WHERE d.informations = :informations")})
public class Demandeur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDemandeur", nullable = false)
    private Integer idDemandeur;
    @Size(max = 45)
    @Column(name = "informations", length = 45)
    private String informations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "demandeur")
    private Collection<CritereDemandeur> critereDemandeurCollection;

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
    public Collection<CritereDemandeur> getCritereDemandeurCollection() {
        return critereDemandeurCollection;
    }

    public void setCritereDemandeurCollection(Collection<CritereDemandeur> critereDemandeurCollection) {
        this.critereDemandeurCollection = critereDemandeurCollection;
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
        return "[Demandeur=" + informations + " ]";
    }
    
}
