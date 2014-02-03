/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
 * @author pascalfares
 */
@Entity
@Table(name = "Domain")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Domain.findAll", query = "SELECT d FROM Domain d")})
public class Domain implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idDomain")
    private Integer idDomain;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomDomain")
    private String nomDomain;
    @Lob
    @Size(max = 65535)
    @Column(name = "descriptionDomain")
    private String descriptionDomain;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "forDomain")
    private Set<Type> typeSet;

    public Domain() {
    }

    public Domain(Integer idDomain) {
        this.idDomain = idDomain;
    }

    public Domain(Integer idDomain, String nomDomain) {
        this.idDomain = idDomain;
        this.nomDomain = nomDomain;
    }

    public Integer getIdDomain() {
        return idDomain;
    }

    public void setIdDomain(Integer idDomain) {
        this.idDomain = idDomain;
    }

    public String getNomDomain() {
        return nomDomain;
    }

    public void setNomDomain(String nomDomain) {
        this.nomDomain = nomDomain;
    }

    public String getDescriptionDomain() {
        return descriptionDomain;
    }

    public void setDescriptionDomain(String descriptionDomain) {
        this.descriptionDomain = descriptionDomain;
    }

    @XmlTransient
    public Set<Type> getTypeSet() {
        return typeSet;
    }

    public void setTypeSet(Set<Type> typeSet) {
        this.typeSet = typeSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDomain != null ? idDomain.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Domain)) {
            return false;
        }
        Domain other = (Domain) object;
        if ((this.idDomain == null && other.idDomain != null) || (this.idDomain != null && !this.idDomain.equals(other.idDomain))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.cofares.Domain[ idDomain=" + idDomain + " ]";
    }
    
}
