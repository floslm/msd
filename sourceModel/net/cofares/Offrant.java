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
import javax.persistence.Lob;
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
@Table(name = "Offrant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Offrant.findAll", query = "SELECT o FROM Offrant o")})
public class Offrant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOffrant")
    private Integer idOffrant;
    @Lob
    @Size(max = 65535)
    @Column(name = "informations")
    private String informations;
    @JoinTable(name = "CriteresOffrant", joinColumns = {
        @JoinColumn(name = "idOffrant", referencedColumnName = "idOffrant")}, inverseJoinColumns = {
        @JoinColumn(name = "idValeurType", referencedColumnName = "idValeurType")})
    @ManyToMany
    private Set<ValeurType> valeurTypeSet;

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
    public Set<ValeurType> getValeurTypeSet() {
        return valeurTypeSet;
    }

    public void setValeurTypeSet(Set<ValeurType> valeurTypeSet) {
        this.valeurTypeSet = valeurTypeSet;
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
        return "net.cofares.Offrant[ idOffrant=" + idOffrant + " ]";
    }
    
}
