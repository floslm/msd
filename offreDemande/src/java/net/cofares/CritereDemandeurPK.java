/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pfares
 */
@Embeddable
public class CritereDemandeurPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idDemandeur", nullable = false)
    private int idDemandeur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtType", nullable = false)
    private int idtType;

    public CritereDemandeurPK() {
    }

    public CritereDemandeurPK(int idDemandeur, int idtType) {
        this.idDemandeur = idDemandeur;
        this.idtType = idtType;
    }

    public int getIdDemandeur() {
        return idDemandeur;
    }

    public void setIdDemandeur(int idDemandeur) {
        this.idDemandeur = idDemandeur;
    }

    public int getIdtType() {
        return idtType;
    }

    public void setIdtType(int idtType) {
        this.idtType = idtType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDemandeur;
        hash += (int) idtType;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CritereDemandeurPK)) {
            return false;
        }
        CritereDemandeurPK other = (CritereDemandeurPK) object;
        if (this.idDemandeur != other.idDemandeur) {
            return false;
        }
        if (this.idtType != other.idtType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.cofares.CritereDemandeurPK[ idDemandeur=" + idDemandeur + ", idtType=" + idtType + " ]";
    }
    
}
