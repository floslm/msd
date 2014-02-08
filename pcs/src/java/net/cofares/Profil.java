/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @NamedQuery(name = "Profil.findByDescription", query = "SELECT p FROM Profil p WHERE p.description = :description"),
    @NamedQuery(name = "Profil.findByTypeProfil", query = "SELECT p FROM Profil p WHERE p.typeProfil = :typeProfil")})
public class Profil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProfil", nullable = false)
    private Integer idProfil;
    @Size(max = 45)
    @Column(name = "description", length = 45)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "typeProfil", nullable = false)
    private Character typeProfil;
    @OneToMany(mappedBy = "pourProfil")
    private Collection<Categories> categoriesCollection;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Character getTypeProfil() {
        return typeProfil;
    }

    public void setTypeProfil(Character typeProfil) {
        this.typeProfil = typeProfil;
    }

    @XmlTransient
    public Collection<Categories> getCategoriesCollection() {
        return categoriesCollection;
    }

    public void setCategoriesCollection(Collection<Categories> categoriesCollection) {
        this.categoriesCollection = categoriesCollection;
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
        if ((this.idProfil == null && other.idProfil != null) || (this.idProfil != null && !this.idProfil.equals(other.idProfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[Profil:" + idProfil + ":" + description + "]";
    }
    
}
