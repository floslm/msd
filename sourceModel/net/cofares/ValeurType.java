/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pascalfares
 */
@Entity
@Table(name = "ValeurType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValeurType.findAll", query = "SELECT v FROM ValeurType v")})
public class ValeurType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idValeurType")
    private Integer idValeurType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valeur")
    private int valeur;
    
    @JoinColumn(name = "type", referencedColumnName = "idType")
    @ManyToOne(optional = false)
    private Type type;

    public ValeurType() {
    }

    public ValeurType(Integer idValeurType) {
        this.idValeurType = idValeurType;
    }

    public ValeurType(Integer idValeurType, int valeur) {
        this.idValeurType = idValeurType;
        this.valeur = valeur;
    }

    public Integer getIdValeurType() {
        return idValeurType;
    }

    public void setIdValeurType(Integer idValeurType) {
        this.idValeurType = idValeurType;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
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
        hash += (idValeurType != null ? idValeurType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValeurType)) {
            return false;
        }
        ValeurType other = (ValeurType) object;
        if ((this.idValeurType == null && other.idValeurType != null) || (this.idValeurType != null && !this.idValeurType.equals(other.idValeurType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.cofares.ValeurType[ idValeurType=" + idValeurType + " ]";
    }
    
}
