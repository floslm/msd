/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pfares
 */
@Entity
@Table(name = "Profil", catalog = "offreDemande", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profil.findAll", query = "SELECT p FROM Profil p"),
    @NamedQuery(name = "Profil.findByIdProfil", query = "SELECT p FROM Profil p WHERE p.idProfil = :idProfil"),
    @NamedQuery(name = "Profil.findByDescriptionprofil", query = "SELECT p FROM Profil p WHERE p.descriptionprofil = :descriptionprofil"),
    @NamedQuery(name = "Profil.findByTypeProfil", query = "SELECT p FROM Profil p WHERE p.typeProfil = :typeProfil")})
public class Profil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProfil", nullable = false)
    private Integer idProfil;
    @Size(max = 45)
    @Column(name = "description", length = 45)
    private String descriptionprofil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "typeProfil", nullable = false)
    private Character typeProfil;
    @OneToMany(mappedBy = "pourProfil")
    private List<Categories> categoriesList;

    public Profil() {
    }

    public Profil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public Profil(Integer idProfil, Character typeProfil) {
        this.idProfil = idProfil;
        this.typeProfil = typeProfil;
    }

    public Integer getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public String getDescriptionprofil() {
        return descriptionprofil;
    }

    public void setDescriptionprofil(String descriptionprofil) {
        this.descriptionprofil = descriptionprofil;
    }

    public Character getTypeProfil() {
        return typeProfil;
    }

    public void setTypeProfil(Character typeProfil) {
        this.typeProfil = typeProfil;
    }

    @XmlTransient
    public List<Categories> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfil != null ? idProfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profil)) {
            return false;
        }
        Profil other = (Profil) object;
        return this.idProfil.equals(other.idProfil);
    }

    @Override
    public String toString() {
        return "[Profil:" + descriptionprofil + "]";
    }
    
}
