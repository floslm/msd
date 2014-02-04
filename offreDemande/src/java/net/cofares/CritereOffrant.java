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
@Table(name = "CritereOffrant", catalog = "offreDemande", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CritereOffrant.findAll", query = "SELECT c FROM CritereOffrant c"),
    @NamedQuery(name = "CritereOffrant.findByIdOffrant", query = "SELECT c FROM CritereOffrant c WHERE c.critereOffrantPK.idOffrant = :idOffrant"),
    @NamedQuery(name = "CritereOffrant.findByIdtType", query = "SELECT c FROM CritereOffrant c WHERE c.critereOffrantPK.idtType = :idtType"),
    @NamedQuery(name = "CritereOffrant.findByValeur", query = "SELECT c FROM CritereOffrant c WHERE c.valeur = :valeur")})
public class CritereOffrant implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CritereOffrantPK critereOffrantPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valeur", nullable = false)
    private int valeur;
    @JoinColumn(name = "idOffrant", referencedColumnName = "idOffrant", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Offrant offrant;
    @JoinColumn(name = "idtType", referencedColumnName = "idType", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Type type;

    public CritereOffrant() {
    }

    public CritereOffrant(CritereOffrantPK critereOffrantPK) {
        this.critereOffrantPK = critereOffrantPK;
    }

    public CritereOffrant(CritereOffrantPK critereOffrantPK, int valeur) {
        this.critereOffrantPK = critereOffrantPK;
        this.valeur = valeur;
    }

    public CritereOffrant(int idOffrant, int idtType) {
        this.critereOffrantPK = new CritereOffrantPK(idOffrant, idtType);
    }

    public CritereOffrantPK getCritereOffrantPK() {
        return critereOffrantPK;
    }

    public void setCritereOffrantPK(CritereOffrantPK critereOffrantPK) {
        this.critereOffrantPK = critereOffrantPK;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public Offrant getOffrant() {
        return offrant;
    }

    public void setOffrant(Offrant offrant) {
        this.offrant = offrant;
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
        hash += (critereOffrantPK != null ? critereOffrantPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CritereOffrant)) {
            return false;
        }
        CritereOffrant other = (CritereOffrant) object;
        if ((this.critereOffrantPK == null && other.critereOffrantPK != null) || (this.critereOffrantPK != null && !this.critereOffrantPK.equals(other.critereOffrantPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[Offrant=" + offrant + " Type="+ type+ "]";
    }
    
}
