/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pfares
 */
@Entity
@Table(name = "payementlog", catalog = "omtPayements", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payementlog.findAll", query = "SELECT p FROM Payementlog p"),
    @NamedQuery(name = "Payementlog.findByFicheId", query = "SELECT p FROM Payementlog p WHERE p.ficheId = :ficheId"),
    @NamedQuery(name = "Payementlog.findByToken", query = "SELECT p FROM Payementlog p WHERE p.token = :token"),
    @NamedQuery(name = "Payementlog.findByAuditeursId", query = "SELECT p FROM Payementlog p WHERE p.auditeursId = :auditeursId"),
    @NamedQuery(name = "Payementlog.findByNomComplet", query = "SELECT p FROM Payementlog p WHERE p.nomComplet = :nomComplet"),
    @NamedQuery(name = "Payementlog.findByNomCompletArabe", query = "SELECT p FROM Payementlog p WHERE p.nomCompletArabe = :nomCompletArabe"),
    @NamedQuery(name = "Payementlog.findByMontant", query = "SELECT p FROM Payementlog p WHERE p.montant = :montant"),
    @NamedQuery(name = "Payementlog.findByDebutTraitement", query = "SELECT p FROM Payementlog p WHERE p.debutTraitement = :debutTraitement"),
    @NamedQuery(name = "Payementlog.findByFindTraitement", query = "SELECT p FROM Payementlog p WHERE p.findTraitement = :findTraitement"),
    @NamedQuery(name = "Payementlog.findByPrisEnCompte", query = "SELECT p FROM Payementlog p WHERE p.prisEnCompte = :prisEnCompte")})
public class Payementlog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ficheId", nullable = false)
    private Integer ficheId;
    @Size(max = 45)
    @Column(name = "token", length = 45)
    private String token;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "auditeursId", nullable = false, length = 10)
    private String auditeursId;
    @Size(max = 45)
    @Column(name = "NomComplet", length = 45)
    private String nomComplet;
    @Size(max = 45)
    @Column(name = "NomCompletArabe", length = 45)
    private String nomCompletArabe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montant", nullable = false)
    private int montant;
    @Column(name = "debutTraitement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date debutTraitement;
    @Column(name = "findTraitement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date findTraitement;
    @Column(name = "prisEnCompte")
    private Short prisEnCompte;

    public Payementlog() {
    }

    public Payementlog(Integer ficheId) {
        this.ficheId = ficheId;
    }

    public Payementlog(Integer ficheId, String auditeursId, int montant) {
        this.ficheId = ficheId;
        this.auditeursId = auditeursId;
        this.montant = montant;
    }

    public Integer getFicheId() {
        return ficheId;
    }

    public void setFicheId(Integer ficheId) {
        this.ficheId = ficheId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuditeursId() {
        return auditeursId;
    }

    public void setAuditeursId(String auditeursId) {
        this.auditeursId = auditeursId;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getNomCompletArabe() {
        return nomCompletArabe;
    }

    public void setNomCompletArabe(String nomCompletArabe) {
        this.nomCompletArabe = nomCompletArabe;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Date getDebutTraitement() {
        return debutTraitement;
    }

    public void setDebutTraitement(Date debutTraitement) {
        this.debutTraitement = debutTraitement;
    }

    public Date getFindTraitement() {
        return findTraitement;
    }

    public void setFindTraitement(Date findTraitement) {
        this.findTraitement = findTraitement;
    }

    public Short getPrisEnCompte() {
        return prisEnCompte;
    }

    public void setPrisEnCompte(Short prisEnCompte) {
        this.prisEnCompte = prisEnCompte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ficheId != null ? ficheId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payementlog)) {
            return false;
        }
        Payementlog other = (Payementlog) object;
        if ((this.ficheId == null && other.ficheId != null) || (this.ficheId != null && !this.ficheId.equals(other.ficheId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.cofares.Payementlog[ ficheId=" + ficheId + " ]";
    }
    
}
