/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "Semantique", catalog = "offreDemande", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semantique.findAll", query = "SELECT s FROM Semantique s"),
    @NamedQuery(name = "Semantique.findByIdSemantique", query = "SELECT s FROM Semantique s WHERE s.idSemantique = :idSemantique")})
public class Semantique implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSemantique", nullable = false)
    private Integer idSemantique;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", length = 65535)
    private String description;
    @OneToMany(mappedBy = "semantique")
    private Collection<Categories> categoriesCollection;

    public Semantique() {
    }

    public Semantique(Integer idSemantique) {
        this.idSemantique = idSemantique;
    }

    public Integer getIdSemantique() {
        return idSemantique;
    }

    public void setIdSemantique(Integer idSemantique) {
        this.idSemantique = idSemantique;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (idSemantique != null ? idSemantique.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Semantique)) {
            return false;
        }
        Semantique other = (Semantique) object;
        if ((this.idSemantique == null && other.idSemantique != null) || (this.idSemantique != null && !this.idSemantique.equals(other.idSemantique))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[Semantique:" + idSemantique + "]";
    }
    
}
