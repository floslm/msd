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
public class CritereOffrantPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOffrant", nullable = false)
    private int idOffrant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtType", nullable = false)
    private int idtType;

    public CritereOffrantPK() {
    }

    public CritereOffrantPK(int idOffrant, int idtType) {
        this.idOffrant = idOffrant;
        this.idtType = idtType;
    }

    public int getIdOffrant() {
        return idOffrant;
    }

    public void setIdOffrant(int idOffrant) {
        this.idOffrant = idOffrant;
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
        hash += (int) idOffrant;
        hash += (int) idtType;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CritereOffrantPK)) {
            return false;
        }
        CritereOffrantPK other = (CritereOffrantPK) object;
        if (this.idOffrant != other.idOffrant) {
            return false;
        }
        if (this.idtType != other.idtType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.cofares.CritereOffrantPK[ idOffrant=" + idOffrant + ", idtType=" + idtType + " ]";
    }
    
}
