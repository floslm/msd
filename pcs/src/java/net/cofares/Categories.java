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
import javax.persistence.JoinColumn;
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
@Table(name = "Categories", catalog = "offreDemande", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nomCategorie"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categories.findAll", query = "SELECT c FROM Categories c"),
    @NamedQuery(name = "Categories.findByIdCategories", query = "SELECT c FROM Categories c WHERE c.idCategories = :idCategories"),
    @NamedQuery(name = "Categories.findByNomCategorie", query = "SELECT c FROM Categories c WHERE c.nomCategorie = :nomCategorie"),
    @NamedQuery(name = "Categories.findByValeur", query = "SELECT c FROM Categories c WHERE c.valeur = :valeur")})
public class Categories implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCategories", nullable = false)
    private Integer idCategories;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomCategorie", nullable = false, length = 45)
    private String nomCategorie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valeur", nullable = false)
    private int valeur;
    
    @JoinColumn(name = "pourProfil", referencedColumnName = "idProfil")
    @ManyToOne
    private Profil pourProfil;
    @OneToMany(mappedBy = "categorieParente")
    private Collection<Categories> categoriesCollection;
    @JoinColumn(name = "categorieParente", referencedColumnName = "idCategories")
    @ManyToOne
    private Categories categorieParente;
    @JoinColumn(name = "semantique", referencedColumnName = "idSemantique")
    @ManyToOne
    private Semantique semantique;

    public Categories() {
    }

    public Categories(Integer idCategories) {
        this.idCategories = idCategories;
    }

    public Categories(Integer idCategories, String nomCategorie, int valeur) {
        this.idCategories = idCategories;
        this.nomCategorie = nomCategorie;
        this.valeur = valeur;
    }

    public Integer getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(Integer idCategories) {
        this.idCategories = idCategories;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    
    public Profil getPourProfil() {
        return pourProfil;
    }

    public void setPourProfil(Profil pourProfil) {
        this.pourProfil = pourProfil;
    }

    @XmlTransient
    public Collection<Categories> getCategoriesCollection() {
        return categoriesCollection;
    }

    public void setCategoriesCollection(Collection<Categories> categoriesCollection) {
        this.categoriesCollection = categoriesCollection;
    }

    public Categories getCategorieParente() {
        return categorieParente;
    }

    public void setCategorieParente(Categories categorieParente) {
        this.categorieParente = categorieParente;
    }

    public Semantique getSemantique() {
        return semantique;
    }

    public void setSemantique(Semantique semantique) {
        this.semantique = semantique;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategories != null ? idCategories.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categories)) {
            return false;
        }
        Categories other = (Categories) object;
        if ((this.idCategories == null && other.idCategories != null) || (this.idCategories != null && !this.idCategories.equals(other.idCategories))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.cofares.Categories[ idCategories=" + idCategories + " ]";
    }
    
}
