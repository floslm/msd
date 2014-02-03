/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pfares
 */
@Entity
@Table(name = "CritereDemandeur", catalog = "supplyDemand", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CritereDemandeur.findAll", query = "SELECT c FROM CritereDemandeur c"),
    @NamedQuery(name = "CritereDemandeur.findByIdDemandeur", query = "SELECT c FROM CritereDemandeur c WHERE c.critereDemandeurPK.idDemandeur = :idDemandeur"),
    @NamedQuery(name = "CritereDemandeur.findByIdtType", query = "SELECT c FROM CritereDemandeur c WHERE c.critereDemandeurPK.idtType = :idtType"),
    @NamedQuery(name = "CritereDemandeur.findByValeur", query = "SELECT c FROM CritereDemandeur c WHERE c.valeur = :valeur")})
public class CritereDemandeur implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CritereDemandeurPK critereDemandeurPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valeur", nullable = false)
    private int valeur;
    @JoinColumn(name = "idDemandeur", referencedColumnName = "idDemandeur", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Demandeur demandeur;
    @JoinColumn(name = "idtType", referencedColumnName = "idType", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Type type;

    public CritereDemandeur() {
    }

    public CritereDemandeur(CritereDemandeurPK critereDemandeurPK) {
        this.critereDemandeurPK = critereDemandeurPK;
    }

    public CritereDemandeur(CritereDemandeurPK critereDemandeurPK, int valeur) {
        this.critereDemandeurPK = critereDemandeurPK;
        this.valeur = valeur;
    }

    public CritereDemandeur(int idDemandeur, int idtType) {
        this.critereDemandeurPK = new CritereDemandeurPK(idDemandeur, idtType);
    }

    public CritereDemandeurPK getCritereDemandeurPK() {
        return critereDemandeurPK;
    }

    public void setCritereDemandeurPK(CritereDemandeurPK critereDemandeurPK) {
        this.critereDemandeurPK = critereDemandeurPK;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public Demandeur getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(Demandeur demandeur) {
        this.demandeur = demandeur;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (critereDemandeurPK != null ? critereDemandeurPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CritereDemandeur)) {
            return false;
        }
        CritereDemandeur other = (CritereDemandeur) object;
        if ((this.critereDemandeurPK == null && other.critereDemandeurPK != null) || (this.critereDemandeurPK != null && !this.critereDemandeurPK.equals(other.critereDemandeurPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[Demandeur=" + demandeur + " Type="+ type+ "]";
    }
    
}
