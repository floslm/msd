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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pfares
 */
@Entity
@Table(name = "Type", catalog = "offreDemande", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nomType"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Type.findAll", query = "SELECT t FROM Type t"),
    @NamedQuery(name = "Type.findByIdType", query = "SELECT t FROM Type t WHERE t.idType = :idType"),
    @NamedQuery(name = "Type.findByNomType", query = "SELECT t FROM Type t WHERE t.nomType = :nomType")})
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idType", nullable = false)
    private Integer idType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomType", nullable = false, length = 45)
    private String nomType;
    @Lob
    @Size(max = 65535)
    @Column(name = "descriptionType", length = 65535)
    private String descriptionType;
    @JoinColumn(name = "forDomain", referencedColumnName = "idDomain", nullable = false)
    @ManyToOne(optional = false)
    private Domain forDomain;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private Collection<CritereOffrant> critereOffrantCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private Collection<CritereDemandeur> critereDemandeurCollection;

    public Type() {
    }

    public Type(Integer idType) {
        this.idType = idType;
    }

    public Type(Integer idType, String nomType) {
        this.idType = idType;
        this.nomType = nomType;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public String getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(String descriptionType) {
        this.descriptionType = descriptionType;
    }

    public Domain getForDomain() {
        return forDomain;
    }

    public void setForDomain(Domain forDomain) {
        this.forDomain = forDomain;
    }

    @XmlTransient
    public Collection<CritereOffrant> getCritereOffrantCollection() {
        return critereOffrantCollection;
    }

    public void setCritereOffrantCollection(Collection<CritereOffrant> critereOffrantCollection) {
        this.critereOffrantCollection = critereOffrantCollection;
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
        hash += (idType != null ? idType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Type)) {
            return false;
        }
        Type other = (Type) object;
        if ((this.idType == null && other.idType != null) || (this.idType != null && !this.idType.equals(other.idType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.cofares.Type[ Type=" + nomType + " ]";
    }
    
}
