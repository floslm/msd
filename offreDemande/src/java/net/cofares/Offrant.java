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
import javax.persistence.Lob;
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
@Table(name = "Offrant", catalog = "offreDemande", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Offrant.findAll", query = "SELECT o FROM Offrant o"),
    @NamedQuery(name = "Offrant.findByIdOffrant", query = "SELECT o FROM Offrant o WHERE o.idOffrant = :idOffrant")})
public class Offrant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOffrant", nullable = false)
    private Integer idOffrant;
    @Lob
    @Size(max = 65535)
    @Column(name = "informations", length = 65535)
    private String informations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "offrant")
    private Collection<CritereOffrant> critereOffrantCollection;

    public Offrant() {
    }

    public Offrant(Integer idOffrant) {
        this.idOffrant = idOffrant;
    }

    public Integer getIdOffrant() {
        return idOffrant;
    }

    public void setIdOffrant(Integer idOffrant) {
        this.idOffrant = idOffrant;
    }

    public String getInformations() {
        return informations;
    }

    public void setInformations(String informations) {
        this.informations = informations;
    }

    @XmlTransient
    public Collection<CritereOffrant> getCritereOffrantCollection() {
        return critereOffrantCollection;
    }

    public void setCritereOffrantCollection(Collection<CritereOffrant> critereOffrantCollection) {
        this.critereOffrantCollection = critereOffrantCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOffrant != null ? idOffrant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Offrant)) {
            return false;
        }
        Offrant other = (Offrant) object;
        if ((this.idOffrant == null && other.idOffrant != null) || (this.idOffrant != null && !this.idOffrant.equals(other.idOffrant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[Offrant:" + informations + "]";
    }
    
}
